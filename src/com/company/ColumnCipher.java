package com.company;


// класс cтолбцового метода шифрования
public  class ColumnCipher extends Cipher {

    // алфавит
    String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // символ для заполнения матрицы
    final char fillSymb = ' ';

    // определение количества строк в матрице
    private int getLineNumb(int textlength, int keyLenght) {
        int lineNumb = 1;
        return lineNumb += (textlength % keyLenght == 0)
                ? (textlength / keyLenght) : (textlength / keyLenght + 1);
    }

    // запись ключа в 0 строку матрицы
    private void writeKeyToMatrix(char[][] matrix, int colNumb, String key) {
        for(int j=0; j<colNumb; j++){
            matrix[0][j] = key.charAt(j);
        }
    }

    // запись текста в матрицу с 1 строки по строкам
    private void writeToMatrixInLine(String str, char[][] matrix, int lineNumb, int colNumb) {
        int textIndex = 0;
        for(int i=1; i < lineNumb; i++) {
            for (int j = 0; j < colNumb; j++) {
                if ( textIndex < str.length()) {
                    matrix[i][j] = str.charAt(textIndex);
                    textIndex++;
                }
            }
        }
    }

    // получение шифра из матрицы по столбцам
    private String getCipherFromMatrix(char[][] matrix, int lineNumb, int colNumb) {

        String result = "";
        for(int i=0; i < alphabet.length(); i++){
            for(int j=0; j < colNumb; j++){
                if (matrix[0][j] == alphabet.charAt(i)){
                    for(int k=1; k<lineNumb; k++) {
                        result += matrix[k][j];
                    }
                }
            }
        }
        return result;
    }

    // запись текста в матрицу с 1 строки по столбцам
    private void writeToMatrixInCol(String str, char[][] matrix, int lineNumb, int colNumb) {
        int textIndex = 0;
        // запись открытого текста в матрицу
        for(int j=0; j < colNumb; j++){
            for(int i=1; i< lineNumb; i++){
                if ( textIndex < str.length()) {
                    matrix[i][j] = str.charAt(textIndex);
                    textIndex++;
                }
            }
        }
    }

    // получение массива индексов ключа
    private int[] getKeyIndex(char matrix[][], int colNumb) {

        int[] keyArr = new int[colNumb];
        int counter = 0;
        for(int i=0; i < alphabet.length(); i++){
            for(int j=0; j < colNumb; j++){
                if (matrix[0][j] == alphabet.charAt(i)){
                    keyArr[j] = counter;
                    counter++;
                }
            }
        }
        return keyArr;
    }

    // перестановка столбцов для получения шифра
    private char[][] matrixPermutation(char[][] matrix, int lineNumb, int colNumb, int keyArr[]) {

        char[][] newMatrix = new char[lineNumb][colNumb];
        for(int j=0; j< colNumb; j++){
            for(int i=0; i<keyArr.length; i++){
                if (j == keyArr[i]){
                    for(int m=0; m<lineNumb; m++){
                        newMatrix[m][i] = matrix[m][j];
                    }
                }
            }
        }
        return newMatrix;
    }

    // получение открытого текста из матрицы по строкам
    private String getOpenTextFromMatrix(char[][] matrix, int lineNumb, int colNumb) {

        String result = "";
        for(int i=1; i < lineNumb; i++){
            for(int j=0;j < colNumb;j++) {
                result += matrix[i][j];
            }
        }
        return result;
    }

    @Override
    public String cipher(String openText, String key) {

        // получение количества символов в ключе
        int keyNumb = key.length();
        // определение количества строк в матрице
        int lineNumb = getLineNumb(openText.length(), keyNumb);
        // создание матрицы для записи ключа и открытого текста
        char[][]  cipherMatrix = createMatrix(fillSymb, lineNumb, keyNumb);
        // запись ключа в матрицу
        writeKeyToMatrix(cipherMatrix, keyNumb, key);
        // запись открытого текста в матрицу по строкам
        writeToMatrixInLine(openText, cipherMatrix, lineNumb, keyNumb);
        System.out.println("Matrix before cipher:");
        printMatrix(cipherMatrix);
        // возврат шифра
        return getCipherFromMatrix(cipherMatrix, lineNumb, keyNumb);
    }


    @Override
    public String decipher(String cipherText, String key) {
        // получение количества символов в ключе
        int keyNumb = key.length();
        // определение количества строк в матрице
        int lineNumb = getLineNumb(cipherText.length(), keyNumb);
        // создание матрицы для записи ключа и открытого текста
        char[][]  cipherMatrix = createMatrix(fillSymb, lineNumb, keyNumb);
        // запись ключа в матрицу
        writeKeyToMatrix(cipherMatrix, keyNumb, key);
        // запись шифра в матрицу по столбцам
        writeToMatrixInCol(cipherText, cipherMatrix, lineNumb, keyNumb);
        System.out.println("Matrix before decipher:");
        printMatrix(cipherMatrix);
        // получение массива индексов ключа
        int[] keyArr = getKeyIndex(cipherMatrix, keyNumb);
        // перестановка столбцов в матрице по индексам  ключа
        cipherMatrix = matrixPermutation(cipherMatrix, lineNumb, keyNumb, keyArr);
        System.out.println("Matrix after decipher:");
        printMatrix(cipherMatrix);
        // возврат расшифрованного текста
        return  getOpenTextFromMatrix(cipherMatrix, lineNumb, keyNumb);
    }
}
