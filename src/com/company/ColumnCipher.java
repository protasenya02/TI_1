package com.company;

// класс cтолбцового метода шифрования
public  class ColumnCipher extends Cipher {

    // алфавит
    String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // символ для заполнения матрицы
    final char fillSymb = ' ';

    // определение количества строк в матрице
    private int GetLineNumb(int textlength, int keyLenght) {
        int lineNumb = 1;
        return lineNumb += (textlength % keyLenght == 0)
                ? (textlength / keyLenght) : (textlength / keyLenght + 1);
    }

    // запись ключа в 0 строку матрицы
    private void WriteKeyToMatrix(char[][] matrix, int colNumb, String key) {
        for(int j=0; j<colNumb; j++){
            matrix[0][j] = key.charAt(j);
        }
    }

    // запись текста в матрицу с 1 строки по строкам
    private void WriteToMatrixInLine(String str, char[][] matrix, int lineNumb, int colNumb) {
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
    private String GetCipherFromMatrix(char[][] matrix, int lineNumb, int colNumb) {

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
    private void WriteToMatrixInCol(String str, char[][] matrix, int lineNumb, int colNumb) {
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
    private int[] GetKeyIndex (char matrix[][], int colNumb) {

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
    private char[][] MatrixPermutation(char[][] matrix, int lineNumb, int colNumb, int keyArr[]) {

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
    private String GetOpenTextFromMatrix(char[][] matrix, int lineNumb, int colNumb) {

        String result = "";
        for(int i=1; i < lineNumb; i++){
            for(int j=0;j < colNumb;j++) {
                result += matrix[i][j];
            }
        }
        return result;
    }

    @Override
    public String Encode(String openText, String key) {

        // получение количества символов в ключе
        int keyNumb = key.length();
        // определение количества строк в матрице
        int lineNumb = GetLineNumb(openText.length(), keyNumb);
        // создание матрицы для записи ключа и открытого текста
        char[][]  ciperMatrix = CreateMatrix(fillSymb, lineNumb, keyNumb);
        // запись ключа в матрицу
        WriteKeyToMatrix(ciperMatrix, keyNumb, key);
        // запись открытого текста в матрицу по строкам
        WriteToMatrixInLine(openText, ciperMatrix, lineNumb, keyNumb);
        System.out.println("Matrix before cipher:");
        PrintMatrix(ciperMatrix);
        // возврат шифра
        return GetCipherFromMatrix(ciperMatrix, lineNumb, keyNumb);
    }


    @Override
    public String Decode(String cipherText, String key) {
        // получение количества символов в ключе
        int keyNumb = key.length();
        // определение количества строк в матрице
        int lineNumb = GetLineNumb(cipherText.length(), keyNumb);
        // создание матрицы для записи ключа и открытого текста
        char[][]  cipherMatrix = CreateMatrix(fillSymb, lineNumb, keyNumb);
        // запись ключа в матрицу
        WriteKeyToMatrix(cipherMatrix, keyNumb, key);
        // запись шифра в матрицу по столбцам
        WriteToMatrixInCol(cipherText, cipherMatrix, lineNumb, keyNumb);
        System.out.println("Matrix before decipher:");
        PrintMatrix(cipherMatrix);
        // получение массива индексов ключа
        int[] keyArr = GetKeyIndex(cipherMatrix, keyNumb);
        // перестановка столбцов в матрице по индексам  ключа
        cipherMatrix = MatrixPermutation(cipherMatrix, lineNumb, keyNumb, keyArr);
        System.out.println("Matrix after decipher:");
        PrintMatrix(cipherMatrix);
        // возврат расшифрованного текста
        return  GetOpenTextFromMatrix(cipherMatrix, lineNumb, keyNumb);
    }
}
