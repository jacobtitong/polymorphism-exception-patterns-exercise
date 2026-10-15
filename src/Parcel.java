public abstract class Parcel {
    private String trackingNumber;
    private String receiverName;
    private double weight;
    private boolean delivered;
    private DeliveryStrategy service;

    public Parcel(String trackingNumber, String receiverName, double weight) {
        this.trackingNumber = trackingNumber;
        this.receiverName = receiverName;
        this.weight = weight;
        this.delivered = false;
        this.service = null;
    }

    public String getTrackingNumber() { return trackingNumber; }
    public String getReceiverName() { return receiverName; }
    public double getWeight() { return weight; }
    public boolean isDelivered() { return delivered; }

    public abstract double getChargeableWeight();
    public abstract double calculateBaseFee();

    public void setService(DeliveryStrategy s) throws SameDayNotAllowedException {
        s.checkAllowed(getChargeableWeight());
        this.service = s;
    }

    public String getServiceName() {
        return service.getName();
    }

    public double getDeliveryFee() {
        return service.calculateFee(calculateBaseFee());
    }

    public String getStatusText() {
        return delivered ? "Delivered" : "Pending";
    }

    public void markDelivered() throws ParcelAlreadyDeliveredException {
        if (delivered) {
            throw new ParcelAlreadyDeliveredException("Parcel was already delivered.");
        }
        delivered = true;
    }
}