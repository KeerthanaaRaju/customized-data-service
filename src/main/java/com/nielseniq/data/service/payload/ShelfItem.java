package com.nielseniq.data.service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShelfItem
{
    private String productId;
    private Double relevancyScore;
}