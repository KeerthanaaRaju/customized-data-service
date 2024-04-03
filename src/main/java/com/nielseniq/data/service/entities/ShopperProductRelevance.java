package com.nielseniq.data.service.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="shopper_product_relevance")
@IdClass(ShopperRelevanceKey.class)
public class ShopperProductRelevance
{
    @Id
    @Column(name="productid")
    private String productId;

    @Id
    @Column(name="shopperid")
    private String shopperId;

    @Column(name = "relevancyscore")
    private Double relevance;
}