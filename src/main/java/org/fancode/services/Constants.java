package org.fancode.services;

public class Constants {
    public static final String USERS_API = "https://jsonplaceholder.typicode.com/users";
    public static final String TODOS_API = "https://jsonplaceholder.typicode.com/todos";
    public enum fanCodeGeoRange {
        LAT1(-40.0),
        LAT2(5.0),
        LONG1(5.0),
        LONG2(100.0);

        private final Double value;

        fanCodeGeoRange(Double value) {
            this.value = value;
        }

        public Double getValue() {
            return value;
        }
    }
}
