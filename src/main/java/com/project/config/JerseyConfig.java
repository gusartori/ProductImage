package com.project.config;

import com.project.product.rest.ImageEndpoint;
import com.project.product.rest.ProductEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ProductEndpoint.class);
        register(ImageEndpoint.class);
    }

}