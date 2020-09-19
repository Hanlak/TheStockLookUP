package com.stocklookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sample Payload:
 *
 * <p>SELL: { "stockName":"test", "buyAt":"0.00", "stopLoss":"0.00", "sellAt":"122.00",
 * "targetPrice":"0.000","createdAt":"2020-08-10", "type":"SELL" }
 *
 * <p>BUY: { "stockName":"test", "buyAt":"1122", "stopLoss":"0.5", "sellAt":"0.00",
 * "targetPrice":"1444", "createdAt":"2020-08-10", "type":"BUY" }
 */
@SpringBootApplication(scanBasePackages = {"com.stocklookup"})
public class TheStockerMainApp {

  public static void main(String[] args) {
    SpringApplication.run(TheStockerMainApp.class, args);
  }
}
