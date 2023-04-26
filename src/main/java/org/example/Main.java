package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.util.CsvHelper;
import org.example.model.CsvEntry;
import org.example.model.ShopIdMinimalPricePair;
import org.example.util.MinimalPriceFetcher;

import java.util.HashMap;

/**
 * @author Sarvagya Surawat
 * Java program to fetch minimal price of baby products user want to purchase corresponding to ShopId.
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
            log.debug("Start processing file to get shop id and minimal price for product needed by user");
            Main.processCsv(args[0], args[1], args[2]);
            log.debug("Done processing file");
    }

    private static void processCsv(String fileName, String productLabel1, String productLabel2) {

        HashMap<String, CsvEntry> csvMap =
                CsvHelper.generateCsvMap(fileName, productLabel1, productLabel2);

        if (csvMap == null) {
            log.warn("Error while processing csv file... exiting");
        } else if (csvMap.isEmpty()) {
            log.warn("Could not find minimal price since csvMap is empty... exiting");
        } else {
            ShopIdMinimalPricePair<String, Float> kv_minimal =
                    MinimalPriceFetcher.fetchResult(csvMap, productLabel1, productLabel2);

            if (kv_minimal == null) {// no pairs found
                log.info("none");
            } else {
                log.info("{}, {}", kv_minimal.getShopId(), kv_minimal.getTotalPrice());
            }
        }
    }
}