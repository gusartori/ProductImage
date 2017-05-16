package com.project.product.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Image {

    @Id
    @XmlElement
    Long id;

    @XmlElement
    String type;

    @XmlElement
    Long product_id;

    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public Image(Long id, String type, Long product_id) {
        this.id = id;
        this.type = type;
        this.product_id = product_id;
    }

    public Image() {
    }
}
