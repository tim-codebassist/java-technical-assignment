package kata.supermarket;

import java.math.BigDecimal;

public class Product extends AbstractProduct {

    private final BigDecimal pricePerUnit;

    public Product(final BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Product(final BigDecimal pricePerUnit, String productType) {
        this.pricePerUnit = pricePerUnit;
        this.productType = productType;
    }

    BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
