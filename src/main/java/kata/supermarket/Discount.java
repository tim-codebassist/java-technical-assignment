package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Discount {

    private Predicate<Item> itemMatcher;
    private Function<List<Item>, BigDecimal> amountCalculator;

    public Discount(Predicate<Item> itemMatcher, Function<List<Item>, BigDecimal> amountCalculator) {
        this.itemMatcher = itemMatcher;
        this.amountCalculator = amountCalculator;
    }

    public Predicate<Item> getItemMatcher() {
        return itemMatcher;
    }

    public Function<List<Item>, BigDecimal> getAmountCalculator() {
        return amountCalculator;
    }
}
