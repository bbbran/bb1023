import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.*;
import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class RentalAgreement {
    private final String toolCode;
    private final String toolType;
    private final String toolBrand;
    private final int rentalDayCount;
    private final String checkOutDate;
    private final String dueDate;
    private final double dailyRentalCharge;
    private int chargeDays;
    private final double prediscountCharge;
    private final int discountPercent;
    private final double discountAmount;
    private final double finalCharge;

    public RentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDayCount, String checkOutDate,
                           String dueDate, double dailyRentalCharge, int discountPercent) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDayCount = rentalDayCount;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;

        // Charge days
        // Date conversion reference: https://stackoverflow.com/questions/74684817/add-5-days-to-a-date-in-java
        DateTimeFormatter monthDayYear = DateTimeFormatter.ofPattern("M/d/yy");
        // Counting days reference: https://stackoverflow.com/questions/65330169/how-to-get-business-days-between-two-dates-in-java
        LocalDate startDate = LocalDate.parse(checkOutDate, monthDayYear);
        LocalDate endDate = LocalDate.parse(dueDate, monthDayYear);
        final Set<DayOfWeek> weekDays = Set.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);
        final Set<DayOfWeek> allDays = Set.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY);

        // Independence Day - If Independence Day falls on a weekend, set Independence Day to the closest week day
        LocalDate independenceDay = LocalDate.of(startDate.getYear(), 7, 4);
        if (independenceDay.getDayOfWeek() == SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek() == SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }

        // Labor Day
        // First day of month reference: https://stackoverflow.com/questions/23971439/get-the-first-monday-of-a-month
        LocalDate september = LocalDate.of(startDate.getYear(), 9, 1);
        LocalDate laborDay = september.with(firstInMonth(DayOfWeek.MONDAY));

        switch (toolType) {
            case "Ladder": {
                List<LocalDate> days = startDate.plusDays(1).datesUntil(endDate.plusDays(1))
                        .filter(t -> allDays.contains(t.getDayOfWeek())).collect(Collectors.toList());
                if (days.contains(independenceDay) && days.contains(laborDay)) {
                    this.chargeDays = days.size() - 2; // Date range contains Independence Day and Labor Day, do not charge
                } else if (days.contains(independenceDay) || days.contains(laborDay)) {
                    this.chargeDays = days.size() - 1; // Date range contains Independence Day or Labor Day, do not charge
                } else {
                    this.chargeDays = days.size();
                }
                break;
            } case "Chainsaw": {
                List<LocalDate> days = startDate.plusDays(1).datesUntil(endDate.plusDays(1))
                        .filter(t -> weekDays.contains(t.getDayOfWeek())).collect(Collectors.toList());
                this.chargeDays = days.size(); // Charge on week days holiday or not

                break;
            } case "Jackhammer": {
                List<LocalDate> days = startDate.plusDays(1).datesUntil(endDate.plusDays(1))
                        .filter(t -> weekDays.contains(t.getDayOfWeek())).collect(Collectors.toList());
                if (days.contains(independenceDay) && days.contains(laborDay)) {
                    this.chargeDays = days.size() - 2; // Date range contains Independence Day and Labor Day, do not charge
                } else if (days.contains(independenceDay) || days.contains(laborDay)) {
                    this.chargeDays = days.size() - 1; // Date range contains Independence Day or Labor Day, do not charge
                } else {
                    this.chargeDays = days.size();
                }
                break;
            }
        }
        this.prediscountCharge = Math.round((chargeDays * dailyRentalCharge) * 100)/100.00;
        this.discountPercent = discountPercent;
        this.discountAmount = Math.round(((discountPercent/100.00) * prediscountCharge) * 100)/100.00;
        this.finalCharge = Math.round((prediscountCharge - discountAmount) * 100)/100.00;
    }

    @Override
    public String toString() {
        return "Tool code: " + this.toolCode +
                "\nTool type: " + this.toolType +
                "\nTool brand: " + this.toolBrand +
                "\nRental days: " + this.rentalDayCount +
                "\nCheck out date: " + this.checkOutDate +
                "\nDue date: " + this.dueDate +
                "\nDaily rental charge: $" + this.dailyRentalCharge +
                "\nCharge days: " + this.chargeDays +
                "\nPre-discount charge: " + String.format("%.2f", this.prediscountCharge) +
                "\nDiscount percent: " + this.discountPercent +
                "\nDiscount amount: " + String.format("%.2f", this.discountAmount) +
                "\nFinal charge: " + String.format("%.2f", this.finalCharge);
    }
}
