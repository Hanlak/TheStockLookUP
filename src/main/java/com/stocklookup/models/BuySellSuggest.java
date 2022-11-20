package com.stocklookup.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuySellSuggest {
    // @ApiModelProperty(notes = "stock name")
    private String stockName;

    // @ApiModelProperty(notes = "the rate the stock should buy and we can buy <= suggested rate")
    private String buyAt;

    // @ApiModelProperty(notes = "In Case of Buy at a target we can suggest stop loss as well")
    private String stopLoss;

    // @ApiModelProperty(notes = "the rate at which the stock should sell")
    private String sellAt;

    // @ApiModelProperty(notes = "Its a suggestion that the stock price will reach too in near or far
    // future")
    private String targetPrice;

    // @ApiModelProperty(notes = "suggestion type - SELL / BUY")
    private String type;

    // @ApiModelProperty(notes = "this is a reference for the creation date of the suggestion")
    private String createdAt;
}
