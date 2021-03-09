package com.company;
import java.util.Scanner;

class Application {

    public void Run() {


        Menu menu = new Menu();

        while(menu.isRun) {
            // выбор типа шифра
            menu.ChooseCipherType();
            // выбор состояния шифра (шифрация/дешифрация)
            menu.ChooseCipherState();


            ColumnCipher columnCipher = new ColumnCipher();
            // menu.OutputCiphertext(columnCipher.Encode(menu.InputOpentext(), menu.InputKey()));
            //menu.OutputOpentext( columnCipher.Decode(menu.InputCiphertext(), menu.InputKey()));
            RailFenceMethod railFenceMethod = new RailFenceMethod();
            menu.OutputCiphertext(railFenceMethod.Encode(menu.InputOpentext(), menu.InputKey()));

            menu.IsContinue();
        }



    }
}


// класс меню
class Menu {

    enum CipherType {
        noType,        // метод не выбран
        railFence,      // метод железнодорожной изгороди
        column,        // столбцовый метод
        grid,          // метод поворачивающейся решетки
        vizhener       // шифр Виженера
    }

    enum CipherState {
        noState,      // состояние не выбрано
        cipher,       // шифрование
        decipher      // дешифрование
    }

    // тип шифра
    CipherType cipherType;
    // текущее состояние шифра (шифратор/дешифратор)
    CipherState cipherState;
    // текущее состояниие программы
    boolean isRun;

    // конструктор
    Menu() {
        this.cipherType = CipherType.noType;
        this.cipherState = CipherState.noState;
        this.isRun = true;
    }

    // выбор типа шифра
    public void ChooseCipherType() {
        System.out.println("Choose cipher type:");
        PrintCipherTypes();
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();
        SetCipherType(choise);
    }

    // установка типа шифра
    private void SetCipherType(int choise) {
        switch (choise){
            case 1:
                this.cipherType = CipherType.railFence;
                break;
            case 2:
                this.cipherType = CipherType.column;
                break;
            case 3:
                this.cipherType = CipherType.grid;
                break;
            case 4:
                this.cipherType = CipherType.vizhener;
                break;
        }
    }

    // печать типов шифрования
    private void PrintCipherTypes(){
        System.out.println("1.Rail Fence method");
        System.out.println("2.Column method");
        System.out.println("3.Rotating grig method");
        System.out.println("4.Vizener cipher");
    }

    // выбор состояния шифра
    public void ChooseCipherState(){
        System.out.println("Choose cipher state:");
        PrintCipherState();
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();
        SetCipherState(choise);
    }

    // печать состояний шифра
    private void PrintCipherState(){
        System.out.println("1.Cipher");
        System.out.println("2.Decipher");
    }

    // установка состояния шифра
    private void SetCipherState(int choise) {
        switch (choise){
            case 1:
                this.cipherState = CipherState.cipher;
                break;
            case 2:
                this.cipherState = CipherState.decipher;
                break;
        }
    }

    // ввод открытого текста
    public String InputOpentext() {
        System.out.println("Input open text: ");
        Scanner scanner = new Scanner(System.in);
        String openText = scanner.nextLine();
        // удаление лишних пробелов
        openText.trim();
        return openText;
    }

    // вывод открытого текста
    public void OutputOpentext(String openText) {
        System.out.println("Open text: "+ openText);
    }

    // ввод зашифрованного текста
    public String InputCiphertext() {
        System.out.println("Input cipher text: ");
        Scanner scanner = new Scanner(System.in);
        String cipherText = scanner.nextLine();
        // удаление лишних пробелов
        cipherText.trim();
        return cipherText;
    }

    // вывод зашифрованного текста
    public void OutputCiphertext(String cipherText) {
        System.out.println("Cipher text: "+ cipherText);
    }

    // ввод ключа
    public String InputKey() {
        System.out.println("Input key:");
        Scanner scanner = new Scanner(System.in);
        String key = scanner.nextLine();
        // удаление лишних пробелов
        key.trim();
        return key;
    }

    // прокручиваение консоли
    public static void cls()
    {
        for(int i=0; i<50; i++){
            System.out.println();
        }
    }

    // проверка продолжения программы
    public void IsContinue() {

        System.out.println("Do you want to continue?");
        System.out.println("1.Yes   2.No");
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();

        switch (choise){
            case 1:
                isRun = true;
                break;
            case 2:
                isRun = false;
                break;
        }
    }

}



abstract class Cipher {

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


// класс метода железнодорожной изгороди
class RailFenceMethod extends Cipher {

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


// класс cтолбцового метода шифрования
class ColumnCipher extends Cipher {

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



public class Main {

    public static void main(String[] args) {

        Application app = new Application();
        app.Run();

    }
}
