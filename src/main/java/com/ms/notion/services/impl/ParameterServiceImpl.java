package com.ms.notion.services.impl;

import com.ms.notion.models.Parameter;
import com.ms.notion.repositories.ParameterRepository;
import com.ms.notion.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParameterServiceImpl implements ParameterService {

    @Autowired
    private ParameterRepository repostiroty;

    @Override
    public Parameter findParameterByDescription(String description){
        return repostiroty.findParameterByDescription(description).orElseThrow();
    }

    @Override
    public Map<String, String> extractNotionHeaders(Parameter parameter){
        Map<String, String> headers = new HashMap<>();
        List<String> splitHeaders = Arrays.stream(parameter.getValue().split(";")).toList();
        String authorization = splitHeaders.get(0);
        String notionVersion = splitHeaders.get(1);

        headers.put("Authorization", authorization);
        headers.put("Notion-Version", notionVersion);

        return headers;
    }

}
