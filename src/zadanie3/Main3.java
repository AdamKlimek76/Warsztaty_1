package src.zadanie3;

import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {
        System.out.println("Pomyśl liczbę od 0 do 1000, a ja zgadnę maksymalnie w 10 próbach.");
        int min = 0;
        int max = 1000;
        int guess = ((max - min) / 2) + min;
        System.out.println("Zgaduję " + guess);
        Scanner scan = new Scanner(System.in);
        System.out.println("Odpowiedz 'za dużo', 'za mało' lub 'zgadłeś'");
        String answer = scan.nextLine();
        int attemptnumber=0;
        boolean isCheat=false;
        while (!answer.equals("zgadłeś")) {
            if (answer.equals("za dużo")) {
                max = guess;
                guess = ((max - min) / 2) + min;
                attemptnumber++;
            } else if (answer.equals("za mało")) {
                min = guess;
                guess = ((max - min) / 2) + min;
                attemptnumber++;
            } else {
                System.out.println("Nie oszukuj!");
            }
            if(attemptnumber>10){
                isCheat=true;
                break;
            }else {
                System.out.println("Zgaduję " + guess);
                answer = scan.nextLine();
            }
        }
        if(isCheat==false) {
            System.out.println("Wygrałem!");
        }else{
            System.out.println("Oszukiwałeś! Do widzenia!");
        }
    }
}
