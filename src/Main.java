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
}