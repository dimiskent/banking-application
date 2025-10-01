import java.util.Locale;
import java.util.Scanner;

public class Input {
    private final Scanner scanner;
    public Input() {
        scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
    }
    public void close() {
        scanner.close();
    }
    public void clear() {
        scanner.nextLine();
    }
    public String getString(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine();
    }
    public int getInteger(String message, int min, int max) {
        int n;
        while (true) {
            System.out.print(message + ": ");
            if(scanner.hasNextInt()) {
                n = scanner.nextInt();
                if(n <= max && n >= min) break;
                else System.out.printf("Number must be between %d and %d!\n", min, max);
            } else {
                scanner.next();
                System.out.println("Please enter a valid whole number!");
            }
        }
        return n;
    }
    public double getDouble(String message) {
        while (true) {
            System.out.print(message + ": ");
            if(scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                scanner.next();
                System.out.println("Please enter a valid number!");
            }
        }
    }
}
