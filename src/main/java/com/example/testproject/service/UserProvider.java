package com.example.testproject.service;

import com.example.testproject.exception.GlobalDataFetcherHandler;
import com.example.testproject.service.datafetcher.UserDataFetchers;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class UserProvider {

    private final UserDataFetchers userDataFetchers;
    private GraphQL graphQL;

    @Value("classpath:User.graphql")
    Resource resource;

    @Bean
    public GraphQL graphQL() {
        return this.graphQL;
    }

    @PostConstruct
    private void init() throws IOException {
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = this.buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema)
                .queryExecutionStrategy(new AsyncExecutionStrategy(new GlobalDataFetcherHandler()))
                .mutationExecutionStrategy(new AsyncExecutionStrategy(new GlobalDataFetcherHandler()))
                .build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("user", userDataFetchers.getUser())
                        .dataFetcher("userbyusername", userDataFetchers.getUserByUsername())
                        .dataFetcher("users", userDataFetchers.getAllUsers())
                )
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("addUser", userDataFetchers.addUser())
                )
                .build();
    }

}
