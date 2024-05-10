package com.dmaia.software.employeeapi.exeptions.model;

import com.dmaia.software.provider.model.ResponseErrorVO;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends ResponseErrorVO {

    public ValidationError() {
       super();

    }

    private List<FieldMessage> erros = new ArrayList<>();

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addError(String fieldName, String msg) {
        erros.add(FieldMessage.builder()
                .fieldName(fieldName)
                .message(msg)
                .build());
    }
}
