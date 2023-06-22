package com.techelevator.client;

import com.techelevator.model.mapbox.Direction;
import com.techelevator.model.mapbox.Maneuver;
import com.techelevator.model.mapbox.Routes;
import com.techelevator.model.mapbox.Step;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MapboxClient {

    private static final String API_BASE_URL_DRIVING = "https://api.mapbox.com/directions/v5/mapbox/driving/";
    private static final String API_BASE_URL_WALKING = "https://api.mapbox.com/directions/v5/mapbox/walking/";
    private static final String STATIC_MAP_URL = "https://api.mapbox.com/styles/v1/mapbox/streets-v12/static/pin-s-a+9ed4bd";

    private static final String APPEND_GEOMETRY = "?alternatives=true&geometries=polyline&language=en&overview=simplified&steps=true&access_token=pk.eyJ1Ijoiamd0MyIsImEiOiJjbGdmNWJtcGIwdTVvM2xwanV4b2kyN2hrIn0.MvFZWiRnKRRdgM1uPDPagw";

    private static final String ACCESS_KEY = "access_token=pk.eyJ1Ijoiamd0MyIsImEiOiJjbGdmNWJtcGIwdTVvM2xwanV4b2kyN2hrIn0.MvFZWiRnKRRdgM1uPDPagw";

    private static final String APPEND_COORDS = "?alternatives=true&geometries=geojson&language=en&overview=simplified&steps=true&access_token=pk.eyJ1Ijoiamd0MyIsImEiOiJjbGdmNWJtcGIwdTVvM2xwanV4b2kyN2hrIn0.MvFZWiRnKRRdgM1uPDPagw";

    private RestTemplate restTemplate = new RestTemplate();

    public Direction getDrivingDirectionsByUserStartAndEnd(double startLong, double startLat, double endLong, double endLat){
        String bodyUrl = API_BASE_URL_DRIVING + startLong + "," + startLat + ";" + endLong + "," + endLat + APPEND_GEOMETRY;

        Direction result;

        ResponseEntity<Direction> response = restTemplate.exchange(bodyUrl, HttpMethod.GET, makeAuthEntity(), Direction.class);
        result = response.getBody();

        return result;
    }

    public Direction getWalkingDirectionsByUserStartAndEnd(double startLong, double startLat, double endLong, double endLat){
        String bodyUrl = API_BASE_URL_WALKING + startLong + "," + startLat + ";" + endLong + "," + endLat + APPEND_GEOMETRY;

        Direction result;

        ResponseEntity<Direction> response = restTemplate.exchange(bodyUrl, HttpMethod.GET, makeAuthEntity(), Direction.class);
        result = response.getBody();

        return result;
    }

    public Direction getGeometryByDriving(double startLong, double startLat, double endLong, double endLat){
        String bodyUrl = API_BASE_URL_DRIVING + startLong + "," + startLat + ";" + endLong + "," + endLat + APPEND_GEOMETRY;

        Direction result;

        ResponseEntity<Direction> response = restTemplate.exchange(bodyUrl, HttpMethod.GET, makeAuthEntity(), Direction.class);
        result = response.getBody();

        return result;
    }
    public Direction getGeometryByWalking(double startLong, double startLat, double endLong, double endLat){
        String bodyUrl = API_BASE_URL_WALKING + startLong + "," + startLat + ";" + endLong + "," + endLat + APPEND_GEOMETRY;

        Direction result;

        ResponseEntity<Direction> response = restTemplate.exchange(bodyUrl, HttpMethod.GET, makeAuthEntity(), Direction.class);
        result = response.getBody();

        return result;
    }


    public Direction getStaticMapWithPath(double startLong, double startLat, double endLong, double endLat, String geometry){
        String bodyUrl = STATIC_MAP_URL + "(" + startLong + "," + startLat+ ")" + ",pin-s-b+000(" + endLong + "," + endLat + "),path-5+f44-0.5(" + geometry + "" + ")/auto/500x300?" + ACCESS_KEY;

        Direction result;

        ResponseEntity<Direction> response = restTemplate.exchange(bodyUrl, HttpMethod.GET, makeAuthEntity(), Direction.class);
        result = response.getBody();

        return result;
    }


    private HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(headers);
    }
}
