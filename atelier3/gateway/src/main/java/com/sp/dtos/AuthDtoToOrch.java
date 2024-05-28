package com.sp.dtos;

import java.util.Map;

public class AuthDtoToOrch {
    private Map<String, Variable> variables;

    public Map<String, Variable> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Variable> variables) {
        this.variables = variables;
    }
}

