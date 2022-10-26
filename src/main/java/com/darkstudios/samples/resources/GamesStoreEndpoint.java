package com.darkstudios.samples.resources;

import com.darkstudios.samples.entities.Games;
import com.darkstudios.samples.services.GameService;

import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@RequestScoped
@Path("games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class GamesStoreEndpoint {
    @Inject
    GameService gameService;

    @GET
    public Response getAll() {
        return Response.ok(gameService.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getBook(@PathParam("id") Long id) {
        Games games = gameService.findById(id);
        return Response.ok(games).build();
    }

    @POST
    public Response create(Games games) {
        gameService.create(games);
        return Response.ok().build();
    }
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Games games) {
        Games updateGames = gameService.findById(id);
        updateGames.setDescription(games.getDescription());
        updateGames.setPrice(games.getPrice());
        updateGames.setPublisher(games.getPublisher());
        updateGames.setTitle(games.getTitle());
        gameService.update(updateGames);
        return Response.ok().build();
    }
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Games getGames = gameService.findById(id);
        gameService.delete(getGames);
        return Response.ok().build();
    }
}
