package controladores;


import Interfaces.Atacadora;
import modelo.Peon;
import modelo.Pieza;
import modelo.Tablero;
import utils.Util;
import vista.VistaConsola;

import java.util.SortedMap;

public class ControllerPrincipal {


    public static void ejecutarMenuEstado() {
        int opcion = -1;
        do {
            opcion = VistaConsola.menuEstadoPartida();
            ControllerTablero.gestionEstadoPartida(opcion);
            VistaConsola.imprimirLinea();

        } while (opcion != 0);
    }

    public static void gestionarMenuPrincipal(int opcion){
        switch (opcion){
            case 1 -> ControllerTablero.iniciarTablero();
            case 2 -> //Util.cargarPartida();
        }
    }

    public static void gestionarMenuJuego(int opcion){
        do {

            switch (opcion) {
                case 1 -> ;
                case 2 -> ControllerTablero.iniciarTablero();
                case 3 -> Util.guardarPartida(t);
                case 4 -> finalizarPartida();
                case 5 -> finalizarPartida();
                case 6 -> {
                    int op = Util.pideEntero("Selecciona una opcion", "Error opcion no valida");
                    ControllerTablero.gestionEstadoPartida(op);
                }
                case 0 -> System.out.println("Has elegido salir del programa");
            }
        }while (opcion == 0);
    }


}
