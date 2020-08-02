package com.stocklookup.mapper;

import com.stocklookup.models.BuySellSuggest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuySellSuggestMapper implements RowMapper<BuySellSuggest> {

  public BuySellSuggest mapRow(ResultSet resultSet, int i) throws SQLException {

    BuySellSuggest buySellSuggest = new BuySellSuggest();
    buySellSuggest.setStockName(resultSet.getString("stockName"));
    buySellSuggest.setBuyAt(resultSet.getString("buyAt"));
    buySellSuggest.setSellAt(resultSet.getString("sellAt"));
    buySellSuggest.setStopLoss(resultSet.getString("stopLoss"));
    buySellSuggest.setTargetPrice(resultSet.getString("targetPrice"));
    buySellSuggest.setCreatedAt(resultSet.getString("createdAt"));
    buySellSuggest.setType(resultSet.getString("type"));
    return buySellSuggest;
  }
}
