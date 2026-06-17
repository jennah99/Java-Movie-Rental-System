import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Rental {
    private final Movie movie;
    private final int daysRented;
    private PriceStrategy priceStrategy;
    private BonusPointsStrategy bonusPointsStrategy;
    private Customer owner;

    private Rental(Movie movie, int daysRented, PriceStrategy priceStrategy, BonusPointsStrategy bonusPointsStrategy) {
        this.movie = movie;
        this.daysRented = daysRented;
        this.priceStrategy = priceStrategy;
        this.bonusPointsStrategy = bonusPointsStrategy;
        this.owner = null;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public static Rental createRental(Movie movie, int daysRented, String couponType) {
        PriceStrategy baseStrategy = createPriceStrategy(movie.getPriceCode());
        BonusPointsStrategy bonusPointsStrategy = createBonusPointsStrategy(movie.getPriceCode());

        Rental rental = new Rental(movie, daysRented, baseStrategy, bonusPointsStrategy);

        List<String> coupons = parseCouponTypes(couponType);

        rental.priceStrategy = applyPriceCoupons(baseStrategy, rental, coupons);
        rental.bonusPointsStrategy = applyBonusPointCoupons(bonusPointsStrategy, rental, coupons);

        return rental;
    }

    private static PriceStrategy createPriceStrategy(int priceCode) {
        return switch (priceCode) {
            case Movie.REGULAR -> new RegularPriceStrategy();
            case Movie.NEW_RELEASE -> new NewReleasePriceStrategy();
            case Movie.CHILDRENS -> new ChildrenPriceStrategy();
            default -> throw new IllegalArgumentException("unknown price code: " + priceCode);
        };
    }

    private static BonusPointsStrategy createBonusPointsStrategy(int priceCode) {
        return switch (priceCode) {
            case Movie.NEW_RELEASE -> new NewReleaseBonusPointsStrategy();
            case Movie.REGULAR, Movie.CHILDRENS -> new StandardBonusPointsStrategy();
            default -> throw new IllegalArgumentException("unknown price code: " + priceCode);
        };
    }

    private static final Map<String, PriceDecoratorFactory> PRICE_DECORATORS = Map.of(
            "HALF", (base, rental) -> new HalfOffDecorator(base),
            "DOLLAR", (base, rental) -> new DollarOffOverFiveDecorator(base),
            "FREE", FreeMovieDecorator::new
    );

    private static final Map<String, BonusPointsDecoratorFactory> BONUS_DECORATORS = Map.of(
            "BONUS10", TenExtraPointsOverTenDollarDecorator::new
    );

    private static PriceStrategy applyPriceCoupons(PriceStrategy baseStrategy, Rental rental, List<String> couponTypes) {
        PriceStrategy strategy = baseStrategy;

        for (String couponType : couponTypes) {
            PriceDecoratorFactory factory = PRICE_DECORATORS.get(couponType);

            if (factory != null) {
                strategy = factory.apply(strategy, rental);
            }
        }

        return strategy;
    }

    private static BonusPointsStrategy applyBonusPointCoupons(BonusPointsStrategy baseStrategy, Rental rental, List<String> couponTypes) {
        BonusPointsStrategy strategy = baseStrategy;

        for (String couponType : couponTypes) {
            BonusPointsDecoratorFactory factory = BONUS_DECORATORS.get(couponType);

            if (factory != null) {
                strategy = factory.apply(strategy, rental);
            }
        }

        return strategy;
    }

    private static List<String> parseCouponTypes(String couponType) {
        List<String> coupons = new ArrayList<>();

        if (couponType == null || couponType.isBlank() || couponType.equalsIgnoreCase("NONE")) {
            return coupons;
        }

        String[] parts = couponType.split(",");

        for (String part : parts) {
            coupons.add(part.trim().toUpperCase());
        }

        return coupons;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double rentalPrice() {
        return priceStrategy.calculatePrice(daysRented);
    }

    public int frequentRenterPoints() {
        return bonusPointsStrategy.calculatePoints(daysRented);
    }

    public String printRentalData() {
        return "\t" + getMovie().getTitle() + "\t" + rentalPrice() + "\n";
    }
}