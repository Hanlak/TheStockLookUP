package com.stocklookup.controller;

import com.stocklookup.dao.BuySellGetterDao;
import com.stocklookup.models.BuySellSuggest;
import com.stocklookup.util.BuySellDecidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/v1/thestocker/getter")
public class StockerGetterController {

    @Autowired
    BuySellGetterDao buySellGetterDao;

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
        //DECIDES WHETHER IT CONTAINS SELL OR BUY OR BOTH OBJECTS
        String decidor = BuySellDecidor.buySellDecide(Collections.singletonList(buySellSuggest));
        //creating a model based on the list
        model = BuySellDecidor.createModel(model, Collections.singletonList(buySellSuggest), decidor);
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
        String decidor = BuySellDecidor.buySellDecide(Collections.singletonList(buySellSuggest));
        model = BuySellDecidor.createModel(model, Collections.singletonList(buySellSuggest), decidor);
        return "index";
    }

    // GET ALL STOCKS
    @GetMapping("/getallsuggestions")
    public String getALLSuggestion(Model model) {
        List<BuySellSuggest> suggestList = buySellGetterDao.getAllSuggestion();
        String decidor = BuySellDecidor.buySellDecide(suggestList);
        model = BuySellDecidor.createModel(model, suggestList, decidor);
        if (suggestList == null || suggestList.size() == 0)
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
        String decidor = BuySellDecidor.buySellDecide(suggestList);
        model = BuySellDecidor.createModel(model, suggestList, decidor);
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
        String decidor = BuySellDecidor.buySellDecide(suggestList);
        model = BuySellDecidor.createModel(model, suggestList, decidor);
        return "index";
    }
}
