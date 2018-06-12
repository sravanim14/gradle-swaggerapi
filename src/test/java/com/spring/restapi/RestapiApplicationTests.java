package com.spring.restapi;

import com.spring.restapi.models.Product;
import com.spring.restapi.repositories.ProductRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestapiApplicationTests {
	
	private ProductRepository productRepository;
	public String id;
    public Product savedProduct;
	
    @Autowired
    public void setRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

	/*@Test
	public void contextLoads() {
	}*/
	
	@Test
    public void createProduct() {
        Product product = new Product("Dummy Product 1", "The First Product in The world part 1", 100.0, "https://dummyimage.com/600x400/000/ggg");
		Product savedProduct = productRepository.save(product);
		
		//system.out.println(savedProduct.getId());
		
        //Product newProduct = productRepository.findOne(savedProduct.getId());
        /*assertEquals("Dummy Product 1", newProduct.getProdName());
        assertEquals("The First Product in The world part 1", newProduct.getProdDesc());
		assertEquals(100.0, newProduct.getProdPrice());
		assertEquals("https://dummyimage.com/600x400/000/ggg", newProduct.getProdImage());*/
		//id = product.getId();
       /* Book savedBook = repository.save(book);
        Book newBook = repository.findOne(savedBook.Id());
        assertEquals("Java 8 in Action", newBook.getName());
        assertEquals("Programming", newBook.getDescription());*/
    }

    @Test
    public void findAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        assertNotNull(products);
        //assertTrue(!products.isEmpty());
    }

    @Test
    public void findProductById() {
        Product product = productRepository.findOne(savedProduct.getId());
        assertNotNull(product);
    }

    @Test
    public void deleteProductWithId() {
        Product product = productRepository.findOne(savedProduct.getId());
        productRepository.delete(product);
        assertNotNull(product);
    }
	

}