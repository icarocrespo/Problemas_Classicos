package JantarDosFilosofos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class Main {

    public static void main(String[] args) {
        Escalonador esc = new Escalonador();
        esc.lerArquivo("C:\\Users\\ketrim\\Documents\\SO\\SO.txt");

    }

}