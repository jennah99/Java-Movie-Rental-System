
public class Customer
{
    private final String name;
    private int frequentRenterPoints;
    private final Transaction transaction = new Transaction();
    private boolean checkedOut = false;

    public Customer (String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    // Return the customer's transaction.
    public Transaction getTransaction()
    {
        return transaction;
    }

    //rental from preexisting rental object (Leftover, no reason for rental to exist before tied to customer, will create phantom rentals)
    public void addRental(Rental rental)
    {
        rental.setOwner(this);
        transaction.addRental(rental);
    }

    public void addRental(Movie movie, int daysRented, String coupons){//rental from movie and days rented w/ coupons.
       Rental rental=Rental.createRental(movie, daysRented, coupons);
       rental.setOwner(this);
       transaction.addRental(rental);
    }
    public void addRental(Movie movie, int daysRented){//rental from movie and days rented w/o coupons
        Rental rental=Rental.createRental(movie, daysRented, "");
        rental.setOwner(this);
        transaction.addRental(rental);
    }

    // Return amount of frequentRenterPoints.
    public int getFrequentRenterPoints() 
    {
        return frequentRenterPoints;
    }

    // Manually increase a customers frequentRenterPoints by an amount.
    public void addFrequentRenterPoints(int points) 
    {
        frequentRenterPoints += points;
    }

    // If a customer has more frequentRenterPoints than a task needs, reduce the points by that much and notify the points have been used. 
    public boolean useFrequentRenterPoints(int points) 
    {
        if (frequentRenterPoints >= points) 
        {
            frequentRenterPoints -= points;
            return true;
        }
        return false;
    }

    //printCustomerStatement acts like a checkout, giving FRP, be wary of multiple uses for now
    public String printCustomerStatement()
    {

        double totalPrice = transaction.getPrice();
        int earnedPoints = transaction.getBonusPoints();

        String result = "Rental Record for " + getName() + "\n";

        for(Rental rentalItem: transaction.getRentals())
        {
            result += rentalItem.printRentalData();
        }

        if(!checkedOut){
            addFrequentRenterPoints(earnedPoints);
            checkedOut = true;
        }

        // Add footer lines
        result += "Amount owed is " + String.valueOf(totalPrice) + "\n";
        result += "You earned " + String.valueOf(earnedPoints) + " frequent renter points";
        result += "\nYou now have " + String.valueOf(this.frequentRenterPoints)+ " frequent renter points";
        return result;
    }   // End of print customer statement.

    public String printCustomerStatementXML() 
    {
        double totalPrice           = transaction.getPrice();
        int frequentRenterPoints    = transaction.getBonusPoints();
    
        StringBuilder result = new StringBuilder();
    
        result.append("<customer>\n");
        result.append("  <name>").append(getName()).append("</name>\n");
    
        for (Rental rentalItem : transaction.getRentals()) 
        {
            double moviePrice = rentalItem.rentalPrice();
            frequentRenterPoints += rentalItem.frequentRenterPoints();
            totalPrice += moviePrice;
    
            result.append("  <rental>\n");
            result.append("    <movie>")
                  .append(rentalItem.getMovie().getTitle())
                  .append("</movie>\n");
            result.append("    <daysRented>")
                  .append(rentalItem.getDaysRented())
                  .append("</daysRented>\n");
            result.append("    <price>")
                  .append(moviePrice)
                  .append("</price>\n");
            result.append("  </rental>\n");
        }
    
        result.append("  <totalAmount>").append(totalPrice).append("</totalAmount>\n");
        result.append("  <frequentRenterPoints>")
              .append(frequentRenterPoints)
              .append("</frequentRenterPoints>\n");
        result.append("</customer>");
    
        return result.toString();
    }   //end of XML
}       //end of customer
