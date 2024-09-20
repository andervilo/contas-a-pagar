package com.anderson.contasapagar.api.resources;


import com.anderson.contasapagar.api.dto.ContaRequest;
import com.anderson.contasapagar.api.mapper.ContaApiMapper;
import com.anderson.contasapagar.domain.models.types.SituacaoPagamentoType;
import com.anderson.contasapagar.domain.services.CadastrarConta;
import com.anderson.contasapagar.domain.services.ImportarConta;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contas")
@RequiredArgsConstructor
@Tag(name = "CSV Upload", description = "API para  importação de Contas via CSV")
public class CsvController {

    private final ImportarConta cadastrarConta;
    private final ContaApiMapper contaApiMapper;

    @PostMapping("/upload-csv")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Importar CSV de Contas")
    public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo está vazio");
        }

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVReader csvReader = new CSVReader(fileReader)) {

            String[] headers = csvReader.readNext();
            List<ContaRequest> contas = new ArrayList<>();
            carregaContasNaLista(csvReader, contas);

            contas.parallelStream().forEach(conta -> {
                var contaDomainToSave = contaApiMapper.toDomain(conta);
                cadastrarConta.execute(contaDomainToSave);
            });

            return ResponseEntity.status(HttpStatus.OK).body("Arquivo CSV processado e contas salvas com sucesso!");

        } catch (IOException | CsvException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo CSV");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro nos dados do arquivo CSV");
        }
    }

    private static void carregaContasNaLista(CSVReader csvReader, List<ContaRequest> contas) throws IOException, CsvValidationException {
        String[] record;
        while ((record = csvReader.readNext()) != null) {
            ContaRequest conta = ContaRequest.builder()
                    .dataVencimento(LocalDate.parse(record[0]))
                    .dataPagamento(parseLocalDateTime(record[1]))
                    .valor(new BigDecimal(record[2]))
                    .descricao(record[3])
                    .situacao(SituacaoPagamentoType.valueOf(record[4]))
                    .build();
            contas.add(conta);
        }
    }

    private static LocalDateTime parseLocalDateTime(String data) {
        if(data.isEmpty() ) return null;

        if(data.length() == 10) {
            return LocalDate.parse(data).atStartOfDay();
        }
        return LocalDateTime.parse(data);
    }

}
