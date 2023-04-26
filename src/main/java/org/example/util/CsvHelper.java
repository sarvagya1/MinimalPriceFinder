package org.example.util;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.InvalidFileFormatException;
import org.example.model.CsvEntry;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Sarvagya Surawat
 * Utility class to process csv, validate file content and generate csvMap
 */
@Slf4j
public class CsvHelper {

    /**
     * Function to process CSV file and generate csvMap
     * @param csvFile : csv file name
     * @param arg1 : product label 1
     * @param arg2 : product label 2
     * @return csvMap : {ShopId, CsvEntry(priceOfItems, ListOfItems)}
     * csvMap contains price of all matching product label user needed along with total price
     */
    public static HashMap<String, CsvEntry> generateCsvMap(String csvFile, String arg1, String arg2) {

        HashMap<String, CsvEntry> csvMap = new HashMap<>();
        InputStream inputStream = CsvHelper.class.getClassLoader().getResourceAsStream(csvFile);

        try (InputStreamReader streamReader =
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            reader.readLine(); //Ignoring first line as header

            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String csvLineWithoutSpace = csvLine.replace(" ", "");

                String shopId = csvLineWithoutSpace.substring(0, csvLineWithoutSpace.indexOf(','));
                try {
                    Integer.parseInt(shopId);
                } catch (NumberFormatException e) {
                    throw new InvalidFileFormatException("Invalid csv file format : ShopId is not integer : " + shopId);
                }

                String priceColumn = csvLineWithoutSpace.substring(csvLineWithoutSpace.indexOf(',') + 1, csvLineWithoutSpace.length());
                String price = priceColumn.substring(0, priceColumn.indexOf(','));
                try {
                    Float.parseFloat(price);
                } catch (NumberFormatException e) {
                    throw new InvalidFileFormatException("Invalid csv file format : price is not decimal : " + price);
                }

                String productLabel = priceColumn.substring(priceColumn.indexOf(',') + 1, priceColumn.length());
                List<String> productLabelList = new ArrayList<>(Arrays.asList(productLabel.split(",")));
                if (productLabelList.isEmpty()) {
                    throw new InvalidFileFormatException("Invalid csv file format : product label not present");
                } else {
                    for (String item : productLabelList) {
                        // product label should only contain lower case characters and underscores
                        if (!item.matches("^[a-z]+(_[a-z]+)*$")) {
                            throw new InvalidFileFormatException("Invalid csv file format : product label is invalid : " + item);
                        }
                    }
                }

                /** If product label list contain any product needed by user
                     1) Add that product to hashmap if ShopId not present along with price and product label
                     2) if ShopId already present, update product list by adding product and
                     add price of that product to total price.
                 */
                if (productLabelList.contains(arg1) || productLabelList.contains(arg2)) {
                    Float newPrice = Float.parseFloat(price);
                    if (csvMap.containsKey(shopId)) {
                        Float previousPrice = csvMap.get(shopId).getPriceOfItems();
                        List<String> listOfItems = csvMap.get(shopId).getItems();
                        listOfItems.addAll(productLabelList);
                        csvMap.put(shopId, new CsvEntry(previousPrice + newPrice, listOfItems));
                    } else {
                        csvMap.put(shopId, new CsvEntry(newPrice, productLabelList));
                    }
                }
            }
        }
        catch (InvalidFileFormatException ie) {
            log.error("Invalid csv file format : {}", ie.getMessage());
            return null;
        } catch (Exception e) {
            log.error("Exception while processing csv file : {}", e.getMessage());
            return null;
        }
        return csvMap;
    }

}
