
import java.util.List;
interface TransactionBonusPointsStrategy {
    int calculatePoints(List<Rental> rentals);
}

class NormalTransactionBonusPointsStrategy implements TransactionBonusPointsStrategy {
    @Override
    public int calculatePoints(List<Rental> rentals) {
        int total = 0;

        for (Rental rental : rentals) {
            total += rental.frequentRenterPoints();
        }

        return total;
    }
}