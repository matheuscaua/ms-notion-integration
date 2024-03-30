package com.ms.notion.services.impl;

import com.ms.notion.dtos.configuration.SystemConfiguration;
import com.ms.notion.enums.JobsEnum;
import com.ms.notion.repositories.SystemConfigurationRepository;
import lombok.AllArgsConstructor;
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

    public void createBlockTaskConfiguration(JobsEnum jobsEnum){
        var attributeName = NAME_PREFIX_ATTR_SCHEDULE_BLOCK + jobsEnum.getCode().toLowerCase();
        var configuration = new SystemConfiguration();
        configuration.setAttributeName(attributeName);
        configuration.setAttributeDescription("BLOCK THE JOB (true or false) - " +jobsEnum.getJobName());
        configuration.setAttributeValue(Boolean.TRUE.toString().toLowerCase());
        repository.save(configuration);
    }

}
