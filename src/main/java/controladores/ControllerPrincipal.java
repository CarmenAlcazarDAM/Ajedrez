package controladores;

import Interfaces.Atacadora;
import modelo.Peon;
import modelo.Pieza;
import modelo.Tablero;
import utils.Util;
import vista.VistaConsola;

import java.sql.SQLOutput;
import java.util.SortedMap;

public class ControllerPrincipal {


    public static void ejecutarMenuEstado(Tablero tablero) {
        int opcion = -1;
        do {
            opcion = VistaConsola.menuEstadoPartida();
            ControllerTablero.gestionEstadoPartida(opcion, tablero);


        } while (opcion != 0);
    }


    public static Tablero gestionarMenuPrincipal() {
        int opcion = 0;
        Tablero tablero = new Tablero();

        boolean continuar=true;

        opcion = Util.pideEnteroRango("Introduce una opción: ", "Error, debe ser una opción entre 0 y 2", 0, 2);
        switch (opcion) {
            case 1 -> tablero.iniciarTablero();
            case 2 -> tablero = Util.cargarPartida();
            case 0-> {  tablero=null;
                VistaConsola.mensajeFinal();
            }
        }
        return tablero;

    }

    public static boolean gestionarMenuJuego(int opcion, Tablero tablero) {
        boolean continuar = true;
        switch (opcion) {
            case 1 -> gestionarMenuMover(tablero);
            case 2 -> tablero.iniciarTablero();
            case 3 -> Util.guardarPartida(tablero);
            case 4 -> continuar = rendirse(tablero); //rendirse
            case 5 -> continuar = tablas(tablero); //tablas
            case 6 -> ejecutarMenuEstado(tablero);
            case 0 -> continuar = deseaFinalizar();
        }
        return continuar;
    }

    public static boolean tablas(Tablero tablero) {

        System.out.println("SE HAN DECLARADO EN TABLAS!");

        return false;
    }

    public static boolean rendirse(Tablero tablero) {

        if (esTurnoBlancas(tablero)) {
            System.out.println("¡LAS BLANCAS SE HAN RENDIDO!");
        } else {
            System.out.println("¡LAS NEGRAS SE HAN RENDIDO!");
        }

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


    public static boolean deseaFinalizar() {
        boolean seguirJugando = true;
        int opcion = VistaConsola.mensajeFinalizar();
        switch (opcion) {
            case 1:
                seguirJugando = false;
                VistaConsola.mensajeFinal();
                break;
            case 2:
                seguirJugando = true;
                break;
        }
        return seguirJugando;
    }

    public static void gestionarMenuMover(Tablero tablero) {
        boolean seguirJugando = true;
        Pieza p = null;
        int fila;
        int columna;
        boolean puedeUsarPieza = false;
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
                    return;
                } else if (!turnoBlancas && esBlanca) {

                    System.out.println("No puedes mover una pieza BLANCA en el turno de las NEGRAS.");
                    return;
                } else {

                    puedeUsarPieza = true;
                }
            }

        } while (!puedeUsarPieza);

        opcionesMovimientos(p, tablero);

    }

    public static void opcionesMovimientos(Pieza p, Tablero tablero) {
        int opcion = -1;
        if (p instanceof Peon) {
            opcion = VistaConsola.menuSeleccionarCasillaPeon();

        } else {
            opcion = VistaConsola.menuSeleccionarCasilla();
        }
        switch (opcion) {
            case 1 -> mover(p, tablero);
            case 2 -> atacaPeon(p, tablero);
        }

    }

    public static void mover(Pieza p, Tablero tablero) {
        boolean movimientoRealizado = false;
        System.out.println("Intentando mover " + p.getClass().getSimpleName() + "(" + p.getFila() + "," + p.getColumna() + ")");
        while (!movimientoRealizado) {
            try {
                int nuevaFila = Util.pideEnteroRango("Fila destino: ", "Error, debe ser entre 0 y 7", 0, 7);
                int nuevaColumna = Util.pideEnteroRango("Columna destino: ", "Error, debe ser entre 0 y 7", 0, 7);

                p.validarDestino(nuevaFila, nuevaColumna, tablero);
                Pieza victima = tablero.obtenerPiezaEnCasilla(nuevaFila, nuevaColumna);

                System.out.println("Intentando mover " + p.getClass().getSimpleName() + " a (" + nuevaFila + "," + nuevaColumna + ")");
                System.out.println("Moviendo...");
                if (p.puedeMover(nuevaFila, nuevaColumna, tablero)) {

                    if (victima != null) {
                        movimientoRealizado = atacarEnMovimiento(p, victima, tablero, nuevaFila, nuevaColumna);
                    } else {
                        p.mover(nuevaFila, nuevaColumna);
                        movimientoRealizado = true;
                    }
                }
                VistaConsola.movimientoCorrectoOIncorrecto(movimientoRealizado);

                if (movimientoRealizado) {
                    tablero.setContadorTurnos(tablero.getContadorTurnos() + 1);

                    Pieza.Color colorOponente;

                    if (p.getColor() == Pieza.Color.BLANCA){
                        colorOponente = Pieza.Color.NEGRA;
                    } else {
                        colorOponente = Pieza.Color.BLANCA;
                    }

                    if (tablero.comprobarJaque(tablero, colorOponente)){
                        System.out.println("El rey "+ colorOponente+"esta en jaque");
                    }
                }

            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
                return;

            }

        }
    }

    public static boolean atacarEnMovimiento(Pieza p, Pieza victima, Tablero tablero, int fila, int columna) {
        if (victima != null) {
            tablero.matarPieza(victima);
            p.mover(fila, columna);
            return true;

        }
        return false;
    }


    public static void atacaPeon(Pieza p, Tablero tablero) {
        boolean ataqueRealizado = false;

        do {
            try {
                int nuevaFila = Util.pideEnteroRango("Fila destino: ", "Error, debe ser entre 0 y 7", 0, 7);
                int nuevaColumna = Util.pideEnteroRango("Columna destino: ", "Error, debe ser entre 0 y 7", 0, 7);
                Pieza victima = tablero.obtenerPiezaEnCasilla(nuevaFila, nuevaColumna);

                p.validarDestino(nuevaFila, nuevaColumna, tablero);

                if (victima != null) {
                    ((Atacadora) p).ataque(nuevaFila, nuevaColumna);
                    tablero.matarPieza(victima);

                    ataqueRealizado = true;
                }
                VistaConsola.movimientoCorrectoOIncorrecto(ataqueRealizado);
                if (ataqueRealizado) {
                    tablero.setContadorTurnos(tablero.getContadorTurnos() + 1);
                }

            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
                return;
            }

        } while (!ataqueRealizado);

    }


}
