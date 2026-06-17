interface BonusPointsStrategy {
    int calculatePoints(int daysRented);
}

class StandardBonusPointsStrategy implements BonusPointsStrategy {
    @Override
    public int calculatePoints(int daysRented) {
        return 1;
    }
}

class NewReleaseBonusPointsStrategy implements BonusPointsStrategy {
    @Override
    public int calculatePoints(int daysRented) {
        return (daysRented > 1) ? 2 : 1;
    }
}