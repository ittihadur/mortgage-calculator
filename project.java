package hotboy;

import java.util.Scanner;
import java.text.NumberFormat;

public class project {

    public static void main(String[] args) {
        final byte Month = 12;
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        int P = (int) readNumber("Principal($1K - 1M): ", 1000, 1000000);
        float r = (float) readNumber("Annual interest", 1, 30);
        int year = (int) readNumber("Period(Years): ", 1, 30);
        double M = calculateMortgage(P, r, year);
        String result = currency.format(M);
        System.out.println();
        System.out.println("Mortgage");
        System.out.println("-------");
        System.out.println("Mortgage : " + result);

        System.out.println();
        System.out.println("Paymrnt in schedule");
        System.out.println("---------");
        for (short month = 1; month <= year * Month; month++) {
            double balance = calculateBalance(P, r, year, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(String prompt, double min, double max) {
        float r;
        do {
            System.out.println(prompt);
            Scanner scanner = new Scanner(System.in);
            r = scanner.nextFloat();
            if (r < min || r > max) {
                System.out.print("The number must be greater than" + min + " or less than " + max + "\n");
            }
        } while (r < min || r > max);
        return r;
    }

    public static double calculateBalance(int P, float r, int year, short numberOfPayments) {
        final byte Month = 12;
        final byte percent = 100;
        r = r / percent / Month;
        year = year * Month;
        double balance = P * (Math.pow(1 + r, year) - Math.pow(1 + r, numberOfPayments)) / (Math.pow(1 + r, year) - 1);
        return balance;
    }

    public static double calculateMortgage(int P, float r, int year) {
        final byte Month = 12;
        final byte percent = 100;
        r = r / percent / Month;
        year = year * Month;
        double M = P * (r * Math.pow(1 + r, year)) / (Math.pow(1 + r, year) - 1);
        return M;
    }
}
