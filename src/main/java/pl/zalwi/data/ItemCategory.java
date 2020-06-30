package pl.zalwi.data;

import java.util.ArrayList;
import java.util.List;

public enum ItemCategory {

    GROCERIES("spozywcze", "Artykuły spożywcze"),
    HOUSEHOLDS("domowe", "Artykuły gospodarstwa domowego"),
    OTHERS("inne", "Inne");

    private String shortDescription;
    private String fullDescription;

    ItemCategory(String shortDescription, String fullDescription) {
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
    }

    public static String getFullDescription(String shortDescription) {
        if (shortDescription == null)
            throw new IllegalArgumentException();
        for (ItemCategory itemCat : values())
            if (shortDescription.equalsIgnoreCase(itemCat.getShortDescription())) return itemCat.getFullDescription();
        throw new IllegalArgumentException();
    }

    public static ItemCategory getItemCategoryByShortDescription(String shortDescription) {
        if (shortDescription == null)
            throw new IllegalArgumentException();
        for (ItemCategory itemCat : values())
            if (shortDescription.equalsIgnoreCase(itemCat.getShortDescription())) return itemCat;
        throw new IllegalArgumentException();
    }

    public List<String> getListOfCategoriesDescription() {
        List<String> listOfFullDescriptions = new ArrayList<String>();
        for (ItemCategory category : ItemCategory.values()) {
            listOfFullDescriptions.add(category.fullDescription);
        }
        return listOfFullDescriptions;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }
}
