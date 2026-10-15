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
}