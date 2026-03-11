package utils;

import modelo.Tablero;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
     * @param fileName --> Nombre del archivo de destino
     * @return --> Devuelve true si se ha guardado la información correctamente
     */
    public static boolean serializarXML(Tablero tablero, String fileName){

        try {
            JAXBContext context = JAXBContext.newInstance(Tablero.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(tablero, new File(fileName));
            return true;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
