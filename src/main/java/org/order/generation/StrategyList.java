package org.order.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StrategyList {

    List<IOrderStrategy> strategies;

    public StrategyList() {
        strategies = new ArrayList<>();
        strategies.add(new OneBeverageStrategy());
        strategies.add(new OnePizzaAndBeverageStrategy());
        strategies.add(new OnePizzaStrategy());
        strategies.add(new MultiplePizzaAndBeverageStrategy());
    }

    public IOrderStrategy getRandomStrategy() {
        return strategies.get(ThreadLocalRandom.current().nextInt(0, strategies.size()));
    }
}
