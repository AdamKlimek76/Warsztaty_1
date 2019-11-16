package src.zadanie1;

import java.util.Random;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        int randNumb = randomNumber();
        System.out.println("Zgadnij liczbę od 0 do 100!");
        int giveNumb = giveNumber();
        while (giveNumb != randNumb) {
            if (giveNumb < 0 | giveNumb > 100) {
                System.out.println("Podana liczba musi być w zakresie od 0 do 100.");
                giveNumb = giveNumber();
            } else if (giveNumb > randNumb) {
                System.out.println("Za dużo!");
                giveNumb = giveNumber();
            } else if (giveNumb < randNumb) {
                System.out.println("Za mało!");
                giveNumb = giveNumber();
            }
        }
        System.out.println("Zgadłeś!");
    }

    static int randomNumber() {
        Random r = new Random();
        return r.nextInt(101);
    }

    static int giveNumber() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.println("To nie jest liczba!");
            scan.next();
        }
        return scan.nextInt();
    }

}
