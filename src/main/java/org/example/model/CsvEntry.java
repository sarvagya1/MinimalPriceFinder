package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Object to hold info i.e. <total price of items, list of items> for particular ShopId.
 */
@Data
@AllArgsConstructor
public class CsvEntry {
    Float priceOfItems;
    List<String> items;

}
