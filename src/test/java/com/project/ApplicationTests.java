package com.project;

import com.project.product.entities.Image;
import com.project.product.entities.Product;
import com.project.product.entities.ProductProjection;
import com.project.product.rest.ImageEndpoint;
import com.project.product.rest.ProductEndpoint;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {


	@Autowired
	private ProductEndpoint productEndpoint;

	@Autowired
    private ImageEndpoint imageEndpoint;

	@Before
	public void setUp() {
	}

	@After
    public void tearDown(){
        productEndpoint.deleteProduct(99l);
        productEndpoint.deleteProduct(899l);
        imageEndpoint.deleteImage(199l);
        imageEndpoint.deleteImage(299l);
        imageEndpoint.deleteImage(699l);
    }

	@Test
	public void productCreateTest(){
	    Product product = new Product(99l,"Test Product", "Integration Test Product", productEndpoint.getSpecificProduct(2l));
		productEndpoint.saveProduct(product);
		assertEquals(productEndpoint.getSpecificProduct(99l).getName(), "Test Product");
		assertEquals(productEndpoint.getSpecificProduct(2l).getProductList().get(0).getName(),"Test Product");
    }

    @Test
    public void productReadTest(){
        Product product = productEndpoint.getSpecificProduct(1l);
        assertEquals(product.getDescription(), "Glue Tube");
    }

    @Test
    public void productUpdateTest(){
        Product product = new Product(899l,"Test Update Product", "Update Test Product", productEndpoint.getSpecificProduct(2l));
        productEndpoint.saveProduct(product);
        assertEquals(productEndpoint.getSpecificProduct(899l).getName(), "Test Update Product");
        Product oldProduct = productEndpoint.getSpecificProduct(899l);
        Product newProduct = new Product(oldProduct.getId(),"New Name for Tests Purposes",oldProduct.getDescription(),oldProduct.getParentProduct());
        productEndpoint.updateProduct(newProduct.getId(), newProduct);
        assertEquals(productEndpoint.getSpecificProduct(newProduct.getId()).getName(), "New Name for Tests Purposes");
    }

    @Test
    public void productDeleteTest(){
        Product product = new Product(999l,"Test Product", "Deletion Test Product", productEndpoint.getSpecificProduct(2l));
        productEndpoint.saveProduct(product);
        assertEquals(productEndpoint.getSpecificProduct(999l).getName(), "Test Product");
        productEndpoint.deleteProduct(999l);
        assertNull(productEndpoint.getSpecificProduct(999l));
    }

    @Test
    public void getAllProductsTest(){
        ArrayList<Product> al = (ArrayList<Product>) productEndpoint.getAllProducts();
        assertNotNull(al);
    }

    @Test
    public void getAllProductsNoRelationshipTest(){
        ArrayList<ProductProjection> al = (ArrayList<ProductProjection>) productEndpoint.getAllProductsNoRelationship();
        assertNotNull(al);
    }

    @Test
    public void getSpecificProductNoRelationshipTest(){
        ProductProjection productProjection = productEndpoint.getSpecificProductNoRelationship(1l);
        assertNotNull(productProjection);
    }

    @Test
    public void getSpecificChildsProductTest(){
        Iterable<Product> childsProducts = productEndpoint.getSpecificChildsProduct(1l);
        assertNotNull(childsProducts);
    }

    @Test
    public void getSpecificImagesProductTest(){
        Iterable<Image> imagesList = productEndpoint.getSpecificImagesProduct(1l);
        assertNotNull(imagesList);
    }

    @Test
    public void imageCreateTest(){
        Image image = new Image(199l, "Test Image", 3l);
        imageEndpoint.saveProduct(image);
        assertEquals(imageEndpoint.getSpecificImage(199l).getType(), "Test Image");
    }

    @Test
    public void imageReadTest(){
        Image image = imageEndpoint.getSpecificImage(1l);
        assertEquals(image.getType(), "tube");
    }

    @Test
    public void imageUpdateTest(){
        Image image = new Image(299l, "Update Test Image", 2l);
        imageEndpoint.saveProduct(image);
        Image updatedImage = imageEndpoint.getSpecificImage(299l);
        assertEquals(updatedImage.getType(), "Update Test Image");
        Image newImage = new Image(updatedImage.getId(), "Type Image was updated!", 2l);
        imageEndpoint.updateImage(newImage.getId(), newImage);
        assertEquals(imageEndpoint.getSpecificImage(newImage.getId()).getType(), "Type Image was updated!");
    }

    @Test
    public void imageDeleteTest(){
        Image image = new Image(399l, "Deletion Test Image", 1l);
        imageEndpoint.saveProduct(image);
        Image createdImage = imageEndpoint.getSpecificImage(399l);
        assertEquals(createdImage.getType(), "Deletion Test Image");
        imageEndpoint.deleteImage(createdImage.getId());
        assertNull(productEndpoint.getSpecificProduct(399l));
    }

    @Test
    public void changingImageOwnerTest(){
        Image image = new Image(699l, "Update Test Image", 1l);
        imageEndpoint.saveProduct(image);
        Image updatedImage = imageEndpoint.getSpecificImage(699l);
        assertEquals(updatedImage.getType(), "Update Test Image");
        Image newImage = new Image(updatedImage.getId(), "Type Image was updated!", 2l);
        imageEndpoint.updateImage(newImage.getId(), newImage);
        assertEquals(imageEndpoint.getSpecificImage(newImage.getId()).getProduct_id(), (Long)2l);
    }
}
