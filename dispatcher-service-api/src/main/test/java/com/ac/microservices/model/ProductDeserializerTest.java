package com.ac.microservices.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class ProductDeserializerTest {

    private final String SOURCE_JSON = "{\"id\":\"CG7088\",        \"name\":\"Nite Jogger Shoes\",        \"model_number\":\"BTO93\",        \"product_type\":\"inline\",        \"meta_data\":{        \"page_title\":\"adidas Nite Jogger Shoes - Black | adidas UK\",        \"site_name\":\"adidas United Kingdom\",        \"description\":\"Shop for Nite Jogger Shoes - Black at adidas.co.uk! See all the styles and colours of Nite Jogger Shoes-Black at the official adidas UK online store.\",        \"keywords\":\"Nite Jogger Shoes\",        \"canonical\":\"//www.adidas.co.uk/nite-jogger-shoes/CG7088.html\"        },        \"pricing_information\":{        \"standard_price\":119.95,        \"standard_price_no_vat\":99.96,        \"currentPrice\":119.95        },        \"product_description\":{        \"title\":\"Nite Jogger Shoes\",        \"subtitle\":\"Modern cushioning updates this flashy '80s standout.\",        \"text\":\"Inspired by the 1980 Nite Jogger, these shoes shine bright with retro style and reflective details.The mesh and nylon ripstop upper is detailed with suede overlays.An updated Boost midsole adds responsive cushioning for all-day comfort.\"        }        }";

    @Test
    public void whenUsingDeserializerManuallyRegistered_thenOk() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(CreateProductEvent.class, new ProductDeserializer());
        mapper.registerModule(module);

        CreateProductEvent createProductEvent = mapper.readValue(SOURCE_JSON, CreateProductEvent.class);

        Assert.assertEquals("Nite Jogger Shoes", createProductEvent.getName());
        Assert.assertEquals("BTO93", createProductEvent.getModelNumber());
        Assert.assertEquals("inline", createProductEvent.getProductType().toString());

        ProductMeta productMeta = createProductEvent.getMeta();

        Assert.assertEquals("adidas Nite Jogger Shoes - Black | adidas UK", productMeta.getPageTitle());
        Assert.assertEquals("adidas United Kingdom", productMeta.getSiteName());
        Assert.assertEquals("Shop for Nite Jogger Shoes - Black at adidas.co.uk! See all the styles and colours of Nite Jogger Shoes-Black at the official adidas UK online store.", productMeta.getDescription());
        Assert.assertEquals("Nite Jogger Shoes", productMeta.getKeywords());
        Assert.assertEquals("//www.adidas.co.uk/nite-jogger-shoes/CG7088.html", productMeta.getCanonical());

        ProductPricingInformation pricingInformation = createProductEvent.getPricingInformation();
        Assert.assertEquals(BigDecimal.valueOf(119.95), pricingInformation.getStandardPrice());
        Assert.assertEquals(BigDecimal.valueOf(99.96), pricingInformation.getStandardPriceNoVat());
        Assert.assertEquals(BigDecimal.valueOf(119.95), pricingInformation.getCurrentPrice());

        ProductDescription productDescription = createProductEvent.getProductDescription();
        Assert.assertEquals("Nite Jogger Shoes", productDescription.getTitle());
        Assert.assertEquals("Modern cushioning updates this flashy '80s standout.", productDescription.getSubtitle());
        Assert.assertEquals("Inspired by the 1980 Nite Jogger, these shoes shine bright with retro style and reflective details.The mesh and nylon ripstop upper is detailed with suede overlays.An updated Boost midsole adds responsive cushioning for all-day comfort.", productDescription.getText());
    }
}
