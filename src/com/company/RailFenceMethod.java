package com.company;

// класс метода железнодорожной изгороди
public class RailFenceMethod extends Cipher {

    // символ для заполнения матрицы
    final char fillSymb = '.';

    // запись открытого текста в матрицу
    private void WriteOpenTextToMatrix(String openText, char[][] matrix, int lineNumb, int colNumb) {

        //int textIndex = 0;
        int counter = 0;
        boolean isTop = false;
        for(int j=0; j < colNumb; j++){

            matrix[counter][j] = openText.charAt(j);

            if(counter == lineNumb-1) {
                isTop = true;
            }

            if(isTop) {
                counter--;
            }else {
                counter++;
            }
        }
    }


    @Override
    public String Encode(String openText, String key) {
        // создание матрицы
        char[][]  cipherMatrix = CreateMatrix(fillSymb, key.length(), openText.length());
        WriteOpenTextToMatrix(openText, cipherMatrix, openText.length(), key.length());
        PrintMatrix(cipherMatrix);


        return  "0";

    }


    @Override
    public String Decode(String openText, String key) {
        return  "0";
    }
}