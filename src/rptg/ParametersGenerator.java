package rptg;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Andrey Vyalkov on 23.03.2016.
 *
 * If program receive the argument (file name),
 * it will run the  parameters generator for the initialization of the generator.
 * On the frequency distribution of characters.
 * Now available for English and Russian.
 * Poseble generate parameters for overs languges.
 * The resul will be like this
 *
 *
 <pre>
 {@code
 weightOfLetters = new int[][] {
 {'\u0061',   39553}, //a
 {'\u0062',    7642}, //b
 {'\u0063',   10656}, //c
 {'\u0064',   22787}, //d
 {'\u0065',   57748}, //e
 {'\u0066',   11817}, //f
 {'\u0067',    9686}, //g
 {'\u0068',   31360}, //h
 {'\u0069',   33355}, //i
 {'\u006a',     364}, //j
 {'\u006b',    3430}, //k
 {'\u006c',   17227}, //l
 {'\u006d',   13735}, //m
 {'\u006e',   32140}, //n
 {'\u006f',   38671}, //o
 {'\u0070',    7456}, //p
 {'\u0071',     322}, //q
 {'\u0072',   25724}, //r
 {'\u0073',   27427}, //s
 {'\u0074',   46698}, //t
 {'\u0075',   13275}, //u
 {'\u0076',    5115}, //v
 {'\u0077',   13364}, //w
 {'\u0078',     616}, //x
 {'\u0079',   10026}, //y
 {'\u007a',     194}, //z
 };

 weightOfSign = new int[][] {
 {'\u002e',    2325}, //.
 {'\u0021',      79}, //!
 {'\u003f',     137}, //?
 };

 percentChanceOfComma = 9;

 weightNumberOfletters = new int[] {
 7695,24439,27509,24780,13877,8787,6058,3434,2361,1530,739,246,160,22,11,0,0,0,0,0,
 };

 weightNumberOfWords = new int[] {
 99,25,26,21,17,26,27,25,22,26,35,22,23,29,29,19,27,25,32,32,21,31,35,34,38,33,28,36,34,28,
 };
 }
 </pre>

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
