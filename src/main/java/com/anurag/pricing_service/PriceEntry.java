package com.anurag.pricing_service;

import java.math.BigDecimal;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class PriceEntry {
  private String productId;
  private String region;
  private BigDecimal price;

  @DynamoDbPartitionKey
  public String getProductId() { return productId; }
  public void setProductId(String productId) { this.productId = productId; }

  @DynamoDbSortKey
  public String getRegion() { return region; }
  public void setRegion(String region) { this.region = region; }

  public BigDecimal getPrice() { return price; }
  public void setPrice(BigDecimal price) { this.price = price; }
}
