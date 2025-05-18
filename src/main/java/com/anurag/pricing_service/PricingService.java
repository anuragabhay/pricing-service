package com.anurag.pricing_service;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PricingService {

  private final PriceRepository repo;

  public PricingService(PriceRepository repo) {
    this.repo = repo;
  }

  public PriceEntry upsert(PriceEntry entry) {
    repo.save(entry);
    return entry;
  }

  public Optional<PriceEntry> fetch(String productId, String region) {
    return repo.findById(productId, region);
  }
}
