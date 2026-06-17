import java.util.List;

abstract class TransactionPriceStrategyDecorator implements TransactionPriceStrategy {
    protected final TransactionPriceStrategy wrapped;

    public TransactionPriceStrategyDecorator(TransactionPriceStrategy wrapped) {
        this.wrapped = wrapped;
    }
}
// CASE 2: $5 OFF COUPON CALCULATION
// Program gets the price from the default price calculation, then performs this decorator's operations.
// If cart.size is 5 or more, reduce the carts price by $5 with a coupon.
class FiveRentalDiscountDecorator extends TransactionPriceStrategyDecorator {
    public FiveRentalDiscountDecorator(TransactionPriceStrategy wrapped) {
        super(wrapped);
    }

    @Override
    public double calculateTotal(List<Rental> cart) {
        // Performs the default price calculation
        double total = wrapped.calculateTotal(cart);
        // Then does the check to see if the coupon can be applied for this transaction.
        if (cart.size() >= 5) {
            total -= 5.0;
        }
        //return max of 0 or total to not return negative value
        return Math.max(total, 0.0);
    }
}