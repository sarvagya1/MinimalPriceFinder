package org.example.util;

import lombok.extern.slf4j.Slf4j;
import org.example.model.CsvEntry;
import org.example.model.ShopIdMinimalPricePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sarvagya Surawat
 * Utility class to fetch minimal price corresponding to shop id.
 */
@Slf4j
public class MinimalPriceFetcher {

    /**
     * Function to get minimal price along with ShopId
     * @param csvMap : Map generated by CsvHelper Utility
     * @param arg1 : product label 1
     * @param arg2 : product label 2
     * @return ShopIdMinimalPricePair<ShopId, TotalPrice>
     */
    public static ShopIdMinimalPricePair<String, Float> fetchResult(HashMap<String, CsvEntry> csvMap, String arg1, String arg2) {
        ShopIdMinimalPricePair<String, Float> shopIdMinimalPricePair = null;

        if (!csvMap.isEmpty()) {
            for (Map.Entry entrySet : csvMap.entrySet()) {
                String id = (String) entrySet.getKey();
                CsvEntry csvEntry = (CsvEntry) entrySet.getValue();
                List<String> listItems = csvEntry.getItems();

                // check if contains any of the product item
                if (listItems.contains(arg1) && listItems.contains(arg2)) {
                    if (shopIdMinimalPricePair == null) { // initialize first entry
                        shopIdMinimalPricePair = new ShopIdMinimalPricePair<>(id, csvEntry.getPriceOfItems());
                    } else if (shopIdMinimalPricePair.getTotalPrice() > csvEntry.getPriceOfItems()) {
                        // find minimal price by comparison and update pair
                        shopIdMinimalPricePair.setShopId(id);
                        shopIdMinimalPricePair.setTotalPrice(csvEntry.getPriceOfItems());
                    }
                }
            }
        } else {
            log.warn("CsvMap is empty");
            return null;
        }
        return shopIdMinimalPricePair;
    }
}
