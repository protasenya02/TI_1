package com.company;

public class Application {

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