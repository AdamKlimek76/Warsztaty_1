package src.zadanie5;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main5 {

    public static void main(String[] args) {
        String[] urlAddresTab = {"http://www.onet.pl/", "http://sport.onet.pl/", "http://www.interia.pl/"};
        String[] exWordsTab = {"oraz", "ponieważ", "wtedy", "jeżeli", "jeśli", "bardzo", "jest", "jestem", "jesteś", "jesteście", "jesteśmy",
                "było", "byli", "była", "będzie", "będą", "coraz", "sobie", "tobie", "tego", "jego", "nasz", "wasz", "nasze", "wasze", "naszych",
                "waszych", "które", "która", "których", "którą", "jaki", "jaka", "jakie", "wówczas", "każdy", "każda", "każdą", "taka", "taki",
                "taką"};
        String popularWordsFile = "popular_words.txt";
        String filteredWordsFile="filtered_popular_words.txt";
        writePopularWords(urlAddresTab, popularWordsFile);
        writefilteredPopularWords(exWordsTab, popularWordsFile, filteredWordsFile);
    }
    //zapis popularnych słów

    static void writePopularWords(String[] urlTab, String fileName) {

        String popularWords = "";
        for (String urlAddres : urlTab) {
            Connection connect = Jsoup.connect(urlAddres);
            StringBuilder strbuild = new StringBuilder();
            try {
                Document document = connect.get();
                org.jsoup.select.Elements links = document.select("span.title");

                for (Element elem : links) {
                    strbuild.append(elem.text()).append(" ");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            popularWords = strbuild.toString();


            String onePopularWordTemp = "";
            Path path = Paths.get(fileName);
            if (Files.exists(path) == false) {
                try {
                    Files.createFile(path);
                } catch (IOException e) {
                    System.out.println("Błąd IOException");
                }
            }

            StringTokenizer popularWordsTok = new StringTokenizer(popularWords);
            try {
                FileWriter fileWriter = new FileWriter(fileName, true);
                while (popularWordsTok.hasMoreTokens()) {
                    onePopularWordTemp = popularWordsTok.nextToken(",.!:?;'- \n\"");
                    if (onePopularWordTemp.length() > 3) {
                        fileWriter.append(onePopularWordTemp + " ");
                    }
                }
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Błąd zapisu do pliku.");
            }
        }
        System.out.println("Zapisano plik " + fileName);
    }
    //koniec zapisu popularnych słów

    //zapis wyfiltrowanych słów
    static void writefilteredPopularWords(String[] excludedWordsTab, String fileName, String filteredFileName) {

        File popularWordFile = new File(fileName);
        filteredFileName = "filtered_popular_words.txt";
        Path filteredPath = Paths.get(filteredFileName);
        boolean isIOExeption = false;
        if (Files.exists(filteredPath) == false) {
            try {
                Files.createFile(filteredPath);
            } catch (IOException e) {
                System.out.println("Błąd IOException");
                isIOExeption = true;
            }
        }

        if (isIOExeption == false) {
            try {
                Scanner scan = new Scanner(popularWordFile);
                String oneLinePopularWords = "";
                boolean isExludedWord = false;
                while (scan.hasNextLine()) {
                    oneLinePopularWords = scan.nextLine();
                    String[] oneLineTab = oneLinePopularWords.split(" ");
                    StringBuilder oneLineBuild = new StringBuilder();
                    for (int i = 0; i < oneLineTab.length; i++) {
                        isExludedWord = false;
                        oneLineTab[i] = oneLineTab[i].toLowerCase();
                        for (int j = 0; j < excludedWordsTab.length; j++) {
                            if (oneLineTab[i].equals(excludedWordsTab[j])) {
                                isExludedWord = true;
                                break;
                            }
                        }
                        if (isExludedWord == false) {
                            oneLineBuild = oneLineBuild.append(oneLineTab[i]).append(" ");
                        }
                    }
                    oneLinePopularWords = oneLineBuild.toString();
                    try {
                        FileWriter fileWriter = new FileWriter(filteredFileName, true);
                        fileWriter.append(oneLinePopularWords).append(' ');
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Nie znaleziono pliku popular_word.txt");
            }
        }
        System.out.println("Zapisano plik " + filteredFileName);
    }

}
