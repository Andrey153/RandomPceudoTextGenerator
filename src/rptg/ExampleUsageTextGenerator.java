package rptg;

import java.io.*;

/***
 * Created by Andrey Vyalkov on 23.03.2016.
 *
 * It's exemple how to use text generator.
 * If program receive the argument (file name),
 * it will run the  parameters generator for the initialization of the generator.
 * On the frequency distribution of characters.
 * Now available for English and Russian.
 * Posibl generate paramerers for overs languges.
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

 *
 * Without parameters result will be like this:
 *
 *
 <pre>

 English text:

 Hoeddfs nt niil ali onnmyhoa jw eatn ag wcan uuete rc tehh? C n acuwaamhor iea
 utgiriwn na ecu, r tw, lrra tehre orest hy nnmttmo? Aw. Eihi routr mhc.

 Enoeue or ftman rb rkt innv e ad oot, bib ooyonieeea ia nnfin uewoem ttlma
 goniaar of? P wt ssetd gdmi ot eoc hcrftehr tdostp ha tetnh ahe ttios im mpo di
 ec, u linr oenn odn h teylumt aoahnolfbe iidlonede hvd eimh da. Yuyanm tte wlm n
 ihoae, fgt ea stln s cbjnhiha ttadefo oo t stte tens in lo sioi wo feht. Ovmyt hc
 oeaauuiiyt, sm. Aisa gam rdd hd ohsofe nge eiih yowhde oaa, ro hw. Edf l xft
 tighe wmte rrsou, msi oaf lhwrgx ead, hitn, htsnl ufkhhmshe aof tisrad, pwo ett
 euetnw aiaa ion ted faoe weoumg wrtaogwo alottooedst. Eie dwkk n uiomea os.

 Dnp hieh couhr toina t odg f d hntt hhthfe tafl of eeyeai nseti roi tldpcad, r
 hte oaeat nneoaet iatyn aaoeehnnnd. Ueriha, iofi. Wsl drl po nouf gf otha
 ayraysdl gl en oabn dhtnolle. Mctr thne e ohn edni iso c wloiett ffstehfm,
 otydwwo tsl, co ehra dnre rgb neowanerr out on he ih aeuies nvk ogeo sftcsaeafcw
 ht.

 </pre>
 *
 *
 */



public class ExampleUsageTextGenerator {


    public static void main(String[] args) throws IOException {

        if (args.length > 0) {

            ParametersGenerator.alphabet = "abcdefghijklmnopqrstuvwxyz";
            //ParametersGenerator.alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
            ParametersGenerator.generateParametersFromFile(args[0]);

        } else {

            StringBuffer text = new StringBuffer();

            System.out.println("English text: \r\n");

            RandomPceudoTextGenerator randomPceudoTextGeneratorEN = new RandomPceudoTextGenerator("EN");
            randomPceudoTextGeneratorEN.generateText(text);

            System.out.println(text);

            System.out.println("\r\nРусский текст: \r\n");

            RandomPceudoTextGenerator randomPceudoTextGeneratorRU = new RandomPceudoTextGenerator("RU");
            randomPceudoTextGeneratorRU.generateText(text);

            System.out.println(text);

        }

    }


}
