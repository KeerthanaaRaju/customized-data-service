package data.service.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ShopperRelevanceKey implements Serializable {

    @Column(name = "shopperid")
    private String shopperId;
    @Column(name = "productid")
    private String productId;

}