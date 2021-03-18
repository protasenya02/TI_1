package com.company;


public class ColumnCipher extends Cipher {

    @Override
    public String cipher(String openText, String key) {
        char[][] matrix = createCipherMatrix(openText, key);
        System.out.println("Matrix before cipher: ");
        printMatrix(matrix);
        sortMatrix(matrix);
        System.out.println("Matrix after cipher: ");
        printMatrix(matrix);

        return getCipherText(matrix);
    }

    @Override
    public String decipher(String cipherText, String key) {

        char[][] matrix = createDecipherMatrix(cipherText, key);
        printMatrix(matrix);

        return convertDecryptionMatrix(matrix);
    }

    // создание матрицы для шифрации
    private char[][] createCipherMatrix(String openText, String key) {

        int colNumb = key.length();
        int rowNumb = (openText.length() - 1) / colNumb + 2;

        char[][] matrix = new char[rowNumb][colNumb];

        // заполнение первой строки ключем
        System.arraycopy(key.toCharArray(), 0, matrix[0], 0, colNumb);

        // заполнение матрицы открытым текстом
        for (int i = 0; i < openText.length(); i++) {
            int m = i / colNumb + 1;
            int n = i % colNumb;

            matrix[m][n] = openText.charAt(i);
        }

        return matrix;
    }

    // получение зашифрованной строки из матрицы
    private String getCipherText(char[][] matrix) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                result.append(matrix[j][i]);
            }
        }

        return result.toString();
    }

    //  сортировка матрицы
    private void sortMatrix(char[][] matrix) {

        for (int i = 0; i < matrix[0].length - 1; i++) {
            for (int j = (matrix[0].length - 1); j > i; j--) {
                if (matrix[0][j - 1] > matrix[0][j]) {
                    for (int k = 0; k < matrix.length; k++) {

                        char temp = matrix[k][j - 1];
                        matrix[k][j - 1] = matrix[k][j];
                        matrix[k][j] = temp;
                    }
                }
            }
        }
    }

    // создание матрицы для шифрации
    private char[][] createDecipherMatrix(String cipherText, String key) {

        int colNumb = key.length();
        int rowNumb = (cipherText.length() - 1) / colNumb + 2;

        char[][] matrix = new char[rowNumb][colNumb];

        // заполнение первой строки ключем
        System.arraycopy(key.toCharArray(), 0, matrix[0], 0, colNumb);

        // заполнение матрицы шифром
        int fullBlocks = cipherText.length() % colNumb;
        int iterator = 0;

        for (int i = 0; i < colNumb; i++) {
            int col = getMinIndex(matrix);

            int blockLen = col < fullBlocks ? rowNumb : rowNumb - 1;

            for (int j = 1; j < blockLen; j++) {
                matrix[j][col] = cipherText.charAt(iterator);
                iterator++;
            }
        }

        return matrix;
    }

    // получение минимального индекса
    private int getMinIndex(char[][] mat) {

        int minId = -1;
        int minVal = 0;

        for (int i = 0; i < mat[0].length; i++) {
            if (mat[1][i] == '\u0000') {
                if (minId == -1 || mat[0][i] < minVal ) {
                    minId = i;
                    minVal = mat[0][minId];
                }
            }
        }

        return minId;
    }

    private String convertDecryptionMatrix(char[][] mat) {

        StringBuilder str = new StringBuilder();

        for (int i = 1; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                str.append(mat[i][j]);
            }
        }

        return str.toString();
    }
}