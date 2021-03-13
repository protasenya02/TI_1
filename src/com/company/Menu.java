package com.company;
import java.util.Scanner;

// класс меню
public class Menu {

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
    public void chooseCipherType() {
        System.out.println("Choose cipher type:");
        printCipherTypes();
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();
        setCipherType(choise);
    }

    // установка типа шифра
    private void setCipherType(int choise) {
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
    private void printCipherTypes() {
        System.out.println("1.Rail Fence method");
        System.out.println("2.Column method");
        System.out.println("3.Rotating grig method");
        System.out.println("4.Vizener cipher");
    }

    // выбор состояния шифра
    public void chooseCipherState() {
        System.out.println("Choose cipher state:");
        printCipherState();
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();
        setCipherState(choise);
    }

    // печать состояний шифра
    private void printCipherState() {
        System.out.println("1.Cipher");
        System.out.println("2.Decipher");
    }

    // установка состояния шифра
    private void setCipherState(int choise) {
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
    public String inputOpentext() {
        System.out.println("Input open text: ");
        Scanner scanner = new Scanner(System.in);
        String openText = scanner.nextLine();
        // удаление лишних пробелов
        openText = UppercaseStr(openText);
        openText = openText.replace(" ", "");
        return openText;
    }

    // вывод открытого текста
    public void outputOpentext(String openText) {
        System.out.println("Open text: "+ openText);
    }

    // ввод зашифрованного текста
    public String inputCiphertext() {
        System.out.println("Input cipher text: ");
        Scanner scanner = new Scanner(System.in);
        String cipherText = scanner.nextLine();
        // удаление лишних пробелов
        cipherText = UppercaseStr(cipherText);
        cipherText = cipherText.replace(" ", "");
        return cipherText;
    }

    // вывод зашифрованного текста
    public void outputCiphertext(String cipherText) {
        System.out.println("Cipher text: "+ cipherText);
    }

    // ввод ключа
    public String inputKey() {
        System.out.println("Input key:");
        Scanner scanner = new Scanner(System.in);
        String key = scanner.nextLine();
        // удаление лишних пробелов
        key = UppercaseStr(key);
        key = key.replace(" ", "");
        return key;
    }

    // прокручиваение консоли
    public static void cls()
    {
        for(int i=0; i<50; i++){
            System.out.println();
        }
    }

    // преобразование строки в верхний регистр
    private String UppercaseStr(String str) {

        char[] charArray = str.toCharArray();
        str = "";
        for (char c : charArray) {
            if (Character.isLowerCase(c))
                c = Character.toUpperCase(c);
            str += c;
        }
        return str;
    }

    // проверка продолжения программы
    public void isContinue() {

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

    public  String inputGridSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Grid size (even number):");
        return  scanner.nextLine();
    }


}


