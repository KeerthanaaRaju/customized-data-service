package data.service.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.service.TestDataCreator;
import data.service.constants.Constants;
import data.service.entities.Product;
import data.service.entities.Shopper;
import data.service.exceptions.DataServiceException;
import data.service.payload.ShopperRelevancePayload;
import data.service.services.ProductServiceImpl;
import data.service.services.ShopperRelevanceServiceImpl;
import data.service.services.ShopperServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@RunWith(SpringRunner.class)
@WebMvcTest(DataServiceController.class)
public class DataServiceControllerTest
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductServiceImpl productService;
    @MockBean
    private ShopperServiceImpl shopperService;
    @MockBean
    private ShopperRelevanceServiceImpl shopperRelevanceService;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void DSController_SaveProd_ShouldReturnSuccess() throws Exception
    {
        Product randomObj = TestDataCreator.getProductObject("P1","HP","Electronics");

        Mockito.when(productService.saveProduct(ArgumentMatchers.any(Product.class)))
                .thenReturn(Constants.PROD_SAVED);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        mvc.perform(
            MockMvcRequestBuilders.post("/product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(result -> Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(Constants.PROD_SAVED))
            .andReturn();

        Mockito.verify(productService, Mockito.times(1)).saveProduct(ArgumentMatchers.any(Product.class));
    }

    @Test
    public void DSController_SaveProd_IntegrityException_ShouldThrowException() throws Exception
    {
        Product randomObj = TestDataCreator.getProductObject("P1","HP","Electronics");

        Mockito.when(productService.saveProduct(ArgumentMatchers.any(Product.class)))
                .thenThrow(DataIntegrityViolationException.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        mvc.perform(
                        MockMvcRequestBuilders.post("/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertThat(result.getResolvedException() instanceof DataIntegrityViolationException).isTrue())
                .andExpect(result -> Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(Constants.DUPLICATE_KEY))
                .andReturn();

        Mockito.verify(productService, Mockito.times(1)).saveProduct(ArgumentMatchers.any(Product.class));
    }

    @Test
    public void DSController_SaveProd_OtherException_ShouldThrowException() throws Exception
    {
        Product randomObj = TestDataCreator.getProductObject("P1","HP","Electronics");

        Mockito.when(productService.saveProduct(ArgumentMatchers.any(Product.class)))
                .thenThrow(new DataServiceException(Constants.PROD_SAVE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        mvc.perform(
                        MockMvcRequestBuilders.post("/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(result -> Assertions.assertThat(result.getResolvedException() instanceof DataServiceException).isTrue())
                .andExpect(result -> Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(Constants.PROD_SAVE_ERROR))
                .andReturn();

        Mockito.verify(productService, Mockito.times(1)).saveProduct(ArgumentMatchers.any(Product.class));
    }

    @Test
    public void DSController_SaveShopper_ShouldReturnSuccess() throws Exception
    {
        Shopper randomObj = TestDataCreator.getShopperObject("S1","Shopper Name");

        Mockito.when(shopperService.saveShopper(ArgumentMatchers.any(Shopper.class)))
                .thenReturn(Constants.SHOPPER_SAVED);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        mvc.perform(
            MockMvcRequestBuilders.post("/shopper")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(result -> Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(Constants.SHOPPER_SAVED))
            .andReturn();

        Mockito.verify(shopperService, Mockito.times(1)).saveShopper(ArgumentMatchers.any(Shopper.class));
    }

    @Test
    public void DSController_SaveShopper_IntegrityException_ShouldThrowException() throws Exception
    {
        Shopper randomObj = TestDataCreator.getShopperObject("S1","name");

        Mockito.when(shopperService.saveShopper(ArgumentMatchers.any(Shopper.class)))
                .thenThrow(DataIntegrityViolationException.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        mvc.perform(
                        MockMvcRequestBuilders.post("/shopper")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertThat(result.getResolvedException() instanceof DataIntegrityViolationException).isTrue())
                .andExpect(result -> Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(Constants.DUPLICATE_KEY))
                .andReturn();

        Mockito.verify(shopperService, Mockito.times(1)).saveShopper(ArgumentMatchers.any(Shopper.class));
    }

    @Test
    public void DSController_SaveShopper_OtherException_ShouldThrowException() throws Exception
    {
        Shopper randomObj = TestDataCreator.getShopperObject("S1","name");

        Mockito.when(shopperService.saveShopper(ArgumentMatchers.any(Shopper.class)))
                .thenThrow(new DataServiceException(Constants.SHOPPER_SAVE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        mvc.perform(
                        MockMvcRequestBuilders.post("/shopper")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(result -> Assertions.assertThat(result.getResolvedException() instanceof DataServiceException).isTrue())
                .andExpect(result -> Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(Constants.SHOPPER_SAVE_ERROR))
                .andReturn();

        Mockito.verify(shopperService, Mockito.times(1)).saveShopper(ArgumentMatchers.any(Shopper.class));
    }

    @Test
    public void DSController_SaveShopperRelevance_ShouldReturnSuccess() throws Exception
    {
        ShopperRelevancePayload srp = TestDataCreator.getShopperRelevancePayload("S1");
        Mockito.when(shopperRelevanceService.saveShopperRelevance(ArgumentMatchers.anyList()))
                .thenReturn(Constants.RELEVANCE_SAVED);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(srp);

         mvc.perform(
            MockMvcRequestBuilders.post("/shopperRelevance")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(mvcResult -> Assertions.assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(Constants.RELEVANCE_SAVED))
            .andReturn();

        Mockito.verify(shopperRelevanceService, Mockito.times(1)).saveShopperRelevance(ArgumentMatchers.anyList());
    }

    @Test
    public void DSController_SaveShopperRelevance_EmptyShopperId_ShouldThrowException() throws Exception
    {
        ShopperRelevancePayload srp = TestDataCreator.getShopperRelevancePayload(null);
        Mockito.when(shopperRelevanceService.saveShopperRelevance(ArgumentMatchers.anyList()))
                .thenReturn(Constants.RELEVANCE_SAVED);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(srp);

        mvc.perform(
            MockMvcRequestBuilders.post("/shopperRelevance")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(result -> Assertions.assertThat(result.getResolvedException() instanceof DataServiceException).isTrue())
            .andExpect(result -> Assertions.assertThat(result.getResolvedException().getMessage()).contains(Constants.MISSING_ITEMS))
            .andReturn();

        Mockito.verify(shopperRelevanceService, Mockito.times(0)).saveShopperRelevance(ArgumentMatchers.anyList());
    }

    @Test
    public void DSController_SaveShopperRelevance_NullShopperId_ShouldThrowException() throws Exception
    {
        ShopperRelevancePayload srp = TestDataCreator.getShopperRelevancePayload(null);
        Mockito.when(shopperRelevanceService.saveShopperRelevance(ArgumentMatchers.anyList()))
                .thenReturn(Constants.RELEVANCE_SAVED);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(srp);

        mvc.perform(
                        MockMvcRequestBuilders.post("/shopperRelevance")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertThat(result.getResolvedException() instanceof DataServiceException).isTrue())
                .andExpect(result -> Assertions.assertThat(result.getResolvedException().getMessage()).contains(Constants.MISSING_ITEMS))
                .andReturn();

        Mockito.verify(shopperRelevanceService, Mockito.times(0)).saveShopperRelevance(ArgumentMatchers.anyList());
    }

    @Test
    public void DSController_SaveShopperRelevance_EmptyRelevanceList_ShouldThrowException() throws Exception
    {
        ShopperRelevancePayload srp = TestDataCreator.getShopperRelevancePayload(null);
        Mockito.when(shopperRelevanceService.saveShopperRelevance(ArgumentMatchers.anyList()))
                .thenReturn(Constants.RELEVANCE_SAVED);

        srp.setShelfItemList(Collections.emptyList());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(srp);

        mvc.perform(
                        MockMvcRequestBuilders.post("/shopperRelevance")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertThat(result.getResolvedException() instanceof DataServiceException).isTrue())
                .andExpect(result -> Assertions.assertThat(result.getResolvedException().getMessage()).contains(Constants.MISSING_ITEMS))
                .andReturn();

        Mockito.verify(shopperRelevanceService, Mockito.times(0)).saveShopperRelevance(ArgumentMatchers.anyList());
    }

    @Test
    public void DSController_SaveShopperRelevance_NullRelevanceList_ShouldThrowException() throws Exception
    {
        ShopperRelevancePayload srp = TestDataCreator.getShopperRelevancePayload(null);
        Mockito.when(shopperRelevanceService.saveShopperRelevance(ArgumentMatchers.anyList()))
                .thenReturn(Constants.RELEVANCE_SAVED);

        srp.setShelfItemList(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(srp);

        mvc.perform(
                        MockMvcRequestBuilders.post("/shopperRelevance")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertThat(result.getResolvedException() instanceof DataServiceException).isTrue())
                .andExpect(result -> Assertions.assertThat(result.getResolvedException().getMessage()).contains(Constants.MISSING_ITEMS))
                .andReturn();

        Mockito.verify(shopperRelevanceService, Mockito.times(0)).saveShopperRelevance(ArgumentMatchers.anyList());
    }

    @Test
    public void DSController_SaveShopperRelevance_OtherException_ShouldThrowException() throws Exception
    {
        ShopperRelevancePayload srp = TestDataCreator.getShopperRelevancePayload("S1");

        Mockito.when(shopperRelevanceService.saveShopperRelevance(ArgumentMatchers.anyList()))
                .thenThrow(new DataServiceException(Constants.RELEVANCE_SAVE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(srp);

        mvc.perform(
                        MockMvcRequestBuilders.post("/shopperRelevance")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(result -> Assertions.assertThat(result.getResolvedException() instanceof DataServiceException).isTrue())
                .andExpect(result -> Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(Constants.RELEVANCE_SAVE_ERROR))
                .andReturn();

        Mockito.verify(shopperRelevanceService, Mockito.times(1)).saveShopperRelevance(ArgumentMatchers.anyList());
    }

    @Test
    public void DSController_GetProd_NoArgs_ShouldReturnSuccess() throws Exception
    {
        Product product = TestDataCreator.getProductObject("P1","HP","Electronics");

        Mockito.when(
                productService.findByShopperIdCategoryAndBrand
                    (ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),10)
                )
                .thenReturn(Collections.singletonList(product));

        mvc.perform(
                        MockMvcRequestBuilders.get("/products")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(productService, Mockito.times(1)).saveProduct(ArgumentMatchers.any(Product.class));
    }

    @Test
    public void DSController_GetProd_AllArgs_ShouldReturnSuccess() throws Exception
    {
        Product product = TestDataCreator.getProductObject("P1","HP","Electronics");

        Mockito.when(
                        productService.findByShopperIdCategoryAndBrand
                                ("S1","Electronics","HP",10)
                )
                .thenReturn(Collections.singletonList(product));

        mvc.perform(
                    MockMvcRequestBuilders.get("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .queryParam("shopperId","S1")
                            .queryParam("brand","HP")
                            .queryParam("category","Electronics")
                            .queryParam("limit","10")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productId").value("P1"))
                ;
        Mockito.verify(productService, Mockito.times(1)).findByShopperIdCategoryAndBrand(ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),ArgumentMatchers.anyInt());
    }

}