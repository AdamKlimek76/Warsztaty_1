package src.zadanie2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        int[] randTab = randLottoTab();

        int[] giveTab = new int[6];
        int i = 0;

        while (i < 6) {
            int giveNumb = giveLottoNumb(i);
            if (giveNumb >= 1 && giveNumb <= 49) {
                if (i > 0) {
                    int k = 0;
                    int l = 0;
                    while (k < i) {
                        if (giveTab[k] == giveNumb) {
                            l = 1;
                            break;
                        }
                        k++;
                    }
                    if (l == 0) {
                        giveTab[i] = giveNumb;
                        i++;
                    } else {
                        System.out.println("Taka liczba już była podana!");
                    }
                } else {
                    giveTab[i] = giveNumb;
                    i++;
                }
            } else {
                System.out.println("Podana liczba nie należy do zakresu od 1 do 49!");
            }
        }
        Arrays.sort(giveTab);
        System.out.println("Podano następujące liczby: " + Arrays.toString(giveTab));
        System.out.println("Wylosowano liczby: " + Arrays.toString(randTab));
        int hitsNumber = 0;
        for (int j = 0; j < giveTab.length; j++) {
            for (int k = 0; k < randTab.length; k++) {
                if (giveTab[j] == randTab[k]) {
                    hitsNumber++;
                }
            }
        }

        if (hitsNumber<3){
            System.out.println("Nie trafiono nawet trójki!");
        }else if(hitsNumber==3){
            System.out.println("Trafiono trójkę!");
        }else if(hitsNumber==4){
            System.out.println("Trafiono czwórkę!");
        }else if(hitsNumber==5){
            System.out.println("Trafiono piątkę! Jesteś nawet kasiasty!");
        }else if(hitsNumber==6){
            System.out.println("Trafiono szóstkę! Z bogaczami nie gadam!");
        }

    }

    static int[] randLottoTab() {
        Integer[] arr = new Integer[49];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        Collections.shuffle(Arrays.asList(arr));
        String str = Arrays.toString(arr);
        String[] randTab = str.split(", ");

        int[] ranLottoTab = new int[6];
        int index = 0;
        for (int i = 1; i < 7; i++) {
            ranLottoTab[index] = Integer.parseInt(randTab[i]);
            index++;
        }
        Arrays.sort(ranLottoTab);
        return ranLottoTab;

    }

    static int giveLottoNumb(int nextNumber) {
        int nexNumb = nextNumber + 1;
        System.out.println("Podaj liczbę nr " + nexNumb + " z zakresu od 1 do 49.");
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.println("Podana wartość nie jest liczbą!");
            scan.next();
        }

        return scan.nextInt();
    }
}
