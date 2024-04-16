package data.service.payload;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShopperRelevancePayload
{
    private String shopperId;
    private List<ShelfItem> shelfItemList = new ArrayList<>();
}