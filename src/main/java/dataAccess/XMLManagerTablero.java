package dataAccess;

import modelo.Tablero;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLManagerTablero {
    /**
     * Método para serializar el programa y guardar la información en un archivo XML
     * @param tablero --> Clase Tablero pasada por parámetro
     * @return --> Devuelve true si se ha guardado la información correctamente
     */
    public static boolean writeXML(Tablero tablero, String fileName){
        boolean result= false;
        try {
            JAXBContext context = JAXBContext.newInstance(Tablero.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(tablero, new File(fileName));
            result = true;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Método para leer la información de un archivo XML
     * @return --> Devuelve un objeto tablero con la información del archivo
     */
    public static Tablero readXML(String fileName){
        Tablero tableroDeserializada;
        try {
            JAXBContext context = JAXBContext.newInstance(Tablero.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            tableroDeserializada = (Tablero) unmarshaller.unmarshal(new File(fileName));

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return tableroDeserializada;
    }
}
