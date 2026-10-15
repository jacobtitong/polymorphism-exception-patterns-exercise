public class StandardDelivery implements DeliveryStrategy {

    @Override
    public String getName() {
        return "Standard";
    }

    @Override
    public void checkAllowed(double chargeableWeight) throws SameDayNotAllowedException {
    }

    @Override
    public double calculateFee(double baseFee) {
        return baseFee;
    }
}