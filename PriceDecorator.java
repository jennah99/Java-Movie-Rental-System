interface PriceDecoratorFactory {
    PriceStrategy apply(PriceStrategy base, Rental rental);
}

abstract class PriceStrategyDecorator implements PriceStrategy {
    protected final PriceStrategy wrappedStrategy;

    public PriceStrategyDecorator(PriceStrategy wrappedStrategy) {
        this.wrappedStrategy = wrappedStrategy;
    }
}

class HalfOffDecorator extends PriceStrategyDecorator {
    public HalfOffDecorator(PriceStrategy wrappedStrategy) {
        super(wrappedStrategy);
    }

    @Override
    public double calculatePrice(int daysRented) {
        return wrappedStrategy.calculatePrice(daysRented) * 0.5;
    }
}

class DollarOffOverFiveDecorator extends PriceStrategyDecorator {
    public DollarOffOverFiveDecorator(PriceStrategy wrappedStrategy) {
        super(wrappedStrategy);
    }

    @Override
    public double calculatePrice(int daysRented) {
        double total = wrappedStrategy.calculatePrice(daysRented);

        if (total > 5.0) {
            total -= 1.0;
        }

        return total;
    }
}

class FreeMovieDecorator extends PriceStrategyDecorator {
    private final Rental rental;
    private boolean freeMovieUsed = false;

    public FreeMovieDecorator(PriceStrategy wrappedStrategy, Rental rental) {
        super(wrappedStrategy);
        this.rental = rental;
    }

    @Override
    public double calculatePrice(int daysRented) {
        Customer owner = rental.getOwner();

        if (freeMovieUsed) {
            return 0.0;
        }

        if (owner != null && owner.useFrequentRenterPoints(10)) {
            freeMovieUsed = true;
            return 0.0;
        }

        return wrappedStrategy.calculatePrice(daysRented);
    }
}