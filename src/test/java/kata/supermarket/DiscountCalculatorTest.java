package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.function.Predicate;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static kata.supermarket.Discounts.*;
import static org.junit.Assert.assertEquals;

class DiscountCalculatorTest {

    private final Product product = new Product(new BigDecimal("0.75"));
    private Predicate<Item> productPredicate = item -> item.getProduct().equals(product);

    private String productType = "Vegetables";
    private final WeighedProduct weighedProduct = new WeighedProduct(new BigDecimal("5.00"), productType);
    private Predicate<Item> weighedProductPredicate = item -> item instanceof ItemByWeight
            && ((ItemByWeight)item).getWeightInKilos().compareTo(new BigDecimal("1.0")) >= 0
            && productType.equals(item.getProduct().getProductType());

    private final Product otherProduct = new Product(new BigDecimal("0.75"));

    @Test
    void calculatesCorrectAmountWhenBuyOneGetOneFree() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                asList(product.oneOf(), product.oneOf(), otherProduct.oneOf()),
                singletonList(new Discount(productPredicate, TWO_FOR_ONE)));
        assertEquals(product.getPricePerUnit(), discountCalculator.getAmount());
    }

    @Test
    void calculatesCorrectAmountWhenTwoItemsForOnePound() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                asList(product.oneOf(), product.oneOf()),
                singletonList(new Discount(productPredicate, TWO_FOR_ONE_POUND)));
        assertEquals(new BigDecimal("0.50"), discountCalculator.getAmount());
    }

    @Test
    void calculatesCorrectAmountWhenThreeForTwo() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                asList(product.oneOf(), product.oneOf(), product.oneOf(), product.oneOf()),
                singletonList(new Discount(productPredicate, THREE_FOR_TWO)));
        assertEquals(product.getPricePerUnit(), discountCalculator.getAmount());
    }

    @Test
    void calculatesCorrectAmountWhenHalfPrice() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                asList(weighedProduct.weighing(new BigDecimal("3")), otherProduct.oneOf()),
                singletonList(new Discount(weighedProductPredicate, HALF_PRICE)));
        assertEquals(new BigDecimal("7.50"), discountCalculator.getAmount());
    }

    @Test
    void calculatesZeroWhenDifferentProductType() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                singletonList(new WeighedProduct(new BigDecimal("10.00"), "Fruit").weighing(new BigDecimal("10"))),
                singletonList(new Discount(weighedProductPredicate, HALF_PRICE)));
        assertEquals(new BigDecimal("0.00"), discountCalculator.getAmount());
    }

    @Test
    void calculatesZeroWhenNotHalfPrice() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                asList(weighedProduct.weighing(new BigDecimal("0.5")), otherProduct.oneOf()),
                singletonList(new Discount(weighedProductPredicate, HALF_PRICE)));
        assertEquals(new BigDecimal("0.00"), discountCalculator.getAmount());
    }

    @Test
    void calculatesZeroWhenNoDiscounts() {
        DiscountCalculator discountCalculator = new DiscountCalculator(
                singletonList(otherProduct.oneOf()),
                asList(new Discount(weighedProductPredicate, HALF_PRICE),
                    new Discount(productPredicate, TWO_FOR_ONE)));
        assertEquals(new BigDecimal("0.00"), discountCalculator.getAmount());
    }
}