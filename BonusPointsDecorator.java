interface BonusPointsDecoratorFactory {
    BonusPointsStrategy apply(BonusPointsStrategy base, Rental rental);
}

abstract class BonusPointsStrategyDecorator implements BonusPointsStrategy {
    protected final BonusPointsStrategy wrappedStrategy;

    public BonusPointsStrategyDecorator(BonusPointsStrategy wrappedStrategy) {
        this.wrappedStrategy = wrappedStrategy;
    }
}

class TenExtraPointsOverTenDollarDecorator extends BonusPointsStrategyDecorator {
    private final Rental rental;

    public TenExtraPointsOverTenDollarDecorator(BonusPointsStrategy wrappedStrategy, Rental rental) {
        super(wrappedStrategy);
        this.rental = rental;
    }

    @Override
    public int calculatePoints(int daysRented) {
        int points = wrappedStrategy.calculatePoints(daysRented);

        if (rental.rentalPrice() >= 10.0) {
            points += 10;
        }

        return points;
    }
}