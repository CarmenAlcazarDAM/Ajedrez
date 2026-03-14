package controladores;

import modelo.Tablero;
import utils.Util;

public class ControllerPrincipal {


    public static void gestionarMenuPrincipal(int opcion){
        switch (opcion){
            case 1 -> ControllerTablero.iniciarTablero();
            case 2 ->Util.cargarPartida();
        }
    }

    public static boolean gestionarMenuJuego(int opcion, Tablero tablero) {
        boolean continuar = true;
        switch (opcion) {
            case 1 -> gestionarMenuMover(tablero);
            case 2 -> ControllerTablero.iniciarTablero();
            case 3 -> Util.guardarPartida(tablero);
            case 4 -> continuar = rendirse(tablero); //rendirse
            case 5 -> continuar = tablas(tablero); //tablas
            case 6 -> ejecutarMenuEstado();
            case 0 -> continuar = deseaFinalizar();
        }
        return continuar;
    }


}
