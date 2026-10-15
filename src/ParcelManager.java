import java.util.Arrays;

public class ParcelManager {
    private static ParcelManager instance;
    private Parcel[] parcels;

    private ParcelManager() {
        parcels = new Parcel[0];
    }

    public static ParcelManager getInstance() {
        if (instance == null) {
            instance = new ParcelManager();
        }
        return instance;
    }

    public void checkTrackingNumber(String tn) throws DuplicateTrackingNumberException {
        for (Parcel p : parcels) {
            if (p.getTrackingNumber().equalsIgnoreCase(tn)) {
                throw new DuplicateTrackingNumberException("Error: A parcel with this Tracking Number already exists!");
            }
        }
    }

    public void addParcel(Parcel p) {
        parcels = Arrays.copyOf(parcels, parcels.length + 1);
        parcels[parcels.length - 1] = p;
    }

    public Parcel findParcel(String tn) throws ParcelNotFoundException {
        for (Parcel p : parcels) {
            if (p.getTrackingNumber().equalsIgnoreCase(tn)) {
                return p;
            }
        }
        throw new ParcelNotFoundException("Parcel not found!");
    }

    public void displayAllParcels() {
        if (parcels.length == 0) {
            System.out.println("No parcels have been added yet.");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf("%-12s | %-8s | %-20s | %-11s | %-9s | %-13s | %-9s%n",
                "Tracking No", "Type", "Receiver", "Chg. Weight", "Service", "Delivery Fee", "Status");
        System.out.println("----------------------------------------------------------------------------------------------------");
        for (Parcel p : parcels) {
            System.out.printf("%-12.12s | %-8.8s | %-20.20s | %-11.11s | %-9.9s | %-13.13s | %-9.9s%n",
                    p.getTrackingNumber(), p.getClass().getSimpleName(), p.getReceiverName(),
                    String.format("%.2f kg", p.getChargeableWeight()), p.getServiceName(),
                    String.format("Php %.2f", p.getDeliveryFee()), p.getStatusText());
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
    }
}