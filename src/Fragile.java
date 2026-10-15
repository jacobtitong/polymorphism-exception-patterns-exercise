public class Fragile extends Box {
    private String handlingNote;

    public Fragile(String trackingNumber, String receiverName, double weight, int length, int width, int height, String handlingNote) {
        super(trackingNumber, receiverName, weight, length, width, height);
        this.handlingNote = handlingNote;
    }

    @Override
    public double calculateBaseFee() {
        return super.calculateBaseFee() + 100;
    }

    public String getHandlingNote() { return handlingNote; }
}