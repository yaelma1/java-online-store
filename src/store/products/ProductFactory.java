
package store.products;

import java.awt.*;

/**
 * Factory class for creating products based on their type.
 */
public class ProductFactory {

    /**
     * Creates a product based on the provided arguments.
     * @param args
     * @return
     */
    public static Product create(String[] args){
        for (int i = 0; i < args.length; i++) {
            System.out.println(i + " -> " + args[i]);
        }

        String type = args[0];

        Product p;
        if (type.equals("BookProduct"))
            p = new BookProduct();
        else if (type.equals("ClothingProduct"))
            p = new ClothingProduct();
        else if (type.equals("ElectronicsProduct"))
            p = new ElectronicsProduct();
        else throw new IllegalArgumentException();

        ConcreteBuilder builder = new ConcreteBuilder(p);

        builder.setName(args[1]);
        builder.setPrice(Double.parseDouble(args[2]));
        builder.setStock(Integer.parseInt(args[3]));
        builder.setDescription(args[4]);
        builder.setCategory(Category.valueOf(args[5]));
        builder.setColor(new Color(Integer.parseInt(args[6])));
        builder.setImageUrl(args[7]);

        if (p instanceof BookProduct){
            ((BookProduct)p).setAuthor(args[8]);
            ((BookProduct)p).setPages(Integer.parseInt(args[9]));
        }
        else if (p instanceof ClothingProduct)
            ((ClothingProduct)p).setSize(Integer.parseInt(args[8]));

        else if (p instanceof ElectronicsProduct){
            ((ElectronicsProduct)p).setBrand(args[9]);               // ✔ Lenovo
            ((ElectronicsProduct)p).setWarranty(Integer.parseInt(args[8])); // ✔ 24
        }

        return builder.build();
    }

}

