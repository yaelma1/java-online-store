
package store.products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum Category {
    BOOKS("BookProduct", List.of("author", "pages")), ELECTRONICS("ElectronicsProduct", List.of("warrant months", "brand")), CLOTHING("ClothingProduct", List.of("size"));

    private final String productType;
    private List<String> ExtraFields;

    Category(String productType, List<String> extraFields) {
        this.productType = productType;
        this.ExtraFields = extraFields;

    }

    public String getProductType() {
        return productType;
    }


    public List<String> getExtraFields(){
        return ExtraFields;
    }










}
