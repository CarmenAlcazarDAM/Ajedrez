package controladores;

import modelo.Peon;
import modelo.Pieza;
import modelo.Tablero;
import utils.Util;
import vista.VistaConsola;

import java.util.SortedMap;

public class ControllerPrincipal {



//    public static void ejecutarPrograma(Tablero tablero){
//        int opcion=-1;
//
//        boolean seguirJugando=true;
//
//        while(seguirJugando) {
//
//        }
//    }


    public static void ejecutarMenuEstado(){
        int opcion = -1;
        do{
            opcion=VistaConsola.menuEstadoPartida();
            ControllerTablero.gestionEstadoPartida(opcion);
            VistaConsola.imprimirLinea();

        }while(opcion!=0);
    }


    public static void gestionarMenuPrincipal(){
        int opcion = 0;

            opcion = Util.pideEnteroRango("Introduce una opción: ", "Error, debe ser una opción entre 0 y 2",0,2);
            switch (opcion){
                case 1 -> ControllerTablero.iniciarTablero();
                case 2 -> Util.cargarPartida();
            }

    }

    public static boolean gestionarMenuJuego(int opcion, Tablero tablero){
        boolean continuar = true;
        switch (opcion) {
                case 1 -> gestionarMenuMover(tablero);
                case 2 -> ControllerTablero.iniciarTablero();
                case 3 -> Util.guardarPartida(tablero);
                case 4 -> continuar=rendirse(tablero); //rendirse
                case 5 -> continuar=tablas(tablero); //tablas
                case 6 -> ejecutarMenuEstado();
                case 0 -> continuar=deseaFinalizar();
            }
            return continuar;
    }

    public static boolean tablas(Tablero tablero){

        if(esTurnoBlancas(tablero)){
            System.out.println("¡BLANCAS HA DECLARADO TABLAS!");
        }else{
            System.out.println("¡NEGRAS HA DECLARADO TABLAS!");
        }

        VistaConsola.mensajeFinal();
        return false;
    }

    public static boolean rendirse(Tablero tablero){

        if(esTurnoBlancas(tablero)){
            System.out.println("¡BLANCAS SE HA RENDIDO!");
        }else{
            System.out.println("¡NEGRAS SE HA RENDIDO!");
        }

        VistaConsola.mensajeFinal();
        return false;
    }


    public static boolean esTurnoBlancas(Tablero tablero){
        int numeroTurno = tablero.getContadorTurnos();

        //si el numero es impar es turno de las blancas
        if(numeroTurno%2!=0){
            return true;
        }
        return false;
    }

    public static boolean deseaFinalizar(){
        boolean seguirJugando=true;
       int opcion= VistaConsola.mensajeFinalizar();
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

    public static void gestionarMenuMover(Tablero tablero){
        boolean seguirJugando=true;
        Pieza p = null;
        int fila;
        int columna;
        boolean puedeUsarPieza=false;
        do {
            fila = Util.pideEnteroRango("Fila: ", "Error, debe ser entre 0 y 7", 0, 7);
            columna = Util.pideEnteroRango("Columna: ", "Error, debe ser entre 0 y 7", 0, 7);

            p = tablero.obtenerPiezaEnCasilla(fila, columna);

            if (p == null) {
                System.out.println("La casilla está vacía.");

            } else {

                boolean esBlanca = (p.getColor() == Pieza.Color.BLANCA);
                boolean turnoBlancas = esTurnoBlancas(tablero);

                if (turnoBlancas && !esBlanca) {

                    System.out.println("No puedes mover una pieza NEGRA en el turno de las BLANCAS.");
                } else if (!turnoBlancas && esBlanca) {

                    System.out.println("No puedes mover una pieza BLANCA en el turno de las NEGRAS.");
                } else {

                    puedeUsarPieza = true;
                }
            }

        } while (!puedeUsarPieza);

        gestionarMovimientos(p, tablero);

    }

    public static void gestionarMovimientos(Pieza p, Tablero tablero){
        int opcion=-1;
        if (p instanceof Peon) {
            opcion= VistaConsola.menuSeleccionarCasillaPeon();

        }else{
            opcion=VistaConsola.menuSeleccionarCasilla();
        }
        switch (opcion){
            case 1:
                boolean movimientoRealizado = false;

                do {
                    try {
                        int nuevaFila = Util.pideEnteroRango("Fila destino: ", "Error 0-7", 0, 7);
                        int nuevaColumna = Util.pideEnteroRango("Columna destino: ", "Error 0-7", 0, 7);

                        p.validarDestino(nuevaFila, nuevaColumna, tablero);

                        if (p.puedeMover(nuevaFila, nuevaColumna, tablero)) {
                            p.mover( nuevaFila, nuevaColumna);
                            movimientoRealizado = true;
                            VistaConsola.movimientoCorrectoOIncorrecto(movimientoRealizado);
                        } else {
                            VistaConsola.movimientoCorrectoOIncorrecto(movimientoRealizado);
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }

                } while (!movimientoRealizado);
        }

    }




}
