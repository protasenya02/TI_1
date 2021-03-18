package com.company;


import java.util.Scanner;


// класс метода поворачивающейся решетки
public class RotatingGridCipher extends Cipher {

    // создание символьной матрицы и заполнение рандомными символами
    private char[][] createCharMatrix(int n){

        char[][] matrix = new char[n][n];
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (char)((int) (Math.random() * 26) + 'A');
            }
        }
        return matrix;
    }

    // создание числовой матрицы
    private static int[][] createNumbMatrix(int n){

        int numbMatrix[][] = new int[n][n];

        // заполнение матрицы цифрами
        for (int rotateCount = 0; rotateCount < 4 ; rotateCount++){
            int numb = 1 ;
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2; j++) {
                    numbMatrix[i][j] = numb;
                    numb++;
                }
            }
            numbMatrix = rotateMatrix(numbMatrix);
        }
        return numbMatrix;
    }

    // печать цифровой матрицы
    private static void printIntMatrix(int[][] matrix){

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // вырезание отверстий в логической матрице на основе цифровой
    private static void cutHoles(int[][] boolMatrix, int[][] numbMatrix){

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < (boolMatrix.length/2)*(boolMatrix.length/2); i++){

            boolean wasInputed = false;
            while (!wasInputed){

                System.out.println("Input hole coordinates for hole №" + (i+1) + ":");
                System.out.print("i: ");
                int x = scanner.nextInt();
                System.out.print("j: ");
                int y = scanner.nextInt() ;

                if (numbMatrix[x][y] == (i + 1)){
                    boolMatrix[x][y] = 1;
                    wasInputed = true;
                } else {
                    System.out.println("Incorrect input!");
                }
            }
        }
    }

    // ввод решетки для шифрации
    private static int[][] gridInput(int n) {

        int boolGrid[][] = new int[n][n];
        // инициализация логической матрицы
        for(int i=0; i<n; i++){
            for(int j=0;j<n;j++){
                boolGrid[i][j]=0;
            }
        }
        int numbGrid[][] = createNumbMatrix(n);
        // печать цифровой матрицы
        System.out.println("Number grid:");
        printIntMatrix(numbGrid);
        // вырезание отверстий в матрице
        cutHoles(boolGrid, numbGrid);
        // вывод логической матрицы
        System.out.println("Bool grid:");
        printIntMatrix(boolGrid);
        // возврат логической матрицы с отверстиями
        return boolGrid;
    }

    // поворот матрицы
    private static int[][] rotateMatrix (int[][] matrix) {

        int SIDE = matrix.length;
        int[][] resMatrix = new int[SIDE][SIDE];
        for (int i = 0; i < SIDE; i++){
            for (int j = 0; j < SIDE; j++){
                resMatrix[i][j] = matrix[SIDE - j - 1][i];
            }
        }
        return resMatrix;
    }

    // получение шифра из матрицы по строка
    private static String getGipherText(char[][] matrix){

        String result = "";
        for (int i = 0 ; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result += matrix[i][j];
            }
        }
        return result;
    }

    @Override
    public String cipher(String openText, String key) {

        // размер решетки
        int n = Integer.parseInt(key);
        // проверка ввода
        if (n % 2 != 0 || n*n < openText.length()){
            return "Incorrect input! Try again.\n";
        }
        // ввод решетки для шифрования
        int[][] gridMatrix = gridInput(n);
        // создание символьной матрицы
        char[][] charMatrix =  createCharMatrix(n);

        int index = 0;
        // заполнение матрицы открытым текстом
        for (int rotateCount = 0; rotateCount < 4; rotateCount++){
            if (index <= openText.length()) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (gridMatrix[i][j] == 1){
                            if (index < openText.length()) {
                                charMatrix[i][j] = openText.charAt(index);
                            }
                            if (index == openText.length()) {
                                charMatrix[i][j] = '0';
                            }
                            index++;
                        }
                    }
                }
            }
            // поворот решетки
            gridMatrix = rotateMatrix(gridMatrix);
        }

        System.out.println("Char matrix after cipher:");
        printMatrix(charMatrix);
        return getGipherText(charMatrix);
    }


    // запись шифра в матрицу по строкам
    private static void writeCipherToMatrix(char[][] matrix, String cipherText) {
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = cipherText.charAt(index);
                index++;
            }
        }
    }

    // получение открытого текста из матрциы при помощи решетки
    private static String getOpenText(char[][] charMatrix, int[][] boolMatrix){

        String result = "";
        for (int rotateCount = 0; rotateCount < 4; rotateCount++){
            for (int i = 0; i < charMatrix.length; i++){
                for (int j = 0; j < charMatrix.length; j++){
                    if (boolMatrix[i][j] == 1){
                        result += charMatrix[i][j];
                    }
                }
            }
            // поворот решетки
            boolMatrix = rotateMatrix(boolMatrix);
        }

        return result.substring(0,result.indexOf('0'));
    }

    @Override
    public String decipher(String cipherText, String key){

        // размер решетки
        int n = Integer.parseInt(key);
        char[][] charMatrix = new char[n][n];
        writeCipherToMatrix(charMatrix, cipherText);
        // проверка ввода
        if (n % 2 != 0 || n*n < cipherText.length()){
            return "Incorrect input! Try again.\n";
        }
        // ввод решетки для дешифрования
        int[][] gridMatrix = gridInput(n);

        // возврат расшифрованного текста
        return getOpenText(charMatrix, gridMatrix);
    }
}
