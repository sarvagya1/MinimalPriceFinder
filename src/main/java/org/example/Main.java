package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.InvalidArgumentException;
import org.example.exception.InvalidFileFormatException;
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
        if (args.length == 3) {
            try {
                Main.processCsv(args[0], args[1], args[2]);
            } catch (InvalidArgumentException fe) {
                log.error(fe.getMessage());
            }
        } else {
            log.error("Argument length should be equal to 3 only.. exiting");
        }
    }

    private static void processCsv(String fileName, String productLabel1, String productLabel2) throws InvalidArgumentException {
        if (!fileName.endsWith(".csv")) {
            throw new InvalidArgumentException("Invalid File extension : " + fileName);
        }

        if (!productLabel1.matches("^[a-z]+(_[a-z]+)*$") || !productLabel2.matches("^[a-z]+(_[a-z]+)*$")) {
            throw new InvalidArgumentException("Invalid product label; All products should be lower case letters and underscores only");
        }

        HashMap<String, CsvEntry> csvMap =
                CsvHelper.generateCsvMap(fileName, productLabel1, productLabel2);

        if (csvMap == null) {
            log.warn("Error while processing csv file... exiting");
        } else if (csvMap.isEmpty()) {
            log.warn("User needed product not match with product label available in file");
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