package com.project.product.rest;

import com.project.product.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/image")
public class ImageEndpoint {

    @Autowired
    ImageRepository imageRepository;

    //CREATE
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Image saveProduct(Image image){
        return imageRepository.save(image);
    }

    //READ
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Image getSpecificImage(@PathParam("id") Long id){
        return imageRepository.findOne(id);
    }

    //UPDATE
    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Image updateImage(@PathParam("id") Long id, Image image){
        Image im = new Image(id, image.getType(), image.getProduct_id());
        return imageRepository.save(im);
    }

    //DELETE
    @Path("{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteImage(@PathParam("id") Long id){
        if(imageRepository.exists(id)){
            imageRepository.delete(id);
        }
    }

}
