package data.service.services;

import data.service.constants.Constants;
import data.service.entities.ShopperProductRelevance;
import data.service.exceptions.DataServiceException;
import data.service.repositories.ShopperRelevanceRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
public class ShopperRelevanceServiceTest
{
    @Mock
    private ShopperRelevanceRepository relevanceRepository;
    @InjectMocks
    private ShopperRelevanceServiceImpl relevanceService;

    private ShopperProductRelevance productRelevance;
    private List<ShopperProductRelevance> relevanceList = new ArrayList<>();

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        productRelevance = new ShopperProductRelevance();
        productRelevance.setShopperId("s1");
        productRelevance.setProductId("p1");
        productRelevance.setRelevance(50.45236);

        relevanceList.add(productRelevance);
    }

    @Test
    public void RelevanceService_CreateObjects_ReturnsSuccess()
    {

        Mockito.when(relevanceRepository.saveAll(ArgumentMatchers.any())).thenReturn(Collections.singletonList(productRelevance));
        String result="";
        try
        {
            result  = relevanceService.saveShopperRelevance(relevanceList);
        }
        catch (DataServiceException e)
        {
            Assert.fail();
        }
        Assertions.assertThat(result).isEqualTo(Constants.RELEVANCE_SAVED);
    }

    @Test
    public void RelevanceService_CreateObjects_NullResult_ShouldThrowException() throws DataServiceException
    {
        Mockito.when(relevanceRepository.saveAll(ArgumentMatchers.any())).thenReturn(null);
        assertThrows(DataServiceException.class, () -> {
            relevanceService.saveShopperRelevance(relevanceList);
        });
    }

    @Test
    public void RelevanceService_CreateObjects_MissingPayload_ShouldThrowException() throws DataServiceException
    {
        Mockito.when(relevanceRepository.saveAll(ArgumentMatchers.any())).thenReturn(Collections.singletonList(productRelevance));
        assertThrows(DataServiceException.class, () -> {
            relevanceService.saveShopperRelevance(relevanceList);
        });
    }

}