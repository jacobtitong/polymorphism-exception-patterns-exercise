public interface DeliveryStrategy {
    String getName();
    void checkAllowed(double chargeableWeight) throws SameDayNotAllowedException;
    double calculateFee(double baseFee);
}