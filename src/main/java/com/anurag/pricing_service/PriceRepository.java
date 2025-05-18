package com.anurag.pricing_service;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class PriceRepository {

  private final DynamoDbTable<PriceEntry> table;

  public PriceRepository(DynamoDbEnhancedClient enhancedClient) {
    this.table = enhancedClient.table("PricingTable", TableSchema.fromBean(PriceEntry.class));
  }

  public void save(PriceEntry entry) {
    table.putItem(entry);
  }

  public Optional<PriceEntry> findById(String productId, String region) {
    Key key = Key.builder()
                 .partitionValue(productId)
                 .sortValue(region)
                 .build();
    return Optional.ofNullable(table.getItem(r -> r.key(key)));
  }
}
