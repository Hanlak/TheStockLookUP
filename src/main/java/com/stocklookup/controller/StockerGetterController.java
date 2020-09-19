package com.stocklookup.controller;

import com.stocklookup.dao.BuySellGetterDao;
import com.stocklookup.models.BuySellSuggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/v1/thestocker/getter")
public class StockerGetterController {

  @Autowired BuySellGetterDao buySellGetterDao;

  // GET STOCK DETAILS BY NAME AND DATE
  @PostMapping("/getbynameanddate")
  public String getBuySellSuggestByNameAndDate(
      Model model,
      @RequestParam("stockName") String stockName,
      @RequestParam("createdAt") String createdAt) {

    // TODO:: check the strings
    BuySellSuggest buySellSuggest =
        buySellGetterDao.getBuySellSuggestByNameAndCreateDate(stockName, createdAt);
    if (buySellSuggest == null) {
      model.addAttribute(
          "error",
          String.format("STOCK:: %s NOT FOUND WITHIN THIS DATE:: %s ", stockName, createdAt));
      return "index";
    }
    model.addAttribute("suggestList", Arrays.asList(buySellSuggest));
    return "index";
  }

  // GET STOCK DETAILS BY NAME, DATE AND TYPE
  @PostMapping("/getbynameanddateandtype")
  public String getBuySellSuggestByNameDateAndType(
      Model model,
      @RequestParam("stockName") String stockName,
      @RequestParam("createdAt") String createdAt,
      @RequestParam("type") String type) {
    BuySellSuggest buySellSuggest =
        buySellGetterDao.getBuySellSuggestByNameAndCreateDateAndType(stockName, createdAt, type);
    if (buySellSuggest == null) {
      model.addAttribute(
          "error",
          String.format(
              "STOCK:: %s NOT FOUND WITHIN THIS DATE %s AND TYPE:: %s",
              stockName, createdAt, type));
      return "index";
    }
    model.addAttribute("suggestList", Arrays.asList(buySellSuggest));
    return "index";
  }

  // GET ALL STOCKS
  @GetMapping("/getallsuggestions")
  public String getALLSuggestion(Model model) {
    List<BuySellSuggest> suggestList = buySellGetterDao.getAllSuggestion();
    model.addAttribute("suggestList", suggestList);
    if (suggestList == null)
      model.addAttribute("error", "SUGGESTION ARE EMPTY. PlEASE ADD SUGGESTIONS BEFORE QUERYING");
    return "index";
  }

  // Get ALL Stocks BY Name
  @PostMapping("/getallsuggestionsbyname")
  public String getALLSuggestion(Model model, @RequestParam("stockName") String stockname) {
    List<BuySellSuggest> suggestList = buySellGetterDao.getALLSuggestionByStockName(stockname);
    if (suggestList == null) {
      model.addAttribute(
          "error", String.format("NO SUGGESTIONS FOUND FOR THE STOCK:: %s", stockname));
      return "index";
    }
    model.addAttribute("suggestList", suggestList);
    return "index";
  }

  // Get ALL STOCKS BY TYPE
  @PostMapping("/getallsuggesstionsbytype")
  public String getALLSuggestionByType(Model model, @RequestParam("type") String type) {
    List<BuySellSuggest> suggestList = buySellGetterDao.getAllSuggestionsByType(type);
    if (suggestList == null) {
      model.addAttribute(
          "error", String.format("NO SUGGESTIONS FOUND FOR THE SUGGESTION TYPE:: %s", type));
      return "index";
    }
    model.addAttribute("suggestList", suggestList);
    return "index";
  }
}
