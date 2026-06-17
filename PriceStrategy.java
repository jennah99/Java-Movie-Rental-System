interface PriceStrategy {
    double calculatePrice(int daysRented);
}

class RegularPriceStrategy implements PriceStrategy {
    @Override
    public double calculatePrice(int daysRented) {
        double moviePrice = 2.0;

        if (daysRented > 2) {
            moviePrice += (daysRented - 2) * 1.5;
        }

        return moviePrice;
    }
}

class NewReleasePriceStrategy implements PriceStrategy {
    @Override
    public double calculatePrice(int daysRented) {
        return daysRented * 3.0;
    }
}

class ChildrenPriceStrategy implements PriceStrategy {
    @Override
    public double calculatePrice(int daysRented) {
        double moviePrice = 1.5;

        if (daysRented > 3) {
            moviePrice += (daysRented - 3) * 1.5;
        }

        return moviePrice;
    }
}