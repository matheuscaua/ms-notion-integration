package com.ms.productivity.services;

import com.ms.productivity.models.Parameter;

public interface ParameterService {
    Parameter findParameterByDescription(String description);
}
