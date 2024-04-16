package data.service.helper;

import data.service.entities.ShopperProductRelevance;
import data.service.payload.ShelfItem;
import data.service.payload.ShopperRelevancePayload;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShopperRelevanceConverterTest
{
    @Test
    public void RelevanceService_PayloadToEntityConversion()
    {
        ShopperRelevancePayload srp = new ShopperRelevancePayload();
        List<ShelfItem> shelfItems = new ArrayList<>();
        shelfItems.add(new ShelfItem("p1",55.8787));
        shelfItems.add(new ShelfItem("p2",52.8787));
        srp.setShelfItemList(shelfItems);

        List<ShopperProductRelevance> relevanceList = ShopperRelevanceConverter.payloadToEntity(srp);
        Assertions.assertThat(relevanceList.size()).isEqualTo(srp.getShelfItemList().size());
    }
}