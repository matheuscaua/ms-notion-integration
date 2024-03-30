package com.ms.notion.services;

import com.ms.notion.models.Parameter;

import java.util.Map;

public interface ParameterService {
    Parameter findParameterByDescription(String description);
    Map<String, String> extractNotionHeaders(Parameter parameter);

}
