package com.nxtexercises.mappinglibraries.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUSDto {
  String productName;
  double priceUSD;
}
