package nguyennmk.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import nguyennmk.product.ProductDTO;

/**
 *
 * @author nguyen
 */
public class CartObject implements Serializable {

    private Map<ProductDTO, Integer> items;

    public Map<ProductDTO, Integer> getItems() {
        return items;
    }

    public boolean addItemToCart(ProductDTO product) {
        boolean result = false;

        // 1. Check if product is null
        if (product == null) {
            return result;
        }

        // 2. Check if items map exists, if not, create a new one
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        // 3. Check if the product already exists in the cart
        if (this.items.containsKey(product)) {
            // If the product exists, increment the quantity
            int quantity = this.items.get(product) + 1;
            this.items.put(product, quantity);
        } else {
            // If the product doesn't exist, add it to the cart with quantity 1
            this.items.put(product, 1);
        }

        result = true;
        return result;
    }

    public boolean removeItemFromCart(ProductDTO product) {
        boolean result = false;

        // 1. Check if items map exists
        if (this.items != null) {
            // 2. Check if the product exists in the cart
            if (this.items.containsKey(product)) {
                this.items.remove(product);
                if (this.items.isEmpty()) {
                    this.items = null;
                }
                result = true;
            }
        }

        return result;
    }
}
