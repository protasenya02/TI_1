package com.company;

public abstract class Cipher {

    // метод  шифрования
    public abstract String Encode(String openText, String key);
    // метод дешифрования
    public abstract String Decode(String cipherText, String key);

    // создание матрицы
    static char[][] CreateMatrix(char symb, int lineNumb, int colNumb) {
        char[][]  cipherMatrix = new char[lineNumb][colNumb];
        // заполнение всей матрицы пробелами
        for(int i=0; i < lineNumb; i++){
            for(int j=0; j < colNumb; j++){
                cipherMatrix[i][j] = symb;
            }
        }
        return cipherMatrix;
    }

    // печать матрицы
    static void PrintMatrix(char[][] matrix) {

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}