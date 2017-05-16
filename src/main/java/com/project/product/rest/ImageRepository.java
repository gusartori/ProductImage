package com.project.product.rest;

import com.project.product.entities.Image;
import org.springframework.data.repository.CrudRepository;

interface ImageRepository extends CrudRepository<Image, Long>{


}
