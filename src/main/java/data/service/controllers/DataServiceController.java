package data.service.controllers;

import data.service.constants.Constants;
import data.service.entities.Product;
import data.service.entities.Shopper;
import data.service.entities.ShopperProductRelevance;
import data.service.exceptions.DataServiceException;
import data.service.helper.ShopperRelevanceConverter;
import data.service.payload.ShopperRelevancePayload;
import com.nielseniq.data.service.services.*;
import data.service.services.ProductService;
import data.service.services.ShopperRelevanceService;
import data.service.services.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class DataServiceController
{
    // TODO: Split into multiple Controllers for future
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopperService shopperService;
    @Autowired
    private ShopperRelevanceService shopperRelevanceService;

    @PostMapping(value = "/product",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveProduct(@RequestBody Product product) throws DataServiceException
    {
       String response = productService.saveProduct(product);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(value = "/shopper",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveShopper(@RequestBody Shopper shopper) throws DataServiceException
    {
        String response = shopperService.saveShopper(shopper);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(value = "/shopperRelevance",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveShopperRelevance(@RequestBody ShopperRelevancePayload shopperRelevancePayload) throws DataServiceException
    {
        if(!StringUtils.hasText(shopperRelevancePayload.getShopperId()) ||  shopperRelevancePayload.getShelfItemList().isEmpty())
        {
            throw new DataServiceException(Constants.MISSING_ITEMS,HttpStatus.BAD_REQUEST);
        }
        List<ShopperProductRelevance> relevanceList = ShopperRelevanceConverter.payloadToEntity(shopperRelevancePayload);

        String response = shopperRelevanceService.saveShopperRelevance(relevanceList);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/products",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProduct(
            @RequestParam(required = false) String shopperId,
            @RequestParam(required = false)  String brand,
            @RequestParam(required = false) String category,
            @RequestParam(required = false,defaultValue = "10")  Integer limit)
            throws DataServiceException
    {
        List<Product> response = productService.findByShopperIdCategoryAndBrand(shopperId,category,brand,limit);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}