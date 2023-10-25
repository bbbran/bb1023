public class Ladder extends Tool {
    String toolType;
    String toolBrand;
    double dailyRentalCharge;

    public Ladder(String toolCode) {
        super(toolCode);
        this.toolType = "Ladder";
        this.toolBrand = "Werner";
        this.dailyRentalCharge = 1.99;
    }
}