package com.anurag.pricing_service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class Config {

  @Bean
  public AwsCredentialsProvider awsCredentialsProvider() {
    return ProfileCredentialsProvider.create("pricing-service");
  }

  @Bean
  public DynamoDbClient dynamoDbClient(
      AwsCredentialsProvider creds,
      @Value("${aws.region}") String region,
      @Value("${aws.dynamodb.endpoint}") String endpoint) {

    return DynamoDbClient.builder()
        .credentialsProvider(creds)
        .region(Region.of(region))
        .endpointOverride(URI.create(endpoint))
        .build();
  }

  @Bean
  public DynamoDbEnhancedClient enhancedClient(DynamoDbClient dynamoDbClient) {
    return DynamoDbEnhancedClient.builder()
        .dynamoDbClient(dynamoDbClient)
        .build();
  }
}
