package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct extends AbstractProduct {

    private final BigDecimal pricePerKilo;

    public WeighedProduct(final BigDecimal pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    public WeighedProduct(final BigDecimal pricePerKilo, String productType) {
        this.pricePerKilo = pricePerKilo;
        this.productType = productType;
    }

    BigDecimal getPricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
