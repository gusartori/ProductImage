package com.project.product.rest;

import com.project.product.entities.Product;
import com.project.product.entities.Image;
import com.project.product.entities.ProductProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Component
@Path("/product")
public class ProductEndpoint {

    @Autowired
    ProductRepository productRepository;

    //CREATE
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    //READ and
    //d) Same as 2 using specific product identity
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product getSpecificProduct(@PathParam("id") Long id){
        return productRepository.findOne(id);
    }


    //UPDATE
    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Product updateProduct(@PathParam("id") Long id, Product p){
        Product product = new Product(id, p.getName(), p.getDescription(), p.getParentProduct());
        return productRepository.save(product);
    }

    //DELETE
    @Path("{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteProduct(@PathParam("id") Long id){
        if(productRepository.exists(id)){
            productRepository.delete(id);
        }
    }

//    a) Get all products excluding relationships (child products, images)
    @Path("all/norelation")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<ProductProjection> getAllProductsNoRelationship(){
        ArrayList<ProductProjection> alProduct = new ArrayList<>();
        productRepository.findAll().forEach(x->{
            alProduct.add(new ProductProjection(x.getId(),x.getName(),x.getDescription()));
        });
        return alProduct;
    }

//    b) Get all products including specified relationships (child product and/or images)
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }

//    c) Same as 1 using specific product identity
    @Path("{id}/norelation")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProductProjection getSpecificProductNoRelationship(@PathParam("id") Long id){
        Product p = productRepository.findOne(id);
        if(p != null){
            return new ProductProjection(p.getId(),p.getName(),p.getDescription());
        }
        return null;
    }

//    e) Get set of child products for specific product
    @Path("{id}/child")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Product> getSpecificChildsProduct(@PathParam("id") Long id){
        Product p = productRepository.findOne(id);
        if(p != null){
            return p.getProductList();
        }
        return null;
    }

//    f) Get set of images for specific product
    @Path("{id}/images")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Image> getSpecificImagesProduct(@PathParam("id") Long id){
        Product p = productRepository.findOne(id);
        if(p != null){
            return p.getImageList();
        }
        return null;
    }

}
