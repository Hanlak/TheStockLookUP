package com.stocklookup.dao;

import com.stocklookup.mapper.BuySellSuggestMapper;
import com.stocklookup.models.BuySellSuggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BuySellGetterDao {
  private static final String SQL_FIND_BUY_SELL_SUGGEST =
      "select * from ssbs where stockName = ? and createdAt = ?";
  private static final String SQL_FIND_BUY_SELL_SUGGEST_WITH_TYPE =
      "select * from ssbs where stockName = ? and createdAt = ? and type = ?";
  private static final String SQL_GET_ALL = "select * from ssbs";
  private static final String SQL_GET_ALL_SUGG_BY_NAME = "select * from ssbs where stockName like CONCAT( '%',?,'%')";
  private static final String SQL_GET_ALL_BY_TYPE = "select * from ssbs where type = ?";
  @Autowired JdbcTemplate jdbcTemplate;

  // TODO: ALL GET SCENARIOS HERE
  // Search the suggestion by name and date
  public BuySellSuggest getBuySellSuggestByNameAndCreateDate(String stockName, String createdAt) {
    try {
      return jdbcTemplate.queryForObject(
          SQL_FIND_BUY_SELL_SUGGEST,
          new Object[] {stockName, createdAt},
          new BuySellSuggestMapper());
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
  // Search the suggestion by name and date and type
  public BuySellSuggest getBuySellSuggestByNameAndCreateDateAndType(
      String stockName, String createdAt, String type) {
    try {
      return jdbcTemplate.queryForObject(
          SQL_FIND_BUY_SELL_SUGGEST_WITH_TYPE,
          new Object[] {stockName, createdAt, type},
          new BuySellSuggestMapper());
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  // Get all the suggestion
  public List<BuySellSuggest> getAllSuggestion() {
    try {
      return jdbcTemplate.query(SQL_GET_ALL, new BuySellSuggestMapper());
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
  // Get ALL by the suggestion type
  public List<BuySellSuggest> getAllSuggestionsByType(String type) {
    try {
      return jdbcTemplate.query(
          SQL_GET_ALL_BY_TYPE, new Object[] {type}, new BuySellSuggestMapper());
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
  // Get ALL by the suggestion type
  public List<BuySellSuggest> getALLSuggestionByStockName(String name) {
    try {
      return jdbcTemplate.query(
          SQL_GET_ALL_SUGG_BY_NAME, new Object[] {name}, new BuySellSuggestMapper());
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
}
