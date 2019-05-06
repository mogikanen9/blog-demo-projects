package com.github.mogikanen9.demo.rest.client;

import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/fruits")
@RegisterRestClient
public interface FruitService {

    @GET
    @Produces("application/json")
    public Set<Fruit> list();

    @POST
    @Produces("application/json")
    public Set<Fruit> add(Fruit fruit);

    @DELETE
    @Produces("application/json")
    public Set<Fruit> delete(Fruit fruit);
}