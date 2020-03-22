package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountCalculator {
    private final List<Item> items;
    private List<Discount> discounts;

    DiscountCalculator(List<Item> items, List<Discount> discounts) {
        this.items = items;
        this.discounts = discounts;
    }

    public BigDecimal getAmount() {
        BigDecimal amount = BigDecimal.ZERO;
        for (Discount discount : discounts) {
            List<Item> discountedItems = items.stream().filter(discount.getItemMatcher()).collect(Collectors.toList());
            amount = amount.add(discount.getAmountCalculator().apply(discountedItems));
        }
        return amount;
    }
}
