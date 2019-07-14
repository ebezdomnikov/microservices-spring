package com.ac.microservices.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class ProductDeserializer extends StdDeserializer<CreateProductEvent> {
    public ProductDeserializer() {
        this(null);
    }

    public ProductDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CreateProductEvent deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode productNode = jp.getCodec().readTree(jp);
        CreateProductEvent product = new CreateProductEvent();
        product.setProductId(productNode.get("id").textValue());
        product.setName(productNode.get("name").textValue());
        product.setModelNumber(productNode.get("model_number").textValue());
        product.setProductType(productNode.get("product_type").textValue());

        ProductMeta productMeta = new ProductMeta();
        productMeta.setPageTitle(productNode.get("meta_data").get("page_title").textValue());
        productMeta.setSiteName(productNode.get("meta_data").get("site_name").textValue());
        productMeta.setDescription(productNode.get("meta_data").get("description").textValue());
        productMeta.setKeywords(productNode.get("meta_data").get("keywords").textValue());
        productMeta.setCanonical(productNode.get("meta_data").get("canonical").textValue());

        product.setMeta(productMeta);

        ProductPricingInformation pricingInformation = new ProductPricingInformation();
        pricingInformation.setStandardPrice(productNode.get("pricing_information").get("standard_price").decimalValue());
        pricingInformation.setStandardPriceNoVat(productNode.get("pricing_information").get("standard_price_no_vat").decimalValue());
        pricingInformation.setCurrentPrice(productNode.get("pricing_information").get("currentPrice").decimalValue());

        product.setPricingInformation(pricingInformation);

        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle(productNode.get("product_description").get("title").textValue());
        productDescription.setSubtitle(productNode.get("product_description").get("subtitle").textValue());
        productDescription.setText(productNode.get("product_description").get("text").textValue());

        product.setProductDescription(productDescription);

        return product;
    }
}


//{
//        "id":"CG7088",
//        "name":"Nite Jogger Shoes",
//        "model_number":"BTO93",
//        "product_type":"inline",
//        "meta_data":{
//        "page_title":"adidas Nite Jogger Shoes - Black | adidas UK",
//        "site_name":"adidas United Kingdom",
//        "description":"Shop for Nite Jogger Shoes - Black at adidas.co.uk! See all the styles
//        and colours of Nite Jogger Shoes-Black at the official adidas UK online store.",
//        "keywords":"Nite Jogger Shoes",
//        "canonical":"//www.adidas.co.uk/nite-jogger-shoes/CG7088.html"
//        },
//        "pricing_information":{
//        "standard_price":119.95,
//        "standard_price_no_vat":99.96,
//        "currentPrice":119.95
//        },
//        "product_description":{
//        "title":"Nite Jogger Shoes",
//        "subtitle":"Modern cushioning updates this flashy '80s standout.",
//        "text":"Inspired by the 1980 Nite Jogger, these shoes shine bright with retro style and
//        reflective details.The mesh and nylon ripstop upper is detailed with suede overlays.An
//        updated Boost midsole adds responsive cushioning for all-day comfort."
//        }
//        }
