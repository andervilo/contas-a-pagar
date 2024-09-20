package com.anderson.contasapagar.api.resources;

import com.anderson.contasapagar.api.dto.ContaCreateRequest;
import com.anderson.contasapagar.api.dto.ContaRequest;
import com.anderson.contasapagar.api.dto.ContaResponse;
import com.anderson.contasapagar.api.dto.ValorPagoResponse;
import com.anderson.contasapagar.api.mapper.ContaApiMapper;
import com.anderson.contasapagar.domain.exceptions.CustomException;
import com.anderson.contasapagar.domain.services.*;
import com.anderson.contasapagar.domain.vos.FiltroPaginado;
import com.anderson.contasapagar.domain.vos.PageDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/contas")
@RequiredArgsConstructor
@Tag(name = "Contas", description = "API de Contas a Pagar")
public class ContaRestController {

    private final ObterContaPorId obterContaPorId;
    private final CadastrarConta cadastrarConta;
    private final PagarConta pagarConta;
    private final CancelarConta cancelarConta;
    private final ReabrirConta reabrirConta;
    private final PagarContaPorData pagarContaPorData;
    private final ObterTotalPagoPorPeriodo obterTotalPagamentosPorPeriodo;
    private final ObterContasPorFiltro obterContasPorFiltro;
    private final AtualizarConta atualizarConta;

    private final ContaApiMapper contaApiMapper;
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obter uma conta por ID")
    public ResponseEntity<ContaResponse> obterContaPorId(@Parameter(description = "ID da conta", example = "1") @PathVariable Long id) {
        var contaDomain = obterContaPorId.execute(id);
        return ResponseEntity.ok(contaApiMapper.toResponse(contaDomain));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Cadastrar uma nova conta")
    public ResponseEntity<ContaResponse> cadastrarConta(@RequestBody ContaCreateRequest contaRequest) {
        var contaDomainToSave = contaApiMapper.toDomain(contaRequest);
        var contaDomain = cadastrarConta.execute(contaDomainToSave);
        return ResponseEntity.ok(contaApiMapper.toResponse(contaDomain));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar uma conta")
    public ResponseEntity<ContaResponse> atualizarConta(@RequestBody ContaRequest contaRequest) {
        var contaDomainToUpdate = contaApiMapper.toDomain(contaRequest);
        var contaDomain = atualizarConta.execute(contaDomainToUpdate);
        return ResponseEntity.ok(contaApiMapper.toResponse(contaDomain));
    }

    @PutMapping("/pagar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Pagar uma conta")
    public ResponseEntity<ContaResponse> pagarConta(@Parameter(description = "ID da conta", example = "1") @PathVariable Long id) {
        var contaDomain = pagarConta.execute(id);
        return ResponseEntity.ok(contaApiMapper.toResponse(contaDomain));
    }

    @PutMapping("/cancelar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Cancelar uma conta")
    public ResponseEntity<ContaResponse> cancelarConta(@Parameter(description = "ID da conta", example = "1") @PathVariable Long id) {
        var contaDomain = cancelarConta.execute(id);
        return ResponseEntity.ok(contaApiMapper.toResponse(contaDomain));
    }

    @PutMapping("/reabrir/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Reabrir uma conta")
    public ResponseEntity<ContaResponse> reabrirConta(@Parameter(description = "ID da conta", example = "1") @PathVariable Long id) {
        var contaDomain = reabrirConta.execute(id);
        return ResponseEntity.ok(contaApiMapper.toResponse(contaDomain));
    }

    @PutMapping("/pagar/{id}/{data}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Pagar uma cota informando a data de pagamento")
    public ResponseEntity<ContaResponse> pagarContaPorData(
            @Parameter(description = "ID da conta", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Data de pagamento", example = "2023-10-01T10:00:00")
            @PathVariable LocalDateTime data) {
        if(Objects.isNull(data)) {
            throw new CustomException(400, "A data de pagamento deve ser informada.");
        }
        var contaDomain = pagarContaPorData.execute(id, data);
        return ResponseEntity.ok(contaApiMapper.toResponse(contaDomain));
    }

    @GetMapping("/total-pago/{dataInicial}/{dataFinal}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obter total de pagamentos feitos por período")
    public ResponseEntity<ValorPagoResponse> obterTotalPagamentosPorPeriodo(
            @Parameter(description = "Data inicial", example = "2023-10-01")
            @PathVariable LocalDate dataInicial,
            @Parameter(description = "Data final", example = "2023-10-01")
            @PathVariable LocalDate dataFinal) {
        if(Objects.isNull(dataInicial) || Objects.isNull(dataFinal)) {
            throw new CustomException(400, "As datas devem ser informadas.");
        }

        var valorPago = ValorPagoResponse.builder()
                .dataInicial(dataInicial)
                .dataFinal(dataFinal)
                .valorPago(Optional.of(obterTotalPagamentosPorPeriodo.execute(dataInicial, dataFinal)).orElse(BigDecimal.ZERO))
                .build();
        return ResponseEntity.ok(valorPago);
    }

    @PostMapping("/listar")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obter lista de Contas com opção de filtro por data vencimento e descrição")
    public ResponseEntity<PageDomain<ContaResponse>> listarContas(@RequestBody FiltroPaginado filtroPaginado) {
        if(filtroPaginado.size() == 0) {
            filtroPaginado = FiltroPaginado.builder()
                    .dataVencimento(filtroPaginado.dataVencimento())
                    .descricao(filtroPaginado.descricao())
                    .page(filtroPaginado.page())
                    .size(10)
                    .build();
        }
        var contas = obterContasPorFiltro.execute(filtroPaginado);
        var contasResponse = contaApiMapper.toResponseList(contas.getContent());
        var pageDomain = PageDomain.<ContaResponse>builder()
                .content(contasResponse)
                .totalElements(contas.getTotalElements())
                .totalPages(contas.getTotalPages())
                .size(contas.getSize())
                .page(contas.getPage())
                .build();
        return ResponseEntity.ok(pageDomain);
    }

}
