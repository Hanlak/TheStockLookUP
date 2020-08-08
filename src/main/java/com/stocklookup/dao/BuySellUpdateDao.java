package com.stocklookup.dao;

import com.stocklookup.models.BuySellSuggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BuySellUpdateDao {

  private static final String SQL_UPDATE_BUY_SELL_SUGGEST =
      "update ssbs set stockName = ?, buyAt = ?,stopLoss =?,sellAt = ?,targetPrice=?,createdAt =?,type = ? where stockName = ? and createdAt = ? and type =?";
  @Autowired JdbcTemplate jdbcTemplate;

  // TODO: ALL UPDATE  SCENARIOS HERE
  // TO update the suggestion in case of mistakes or changes
  public boolean updateBuySellSuggestByNameAndCreateDate(BuySellSuggest buySellSuggest) {
    return jdbcTemplate.update(
            SQL_UPDATE_BUY_SELL_SUGGEST,
            buySellSuggest.getStockName(),
            buySellSuggest.getBuyAt(),
            buySellSuggest.getStopLoss(),
            buySellSuggest.getSellAt(),
            buySellSuggest.getTargetPrice(),
            buySellSuggest.getCreatedAt(),
            buySellSuggest.getType(),
            buySellSuggest.getStockName(),
            buySellSuggest.getCreatedAt(),
            buySellSuggest.getType())
        > 0;
  }
}
