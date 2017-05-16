package com.project.product.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity
@XmlRootElement
public class Product {

    @Id
    @XmlElement
    Long id;

    @XmlElement
    String name;

    @XmlElement
    String description;

    @ManyToOne(optional = true)
    Product parentProduct;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentProduct")
    @XmlElement
    List<Product> productList;

    @XmlElement
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product_id")
    List<Image> imageList;

    @XmlElement
    public void setParentProduct(Product parentProduct) {
        this.parentProduct = parentProduct;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    @XmlTransient
    public Product getParentProduct() {
        return parentProduct;
    }

    public Product(Long id, String name, String description, Product parentProduct) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentProduct = parentProduct;
    }

    public Product() {
    }
}
