package com.techelevator;

import com.techelevator.client.MapboxClient;
import com.techelevator.client.YelpClient;
import com.techelevator.model.Attraction;
import com.techelevator.model.Open;
import com.techelevator.model.mapbox.Legs;
import com.techelevator.model.mapbox.Maneuver;
import com.techelevator.model.mapbox.Routes;
import com.techelevator.model.mapbox.Step;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application {

    private static YelpClient yelpClient;
    private static MapboxClient mapboxClient;

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(Application.class, args);

        /*YELP TEST*/
        yelpClient = new YelpClient();
//
//        Attraction testAtt = yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug");
//        System.out.println(testAtt);
//
//        ArrayList<String> imageUrls = yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug").getPhotos();
//
//        for(String img : imageUrls) {
//            System.out.println(img);
//        }

        Open[] daysOpen = yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug").getHours()[0].getOpen();

        /*for(int i = 0; i < daysOpen.length; i++) {

            int dayOfWeek = daysOpen[i].getDay();
            String dayName;
            switch (dayOfWeek) {
                case 0:
                    dayName = "Sunday";
                    break;
                case 1:
                    dayName = "Monday";
                    break;
                case 2:
                    dayName = "Tuesday";
                    break;
                case 3:
                    dayName = "Wednesday";
                    break;
                case 4:
                    dayName = "Thursday";
                    break;
                case 5:
                    dayName = "Friday";
                    break;
                case 6:
                    dayName = "Saturday";
                    break;
                default:
                    dayName = "Invalid day";
                    break;
            }

            int startTimeInt = daysOpen[i].getStart();
            int endTimeInt = daysOpen[i].getEnd();

            String startTimeStr = String.format("%04d", startTimeInt);
            String endTimeStr = String.format("%04d", endTimeInt);

            SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
            Date startTime = sdf.parse(startTimeStr);
            Date endTime = sdf.parse(endTimeStr);

            sdf = new SimpleDateFormat("h:mm a");
            String formattedStartTime = sdf.format(startTime);
            String formattedEndTime = sdf.format(endTime);

            System.out.println(dayName + ": " + formattedStartTime + " - " + formattedEndTime);
        }*/

        /*System.out.println(yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug").getName());
        System.out.println(yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug").getCoordinates().getLatitude());
        System.out.println(yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug").getCoordinates().getLongitude());*/
//        System.out.println(Arrays.toString(yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug").getLocations().getDisplayAddress()));

/*        System.out.println(yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug").getPhone());

        System.out.println(yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug").getHours()[0].getOpen()[0].getStart());
        System.out.println(yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug").getHours()[0].getOpen()[0].getEnd());
        System.out.println(yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug").getHours()[0].getOpen()[0].getDay());*/

//        System.out.println(yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug").getLocation().display_address);
//        System.out.println(yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug"));
//        System.out.println(yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug").getLocation().display_address);
//        System.out.println(yelpClient.getLocationDetailsById("GJ3vgPvcJlOejZepOotQug").getName());

//        ArrayList<Attraction> test =  yelpClient.getAttractionsByCoor(40.761441, -73.977625).getBusinesses();
//
//        for (Attraction a : test) {
//            System.out.println(a.getName());
//        }

        /* MAPBOX TEST  Step by Step Directions*/
        mapboxClient = new MapboxClient();

        /* TEST GEOMETRY */

        /*String geoTest = mapboxClient.getGeometryByDriving(-73.98724024445688,40.76011384643282, -73.7830746,40.7561491).getRoutes().get(0).getGeometry();
        String walkGeo = mapboxClient.getGeometryByWalking(-73.98724024445688,40.76011384643282, -73.7830746,40.7561491).getRoutes().get(0).getGeometry();
        System.out.println(geoTest);
        System.out.println(walkGeo);*/

        //System.out.println(mapboxClient.testDirection().getRoutes().get(0).getLegs().get(0).getSteps().get(0).getManeuver().getInstruction());

        /*ArrayList<Step> test = mapboxClient.getDirectionsByUserStartAndEnd(-73.99148,40.731767,-73.990076,40.737268).getRoutes().get(0).getLegs().get(0).getSteps();

        for(int i = 1; i < test.size(); i++){
            System.out.println("Step " + i + ": " + test.get(i).getManeuver().getInstruction());
        }*/

    /* GETTING DIRECTIONS WALKING*/
        ArrayList<Step> test = mapboxClient.getDrivingDirectionsByUserStartAndEnd(-73.99148,40.731767,-73.990076,40.737268).getRoutes().get(0).getLegs().get(0).getSteps();
        for(int i = 1; i < test.size(); i++) {
            System.out.println("Step " + i + ": " + test.get(i).getManeuver().getInstruction());
        }
    }



}
