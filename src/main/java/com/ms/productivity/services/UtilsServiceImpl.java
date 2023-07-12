package com.ms.productivity.services;

import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImpl {

    private final String api_key = "?key=063dfd266be6f4aeafdc501c330bf0fd";
    private final String token = "token=ATTA35f3943f894202fb46045411718132bf3ba379fb2f2bdab581b05e634dd322c38C69AAD9";
    public String createUrlGetAllListsOnABoard(String urlBase){
        return urlBase + "/boards/sJNZ5wHW/lists" + api_key +"&"+ token;
    }

    public String createUrlGetCardsOnAList(String urlBase, String idList){
        return urlBase + "/lists/"+idList+"/cards"+ api_key +"&"+ token;
    }

}
