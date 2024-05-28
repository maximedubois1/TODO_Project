package com.sp.utils;

import com.sp.dtos.AuthDTO;
import com.sp.dtos.AuthDtoToOrch;
import com.sp.dtos.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class GatewayUtils {

    private static final Logger log = LoggerFactory.getLogger(GatewayUtils.class);

    public static AuthDtoToOrch convertAuthDtoForOrchestrator(AuthDTO authDTO) {
        log.info("Converting auth dto for orchestrator");
        AuthDtoToOrch authDtoToOrch = new AuthDtoToOrch();
        Map<String, Variable> variables = new HashMap<>();

        Variable usernameVariable = new Variable();
        usernameVariable.setValue(authDTO.getSurname());
        usernameVariable.setType("String");
        variables.put("username", usernameVariable);

        Variable passwordVariable = new Variable();
        passwordVariable.setValue(authDTO.getPassword());
        passwordVariable.setType("String");
        variables.put("password", passwordVariable);

        authDtoToOrch.setVariables(variables);

        return authDtoToOrch;
    }

    public static AuthDTO convertFromOrchestratorToAuthDto(AuthDtoToOrch authDtoToOrch) {
        log.info("Converting from orchestrator to auth dto");
        AuthDTO authDTO = new AuthDTO();
        authDTO.setSurname(authDtoToOrch.getVariables().get("username").getValue());
        authDTO.setPassword(authDtoToOrch.getVariables().get("password").getValue());
        return authDTO;
    }
}
