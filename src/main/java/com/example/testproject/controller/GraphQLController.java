package com.example.testproject.controller;

import com.google.gson.Gson;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/graphQL")
public class GraphQLController {

    private final GraphQL graphQL;
    private static final String PREFIX_QUERY = "query";

    public GraphQLController(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @PostMapping
    public ExecutionResult graphQLResult(@RequestBody String query) {

        if (query.contains(PREFIX_QUERY)) {
            Map<String, String> jsonMap = new Gson().fromJson(query, Map.class);
            query = jsonMap.get("query");
        }

        return graphQL.execute(query);
    }
}
