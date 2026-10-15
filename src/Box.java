public class Box extends Parcel {
    private int length;
    private int width;
    private int height;

    public Box(String trackingNumber, String receiverName, double weight, int length, int width, int height) {
        super(trackingNumber, receiverName, weight);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public double getChargeableWeight() {
        double volumetric = (length * width * height) / 3500.0;
        double bigger;
        if (getWeight() > volumetric) {
            bigger = getWeight();
        } else {
            bigger = volumetric;
        }

        int whole = (int) bigger;
        if (bigger > whole) {
            whole = whole + 1;
        }
        return whole;
    }

    @Override
    public double calculateBaseFee() {
        return 40 * getChargeableWeight();
    }

    public int getLength() { return length; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}