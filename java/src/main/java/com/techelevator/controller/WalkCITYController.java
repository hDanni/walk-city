package com.techelevator.controller;

import com.techelevator.client.MapboxClient;
import com.techelevator.client.YelpClient;
import com.techelevator.dao.AwardDao;
import com.techelevator.dao.DbAttractionDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import com.techelevator.model.mapbox.Step;
import com.techelevator.service.WalkCITYService;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.CharacterCodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class WalkCITYController {

    //todo calls to our SQL database
    private DbAttractionDao attractionDao;
    private WalkCITYService walkCITYService;
    private UserDao userDao;
    private YelpClient yelpClient;
    private MapboxClient mapboxClient;
    private AwardDao awardDao;

    public WalkCITYController(DbAttractionDao attractionDao, WalkCITYService walkCITYService, UserDao userDao, YelpClient yelpClient, MapboxClient mapboxClient, AwardDao awardDao) {
        this.attractionDao = attractionDao;
        this.walkCITYService = walkCITYService;
        this.userDao = userDao;
        this.yelpClient = yelpClient;
        this.mapboxClient = mapboxClient;
        this.awardDao = awardDao;
    }

    @GetMapping(path = "/attraction")
    public List<DbAttraction> getAllAttraction(){
        return walkCITYService.getAllAttractions();
    }

    @GetMapping(path = "/attraction/detail/{businessId}")
    public Attraction getAttractionByBusiId(@PathVariable String businessId){
        return yelpClient.getLocationDetailsById(businessId);
    }

    @GetMapping(path = "/view")
    public Businesses getNearbyLocations(@RequestParam String latitude, @RequestParam String longitude) {
//        System.out.println(latitude);
//        System.out.println(longitude);
        return yelpClient.getAttractionsByCoor(Double.parseDouble(latitude), Double.parseDouble(longitude));
    }

    @GetMapping(path = "/attraction/{category}")
    public List<DbAttraction> getAttractionByCategory(@PathVariable String category){
        return walkCITYService.getAllAttractionsByCategory(category);
    }

//    @RequestMapping(value = "/attraction/create", method = RequestMethod.POST)
//    public void createAttraction(@RequestBody DbAttraction dbAttraction) {
//        walkCITYService.addedAttractionToDatabase(dbAttraction);
//    }

    @RequestMapping(value = "/checkin/create", method = RequestMethod.POST)
    public void createCheckIn(@RequestBody DbAttraction dbAttraction, Principal principal) {
        walkCITYService.addCheckIn(dbAttraction, principal.getName());
    }

    @GetMapping(path = "/checkin")
    public List<CheckIn> getAllCheckIns(){
        return walkCITYService.getAllCheckIns();
    }


    @GetMapping(path = "/checkin/history")
    public List<DbAttraction> getCheckInById(Principal principal){
        return walkCITYService.getCheckInById(principal.getName());
    }


    @GetMapping(path = "/attraction/detail/{businessId}/directions/driving")
    public ArrayList<Step> getDrivingDirections(@PathVariable String businessId,@RequestParam String userLong,@RequestParam String userLat){
        double endLong = Double.parseDouble(yelpClient.getLocationDetailsById(businessId).getCoordinates().getLongitude());
        double endLat = Double.parseDouble(yelpClient.getLocationDetailsById(businessId).getCoordinates().getLatitude());

        return mapboxClient.getDrivingDirectionsByUserStartAndEnd(Double.parseDouble(userLong),Double.parseDouble(userLat), endLong, endLat).getRoutes().get(0).getLegs().get(0).getSteps();
    }

    @GetMapping(path = "/attraction/detail/{businessId}/directions/walking")
    public ArrayList<Step> getWalkingDirections(@PathVariable String businessId,@RequestParam String userLong,@RequestParam String userLat){
        double endLong = Double.parseDouble(yelpClient.getLocationDetailsById(businessId).getCoordinates().getLongitude());
        double endLat = Double.parseDouble(yelpClient.getLocationDetailsById(businessId).getCoordinates().getLatitude());

        return mapboxClient.getWalkingDirectionsByUserStartAndEnd(Double.parseDouble(userLong),Double.parseDouble(userLat), endLong, endLat).getRoutes().get(0).getLegs().get(0).getSteps();
    }

    @GetMapping("/attraction/detail/{businessId}/geometry/driving")
    public String getDrivingGeometry(@PathVariable String businessId, @RequestParam String userLong,@RequestParam String userLat){
        double endLong = Double.parseDouble(yelpClient.getLocationDetailsById(businessId).getCoordinates().getLongitude());
        double endLat = Double.parseDouble(yelpClient.getLocationDetailsById(businessId).getCoordinates().getLatitude());

        return mapboxClient.getGeometryByDriving(Double.parseDouble(userLong),Double.parseDouble(userLat), endLong, endLat).getRoutes().get(0).getGeometry();
    }

    @GetMapping("/attraction/detail/{businessId}/geometry/walking")
    public String getWalkingGeometry(@PathVariable String businessId, @RequestParam String userLong,@RequestParam String userLat){
        double endLong = Double.parseDouble(yelpClient.getLocationDetailsById(businessId).getCoordinates().getLongitude());
        double endLat = Double.parseDouble(yelpClient.getLocationDetailsById(businessId).getCoordinates().getLatitude());

        return mapboxClient.getGeometryByWalking(Double.parseDouble(userLong),Double.parseDouble(userLat), endLong, endLat).getRoutes().get(0).getGeometry();
    }

    @GetMapping(path = "/awards")
    public List<Award> getAllAwards(){
        return walkCITYService.getAllAwards();
    }

    @GetMapping(path = "/user/awards")
    public List<Award> getAwardById(Principal principal){
        return walkCITYService.getAwardById(principal.getName());
    }

    @GetMapping(path = "/search/{keyword}")
    public List<DbAttraction> getAttractionByKeyword(@PathVariable String keyword) {
        return walkCITYService.getAttractionByKeyword(keyword);
    }

    @GetMapping("/attractions/search")
    public Businesses searchAttractionsByTerm(@RequestParam String term, @RequestParam String latitude, @RequestParam String longitude) {
        return yelpClient.searchAttractionsByTerm(term, Double.parseDouble(latitude), Double.parseDouble(longitude));
    }

//    @GetMapping("/attractions/search")
//    public Businesses searchNYCAttractionsByTerm(@RequestParam String term) {
//        return yelpClient.searchNYCAttractionsByTerm(term);
//    }


}
