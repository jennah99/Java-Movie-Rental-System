import java.util.List;
// Strategy interface with calculateTotal method that can be overridden based on strategy implemented.
interface TransactionPriceStrategy {
    double calculateTotal(List<Rental> rentals);
}
// Return selected pricing strategy
class NormalTransactionPriceStrategy implements TransactionPriceStrategy {
    // CASE 1: DEFAULT PRICE CALCULATION
    // Program looks at all the rentals in the transactionCart list and adds their total together.
    @Override
    public double calculateTotal(List<Rental> cart) {
        double total = 0;

        for (Rental rental : cart) {
            total += rental.rentalPrice();
        }

        return total;
    }
}