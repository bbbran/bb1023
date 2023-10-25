import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Checkout {
    static void checkoutTool(Chainsaw chainsaw, String checkoutDate, int rentalDayCount, int discountPercent) throws
            InvalidDiscountPercentageException, InvalidRentalDayCountException {

        // Date conversion reference: https://stackoverflow.com/questions/74684817/add-5-days-to-a-date-in-java
        DateTimeFormatter monthDayYear = DateTimeFormatter.ofPattern("M/d/yy");
        LocalDate checkOutDate = LocalDate.parse(checkoutDate, monthDayYear); // Specified at checkout
        String dueDate = checkOutDate.plusDays(rentalDayCount).format(monthDayYear);

        if (discountPercent < 0 || discountPercent > 100) {
            throw new InvalidDiscountPercentageException("InvalidDiscountPercentageException: Discount percent " +
                    "is not in the range 0-100");
        } else if (rentalDayCount < 1) {
            throw new InvalidRentalDayCountException("InvalidRentalDayCountException: Rental day count is not 1 " +
                    "or greater");
        } else {
            RentalAgreement rentalAgreement = new RentalAgreement(chainsaw.toolCode, chainsaw.toolType,
                    chainsaw.toolBrand, rentalDayCount, checkOutDate.format(monthDayYear), dueDate,
                    chainsaw.dailyRentalCharge, discountPercent);
            System.out.println(rentalAgreement); // Print rental agreement
        }
    }

    static void checkoutTool(Ladder ladder, String checkoutDate, int rentalDayCount, int discountPercent) throws
            InvalidDiscountPercentageException, InvalidRentalDayCountException {

        // Date conversion reference: https://stackoverflow.com/questions/74684817/add-5-days-to-a-date-in-java
        DateTimeFormatter monthDayYear = DateTimeFormatter.ofPattern("M/d/yy");
        LocalDate checkOutDate = LocalDate.parse(checkoutDate, monthDayYear); // Specified at checkout
        String dueDate = checkOutDate.plusDays(rentalDayCount).format(monthDayYear);

        if (discountPercent < 0 || discountPercent > 100) {
            throw new InvalidDiscountPercentageException("InvalidDiscountPercentageException: Discount percent " +
                    "is not in the range 0-100");
        } else if (rentalDayCount < 1) {
            throw new InvalidRentalDayCountException("InvalidRentalDayCountException: Rental day count is not 1 " +
                    "or greater");
        } else {
            RentalAgreement rentalAgreement = new RentalAgreement(ladder.toolCode, ladder.toolType,
                    ladder.toolBrand, rentalDayCount, checkOutDate.format(monthDayYear), dueDate,
                    ladder.dailyRentalCharge, discountPercent);
            System.out.println(rentalAgreement); // Print rental agreement
        }
    }

    static void checkoutTool(Jackhammer jackhammer, String checkoutDate, int rentalDayCount, int discountPercent) throws
            InvalidDiscountPercentageException, InvalidRentalDayCountException {

        // Date conversion reference: https://stackoverflow.com/questions/74684817/add-5-days-to-a-date-in-java
        DateTimeFormatter monthDayYear = DateTimeFormatter.ofPattern("M/d/yy");
        LocalDate checkOutDate = LocalDate.parse(checkoutDate, monthDayYear); // Specified at checkout
        String dueDate = checkOutDate.plusDays(rentalDayCount).format(monthDayYear);

        if (discountPercent < 0 || discountPercent > 100) {
            throw new InvalidDiscountPercentageException("Discount percent " +
                    "is not in the range 0-100");
        } else if (rentalDayCount < 1) {
            throw new InvalidRentalDayCountException("Rental day count is not 1 " +
                    "or greater");
        } else {
            RentalAgreement rentalAgreement = new RentalAgreement(jackhammer.toolCode, jackhammer.toolType,
                    jackhammer.toolBrand, rentalDayCount, checkOutDate.format(monthDayYear), dueDate,
                    jackhammer.dailyRentalCharge, discountPercent);
            System.out.println(rentalAgreement); // Print rental agreement
        }
    }

    public static void main(String[] args) {
        try {
            // Tools with tool codes specified at checkout
            Chainsaw chainsaw = new Chainsaw("CHNS");
            Ladder ladder = new Ladder("LADW");
            Jackhammer jackhammerD = new Jackhammer("JAKD");
            Jackhammer jackhammerR = new Jackhammer("JAKR");

            String checkoutDate = "7/5/21"; // Specified at checkout
            int rentalDayCount = 3; // Specified at checkout
            int discountPercent = 20; // Specified at checkout

        // Specify tool, checkout date, rental day count, and discount percent at checkout
        // Example: checkout chainsaw tool on the following line
        checkoutTool(chainsaw, checkoutDate, rentalDayCount, discountPercent);
        } catch (InvalidDiscountPercentageException invalidDiscountPercentageException) {
            System.out.println("Caught InvalidDiscountPercentageException " + invalidDiscountPercentageException);
        } catch (InvalidRentalDayCountException invalidRentalDayCountException) {
            System.out.println("Caught InvalidRentalDayCountException " + invalidRentalDayCountException);
        } catch (Exception exception) {
            System.out.println("Caught Exception " + exception);
        }
    }
}
