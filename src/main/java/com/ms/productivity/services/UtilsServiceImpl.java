package com.ms.productivity.services;

import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImpl {


    public String createUrlGetAllListsOnABoard(String urlBase){
        return "https://api.notion.com/v1/databases/e73f9628d0b047fb8a6225cfaf31ba17/query";
    }

}
