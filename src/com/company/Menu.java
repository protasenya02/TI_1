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


