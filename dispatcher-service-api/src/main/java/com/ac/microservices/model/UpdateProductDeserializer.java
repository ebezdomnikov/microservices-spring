package com.ac.microservices.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class UpdateProductDeserializer extends StdDeserializer<UpdateProductEvent> {
    public UpdateProductDeserializer() {
        this(null);
    }

    public UpdateProductDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public UpdateProductEvent deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode productNode = jp.getCodec().readTree(jp);
        UpdateProductEvent product = new UpdateProductEvent();
        product.setProductId(productNode.get("id").textValue());

        if (productNode.hasNonNull("name")) {
            product.setName(productNode.get("name").textValue());
        }

        if (productNode.hasNonNull("model_number")) {
            product.setName(productNode.get("model_number").textValue());
        }

        if (productNode.hasNonNull("product_type")) {
            product.setName(productNode.get("product_type").textValue());
        }

        if (productNode.hasNonNull("meta_data")) {
            ProductMeta productMeta = new ProductMeta();

            JsonNode metaNode = productNode.get("meta_data");

            if (metaNode.hasNonNull("page_title")) {
                productMeta.setPageTitle(metaNode.get("page_title").textValue());
            }

            if (metaNode.hasNonNull("site_name")) {
                productMeta.setSiteName(metaNode.get("site_name").textValue());
            }

            if (metaNode.hasNonNull("description")) {
                productMeta.setDescription(metaNode.get("description").textValue());
            }

            if (metaNode.hasNonNull("keywords")) {
                productMeta.setKeywords(metaNode.get("keywords").textValue());
            }

            if (metaNode.hasNonNull("canonical")) {
                productMeta.setCanonical(metaNode.get("canonical").textValue());
            }

            product.setMeta(productMeta);
        }

        if (productNode.hasNonNull("pricing_information")) {
            ProductPricingInformation pricingInformation = new ProductPricingInformation();        product.setPricingInformation(pricingInformation);

            JsonNode metaNode = productNode.get("pricing_information");
            if (metaNode.hasNonNull("standard_price")) {
                pricingInformation.setStandardPrice(metaNode.get("standard_price").decimalValue());
            }
            if (metaNode.hasNonNull("standard_price_no_vat")) {
                pricingInformation.setStandardPriceNoVat(metaNode.get("standard_price_no_vat").decimalValue());
            }
            if (metaNode.hasNonNull("currentPrice")) {
                pricingInformation.setCurrentPrice(metaNode.get("currentPrice").decimalValue());
            }

            product.setPricingInformation(pricingInformation);
        }

        if (productNode.hasNonNull("product_description")) {
            ProductDescription productDescription = new ProductDescription();

            JsonNode metaNode = productNode.get("product_description");

            if (metaNode.hasNonNull("title")) {
                productDescription.setTitle(metaNode.get("title").textValue());
            }
            if (metaNode.hasNonNull("subtitle")) {
                productDescription.setSubtitle(metaNode.get("subtitle").textValue());
            }
            if (metaNode.hasNonNull("text")) {
                productDescription.setText(metaNode.get("text").textValue());
            }

            product.setProductDescription(productDescription);

        }

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
