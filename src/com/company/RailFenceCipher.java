package com.company;


// класс метода железнодорожной изгороди
public class RailFenceCipher extends Cipher {

    // символ для заполнения матрицы
    final char fillSymb = '.';

    @Override
    public String cipher(String openText, String key) {
        // создание матрицы
        char[][]  cipherMatrix = createMatrix(fillSymb, key.length(), openText.length());
        writeOpenTextToMatrix(openText, cipherMatrix, key.length(), openText.length());
        printMatrix(cipherMatrix);
        // возврат зашифрованной  строки
        return getCipherText(cipherMatrix);
    }

    @Override
    public String decipher(String cipherText, String key) {
        // создание матрицы
        char[][]  cipherMatrix = createMatrix(fillSymb, key.length(), cipherText.length());
        // проставление  символов
        writeSymbToMatrix(cipherMatrix, key.length(), cipherText.length(), '_');
        System.out.println("Matrix before add cipher text:");
        printMatrix(cipherMatrix);
        // запись шифра в матрице на место символов
        writeCipherTextToMatrix(cipherMatrix, cipherText.length(), key.length(), cipherText);
        System.out.println("Matrix after add cipher text:");
        printMatrix(cipherMatrix);
        // получение расшифрованной строки
        return getOpenTextFromMatrix(cipherMatrix, cipherText.length(), key.length());
    }

    // запись открытого текста в матрицу
    private void writeOpenTextToMatrix(String openText, char[][] matrix, int lineNumb, int colNumb) {

        int period = 2 * (lineNumb - 1);

        for (int i = 0; i < openText.length(); i++) {
            int ost = i % period;
            int row = lineNumb - 1 - Math.abs(lineNumb - 1 - ost);
            matrix[row][i] = openText.charAt(i);
        }
    }

    // получение зашифрованного текста
    private String getCipherText(char[][] matrix){

        String result = "";

        for(int i=0;i<matrix.length;i++){
            for(int j=0; j<matrix[i].length; j++){
                if(matrix[i][j] != fillSymb) {
                    result += matrix[i][j];
                }
            }
        }

        return result;
    }

    // запись символов в матрицу
    private void writeSymbToMatrix(char[][] matrix, int lineNumb, int colNumb, char symb) {

        int index = 0;
        int period = 2 * (lineNumb - 1);

        for (int i = 0; i < colNumb; i++) {
            int ost = i % period;
            int row = lineNumb - 1 - Math.abs(lineNumb- 1 - ost);
            matrix[row][i] = symb;
        }
    }

    // запись шифра в матрицу на место символов
    private void writeCipherTextToMatrix(char[][] matrix, int colNumb, int lineNumb, String cipherText) {

        int textIndex = 0;

        for (int i = 0; i < lineNumb; i++) {
            for(int j=0; j < colNumb; j++){
                if(matrix[i][j] == '_'){
                    matrix[i][j] = cipherText.charAt(textIndex);
                    textIndex++;
                }
            }
        }
    }

    // получение расшифрованной строки из марицы
    private String getOpenTextFromMatrix(char[][] matrix, int colNumb, int lineNumb) {

        String result = "";
        int period = 2 * (lineNumb - 1);

        for (int i = 0; i < colNumb; i++) {
            int ost = i % period;
            int row = lineNumb - 1 - Math.abs(lineNumb - 1 - ost);
            result += matrix[row][i];
        }

        return result;
    }

}