package org.order.generation;

import org.menu_and_pizza.AbstractProduct;
import java.util.List;

public interface IOrderStrategy {
     List<AbstractProduct> generate();
}
