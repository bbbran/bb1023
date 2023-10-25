public class Jackhammer extends Tool {
    String toolType;
    String toolBrand;
    double dailyRentalCharge;

    public Jackhammer(String toolCode) {
        super(toolCode);
        this.toolType = "Jackhammer";
        if (toolCode.equals("JAKD")) {
            toolBrand = "DeWalt";
        } else if (toolCode.equals("JAKR")) {
            toolBrand = "Ridgid";
        }
        this.dailyRentalCharge = 2.99;
    }
}

