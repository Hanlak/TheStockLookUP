package com.stocklookup.util;

import com.stocklookup.Constants;
import com.stocklookup.models.BuySellSuggest;

public class SuggestionValidator {
  public static BuySellSuggest suggestionValidator(BuySellSuggest buySellSuggest) {
    // DO SOME VALIDATIONS
    if (buySellSuggest.getType().equals(Constants.SUGGESTION_TYPE.BUY.toString())) {
      buySellSuggest.setSellAt("0");
      buySellSuggest.setType("BUY");
      try {
        Integer check = Integer.parseInt(buySellSuggest.getTargetPrice());
        Integer check1 = Integer.parseInt(buySellSuggest.getStopLoss());
        String[] arrcheck = buySellSuggest.getBuyAt().split("<->");
        Integer check2 = Integer.parseInt(arrcheck[0]);
        Integer check3 = Integer.parseInt(arrcheck[1]);
      } catch (Exception e) {
        System.out.println("error in buy input form" + e.getMessage());
        buySellSuggest = null;
      }
      return buySellSuggest;
    } else if (buySellSuggest.getType().equals(Constants.SUGGESTION_TYPE.SELL.toString())) {
      buySellSuggest.setTargetPrice("0");
      buySellSuggest.setBuyAt("0");
      buySellSuggest.setStopLoss("0");
      buySellSuggest.setType("SELL");
      try {
        Integer check = Integer.parseInt(buySellSuggest.getSellAt());
      } catch (Exception e) {
        System.out.println("error in sell input form" + e.getMessage());
        buySellSuggest = null;
      }
      return buySellSuggest;
    } else return null;
  }
}
