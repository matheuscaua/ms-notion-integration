package com.ms.productivity.services;

import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImpl {


    public String createUrlGetNotionDatabase(String urlBase){
        return urlBase.concat("/v1/databases/e73f9628d0b047fb8a6225cfaf31ba17/query");
    }

}
