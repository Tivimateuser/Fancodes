package org.fancode.services;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.fancode.services.Constants.USERS_API;

public class User {

    public static List<Map> getUsers() {
        Response response = RestAssured.get(USERS_API);
        return response.jsonPath().getList("", Map.class);
    }

    public static List<Map> filterUsersByGeoRange(List<Map> users, Double lat1, Double lat2, Double long1, Double long2) {
        return users.stream()
                .filter(user -> isWithinGeoRange(user, lat1, lat2, long1, long2))
                .collect(Collectors.toList());
    }

    private static boolean isWithinGeoRange(Map user, Double lat1, Double lat2, Double long1, Double long2) {
        Map address = (Map) user.get("address");
        Map geo = (Map) address.get("geo");
        Double lat = Double.parseDouble(geo.get("lat").toString());
        Double lng = Double.parseDouble(geo.get("lng").toString());
        return (lat >= lat1 && lat <= lat2) && (lng >= long1 && lng <= long2);
    }

}