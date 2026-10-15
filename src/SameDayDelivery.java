public class SameDayDelivery implements DeliveryStrategy {

    @Override
    public String getName() {
        return "Same Day";
    }

    @Override
    public void checkAllowed(double chargeableWeight) throws SameDayNotAllowedException {
        if (chargeableWeight > 10) {
            throw new SameDayNotAllowedException("Same Day delivery is only for parcels up to 10 kg!");
        }
    }

    @Override
    public double calculateFee(double baseFee) {
        return (baseFee * 2) + 100;
    }
}