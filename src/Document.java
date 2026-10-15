public class Document extends Parcel {
    private int pages;

    public Document(String trackingNumber, String receiverName, double weight, int pages) {
        super(trackingNumber, receiverName, weight);
        this.pages = pages;
    }

    @Override
    public double getChargeableWeight() {
        return getWeight();
    }

    @Override
    public double calculateBaseFee() {
        return 50;
    }

    public int getPages() { return pages; }
}