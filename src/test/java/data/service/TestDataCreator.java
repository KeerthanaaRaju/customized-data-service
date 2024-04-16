package data.service;

import data.service.entities.Product;
import data.service.entities.Shopper;
import data.service.entities.ShopperProductRelevance;
import data.service.payload.ShelfItem;
import data.service.payload.ShopperRelevancePayload;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestDataCreator
{
    public static List<Product> getTestProductsList()
    {
        List<Product> products = new LinkedList<>();

        Product product1 = getProductObject("Prod1","Nike","Shoes");
        Product product2 = getProductObject("Prod2","Puma","Shoes");
        Product product3 = getProductObject("Prod3","Samsung","Electronics");
        Product product4 = getProductObject("Prod4","Lenovo","Electronics");

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        return products;
    }

    public static List<Shopper> getTestShoppersList()
    {
        Shopper shopper1 = getShopperObject("S1","S1");
        Shopper shopper2 = getShopperObject("S2","S2");

        List<Shopper> shoppers = new ArrayList<>();
        shoppers.add(shopper1);
        shoppers.add(shopper2);

        return shoppers;
    }

    public static List<ShopperProductRelevance> getTestRelevanceList()
    {
        ShopperProductRelevance spr1 = getRelevanceObject("Prod1","S1",40.00000);
        ShopperProductRelevance spr2 = getRelevanceObject("Prod2","S1",40.00);
        ShopperProductRelevance spr3 = getRelevanceObject("Prod3","S1",40.00000);
        ShopperProductRelevance spr4 = getRelevanceObject("Prod1","S2",40.00);
        ShopperProductRelevance spr5 = getRelevanceObject("Prod3","S2",40.00000);
        ShopperProductRelevance spr6 = getRelevanceObject("Prod4","S2",40.00);


        List<ShopperProductRelevance> sprList = new LinkedList<>();
        sprList.add(spr1);
        sprList.add(spr2);
        sprList.add(spr3);
        sprList.add(spr4);
        sprList.add(spr5);
        sprList.add(spr6);

        return sprList;
    }

    public static ShopperProductRelevance getRelevanceObject(String productId, String shopperId, Double relevance)
    {
        ShopperProductRelevance spr = new ShopperProductRelevance();
        spr.setProductId(productId);
        spr.setShopperId(shopperId);
        spr.setRelevance(relevance);
        return spr;
    }

    public static Shopper getShopperObject( String shopperId, String name)
    {
        Shopper shopper = new Shopper();
        shopper.setName(name);
        shopper.setShopperId(shopperId);

        return shopper;
    }

    public static Product getProductObject(String productId, String brand, String category)
    {
        Product prod = new Product();
        prod.setProductId(productId);
        prod.setBrand(brand);
        prod.setCategory(category);
        return prod;
    }

    public static ShopperRelevancePayload getShopperRelevancePayload(String shopperId)
    {
        List<ShelfItem> shelfItems = new LinkedList<>();
        List<ShopperProductRelevance> productRelevances = getTestRelevanceList();
        for(ShopperProductRelevance spr : productRelevances)
        {
            ShelfItem shelfItem = new ShelfItem(spr.getProductId(), spr.getRelevance());
            shelfItems.add(shelfItem);
        }

        return getShopperRelevancePayload(shopperId,shelfItems);
    }

    public static ShopperRelevancePayload getShopperRelevancePayload(String shopperId,List<ShelfItem> shelfItems)
    {
        ShopperRelevancePayload payload = new ShopperRelevancePayload();
        payload.setShopperId(shopperId);
        payload.setShelfItemList(shelfItems);
        return payload;
    }
}