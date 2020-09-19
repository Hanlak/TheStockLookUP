package com.stocklookup.dao;

import com.stocklookup.exception.SuggestionCreationException;
import com.stocklookup.models.BuySellSuggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BuySellDao {

  private static final String SQL_INSERT_BUY_SELL_SUGGEST =
      "insert into ssbs(stockName, buyAt, stopLoss,sellAt,targetPrice,createdAt,type) values(?,?,?,?,?,?,?)";
  @Autowired JdbcTemplate jdbcTemplate;

  // TODO:: ALL CREATE SCENARIOS HERE
  // To Save the The Suggestion
  public boolean save(BuySellSuggest buySellSuggest) {
    try {
      return jdbcTemplate.update(
              SQL_INSERT_BUY_SELL_SUGGEST,
              buySellSuggest.getStockName(),
              buySellSuggest.getBuyAt(),
              buySellSuggest.getStopLoss(),
              buySellSuggest.getSellAt(),
              buySellSuggest.getTargetPrice(),
              buySellSuggest.getCreatedAt(),
              buySellSuggest.getType())
          > 0;
    } catch (Exception e) {
      throw new SuggestionCreationException(e.getMessage());
    }
  }
}
