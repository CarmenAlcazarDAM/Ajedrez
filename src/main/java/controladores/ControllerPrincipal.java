package controladores;

import modelo.Tablero;
import utils.Util;

public class ControllerPrincipal {
    private Tablero t = new Tablero();

    /*
    private static int contador = 1;

    public boolean contadorTurnos(){
        contador ++;
        if (this.contador/2 == 0 ){
            return true;
        } else {
            return false;
        }
    }
    */


    public static void gestionarMenuPrincipal(int opcion){
        switch (opcion){
            case 1 -> ControllerTablero.iniciarTablero();
            case 2 ->Util.cargarPartida();
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
