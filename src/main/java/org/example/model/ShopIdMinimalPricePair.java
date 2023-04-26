package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sarvagya Surawat
 * Object will carry info related to minimal price pair <shop id, total price>
 * @param <K> : shop id
 * @param <V> : total price
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopIdMinimalPricePair<K, V> {
    private K shopId;
    private V totalPrice;

}
