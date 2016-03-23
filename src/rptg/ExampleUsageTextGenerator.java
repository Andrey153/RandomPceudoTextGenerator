package rptg;

import java.io.*;

/**
 * Created by Andrey Vyalkov on 23.03.2016.
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
