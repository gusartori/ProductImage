package com.project.product.rest;

import com.project.product.entities.Product;
import org.springframework.data.repository.CrudRepository;

interface ProductRepository extends CrudRepository<Product, Long>{


}
