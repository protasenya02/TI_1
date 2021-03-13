package com.company;


public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();

        while(menu.isRun) {
            // выбор типа шифра
            menu.chooseCipherType();
            // выбор состояния шифра (шифрация/дешифрация)
            menu.chooseCipherState();
            // создание обьектов
            ColumnCipher columnCipher = new ColumnCipher();
            RailFenceCipher railFenceCipher = new RailFenceCipher();
            VizenerCipher vizenerCipher = new VizenerCipher();
            RotatingGridCipher rotatingGridCipher = new RotatingGridCipher();

            switch (menu.cipherType) {
                case column:
                    if (menu.cipherState == Menu.CipherState.cipher) {
                        menu.outputCiphertext(columnCipher.cipher(menu.inputOpentext(), menu.inputKey()));
                    } else {
                        menu.outputOpentext(columnCipher.decipher(menu.inputCiphertext(), menu.inputKey()));
                    }
                    break;

                case railFence:
                    if (menu.cipherState == Menu.CipherState.cipher) {
                        menu.outputCiphertext(railFenceCipher.cipher(menu.inputOpentext(), menu.inputKey()));
                    } else {
                        menu.outputOpentext( railFenceCipher.decipher(menu.inputCiphertext(), menu.inputKey()));
                    }
                    break;

                case vizhener:
                    if (menu.cipherState == Menu.CipherState.cipher) {
                        menu.outputCiphertext(vizenerCipher.cipher(menu.inputOpentext(), menu.inputKey()));
                    } else {
                        menu.outputOpentext(vizenerCipher.decipher(menu.inputCiphertext(), menu.inputKey()));
                    }
                    break;

                case grid:
                    if (menu.cipherState == Menu.CipherState.cipher) {
                        menu.outputCiphertext(rotatingGridCipher.cipher(menu.inputOpentext(), menu.inputGridSize()));
                    } else {
                        menu.outputOpentext(rotatingGridCipher.decipher(menu.inputCiphertext(), menu.inputGridSize()));
                    }
                    break;
            }
            menu.isContinue();
        }
    }
}
