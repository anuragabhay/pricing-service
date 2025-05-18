package com.anurag.pricing_service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pricing")
public class PricingController {

  private final PricingService svc;

  public PricingController(PricingService svc) {
    this.svc = svc;
  }

  @PostMapping
  public PriceEntry upsert(@RequestBody PriceEntry entry) {
    return svc.upsert(entry);
  }

  @GetMapping("/{productId}/{region}")
  public PriceEntry get(@PathVariable String productId,
                        @PathVariable String region) {
    return svc.fetch(productId, region)
              .orElseThrow(() -> new ResponseStatusException(
                  HttpStatus.NOT_FOUND, "Price not found"));
  }
}
