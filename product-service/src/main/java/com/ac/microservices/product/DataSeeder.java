package com.ac.microservices.product;

import com.ac.microservices.product.model.Product;
import com.ac.microservices.product.model.ProductDescription;
import com.ac.microservices.product.model.ProductMeta;
import com.ac.microservices.product.model.ProductPriceInformation;
import com.ac.microservices.product.service.ProductService;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class DataSeeder implements CommandLineRunner {

    private ProductService productService;
    private Faker faker = new Faker();


    public DataSeeder(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... strings) throws Exception {

        for (int i = 0; i< 100; i++) {

            Product product = new Product();
            product.setName(faker.book().title());
            product.setId(UUID.randomUUID().toString());
            product.setProductType("inline");
            product.setModelNumber("BTO93");

            product.setMeta(ProductMeta.builder()
                    .siteName("adidas United Kingdom")
                    .description(faker.chuckNorris().fact())
                    .keywords(faker.lorem().characters(100))
                    .canonical("//www.adidas.co.uk/nite-jogger-shoes/CG7088.html")
                    .pageTitle(faker.lorem().characters(100))
                    .build()
            );

            product.setProductDescription(ProductDescription
                    .builder()
                    .title(faker.chuckNorris().fact())
                    .subtitle(faker.gameOfThrones().quote())
                    .text(faker.harryPotter().quote())
                    .build()
            );

            product.setPriceInformation(ProductPriceInformation
                    .builder()
                    .currentPrice(BigDecimal.valueOf(faker.number().numberBetween(1, 100)))
                    .standardPrice(BigDecimal.valueOf(faker.number().numberBetween(1, 100)))
                    .standardPriceNoVat(BigDecimal.valueOf(faker.number().numberBetween(1, 100)))
                    .build());

            productService.save(product);

            System.out.println("Write Product: " + product.getName());
        }
    }
}

//{
//        "id": "CG7088",
//        "name": "Nite Jogger Shoes",
//        "model_number": "BTO93",
//        "product_type": "inline",
//        "meta_data": {
//        "page_title": "adidas Nite Jogger Shoes - Black | adidas UK",
//        "site_name": "adidas United Kingdom",
//        "description": "Shop for Nite Jogger Shoes - Black at adidas.co.uk! See all the styles
//        and colours of Nite Jogger Shoes - Black at the official adidas UK online store.",
//        "keywords": "Nite Jogger Shoes",
//        "canonical": "//www.adidas.co.uk/nite-jogger-shoes/CG7088.html"
//        },
//        "pricing_information": {
//        "standard_price": 119.95,
//        "standard_price_no_vat": 99.96,
//        "currentPrice": 119.95
//        },
//        "product_description": {
//        "title": "Nite Jogger Shoes",
//        "subtitle": "Modern cushioning updates this flashy '80s standout.",
//        "text": "Inspired by the 1980 Nite Jogger, these shoes shine bright with retro style and
//        reflective details. The mesh and nylon ripstop upper is detailed with suede overlays. An
//        updated Boost midsole adds responsive cushioning for all-day comfort."
//        }
//        }

