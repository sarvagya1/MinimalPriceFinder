import org.example.model.CsvEntry;
import org.example.model.ShopIdMinimalPricePair;
import org.example.util.CsvHelper;
import org.example.util.MinimalPriceFetcher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MinimalPriceFinderTest {

    @Test
    public void GenerateCsvMapTest() {
        HashMap<String, CsvEntry> hashMap = CsvHelper.generateCsvMap("data3.csv", "scissor" , "bath_towel");
        assert hashMap.size() != 0;
    }

    @Test
    public void MinimalPriceFetcherTest() {
        HashMap<String, CsvEntry> hashMap = new HashMap<>();
        List<String> productList = new ArrayList<>();
        productList.add("scissor");
        productList.add("bath_towel");
        hashMap.put("1", new CsvEntry(12.00F, productList));
        hashMap.put("2", new CsvEntry(11.5F, productList));
        ShopIdMinimalPricePair<String, Float> kv_minimal = MinimalPriceFetcher.fetchResult(hashMap, "scissor" , "bath_towel");
        assert kv_minimal != null;
        assert kv_minimal.getTotalPrice() == 11.5F;
        assert kv_minimal.getShopId() == "2";
    }

}
