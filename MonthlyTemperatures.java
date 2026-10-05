import java.util.Scanner;

/**
 * MonthlyTemperatures
 *
 * Stores the average temperature for each month of the year in two parallel
 * arrays (month names and temperatures). The user enters a month to see that
 * month's average, or "year" to see every month plus the yearly average and
 * the highest and lowest monthly averages. Enter "exit" to quit.
 *
 * Sample data: approximate monthly average temperatures (F) for Denver, CO.
 */
public class MonthlyTemperatures {

    public static void main(String[] args) {
        // Parallel arrays: index i in months matches index i in temperatures
        String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
        double[] temperatures = {
            31.7, 32.4, 40.6, 47.4, 57.1, 67.4,
            74.2, 72.1, 63.6, 50.7, 39.7, 31.1
        };

        Scanner input = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Monthly Temperature Viewer ===");

        while (running) {
            System.out.print("\nEnter a month (e.g., March), \"year\" for the full report, or \"exit\" to quit: ");
            String choice = input.nextLine().trim();

            if (choice.equalsIgnoreCase("exit")) {
                running = false;
                System.out.println("Goodbye.");
            } else if (choice.equalsIgnoreCase("year")) {
                displayYear(months, temperatures);
            } else {
                int index = findMonth(months, choice);
                if (index >= 0) {
                    System.out.printf("%s average temperature: %.1f F%n",
                            months[index], temperatures[index]);
                } else {
                    System.out.println("Invalid entry. Please enter a month name, \"year\", or \"exit\".");
                }
            }
        }

        input.close();
    }

    /**
     * Searches the months array for a match. Accepts the full month name or
     * its first three letters, case-insensitive. Returns -1 if not found.
     */
    public static int findMonth(String[] months, String entry) {
        for (int i = 0; i < months.length; i++) {
            String full = months[i];
            String abbrev = full.substring(0, 3);
            if (entry.equalsIgnoreCase(full) || entry.equalsIgnoreCase(abbrev)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Prints every month's temperature, the yearly average, and the
     * highest and lowest monthly averages.
     */
    public static void displayYear(String[] months, double[] temperatures) {
        double total = 0.0;
        int highIndex = 0;
        int lowIndex = 0;

        System.out.println("\nMonth        Avg Temp (F)");
        System.out.println("-------------------------");

        for (int i = 0; i < months.length; i++) {
            System.out.printf("%-12s %8.1f%n", months[i], temperatures[i]);
            total += temperatures[i];

            if (temperatures[i] > temperatures[highIndex]) {
                highIndex = i;
            }
            if (temperatures[i] < temperatures[lowIndex]) {
                lowIndex = i;
            }
        }

        double yearlyAverage = total / temperatures.length;

        System.out.println("-------------------------");
        System.out.printf("Yearly average:  %.1f F%n", yearlyAverage);
        System.out.printf("Highest monthly: %s (%.1f F)%n",
                months[highIndex], temperatures[highIndex]);
        System.out.printf("Lowest monthly:  %s (%.1f F)%n",
                months[lowIndex], temperatures[lowIndex]);
    }
}
