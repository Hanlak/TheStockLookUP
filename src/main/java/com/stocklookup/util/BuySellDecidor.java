package com.stocklookup.util;

import com.google.gson.internal.$Gson$Preconditions;
import com.stocklookup.models.BuySellSuggest;

import java.util.ArrayList;
import java.util.List;
public class BuySellDecidor {
    public static String buySellDecide(List<BuySellSuggest> list){
        String decide = "NONE";
        List<String> buysell  = new ArrayList<>();
        for (BuySellSuggest bSS:list){
            buysell.add(bSS.getType());
        }
        if (buysell.contains("BUY") && buysell.contains("SELL")){
            decide = "BUYSELL";
        }else if (buysell.contains("BUY") && !buysell.contains("SELL")){
            decide = "BUY";
        }else if (!buysell.contains("BUY") && buysell.contains("SELL")){
            decide = "SELL";
        }
        return decide;
    }

  public static List<BuySellSuggest> buySellAdjustor(List<BuySellSuggest> list) {
        List<BuySellSuggest> buySellSuggests = new ArrayList<>();
        if (list!=null && list.size()>0){
    for (BuySellSuggest bs : list) {
      if (bs.getType().equals("BUY")) {
        if (bs.getStopLoss().equals("0")) {
          bs.setStopLoss("-");
        }
        if (bs.getTargetPrice().equals("0")) {
          bs.setTargetPrice("-");
        }
        bs.setSellAt("-");
          buySellSuggests.add(bs);
      }
      else if (bs.getType().equals("SELL")) {
        bs.setStopLoss("-");
        bs.setTargetPrice("-");
        bs.setBuyAt("-");
          buySellSuggests.add(bs);
      }
    }}
    return buySellSuggests;
        }
}
