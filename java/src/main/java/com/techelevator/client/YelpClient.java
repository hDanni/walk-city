package com.techelevator.client;

import com.techelevator.model.Attraction;
import com.techelevator.model.Businesses;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class YelpClient {

    public static final String API_BASE_URL = "https://api.yelp.com/v3/businesses/";
    private final RestTemplate restTemplate = new RestTemplate();

    private String authToken = "tEgxoP-dSsHw1Vx4lX-ly6S00jFLcRh2IZ1mIaxY19BN5qaLLGE54jKMPa6JjYB5DJQyHexa0G3YDomW88dEiq33UBQwEyfjoCAvCAkRlWDjgrXHM37Z14N-WT00ZHYx";

    public Attraction getLocationDetailsById(String locationId){
//        String locationId = "GJ3vgPvcJlOejZepOotQug";
        Attraction result = null;

        ResponseEntity<Attraction> response = restTemplate.exchange(API_BASE_URL + "/" + locationId, HttpMethod.GET, makeAuthEntity(), Attraction.class);
        result = response.getBody();

        return result;
    }

    public Businesses getAttractionsByCoor(double latitude, double longitude) {
        ResponseEntity<Businesses> response = restTemplate.exchange(API_BASE_URL + "/search?latitude=" + latitude + "&longitude=" + longitude + "&radius=40000&categories=bookstore&categories=parks&categories=museums&categories=shopping&categories=attraction&sort_by=best_match&limit=50", HttpMethod.GET, makeAuthEntity(), Businesses.class);
        Businesses result = response.getBody();
        return result;
    }

    public Businesses searchAttractionsByTerm(String term, double latitude, double longitude) {
        //List<Attraction> returnedAttractions = new ArrayList<>();
        String url = API_BASE_URL + "search?" + "&latitude=" + latitude + "&longitude=" + longitude + "&term=" + term + "&sort_by=best_match&limit=20";
        ResponseEntity<Businesses> response = restTemplate.exchange(url, HttpMethod.GET, makeAuthEntity(), Businesses.class);
        Businesses result = response.getBody();
        return result;
    }

//    public Businesses searchNYCAttractionsByTerm(String term) {
//        //List<Attraction> returnedAttractions = new ArrayList<>();
//        String url = API_BASE_URL + "search?location=new%20york%20city&term=" + term + "&sort_by=best_match&limit=20";
//        ResponseEntity<Businesses> response = restTemplate.exchange(url, HttpMethod.GET, makeAuthEntity(), Businesses.class);
//        Businesses result = response.getBody();
//        return result;
//    }


    private HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

}
