package org.fancode.services;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.fancode.services.Constants.TODOS_API;

public class ToDo {

    public static List<Map> getTodos() {
        Response response = RestAssured.get(TODOS_API);
        return response.jsonPath().getList("", Map.class);
    }

    public static List<Map> filterTodosByUserId(List<Map> todos, Integer userId) {
        return todos.stream()
                .filter(todo -> userId.equals(Integer.parseInt(todo.get("userId").toString())))
                .collect(Collectors.toList());
    }

}
