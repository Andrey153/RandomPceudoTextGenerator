package rptg;

import java.util.Random;

/**
 * Created by Andrey Vyalkov on 23.03.2016.
 * minor changes 2
 */

public class RandomPceudoTextGenerator {

    boolean newLineEverySentence = false;
    int widthText = 80;

    public static int[][] weightOfLetters;
    public static int[][] weightOfSign;

    public static int percentChanceOfComma;

    public static int[] weightNumberOfletters;
    public static int[] weightNumberOfWords;

    public static int[] weightNumberOfSentences;
    public static int[] weightNumberOfParagraphs;

    private int[] lineWeightNumberOfletters;
    private int[] lineWeightNumberOfWords;
    private int[] lineWeightNumberOfSentences;
    private int[] lineWeightNumberOfParagraphs;
    private int[] lineWeightOfLetters;
    private int[] lineWeightOfSign;

    private Random random = new Random();
    private int currentPos = 0;


    // convert {1,2,1,0} to {1,3,4,4}


    static int[] calculateLineWeight(int[] weight) {

        int wl = weight.length;
        int lineWeight[] = new int[wl];
        int sum = 0;

        for ( int i = 0 ; i < wl ; i++ ) {
            sum = sum + weight[i];
            lineWeight[i] = sum;
        }
        return lineWeight;
    }

    // convert {['a',1],{'b',2},{'c',1}} to {1,3,4}
    static int[] calculateLineWeightForAlphapet(int[][] weight) {

        int wl = weight.length;
        int lineWeight[] = new int[wl];
        int sum = 0;

        for ( int i = 0 ; i < wl ; i++ ) {
            sum = sum + weight[i][1];
            lineWeight[i] = sum;
        }
        return lineWeight;
    }

    /**
     *
     * @param lineWeight Array of integer with weight
     * @return Array of integer with for genenerate numberes whith existing distribution.
     */
    int takeIndex(int[] lineWeight){
        int lwl = lineWeight.length;
        int r = random.nextInt(lineWeight[lwl-1]) + 1;

        for (int i = 0; i < lwl; i++) {
            if (r <= lineWeight[i]) return i;
        }
        return 0;
    };

    public void init(){

        lineWeightNumberOfletters = calculateLineWeight(weightNumberOfletters);
        lineWeightNumberOfWords = calculateLineWeight(weightNumberOfWords);
        lineWeightNumberOfSentences = calculateLineWeight(weightNumberOfSentences);
        lineWeightNumberOfParagraphs = calculateLineWeight(weightNumberOfParagraphs);

        lineWeightOfLetters = calculateLineWeightForAlphapet(weightOfLetters);
        lineWeightOfSign = calculateLineWeightForAlphapet(weightOfSign);

    }


    /**
     * Contructor initialize variable of object. such as weit of leters in alphabet
     *
     *
     * @param language Language wich use for text generating.
     *                 current available mining is Russian "RU" in other casses use English.
     */
    RandomPceudoTextGenerator(String language) {

        if (language == "RU") {

            weightOfLetters = new int[][] {
                    {'\u0430',   45176}, //а
                    {'\u0431',    9302}, //б
                    {'\u0432',   24794}, //в
                    {'\u0433',   11168}, //г
                    {'\u0434',   16380}, //д
                    {'\u0435',   42472}, //е
                    {'\u0451',     431}, //ё
                    {'\u0436',    5456}, //ж
                    {'\u0437',    9592}, //з
                    {'\u0438',   35789}, //и
                    {'\u0439',    6203}, //й
                    {'\u043a',   19315}, //к
                    {'\u043b',   27262}, //л
                    {'\u043c',   15921}, //м
                    {'\u043d',   35097}, //н
                    {'\u043e',   61229}, //о
                    {'\u043f',   13837}, //п
                    {'\u0440',   24545}, //р
                    {'\u0441',   28103}, //с
                    {'\u0442',   30590}, //т
                    {'\u0443',   15444}, //у
                    {'\u0444',    1205}, //ф
                    {'\u0445',    4595}, //х
                    {'\u0446',    2179}, //ц
                    {'\u0447',    7340}, //ч
                    {'\u0448',    5087}, //ш
                    {'\u0449',    1511}, //щ
                    {'\u044a',     283}, //ъ
                    {'\u044b',   10222}, //ы
                    {'\u044c',   10491}, //ь
                    {'\u044d',    1628}, //э
                    {'\u044e',    3495}, //ю
                    {'\u044f',   12469}, //я
            };

            weightOfSign = new int[][] {
                    {'\u002e',    6648}, //.
                    {'\u0021',     888}, //!
                    {'\u003f',     806}, //?
            };

            percentChanceOfComma = 16;

            weightNumberOfletters = new int[] {
                    11713,12181,12560,9749,12766,12600,10494,7809,5460,3916,2300,1402,693,341,173,61,55,78,5,6,
            };

            weightNumberOfWords = new int[] {
                    311,569,523,484,491,436,372,346,360,328,276,258,261,237,249,192,185,171,152,170,121,110,114,103,94,84,89,82,62,61,
            };


        } else {

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


        weightNumberOfSentences = new int[] {
                0,2,3,4,5,6,7,6,5,4,3,2,1
                //0,1
        };
        weightNumberOfParagraphs = new int[] {
                1,2,3,4,5,5,4,3,2,1,
                //0,1,0,
        };

        init();

    }

    /**
     * generate text
     * @param str String buffer in wich add generated text.
     */
    public void generateText(StringBuffer str) {

        int sizeOfText = takeIndex(lineWeightNumberOfParagraphs) + 1;

        generateParagraph(str);

        for(int i = 1; i < sizeOfText; i++){
            str.append("\r\n\r\n");
            currentPos = str.length();
            generateParagraph(str);
        }

    }


    public void generateParagraph(StringBuffer str) {

        int sizeOfParagraph = takeIndex(lineWeightNumberOfSentences) + 1;

        generateSentence(str);

        for(int i = 1; i < sizeOfParagraph; i++){
            if (newLineEverySentence) {
                str.append("\r\n");
                currentPos = str.length();
            } else {
                str.append(' ');
            }
            generateSentence(str);
        }

    }



    public void generateSentence(StringBuffer str) {

        int r;
        int sizeOfSentence = takeIndex(lineWeightNumberOfWords) + 1;

        generateWord(str,true);

        for(int i = 1; i < sizeOfSentence; i++){

            //comma
            r = random.nextInt(100) + 1;
            if (r < percentChanceOfComma) {
                str.append(',');
            }



            str.append(' ');



            generateWord(str,false);


        }

        //sign
        str.append((char) weightOfSign[takeIndex(lineWeightOfSign)][0]);

    }


    public void generateWord(StringBuffer str, boolean firstUp) {

        int sizeOfWord = takeIndex(lineWeightNumberOfletters) + 1;

        if (widthText > 0) {
            if (str.length() - currentPos + sizeOfWord > widthText + 1) {
                str.append("\r\n");
                currentPos = str.length();
            }
        }


        if (firstUp) {
            str.append(Character.toUpperCase((char) weightOfLetters[takeIndex(lineWeightOfLetters)][0]));
        } else {
            str.append((char) weightOfLetters[takeIndex(lineWeightOfLetters)][0]);
        }

        for(int i = 1; i < sizeOfWord; i++){
            str.append( (char) weightOfLetters[takeIndex(lineWeightOfLetters)][0] );
        }

    }


}
