package utils;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public  class Util {
    public static Scanner teclado = new Scanner(System.in);


    public static int pideEntero(String msn) {
        int n = 0;
        boolean error = false;
        do {
            try {
                System.out.println(msn);
                n = teclado.nextInt();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("Valor no válido");
                error = true;
                teclado.next();
            }
        } while (error);
        return n;
    }
    public static double pideDouble(String msn) {
        double n = 0;
        boolean error = false;
        do {
            try {
                System.out.println(msn);
                n = teclado.nextDouble();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("Valor no válido");
                error = true;
                teclado.next();
            }
        } while (error);
        return n;
    }

    public static String pideString(String msn) {
        String cadena=null;
        System.out.println(msn);
        cadena = teclado.next();
        return cadena;
    }

    public static int generateRandomNumber(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
