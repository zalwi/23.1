package pl.zalwi.data;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {

    List<Item> itemList;

    public ItemRepository(){
        itemList = new ArrayList<Item>();
        //spozywcze
        itemList.add(new Item("Marchewka", ItemCategory.GROCERIES, new BigDecimal("2.50")));
        itemList.add(new Item("Pietruszka", ItemCategory.GROCERIES, new BigDecimal("3.70")));
        itemList.add(new Item("Pomidor", ItemCategory.GROCERIES, new BigDecimal("4.90")));
        //domowe
        itemList.add(new Item("Płyn do mycia naczyń", ItemCategory.HOUSEHOLDS, new BigDecimal("11.30")));
        itemList.add(new Item("Gąbka do mycia", ItemCategory.HOUSEHOLDS, new BigDecimal("7.10")));
        itemList.add(new Item("Kostki do zmywarki", ItemCategory.HOUSEHOLDS, new BigDecimal("35.49")));
        //inne
        itemList.add(new Item("Zapalniczka", ItemCategory.OTHERS, new BigDecimal("1.30")));
        itemList.add(new Item("Parasol", ItemCategory.OTHERS, new BigDecimal("19.99")));
        itemList.add(new Item("Płaszcz przeciwdeszczowy", ItemCategory.OTHERS, new BigDecimal("9.99")));
    }

    public BigDecimal totalPriceOfItemsInCategory(String shortCategoryDescription){
        return itemList.stream()
                        .filter(item -> item.getCategory().getShortDescription().equals(shortCategoryDescription))
                        .map(Item::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalPriceOfAllItems(){
        return itemList.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Item> getListOfItemInCategory(String shortCategoryDescription){
        return itemList.stream()
                .filter(item -> item.getCategory().getShortDescription().equals(shortCategoryDescription))
                        .collect(Collectors.toList());
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void addItem(Item item){
        itemList.add(item);
    }
}
