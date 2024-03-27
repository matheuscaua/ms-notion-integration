package com.ms.productivity.services.impl;

import com.ms.productivity.dtos.configuration.SystemConfiguration;
import com.ms.productivity.enums.JobsEnum;
import com.ms.productivity.repositories.SystemConfigurationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SystemConfigurationServiceImpl {

    private static final String NAME_PREFIX_ATTR_SCHEDULE_BLOCK = "schedule.block.";

    private final SystemConfigurationRepository repository;

    public Boolean findValueBlockTaskJobsEnum(JobsEnum jobsEnum){
        var attributeName = NAME_PREFIX_ATTR_SCHEDULE_BLOCK + jobsEnum.getCode().toLowerCase();
        var value = findValueSystemConfigurationByAttributeName(attributeName);
        return (value != null && !value.isBlank()) ? ("true".equalsIgnoreCase(value)) : null;
    }

    private SystemConfiguration findSystemConfigurationByName(String attributeName){
       return repository.findByAttributeName(attributeName);
    }
    private String findValueSystemConfigurationByAttributeName(String attributeName) {
        var systemConfiguration = findSystemConfigurationByName(attributeName);
        return systemConfiguration.getAttributeValue();
    }

    public SystemConfiguration createBlockTaskConfiguration(JobsEnum jobsEnum){
        var attributeName = NAME_PREFIX_ATTR_SCHEDULE_BLOCK + jobsEnum.getCode().toLowerCase();
        var configuration = new SystemConfiguration();
        configuration.setAttributeName(attributeName);
        configuration.setAttributeDescription("BLOCK THE JOB (true or false) - " +jobsEnum.getJobName());
        configuration.setAttributeValue(Boolean.TRUE.toString().toLowerCase());
        return repository.save(configuration);
    }

}
