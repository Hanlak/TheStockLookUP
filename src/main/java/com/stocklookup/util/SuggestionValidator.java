package com.stocklookup.util;

import com.stocklookup.Constants;
import com.stocklookup.models.BuySellSuggest;

public class SuggestionValidator {
  public static BuySellSuggest suggestionValidator(BuySellSuggest buySellSuggest) {
    // DO SOME VALIDATIONS
    if (buySellSuggest.getType().equals(Constants.SUGGESTION_TYPE.BUY.toString())) {
      buySellSuggest.setSellAt("0.00");
      buySellSuggest.setType("BUY");
      return buySellSuggest;
    } else if (buySellSuggest.getType().equals(Constants.SUGGESTION_TYPE.SELL.toString())) {
      buySellSuggest.setTargetPrice("0.00");
      buySellSuggest.setBuyAt("0.00");
      buySellSuggest.setStopLoss("0.00");
      buySellSuggest.setType("SELL");
      return buySellSuggest;
    } else return null;
  }
}
