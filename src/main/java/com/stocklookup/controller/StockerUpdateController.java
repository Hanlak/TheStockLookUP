package com.stocklookup.controller;

import com.stocklookup.dao.BuySellUpdateDao;
import com.stocklookup.exception.TypeValidationException;
import com.stocklookup.models.BuySellSuggest;
import com.stocklookup.util.SuggestionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/thestocker/updater")
public class StockerUpdateController {
  @Autowired BuySellUpdateDao buySellUpdateDao;

  @PostMapping("/updateSellSuggestion")
  public int updateSellSuggestion(@Valid @RequestBody BuySellSuggest buySellSuggest) {
    // TODO:: DISABLE STOCK NAME CHANGE AND DATE AND TYPE CHANGE IN THE EDIT PAGE
    buySellSuggest = SuggestionValidator.suggestionValidator(buySellSuggest);
    if (buySellSuggest == null)
      throw new TypeValidationException("INVALID SUGGESTION TYPE. PLEASE USE BUY OR SELL");

    boolean check = buySellUpdateDao.updateBuySellSuggestByNameAndCreateDate(buySellSuggest);

    return check ? HttpStatus.OK.value() : HttpStatus.INTERNAL_SERVER_ERROR.value();
  }
}
