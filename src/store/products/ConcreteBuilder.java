package store.products;

import java.awt.*;

public class ConcreteBuilder implements ProductBuilder {
    private Product product;

    public ConcreteBuilder(Product product) {
        this.product = product;
    }

    @Override
    public boolean setName(String name) {
        product.setName(name);
        return true;
    }

    @Override
    public boolean setPrice(double price) {
        product.setPrice(price);
        return true;
    }

    @Override
    public boolean setStock(int stock) {
        product.setStock(stock);
        return true;
    }

    @Override
    public boolean setDescription(String description) {
        product.setDescription(description);
        return true;
    }

    @Override
    public boolean setCategory(Category category) {
        product.setCategory(category);
        return true;
    }

    @Override
    public boolean setColor(Color color) {
        product.setColor(color);
        return true;
    }

    @Override
    public boolean setImageUrl(String imageUrl) {
        product.setImageUrl(imageUrl);
        return true;
    }

    @Override
    public Product build() {
        return product;
    }

}
