package com.ms.productivity.services;

import com.ms.productivity.models.Parameter;

import java.util.Map;

public interface ParameterService {
    Parameter findParameterByDescription(String description);
    Map<String, String> extractNotionHeaders(Parameter parameter);

}
