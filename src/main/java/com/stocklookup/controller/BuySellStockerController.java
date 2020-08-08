package com.stocklookup.controller;

import com.stocklookup.dao.BuySellDao;
import com.stocklookup.dao.BuySellGetterDao;
import com.stocklookup.exception.NoResultSetException;
import com.stocklookup.exception.TypeValidationException;
import com.stocklookup.models.BuySellSuggest;
import com.stocklookup.util.SuggestionValidator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/v1/thestocker")
public class BuySellStockerController {

  /**
   * Sample Payload:
   *
   * <p>SELL: { "stockName":"test", "buyAt":"0.00", "stopLoss":"0.00", "sellAt":"122.00",
   * "targetPrice":"0.000","createdAt":"2020-08-10", "type":"SELL" }
   *
   * <p>BUY: { "stockName":"test", "buyAt":"1122", "stopLoss":"0.5", "sellAt":"0.00",
   * "targetPrice":"1444", "createdAt":"2020-08-10", "type":"BUY" }
   */
  @Autowired BuySellDao buySellDao;

  @Autowired BuySellGetterDao buySellGetterDao;

  // To ADD the BUY SUGGESTION
  @PostMapping("/buysell/addsuggestion")
  @ApiOperation(value = "add a suggestion")
  @ResponseBody()
  public int addBuyPrediction(@Valid @RequestBody BuySellSuggest buySellSuggest) {
    // Since its a buy Suggesstion we have to ensure that the Sell values stays empty and type is
    // BUY
    buySellSuggest = SuggestionValidator.suggestionValidator(buySellSuggest);
    if (buySellSuggest == null)
      throw new TypeValidationException("INVALID SUGGESTION TYPE. PLEASE USE VALID TYPE(SELL|BUY)");
    BuySellSuggest suggestionAlreadyExist =
        buySellGetterDao.getBuySellSuggestByNameAndCreateDate(
            buySellSuggest.getStockName(), buySellSuggest.getCreatedAt());
    if (suggestionAlreadyExist != null) throw new NoResultSetException("suggestion already exists");
    else {
      // BY Default we will be assigning the date when creating the suggestion
      buySellSuggest.setCreatedAt(LocalDate.now().toString());
      return buySellDao.save(buySellSuggest)
          ? HttpStatus.OK.value()
          : HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
  }
}
