import java.util.ArrayList;
import java.util.List;

public class Transaction
{
    private final List<Rental> transactionCart = new ArrayList<>();
    private TransactionPriceStrategy pricingStrategy;
    private TransactionBonusPointsStrategy bonusPointsStrategy;

    public Transaction() 
    {
        this.pricingStrategy = new NormalTransactionPriceStrategy();
        this.bonusPointsStrategy = new NormalTransactionBonusPointsStrategy();
    }

    // Add a new rental to the customers "Cart"
    public void addRental(Rental rental) 
    {
        transactionCart.add(rental);
    }

    public List<Rental> getRentals() 
    {
        return transactionCart;
    }

    // Get the total price of the transactionCart list. 
    public double getPrice() 
    {
        return pricingStrategy.calculateTotal(transactionCart);
    }
    // Get the total FRP of the transactionCart list.
    public int getBonusPoints()
    {
    return bonusPointsStrategy.calculatePoints(transactionCart);
    }


    // Apply price coupons to a users transactionCart as a strategy.
    public void applyPriceCoupon(TransactionPriceStrategy strategy)
    {
        this.pricingStrategy = strategy;
    }
    // Apply FRP coupons to a users transactionCart as a strategy.
    public void applyBonusCoupon(TransactionBonusPointsStrategy strategy) {
        this.bonusPointsStrategy = strategy;
    }
    public TransactionPriceStrategy getPricingStrategy()
    {
        return pricingStrategy;
    }
    public TransactionBonusPointsStrategy getBonusPointsStrategy() {
        return bonusPointsStrategy;
    }

}

