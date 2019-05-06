package com.github.mogikanen9.demo.rest.client;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/v1/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProxyFruitsResource {

    @Inject
    @RestClient
    FruitService fruitService;

    @Path("/hello")
    @GET
    public String hello() {
        return "hello";
    }

    @GET
    public Set<Fruit> list() {
        return fruitService.list();
    }

    @POST
    public Set<Fruit> add(Fruit fruit) {
        return fruitService.add(fruit);
    }

    @DELETE
    public Set<Fruit> delete(Fruit fruit) {
        return fruitService.delete(fruit);
    }
}