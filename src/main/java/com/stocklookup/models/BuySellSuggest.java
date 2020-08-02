package com.stocklookup.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuySellSuggest {
  private String stockName;
  private String buyAt;
  private String stopLoss;
  private String sellAt;
  private String targetPrice;
  private String type;
  private String createdAt;
}
