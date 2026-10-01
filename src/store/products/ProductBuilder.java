package store.products;

import java.awt.*;

public interface ProductBuilder {
    boolean setName(String name);
    boolean setPrice(double price);
    boolean setStock(int stock);
    boolean setDescription(String description);
    boolean setCategory(Category category);
    boolean setColor(Color color);
    boolean setImageUrl(String imageUrl);
    Product build();
}
