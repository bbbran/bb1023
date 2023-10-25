public class Chainsaw extends Tool {
    String toolType;
    String toolBrand;
    double dailyRentalCharge;

    public Chainsaw(String toolCode) {
        super(toolCode);
        this.toolType = "Chainsaw";
        this.toolBrand = "Stihl";
        this.dailyRentalCharge = 1.49;
    }
}
