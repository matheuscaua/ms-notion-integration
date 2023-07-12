package com.ms.productivity.clients;

import com.ms.productivity.dtos.CardTrelloDTO;
import com.ms.productivity.dtos.ListTrelloDTO;
import com.ms.productivity.services.UtilsServiceImpl;
import jdk.dynalink.linker.LinkerServices;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloClient {

    @Value("${trello.api.base.url}")
    private String BASE_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UtilsServiceImpl utilsService;

    public List<CardTrelloDTO> getAllListsOnABoard(){
        String urlList = utilsService.createUrlGetAllListsOnABoard(BASE_URL);
        ResponseEntity<List<ListTrelloDTO>> searchResult = null;
        List<ListTrelloDTO> listTrelloDTOList = null;
        if(!urlList.isEmpty()){
            ParameterizedTypeReference<List<ListTrelloDTO>> responseType = new ParameterizedTypeReference<List<ListTrelloDTO>>() {};
            searchResult = restTemplate.exchange(urlList, HttpMethod.GET, null, responseType);
            listTrelloDTOList = searchResult.getBody();
        }
        List<CardTrelloDTO> listCards = getCardsOnAList(listTrelloDTOList);
        return listCards;
    }

    public List<CardTrelloDTO> getCardsOnAList(List<ListTrelloDTO> listTrelloDTOList){
        List<CardTrelloDTO> cardList = null;
        if(!listTrelloDTOList.isEmpty()) {
            ParameterizedTypeReference<List<CardTrelloDTO>> responseType = new ParameterizedTypeReference<List<CardTrelloDTO>>() {};
            cardList = listTrelloDTOList.stream().map(l -> {
                String urlCard = utilsService.createUrlGetCardsOnAList(BASE_URL, l.getId());
                ResponseEntity<List<CardTrelloDTO>> cardResponse = null;
                List<CardTrelloDTO> listCard = null;
                cardResponse = restTemplate.exchange(urlCard, HttpMethod.GET, null, responseType);
                listCard = cardResponse.getBody();
                return listCard.get(0);
            }).collect(Collectors.toList());
            return cardList;
        }else return cardList;
    }
}
