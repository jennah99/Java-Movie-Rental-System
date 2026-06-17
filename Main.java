public class Main 
{
    public static void main(String[] args) 
    {
        // Creating movie objects.
        Movie regularMovie   = new Movie("The Godfather", Movie.REGULAR);
        Movie newReleaseMovie = new Movie("Marty Supreme", Movie.NEW_RELEASE);
        Movie kidsMovie      = new Movie("Toy Story", Movie.CHILDRENS);

        // Creating customer object.
        Customer customer = new Customer("Tien Nguyen");
        Customer customer2 = new Customer("John Doe");
        Customer customer3 = new Customer("Marty Mouser");
        Customer customer4 = new Customer("Ryan Gosling");
        Customer customer5 = new Customer("Woody Allen");
        Customer customer6 = new Customer("Stanley Kubrick");


        // Adding Rentals to a customer and displaying customer details. 
        System.out.println("~~~ Test 1: Renting out movies for a customer ~~~");

        customer.addRental(regularMovie, 3);
        customer.addRental(newReleaseMovie, 2);
        customer.addRental(kidsMovie, 4);

        // Print without any discount
        System.out.println(customer.printCustomerStatement());

        //Test 2 for discounts
        System.out.println("\n~~~ Test 2: Renting out movies for a customer with discounts ~~~");

        customer2.addRental(regularMovie, 3,"Half");
        customer2.addRental(newReleaseMovie, 4,"dollar");
        customer2.addRental(kidsMovie, 6,"dollar,half");

        // Print without any discount
        System.out.println(customer2.printCustomerStatement());


        // Adding 5 Rentals to a customer and displaying customer details. Then, display again after applying $5 off 5 rentals coupon.
        System.out.println("\n~~~ Test 3: Renting out 5 movies and adding a $5 off 5 rentals coupon ~~~");

        // Adding 5 rentals to customer 2s transaction
        customer3.addRental(regularMovie, 2);
        customer3.addRental(newReleaseMovie, 3);
        customer3.addRental(kidsMovie, 5);
        customer3.addRental(regularMovie, 1);
        customer3.addRental(newReleaseMovie, 2);
        System.out.println(customer3.printCustomerStatement());
        // After displaying the initial transaction, signify it will be shown again with a coupon added.  
        System.out.println("\nApplying $5 off 5 rentals coupon");

        Transaction customer3Transaction = customer3.getTransaction();

        // Applying the $5 off 5 rentals coupon. 
        customer3Transaction.applyPriceCoupon(
            new FiveRentalDiscountDecorator(
                customer3Transaction.getPricingStrategy()
            )
        );
        // Print again AFTER discount
        System.out.println(customer3.printCustomerStatement());

        //Test 4 for 10 points for a free movie
        System.out.println("\n~~~ Test 4: Using 10 points to get a free movie ~~~");
        customer4.addFrequentRenterPoints(10);// can only spend banked points from before transaction
        customer4.addRental(newReleaseMovie, 2, "free"); // should be free
        customer4.addRental(newReleaseMovie, 2, "free"); // should not be free
        System.out.println(customer4.printCustomerStatement());

        //Test 5 for 10 extra points for a 10 dollar rental
        System.out.println("\n~~~ Test 5: 10 extra points for rental of $10 or more ~~~");
        customer5.addRental(newReleaseMovie, 4, "bonus10"); // $12, should get 12 points total: 2 + 10
        customer5.addRental(regularMovie, 3, "bonus10");    // $3.5, should only get 1 point
        System.out.println(customer5.printCustomerStatement());

        //Test 6 for 20 extra points on 4 or more rentals in transaction cart
        System.out.println("\n~~~ Test 6: 20 extra points for 4 or more rentals in a transaction ~~~");
        customer6.addRental(regularMovie, 2);//1 point
        customer6.addRental(newReleaseMovie, 3);//2 points
        customer6.addRental(kidsMovie, 5);//1 point
        customer6.addRental(regularMovie, 1);//1 point

        Transaction customer6Transaction = customer6.getTransaction();

        customer6Transaction.applyBonusCoupon(
                new TwentyExtraPointsForFourRentalsDecorator(
                        customer6Transaction.getBonusPointsStrategy()
                )
        );
        System.out.println(customer6.printCustomerStatement());//total should be 25 points
    }//end of main
}
