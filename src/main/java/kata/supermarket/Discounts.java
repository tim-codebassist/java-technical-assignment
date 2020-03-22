package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;

public class Discounts {

    //TODO create a builder for building these

    public static final Function<List<Item>, BigDecimal> TWO_FOR_ONE =
            discountedItems -> {
                if (discountedItems.size() > 0) {
                    BigDecimal price = discountedItems.get(0).price();
                    return price.multiply(new BigDecimal(discountedItems.size() / 2));
                }
                else return BigDecimal.ZERO;
            };

    public static final Function<List<Item>, BigDecimal> TWO_FOR_ONE_POUND =
            discountedItems -> {
                if (discountedItems.size() > 0) {
                    BigDecimal price = discountedItems.get(0).price();
                    int numberOfPairs = discountedItems.size() / 2;
                    BigDecimal discountPerPair = price.multiply(new BigDecimal(2)).subtract(new BigDecimal("1.00"));
                    return discountPerPair.multiply(new BigDecimal(numberOfPairs));
                }
                else return BigDecimal.ZERO;
            };

    public static final Function<List<Item>, BigDecimal> THREE_FOR_TWO =
            discountedItems -> {
                if (discountedItems.size() > 0) {
                    BigDecimal price = discountedItems.get(0).price();
                    return price.multiply(new BigDecimal(discountedItems.size() / 3));
                }
                else return BigDecimal.ZERO;
            };

    public static final Function<List<Item>, BigDecimal> HALF_PRICE =
            discountedItems -> discountedItems.stream().map(
                    item -> item.price().multiply(new BigDecimal("0.5")))
                    .reduce(BigDecimal::add).orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
}
