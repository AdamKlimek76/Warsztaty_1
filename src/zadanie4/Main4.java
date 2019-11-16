package src.zadanie4;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.Scanner;

public class Main4 {

    public static void main(String[] args) {
        Random r = new Random();
        int sumD = 0;
        int nextRandom = 0;
        int []diceTab=new int[3];
        diceTab=throwDices();
        int leftDiceNumb=diceTab[0];
        int rightDiceNumb=diceTab[1];
        int sidesDiceNumb=diceTab[2];
        for (int i = 0; i < leftDiceNumb; i++) {
            nextRandom = r.nextInt(sidesDiceNumb) + 1;
            sumD += nextRandom;
        }

        sumD += rightDiceNumb;
        System.out.println(sumD);
    }

    static int[] throwDices() {
        String[] diceTab = {"D3", "D4", "D6", "D8", "D10", "D12", "D20", "D100"};
        System.out.println("Rzuć kością używając schematu xDy+z. " +
                "Wybierz z kości: D3, D4, D6, D8, D10, D20, D100.");
        Scanner scan = new Scanner(System.in);
        int digitControl = 1;
        int leftNumb = 0;
        int sidesNumb = 0;
        int rightNumb = 0;
        String str = "";

        while (digitControl == 1) {
            str = scan.next();

            int indexPlus = StringUtils.indexOf(str, '+', 0);
            int strLen = StringUtils.length(str);
            int indexD = StringUtils.indexOf(str, 'D', 0);
            //Czy znaleziono duże D
            if (indexD != -1) {
                digitControl = 0;
            } else {
                System.out.println("W ciągu rzutu kostką nie znaleziono dużej litery D.");
            }

            //początek sprawdzenia schematu rzutu kostką np. D3 czy jest w podanej tablicy
            if (digitControl == 0) {
                String dice = "";
                if (indexPlus == -1) {
                    dice = StringUtils.mid(str, indexD, strLen - indexD);
                } else {
                    dice = StringUtils.mid(str, indexD, indexPlus - indexD);
                }
                int i = 0;
                while (i < diceTab.length) {
                    if (dice.equals(diceTab[i])) {
                        digitControl = 0;
                        break;
                    } else {
                        digitControl = 1;
                    }
                    i++;
                }
                if (digitControl == 1) {
                    System.out.println("Nie znaleziono w początekowej tablicy podanego schematu rzut kostką np. D3");
                }
            }
            //koniec sprawdzanie schematu rzutu kostką np. D3 czy jest w podanej tablicy

            //sprawdzenie czy po prawej stronie plusa znajduje się poprawna wartość
            if (digitControl == 0) {
                String right = "";
                if (indexPlus == -1) {
                    indexPlus = strLen;
                } else {
                    right = StringUtils.right(str, strLen - indexPlus - 1);
                    try {
                        rightNumb = Integer.parseInt(right);
                    } catch (NumberFormatException e) {
                        digitControl = 1;
                        System.out.println("Wartość 'z' z prawej strony plusa - ze schematu xDy+z nie jest liczbą!");
                    }
                }
            }
            //koniec sprawdzania poprawnej wartości po prawej stronie plusa

            //sprawdzenie czy podana wartość podana po lewej stronie od litery D jest liczbą
            if (digitControl == 0) {
                if (indexD == 0) {
                    leftNumb = 1;
                } else {
                    String left = StringUtils.left(str, indexD);
                    try {
                        leftNumb = Integer.parseInt(left);
                    } catch (NumberFormatException e) {
                        digitControl = 1;
                        System.out.println("Wartość 'x' po lewej stronie podanego ciągu znaków - ze schematu zDy+z nie jest liczbą!");
                    }
                }
            }

            //koniec sprawdzania czy podana wartość po lewej stronie od litery D jest liczbą

            // sprawdzenie czy ilość ścian kości jest liczbą
            if (digitControl == 0) {
                String sides = StringUtils.mid(str, indexD + 1, indexPlus - indexD - 1);
                try {
                    sidesNumb = Integer.parseInt(sides);
                } catch (NumberFormatException e) {
                    digitControl = 1;
                    //System.out.println("Ilość ścian kości nie jest liczbą!");
                }
            }
            //koniec sprawdzania czy ilość ścian kości jest liczbą

        }
        int[] leftRightSidesNumberTab = new int[3];
        leftRightSidesNumberTab[0] = leftNumb;
        leftRightSidesNumberTab[1] = rightNumb;
        leftRightSidesNumberTab[2] = sidesNumb;
        return leftRightSidesNumberTab;
    }


}

