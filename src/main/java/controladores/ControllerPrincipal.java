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

    public static Tablero gestionarMenuPrincipal(){
        int opcion = 0;
        Tablero tablero = new Tablero();

        opcion = Util.pideEnteroRango("Introduce una opción: ", "Error, debe ser una opción entre 0 y 2", 0, 2);
        switch (opcion){
            case 1 -> ControllerTablero.iniciarTablero();
            case 2 -> tablero = Util.cargarPartida();
        }
        return tablero;
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
    public static boolean tablas(Tablero tablero) {

        if (esTurnoBlancas(tablero)) {
            System.out.println("¡BLANCAS HA DECLARADO TABLAS!");
        } else {
            System.out.println("¡NEGRAS HA DECLARADO TABLAS!");
        }

        VistaConsola.mensajeFinal();
        return false;
    }
    public static boolean esTurnoBlancas(Tablero tablero) {
        int numeroTurno = tablero.getContadorTurnos();

        //si el numero es impar es turno de las blancas
        if (numeroTurno % 2 != 0) {
            return true;
        }
        return false;
    }

    public static boolean rendirse(Tablero tablero) {

        if (esTurnoBlancas(tablero)) {
            System.out.println("¡BLANCAS SE HA RENDIDO!");
        } else {
            System.out.println("¡NEGRAS SE HA RENDIDO!");
        }

        VistaConsola.mensajeFinal();
        return false;
    }

    public static boolean deseaFinalizar() {
        boolean seguirJugando = true;
        int opcion = VistaConsola.mensajeFinalizar();
        switch (opcion) {
            case 1:
                seguirJugando = false;
                break;
            case 2:
                seguirJugando = true;
                break;
        }
        return seguirJugando;
    }



}
