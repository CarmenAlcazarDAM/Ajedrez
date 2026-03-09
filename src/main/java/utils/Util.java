package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public  class Util {
    public static Scanner teclado = new Scanner(System.in);

    /**
     * Pide un entero sin que me puedan poner otra cosa que no sea un entero
     * @param msn Mensaje para pedir el entero
     * @param msnError Mensaje de error si el usuario pone algo que no esta permitido
     * @return devuelvo el entero recogido
     */
    public static int pideEntero(String msn, String msnError) {
        int n = 0;
        boolean error = false;
        do {
            try {
                System.out.println(msn);
                n = teclado.nextInt();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println(msnError);
                error = true;
                teclado.next();
            }
        } while (error);
        return n;
    }
}
