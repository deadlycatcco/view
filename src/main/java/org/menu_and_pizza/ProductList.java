package org.menu_and_pizza;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private List<AbstractProduct> abstractProductList = new ArrayList<AbstractProduct>();

    void addToList(AbstractProduct abstractProduct) {
        abstractProductList.add(abstractProduct);
    }

    void removeFromList(AbstractProduct abstractProduct) {
        abstractProductList.remove(abstractProduct);
    }


}
