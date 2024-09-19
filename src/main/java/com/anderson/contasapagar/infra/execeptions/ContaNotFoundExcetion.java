package com.anderson.contasapagar.infra.execeptions;

import com.anderson.contasapagar.domain.exceptions.CustomException;

public class ContaNotFoundExcetion extends CustomException {
    public ContaNotFoundExcetion(int statusCode, String message) {
        super(statusCode, message);
    }
}
