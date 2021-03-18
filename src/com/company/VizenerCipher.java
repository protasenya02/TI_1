package com.company;
import java.util.Locale;


// класс метода шифрования Виженера
public class VizenerCipher extends Cipher {


    @Override
    public String cipher(String openText, String key) {

        char[][] cipherTable = genTable();
        // printMatrix(cipherTable);
        key = createNewKey(openText, key);
        //System.out.println();
        System.out.println(openText);
        System.out.println(key);

        return getCipherText(cipherTable, openText, key);
    }

    @Override
    public String decipher(String cipherText, String key) {

        char[][] cipherTable = genTable();
        key = createNewKey(cipherText, key);
        System.out.println(cipherText);
        System.out.println(key);

        return getOpenText(cipherTable, cipherText, key);
    }

    // cоздание матрицы подстановки
    private char[][] genTable() {

        char[][] table = new char[26][26];

        for (int i = 0; i < 26; i++) {

            int off = i;
            for (int j = 0; j < 26; j++) {
                if(off == 26) {
                    off = 0;
                }
                table[i][j] = (char)(65 + off);
                off++;
            }
        }

        return table;
    }

    // подгон ключа под открытый текст
    private String createNewKey(String opentText, String key) {

        String newKey = "";
        int index = 0;

        for(int i = 0; i < opentText.length(); i++) {
            newKey += key.charAt(index);
            index++;

            if(index == key.length()) {
                index = 0;
            }
        }

        return newKey;
    }

    // получениие шифра из матрицы подстановки
    private String getCipherText(char[][] matrix, String openText, String key) {

        String result = "";
        int k = 0; int t = 0;

        for (int i = 0; i < openText.length(); i++) {
            k = (int)key.charAt(i) - 65;
            t = (int)openText.charAt(i) - 65;
            result += matrix[k][t];
        }

        return result;
    }

    // получениие открытого текста из матрицы подстановки
    private String getOpenText(char[][] matrix, String cipherText, String key) {

        String result = "";
        int k = 0;
        int t = 0;

        for (int i = 0; i < cipherText.length(); i++) {
            k = (int) key.charAt(i) - 97;
            t = (int) cipherText.charAt(i) - 97;

            if (k > t) {
                result += matrix[26 + (t - k)][0];
            } else {
                result += matrix[t - k][0];
            }
        }

        return result;
    }
}
