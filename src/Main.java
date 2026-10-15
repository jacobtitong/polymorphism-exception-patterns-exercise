import java.util.Scanner;

public class Main {
    static ParcelManager manager = ParcelManager.getInstance();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean endProgram = false;

        while (!endProgram) {
            System.out.print("\n=== PARCEL DELIVERY SYSTEM MENU ===");
            System.out.print("\n1 - Add Parcel\n2 - View All Parcels\n3 - Change Delivery Service\n4 - Mark as Delivered\n5 - Exit\n");
            int option = getValidInt("--> Option: ", 5);

            switch (option) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    System.out.println("\n=== ALL PARCELS ===");
                    manager.displayAllParcels();
                    break;
                case 3:
                    changeDeliveryService();
                    break;
                case 5:
                    endProgram = true;
                    break;
                default:
                    System.out.println("Error: Invalid option. Please choose 1-5.");
            }
        }
    }

    public static int getValidInt(String prompt, int max) {
        int number = -1;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(prompt);
            String input = sc.nextLine();

            if (input.trim().isEmpty()) {
                System.out.println("Error: Input cannot be empty or just spaces.");
                continue;
            }

            try {
                number = Integer.parseInt(input.trim());
                if (number < 1 || number > max) {
                    System.out.println("Error: Invalid option. Please choose 1-" + max + ".");
                } else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number, not text.");
            }
        }
        return number;
    }

    public static int getValidWholeNumber(String prompt, int min, int max, String rangeMessage) {
        int number = -1;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(prompt);
            String input = sc.nextLine();

            if (input.trim().isEmpty()) {
                System.out.println("Error: Input cannot be empty or just spaces.");
                continue;
            }

            try {
                number = Integer.parseInt(input.trim());
                if (number < min || number > max) {
                    System.out.println(rangeMessage);
                } else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid whole number, not text.");
            }
        }
        return number;
    }

    public static String getNonBlank(String prompt) {
        String input = "";
        boolean isValid = false;

        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty or just spaces.");
            } else {
                isValid = true;
            }
        }
        return input;
    }

    public static String getValidTracking(String prompt) {
        String input = "";
        boolean isValid = false;

        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty or just spaces.");
                continue;
            }

            if (input.matches("ABC[0-9]{4}")) {
                isValid = true;
            } else {
                System.out.println("Error: Tracking Number must be \"ABC\" followed by 4 digits (e.g. ABC0001).");
            }
        }
        return input;
    }

    public static String getValidName(String prompt) {
        String input = "";
        boolean isValid = false;

        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty or just spaces.");
                continue;
            }

            if (input.length() >= 2 && input.length() <= 50 && input.matches("[A-Za-z][A-Za-z .'-]*")) {
                isValid = true;
            } else {
                System.out.println("Error: Name must be 2-50 characters and contain only letters, spaces, periods, apostrophes, and hyphens.");
            }
        }
        return input;
    }

    public static double getValidWeight(String prompt, double maxWeight, String maxText, String typeName) {
        double weight = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty or just spaces.");
                continue;
            }

            if (!input.matches("[0-9]+(\\.[0-9]{1,2})?")) {
                System.out.println("Error: Please enter a valid decimal number (e.g. 1.5, up to 2 decimal places).");
                continue;
            }

            try {
                weight = Double.parseDouble(input);
                if (weight <= 0) {
                    System.out.println("Error: Weight must be greater than 0.");
                } else if (weight > maxWeight) {
                    System.out.println("Error: Weight cannot exceed " + maxText + " kg for a " + typeName + " parcel.");
                } else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid decimal number (e.g. 1.5, up to 2 decimal places).");
            }
        }
        return weight;
    }

    public static String getValidNote(String prompt) {
        String input = "";
        boolean isValid = false;

        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty or just spaces.");
                continue;
            }

            if (input.length() >= 3 && input.length() <= 50 && input.matches("^(?=.*[a-zA-Z])[a-zA-Z0-9 .,!'-]+$")) {
                isValid = true;
            } else {
                System.out.println("Error: Handling note must be 3-50 characters, contain a letter, and use only letters, numbers, spaces, and . , ! ' -");
            }
        }
        return input;
    }

    public static void addParcel() {
        System.out.println("\n=== ADD PARCEL ===");
        System.out.print("1 - Document\n2 - Box\n3 - Fragile\n");
        int type = getValidInt("Select parcel type (1-3): ", 3);

        double maxWeight = 0.5;
        String maxText = "0.5";
        String typeName = "Document";
        if (type == 2) {
            maxWeight = 50;
            maxText = "50";
            typeName = "Box";
        } else if (type == 3) {
            maxWeight = 30;
            maxText = "30";
            typeName = "Fragile";
        }

        try {
            String trackingNumber = getValidTracking("Tracking Number: ");
            manager.checkTrackingNumber(trackingNumber);

            String receiver = getValidName("Receiver Name: ");
            double weight = getValidWeight("Weight (kg): ", maxWeight, maxText, typeName);

            Parcel parcel;
            if (type == 1) {
                int pages = getValidWholeNumber("Number of pages: ", 1, 100, "Error: Number of pages must be between 1 and 100.");
                parcel = new Document(trackingNumber, receiver, weight, pages);
            } else {
                int length = getValidWholeNumber("Length (cm): ", 1, 200, "Error: Dimension must be a whole number between 1 and 200 cm.");
                int width = getValidWholeNumber("Width (cm): ", 1, 200, "Error: Dimension must be a whole number between 1 and 200 cm.");
                int height = getValidWholeNumber("Height (cm): ", 1, 200, "Error: Dimension must be a whole number between 1 and 200 cm.");
                if (type == 2) {
                    parcel = new Box(trackingNumber, receiver, weight, length, width, height);
                } else {
                    String note = getValidNote("Handling Note: ");
                    parcel = new Fragile(trackingNumber, receiver, weight, length, width, height, note);
                }
            }

            boolean accepted = false;
            while (!accepted) {
                DeliveryStrategy service = selectService();
                try {
                    parcel.setService(service);
                    accepted = true;
                } catch (SameDayNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }

            manager.addParcel(parcel);
            System.out.println("Parcel added successfully! Delivery fee: " + String.format("Php %.2f", parcel.getDeliveryFee()));
        } catch (DuplicateTrackingNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DeliveryStrategy selectService() {
        System.out.print("1 - Standard\n2 - Express\n3 - Same Day\n");
        int choice = getValidInt("Select delivery service (1-3): ", 3);

        DeliveryStrategy service = null;
        switch (choice) {
            case 1:
                service = new StandardDelivery();
                break;
            case 2:
                service = new ExpressDelivery();
                break;
            case 3:
                service = new SameDayDelivery();
                break;
        }
        return service;
    }

    public static void changeDeliveryService() {
        System.out.println("\n=== CHANGE DELIVERY SERVICE ===");
        String trackingNumber = getNonBlank("Tracking Number: ");

        try {
            Parcel parcel = manager.findParcel(trackingNumber);

            if (parcel.isDelivered()) {
                throw new ParcelAlreadyDeliveredException("Parcel already delivered!");
            }

            DeliveryStrategy service = selectService();
            parcel.setService(service);
            System.out.println("Delivery service changed! New fee: " + String.format("Php %.2f", parcel.getDeliveryFee()));
        } catch (ParcelNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ParcelAlreadyDeliveredException e) {
            System.out.println(e.getMessage());
        } catch (SameDayNotAllowedException e) {
            System.out.println(e.getMessage());
        }
    }
}