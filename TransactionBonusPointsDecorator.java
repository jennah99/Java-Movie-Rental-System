
import java.util.List;
abstract class TransactionBonusPointsDecorator implements TransactionBonusPointsStrategy {
    protected final TransactionBonusPointsStrategy wrapped;

    public TransactionBonusPointsDecorator(TransactionBonusPointsStrategy wrapped) {
        this.wrapped = wrapped;
    }
}

class TwentyExtraPointsForFourRentalsDecorator extends TransactionBonusPointsDecorator {
    public TwentyExtraPointsForFourRentalsDecorator(TransactionBonusPointsStrategy wrapped) {
        super(wrapped);
    }

    @Override
    public int calculatePoints(List<Rental> rentals) {
        int points = wrapped.calculatePoints(rentals);

        if (rentals.size() >= 4) {
            points += 20;
        }

        return points;
    }
}