package utils;

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
     * Método para serializar el programa y guardar la información en un archivo XML
     * @param tablero --> Clase Tablero pasada por parámetro
     * @return --> Devuelve true si se ha guardado la información correctamente
     */
    public static boolean guardarPartida(Tablero tablero){
           Tablero clon = Tablero.clonarTablero(tablero);
        try {
            JAXBContext context = JAXBContext.newInstance(Tablero.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(clon, new File("tableroXML"));
            return true;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para leer la información de un archivo XML
     * @return --> Devuelve un objeto tablero con la información del archivo
     */
    public static Tablero cargarPartida(){
        Tablero tableroDeserializada;
        try {
            JAXBContext context = JAXBContext.newInstance(Tablero.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            tableroDeserializada = (Tablero) unmarshaller.unmarshal(new File("tableroXML"));

            if(tableroDeserializada == null){
                System.out.println("Error: no existe partida guardada.");
                return null;
            }

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return tableroDeserializada;
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
