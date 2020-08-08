package com.stocklookup.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stocklookup.dao.BuySellGetterDao;
import com.stocklookup.exception.NoResultSetException;
import com.stocklookup.models.BuySellSuggest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/thestocker/getter")
public class StockerGetterController {

  @Autowired BuySellGetterDao buySellGetterDao;

  // GET STOCK DETAILS BY NAME AND DATE
  @PostMapping("/getbynameanddate")
  @ApiOperation(value = "get the suggestion using name and suggestion created date")
  public BuySellSuggest getBuySellSuggestByNameAndDate(@RequestBody String body) {
    JsonObject jsonObject = new Gson().fromJson(body, JsonObject.class);
    String stockName = jsonObject.get("stockName").getAsString();
    String createdAt = jsonObject.get("createdAt").getAsString();
    BuySellSuggest buySellSuggest =
        buySellGetterDao.getBuySellSuggestByNameAndCreateDate(stockName, createdAt);
    if (buySellSuggest == null)
      throw new NoResultSetException(
          String.format("STOCK:: %s NOT FOUND WITHIN THIS DATE:: %s ", stockName, createdAt));
    return buySellSuggest;
  }

  // GET STOCK DETAILS BY NAME, DATE AND TYPE
  @PostMapping("/getbynameanddateandtype")
  @ApiOperation(
      value =
          "get the suggestion using name and suggestion created date and suggestion type (BUY or SELL)")
  public BuySellSuggest getBuySellSuggestByNameDateAndType(@RequestBody String body) {
    JsonObject jsonObject = new Gson().fromJson(body, JsonObject.class);
    String stockName = jsonObject.get("stockName").getAsString();
    String createdAt = jsonObject.get("createdAt").getAsString();
    String type = jsonObject.get("type").getAsString();
    BuySellSuggest buySellSuggest =
        buySellGetterDao.getBuySellSuggestByNameAndCreateDateAndType(stockName, createdAt, type);
    if (buySellSuggest == null)
      throw new NoResultSetException(
          String.format(
              "STOCK:: %s NOT FOUND WITHIN THIS DATE %s AND TYPE:: %s",
              stockName, createdAt, type));
    return buySellSuggest;
  }

  // GET ALL STOCKS
  @GetMapping("/getallsuggestions")
  @ApiOperation(value = "get list of all available suggestions")
  public List<BuySellSuggest> getALLSuggestion() {
    List<BuySellSuggest> suggestList = buySellGetterDao.getAllSuggestion();
    if (suggestList == null)
      throw new NoResultSetException(
          "SUGGESTION ARE EMPTY. PlEASE ADD SUGGESTIONS BEFORE QUERYING");
    return suggestList;
  }

  // Get ALL Stocks BY Name
  @GetMapping("/getallsuggestionsbyname/{name}")
  @ApiOperation(value = "get list of all available suggestions using name of the stock")
  public List<BuySellSuggest> getALLSuggestion(@PathVariable("name") String stockname) {
    List<BuySellSuggest> suggestList = buySellGetterDao.getALLSuggestionByStockName(stockname);
    if (suggestList == null)
      throw new NoResultSetException(
          String.format("NO SUGGESTIONS FOUND FOR THE STOCK:: %s", stockname));
    return suggestList;
  }

  // Get ALL STOCKS BY TYPE
  @GetMapping("/getallsuggesstionsbytype/{type}")
  @ApiOperation(value = "get list of all available suggestions using type of order BUY or SELL")
  public List<BuySellSuggest> getALLSuggestionByType(@PathVariable("type") String type) {
    List<BuySellSuggest> suggestList = buySellGetterDao.getAllSuggestionsByType(type);
    if (suggestList == null)
      throw new NoResultSetException(
          String.format("NO SUGGESTIONS FOUND FOR THE SUGGESTION TYPE:: %s", type));
    return suggestList;
  }
}
