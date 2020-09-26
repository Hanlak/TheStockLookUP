package com.stocklookup.util;

import com.google.gson.internal.$Gson$Preconditions;
import com.stocklookup.models.BuySellSuggest;
import org.springframework.ui.Model;

import java.util.*;

public class BuySellDecidor {
    public static Model createModel(Model model,List<BuySellSuggest> buySellSuggest , String decidor){
        if (decidor.equals("BUYSELL")) {
            // PASS IT TO SEPARATOR
            Map<String, List<BuySellSuggest>> map =
                    BuySellDecidor.buySellSeperator(buySellSuggest);
            List<BuySellSuggest> sellSuggests = BuySellDecidor.buySellAdjustor(BuySellDecidor.buySellAdjustor(map.get("SELL")));
            List<BuySellSuggest> buySuggests = BuySellDecidor.buySellAdjustor(BuySellDecidor.buySellAdjustor(map.get("BUY")));

            model.addAttribute("decide",decidor);
            model.addAttribute("sellList",sellSuggests);
            model.addAttribute("buyList",buySuggests);
        }else if (decidor.equals("BUY")){
            //ADJUST THE MODEL FOR UI.
            List<BuySellSuggest> buySuggestList = BuySellDecidor.buySellAdjustor(buySellSuggest);
            model.addAttribute("decide",decidor);
            model.addAttribute("buyList", buySuggestList);
            model.addAttribute("sellList", new ArrayList<>());
        } else if (decidor.equals("SELL")){
            //ADJUST THE MODEL FOR UI.
            List<BuySellSuggest> SellSuggestList = BuySellDecidor.buySellAdjustor(buySellSuggest);
            model.addAttribute("decide",decidor);
            model.addAttribute("sellList", SellSuggestList);
            model.addAttribute("buyList", new ArrayList<>());
        }else {
            model.addAttribute("decide","NONE");
        }
        return model;
    }
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

    public static Map<String,List<BuySellSuggest>> buySellSeperator(List<BuySellSuggest> buySellSuggestList){
        Map<String,List<BuySellSuggest>> map = new HashMap<>();
        List<BuySellSuggest> sellList = new ArrayList<>();
        List<BuySellSuggest> buyList = new ArrayList<>();
        for (BuySellSuggest buysell:buySellSuggestList){
            if (buysell.getType().equals("BUY")){
                buyList.add(buysell);
            }else if (buysell.getType().equals("SELL")){
                sellList.add(buysell);
            }
        }
        map.put("BUY",buyList);
        map.put("SELL",sellList);
        return map;
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
