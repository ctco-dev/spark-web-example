package com.home;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.home.controllers.Controller;
import com.home.infrastructure.ApplicationModule;
import com.home.util.Stats;
import lombok.extern.slf4j.Slf4j;
import spark.Route;

import javax.inject.Inject;

import static spark.Spark.*;

@Slf4j
public class Application {

    @Inject
    private Controller controller;
    @Inject
    private ObjectMapper objectMapper;

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ApplicationModule());
        Application application = injector.getInstance(Application.class);
        application.start();
    }

    public void start() {
        before((request, response) -> response.type("application/json"));

        getAsJson("/aggregate", controller::getAggregate);
        getAsJson("/data/:id", controller::getData);
        get("/stats", (req, res) -> {
            Stats.printMemoryStats();
            return 1;
        });

        put("/data/:id", controller::updateData, objectMapper::writeValueAsString);
        delete("/data/:id", controller::deleteData, objectMapper::writeValueAsString);
        post("/data", controller::createData, objectMapper::writeValueAsString);

        Stats.printMemoryStats();
    }

    private void getAsJson(String path, Route route) {
        get(path, route, objectMapper::writeValueAsString);
    }


}