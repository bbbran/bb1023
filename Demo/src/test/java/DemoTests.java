import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class DemoTests {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Test
    void exampleOneTest() {
        Jackhammer jackhammer = new Jackhammer("JAKR");
        // Reference: https://www.baeldung.com/junit-assert-exception
        String message = "Discount percent is not in the range 0-100";
        Exception invalidDiscountPercentageException = assertThrows(InvalidDiscountPercentageException.class, () -> {
            Checkout.checkoutTool(jackhammer, "9/3/15", 5, 101);
        });
        String output = invalidDiscountPercentageException.getMessage();
        assertTrue(output.contains(message));
    }

    @Test
    void exampleTwoTest() throws InvalidDiscountPercentageException, InvalidRentalDayCountException {
        // Reference: https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
        System.setOut(new PrintStream(outContent));
        Ladder ladder = new Ladder("LADW");
        Checkout.checkoutTool(ladder, "7/2/20", 3, 10);
        assertEquals("Tool code: LADW\n" +
            "Tool type: Ladder\n" +
            "Tool brand: Werner\n" +
            "Rental days: 3\n" +
            "Check out date: 7/2/20\n" +
            "Due date: 7/5/20\n" +
            "Daily rental charge: $1.99\n" +
            "Charge days: 2\n" +
            "Pre-discount charge: 3.98\n" +
            "Discount percent: 10\n" +
            "Discount amount: 0.40\n" +
            "Final charge: 3.58\n", outContent.toString());
    }

    @Test
    void exampleThreeTest() throws InvalidDiscountPercentageException, InvalidRentalDayCountException {
        // Reference: https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
        System.setOut(new PrintStream(outContent));
        Chainsaw chainsaw = new Chainsaw("CHNS");
        Checkout.checkoutTool(chainsaw, "7/2/15", 5, 25);
        assertEquals("Tool code: CHNS\n" +
            "Tool type: Chainsaw\n" +
            "Tool brand: Stihl\n" +
            "Rental days: 5\n" +
            "Check out date: 7/2/15\n" +
            "Due date: 7/7/15\n" +
            "Daily rental charge: $1.49\n" +
            "Charge days: 3\n" +
            "Pre-discount charge: 4.47\n" +
            "Discount percent: 25\n" +
            "Discount amount: 1.12\n" +
            "Final charge: 3.35\n", outContent.toString());
    }

    @Test
    void exampleFourTest() throws InvalidDiscountPercentageException, InvalidRentalDayCountException {
        // Reference: https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
        System.setOut(new PrintStream(outContent));
        Jackhammer jackhammer = new Jackhammer("JAKD");
        Checkout.checkoutTool(jackhammer, "9/3/15", 6, 0);
        assertEquals("Tool code: JAKD\n" +
            "Tool type: Jackhammer\n" +
            "Tool brand: DeWalt\n" +
            "Rental days: 6\n" +
            "Check out date: 9/3/15\n" +
            "Due date: 9/9/15\n" +
            "Daily rental charge: $2.99\n" +
            "Charge days: 3\n" +
            "Pre-discount charge: 8.97\n" +
            "Discount percent: 0\n" +
            "Discount amount: 0.00\n" +
            "Final charge: 8.97\n", outContent.toString());
    }

    @Test
    void exampleFiveTest() throws InvalidDiscountPercentageException, InvalidRentalDayCountException {
        // Reference: https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
        System.setOut(new PrintStream(outContent));
        Jackhammer jackhammer = new Jackhammer("JAKR");
        Checkout.checkoutTool(jackhammer, "7/2/15", 9, 0);
        assertEquals("Tool code: JAKR\n" +
            "Tool type: Jackhammer\n" +
            "Tool brand: Ridgid\n" +
            "Rental days: 9\n" +
            "Check out date: 7/2/15\n" +
            "Due date: 7/11/15\n" +
            "Daily rental charge: $2.99\n" +
            "Charge days: 5\n" +
            "Pre-discount charge: 14.95\n" +
            "Discount percent: 0\n" +
            "Discount amount: 0.00\n" +
            "Final charge: 14.95\n", outContent.toString());
    }

    @Test
    void exampleSixTest() throws InvalidDiscountPercentageException, InvalidRentalDayCountException {
        // Reference: https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
        System.setOut(new PrintStream(outContent));
        Jackhammer jackhammer = new Jackhammer("JAKR");
        Checkout.checkoutTool(jackhammer, "7/2/20", 4, 50);
        assertEquals("Tool code: JAKR\n" +
            "Tool type: Jackhammer\n" +
            "Tool brand: Ridgid\n" +
            "Rental days: 4\n" +
            "Check out date: 7/2/20\n" +
            "Due date: 7/6/20\n" +
            "Daily rental charge: $2.99\n" +
            "Charge days: 1\n" +
            "Pre-discount charge: 2.99\n" +
            "Discount percent: 50\n" +
            "Discount amount: 1.50\n" +
            "Final charge: 1.49\n", outContent.toString());
    }
}
