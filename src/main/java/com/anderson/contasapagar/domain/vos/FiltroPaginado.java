package com.anderson.contasapagar.domain.vos;

import java.time.LocalDate;

public record FiltroPaginado (LocalDate dataVencimento, String descricao, int page, int size){
}
