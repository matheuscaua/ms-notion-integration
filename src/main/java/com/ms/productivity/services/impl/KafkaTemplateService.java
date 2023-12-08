package com.ms.productivity.services.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaTemplateService {

    private KafkaTemplate<String, String> template;

    

}
