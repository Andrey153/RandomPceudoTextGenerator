package rptg;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Andrey Vyalkov on 23.03.2016.
 */


public class ParametersGenerator {

    public static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    //public static String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static String signs = ".!?";

    public static int maxSizeWord = 20;
    public static int maxSizeSentence = 30;

    public static void generateParametersFromFile(String arg)  throws IOException {

        int c = 0;

        int sizeWord = 0;
        int sizeSentence = 0;

        int numberOfComma = 0;
        int allNumberOfWord = 0;
        int allNumberOfSentence = 0;

        int[] sizeWordArray = new int[maxSizeWord];
        int[] sizeSentenceArray = new int[maxSizeSentence];

        int[] numOfLetters = new int[alphabet.length()];
        int[] numOfSign = new int[signs.length()];

        boolean wordBegin = false;
        boolean sentenceBegin = false;

        int index;

        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        FileReader fStream = new FileReader(arg);

        while (true) {
            c = Character.toLowerCase(fStream.read());
            if (c == -1) break;

            index = alphabet.indexOf(c);
            if (index != -1) {                                //it's letter

                numOfLetters[index]++;

                if (wordBegin == false) {                     //wordd begin
                    wordBegin = true;
                    sizeWord = 1;
                } else {
                    sizeWord++;
                }
                if (sentenceBegin == false) {                 //sentence begin
                    sentenceBegin = true;
                    sizeSentence = 0;
                }

            } else {
                if (wordBegin == true) {                      //word end
                    if (sizeWord <= maxSizeWord) {
                        sizeWordArray[sizeWord - 1]++;
                    }
                    wordBegin = false;
                    sizeSentence++;
                    allNumberOfWord++;
                }
            }


            index = signs.indexOf(c);
            if (index != -1) {                                 //it's sign

                numOfSign[index]++;

                if (sentenceBegin == true) {                   //sentence end
                    sentenceBegin = false;
                    allNumberOfSentence++;
                    if (sizeSentence <= maxSizeSentence) {
                        sizeSentenceArray[sizeSentence - 1]++;
                    }
                }
            }

            if (c == ',') {                           //it's comma
                numberOfComma++;
            }

        }

        //===============================  output result to screen  ===========================

        System.out.println();
        System.out.println("weightOfLetters = new int[][] {");
        int i;
        for(i = 0; i < alphabet.length(); i++) {
            System.out.format("   {'\\u%04x', %7d}, //%s\r\n", (int) alphabet.charAt(i), numOfLetters[i], alphabet.charAt(i));
        }
        System.out.println("};");
        System.out.println();

        System.out.println("weightOfSign = new int[][] {");
        for(i = 0; i < signs.length(); i++) {
            System.out.format("   {'\\u%04x', %7d}, //%s\r\n", (int) signs.charAt(i), numOfSign[i], signs.charAt(i));
        }
        System.out.println("};");
        System.out.println();

        System.out.println("percentChanceOfComma = " +
                numberOfComma*100/(allNumberOfWord - allNumberOfSentence) +
                ";");
        System.out.println();

        System.out.println("weightNumberOfletters = new int[] {");
        //1,2,3,4,5,6,7,6,5,4,3,2,1
        System.out.print("   ");
        for(i = 0; i < sizeWordArray.length; i++) {
            System.out.print(sizeWordArray[i] + ",");
        }
        System.out.println();
        System.out.println("};");
        System.out.println();

        System.out.println("weightNumberOfWords = new int[] {");
        //1,2,3,4,5,6,7,6,5,4,3,2,1
        System.out.print("   ");
        for(i = 0; i < sizeSentenceArray.length; i++) {
            System.out.print(sizeSentenceArray[i] + ",");
        }
        System.out.println();
        System.out.println("};");
        System.out.println();

    }

}
