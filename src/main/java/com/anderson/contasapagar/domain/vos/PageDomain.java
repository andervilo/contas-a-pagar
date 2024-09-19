package com.anderson.contasapagar.domain.vos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PageDomain<T> {
    private final int page;
    private final int totalPages;
    private final int size;
    private final long totalElements;
    private final List<T> content;
}
