package utils;

import dataAccess.XMLManagerTablero;
import modelo.Tablero;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

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

   /**
     * Método que guarda la partida actual y controla que no se pare el código por una RuntimeException
     * @param tablero --> estado actual de la partida en curso
     * @return --> devuelve true si se ha guardado correctamente, false si no lo hace
     */
    public static boolean guardarPartida(Tablero tablero) {
        boolean estaGuardada = false;
        try {
            Tablero clon = Tablero.clonarTablero(tablero);
            estaGuardada = XMLManagerTablero.writeXML(clon, "tablero.xml");
        } catch (RuntimeException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
            return false;
        }
        return estaGuardada;
    }

    /**
     * Método para leer la información de un archivo XML
     * @return --> Devuelve un objeto tablero con la información del archivo
     */
    public static Tablero cargarPartida() {
        Tablero cargado = null;
        try {
            cargado = XMLManagerTablero.readXML("tablero.xml");

        } catch (RuntimeException e) {
            System.out.println("Error al cargar la partida: " + e.getMessage());
            return null;
        }
        return cargado;
    }

    /**
     * Método para pedir un número dentro de un rango de opciones y comprobar si el número entero es correcto
     *
     * @param mensaje      --> mensaje que muestra por pantalla para solicitar un número
     * @param mensajeError --> --> mensaje que se muestra en caso de no introducir un número entero o no estar dentro de rango
     * @param min          --> número mínimo que puedes introducir
     * @param max          --> numero máximo que puedes introducir
     * @return --> devuelve el número entero si no hay error.
     */
    public static int pideEnteroRango(String mensaje, String mensajeError, int min, int max) {

        int numero = 0;
        boolean noHayError = true;
        do {
            System.out.println(mensaje);
            try {
                numero = teclado.nextInt();
                if (numero >= min && numero <= max) {

                    noHayError = true;
                } else {
                    System.out.println(mensajeError);
                    noHayError = false;
                }
            } catch (InputMismatchException e) {
                System.out.println(mensajeError);
                noHayError = false;
                teclado.nextLine();
            }

        } while (!noHayError);
        return numero;
    }




}
