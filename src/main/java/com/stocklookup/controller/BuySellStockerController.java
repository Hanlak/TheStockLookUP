package com.stocklookup.controller;

import com.stocklookup.dao.BuySellDao;
import com.stocklookup.dao.BuySellGetterDao;
import com.stocklookup.models.BuySellSuggest;
import com.stocklookup.util.SuggestionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/thestocker")
public class BuySellStockerController {

    /**
     * Sample Payload:
     *
     * <p>SELL: { "stockName":"test", "buyAt":"0", "stopLoss":"0", "sellAt":"122",
     * "targetPrice":"0","createdAt":"2020-08-10", "type":"SELL" }
     *
     * <p>BUY: { "stockName":"test", "buyAt":"1122", "stopLoss":"220", "sellAt":"0",
     * "targetPrice":"1444", "createdAt":"2020-08-10", "type":"BUY" }
     */
    @Autowired
    BuySellDao buySellDao;

    @Autowired
    BuySellGetterDao buySellGetterDao;

    @GetMapping("/suggestion")
    public String getSuggestionView(Model model) {
        return "suggestionadd";
    }

    // To ADD the BUY SUGGESTION
    @PostMapping("/addsuggestion")
    public String addBuyPrediction(
            Model model,
            @RequestParam("stockName") String stockName,
            @RequestParam("buyAt") String buyAt,
            @RequestParam("stopLoss") String stopLoss,
            @RequestParam("sellAt") String sellAt,
            @RequestParam("targetPrice") String targetPrice,
            @RequestParam("type") String type,
            @RequestParam("authToken") String authToken) {

        //Check the admin user is authorized or not
        if (!"stocker".equals(authToken)) {
            model.addAttribute("info", "you are not authorized to add the suggestion. please contact the admin");
            return "suggestionadd";
        }
        // Since its a buy Suggesstion we have to ensure that the Sell values stays empty and type is
        // BUY
        BuySellSuggest buySellSuggest = new BuySellSuggest();
        buySellSuggest.setStockName(stockName);
        buySellSuggest.setSellAt(sellAt);
        buySellSuggest.setStopLoss(stopLoss);
        buySellSuggest.setType(type);
        buySellSuggest.setTargetPrice(targetPrice);
        buySellSuggest.setBuyAt(buyAt);
        buySellSuggest.setCreatedAt(LocalDate.now().toString());
        buySellSuggest = SuggestionValidator.suggestionValidator(buySellSuggest);
        if (buySellSuggest == null) {
            model.addAttribute("info", "input not valid. please give valid inputs");
            return "suggestionadd";
        }
        BuySellSuggest suggestionAlreadyExist =
                buySellGetterDao.getBuySellSuggestByNameAndCreateDate(
                        buySellSuggest.getStockName(), buySellSuggest.getCreatedAt());
        if (suggestionAlreadyExist != null) {
            model.addAttribute("info", "suggestion already exists");
            return "suggestionadd";
        } else {
            // BY Default we will be assigning the date when creating the suggestion
            buySellSuggest.setCreatedAt(LocalDate.now().toString());
            boolean check = buySellDao.save(buySellSuggest);
            if (check) {
                model.addAttribute("info", "suggestion addon successful. Thanks You :) ");
            } else {
                model.addAttribute("info", "suggestion addon failed. Please Try :( ");
            }
            return "suggestionadd";
        }
    }
}
