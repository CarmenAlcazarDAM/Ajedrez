package controladores;

import Interfaces.Atacadora;
import modelo.Peon;
import modelo.Pieza;
import modelo.Tablero;
import utils.Util;
import vista.VistaConsola;


public class ControllerPrincipal {

    /**
     * Gestiona las opciones del menú de estado de la partida, donde podremos ver las listas y puntos de cada color
     *
     * @param tablero --> tablero en el que se va a realizar la accion
     */
    public static void ejecutarMenuEstado(Tablero tablero) {
        int opcion = -1;
        do {
            opcion = VistaConsola.menuEstadoPartida();
            ControllerTablero.gestionEstadoPartida(opcion, tablero);


        } while (opcion != 0);
    }

    /**
     * Gestiona las opciones del menú para iniciar una partida o cargar ya una existente
     *
     * @return --> devuelve un tablero iniciado o el tablero guardado en el xml
     */
    public static Tablero gestionarMenuPrincipal() {
        int opcion = 0;
        Tablero tablero = new Tablero();

        boolean continuar = true;

        opcion = Util.pideEnteroRango("Introduce una opción: ", "Error, debe ser una opción entre 0 y 2", 0, 2);
        switch (opcion) {
            case 1 -> tablero.iniciarTablero();
            case 2 -> tablero = Util.cargarPartida();
            case 0 -> {
                tablero = null;
                VistaConsola.mensajeFinal();
            }
        }
        return tablero;

    }

    /**
     * Gestiona el menú de juego una vez establecido el tablero en el que se va a jugar
     *
     * @param opcion  --> el número de opcion elegida por el usuario
     * @param tablero --> tablero en el que se va a realizar la accion
     * @return --> devuelve true si la partida continua, false si se ha acabado
     */
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

    /**
     * Declara el final de la partida por tablas (empate)
     *
     * @param tablero --> tablero en el que se va a realizar la accion
     * @return --> devuelve false porque no se desea seguir jugando la partida
     */
    public static boolean tablas(Tablero tablero) {
        VistaConsola.imprimirEnVista("SE HAN DECLARADO EN TABLAS!");

        return false;
    }

    /**
     * Declara el final de la partida por rendición del color y muestra el mensaje de quien se ha rendido
     *
     * @param tablero --> tablero en el que se va a realizar la accion
     * @return --> devuelve false porque no se desea seguir jugando la partida por rendición
     */
    public static boolean rendirse(Tablero tablero) {

        if (esTurnoBlancas(tablero)) {
            VistaConsola.imprimirEnVista("¡LAS BLANCAS SE HAN RENDIDO!");
        } else {
            VistaConsola.imprimirEnVista("¡LAS NEGRAS SE HAN RENDIDO!");        }

        return false;
    }

    /**
     * Determina si el turno actual corresponde al jugador de las piezas blancas, siendo los turnos impares para las
     * piezas blancas y los pares para las negras. El contador inicia en 1
     *
     * @param tablero --> tablero en el que se va a realizar la accion--> tablero sobre el que se va a realizar la acción
     * @return --> devuelve true si es el turno de las blancas, false si es el de las negras
     */
    public static boolean esTurnoBlancas(Tablero tablero) {
        int numeroTurno = tablero.getContadorTurnos();

        //si el numero es impar es turno de las blancas
        if (numeroTurno % 2 != 0) {
            return true;
        }
        return false;
    }


    /**
     * Muestra un mensaje al usuario para confirmar si desea cerrar la aplicación o volver al menú
     *
     * @return --> devuelve false si se desea finalizar la partida, true si quiere seguir
     */
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

    /**
     * Gestiona la selección de una pieza en el tablero para realizar un movimiento.
     * Solicita introducir los datos de fila y columna al usuario, valida si la casilla de destino está vacía
     * y que la pieza seleccionada para mover sea del color del turno correspondiente
     *
     * @param tablero --> tablero en el que se va a realizar la acción
     */
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
                VistaConsola.imprimirEnVista("La casilla está vacía.");

            } else {

                boolean esBlanca = (p.getColor() == Pieza.Color.BLANCA);
                boolean turnoBlancas = esTurnoBlancas(tablero);

                if (turnoBlancas && !esBlanca) {

                    VistaConsola.imprimirEnVista("No puedes mover una pieza NEGRA en el turno de las BLANCAS.");
                    return;
                } else if (!turnoBlancas && esBlanca) {
                    VistaConsola.imprimirEnVista("No puedes mover una pieza BLANCA en el turno de las NEGRAS.");
                    return;
                } else {

                    puedeUsarPieza = true;
                }
            }

        } while (!puedeUsarPieza);

        opcionesMovimientos(p, tablero);

    }


    /**
     * Muestra las acciones disponibles para cada tipo de pieza
     *
     * @param p       --> pieza seleccionada sobre la que se va a realizar la acción
     * @param tablero --> tablero en el que se va a realizar la acción
     */
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

    /**
     * Gestiona el proceso completo de mover una pieza seleccionada a un nuevo destino.
     * Solicita las coordenadas de la casilla de destino, valida las reglas de cada pieza para saber si puede realizar el movimiento,
     * que la pieza víctima no sea del mismo color o un rey, gestión de ataques, actualización del contador de turnos y
     * comprobación de situaciones de Jaque al oponente.
     *
     * @param p       --> pieza seleccionada sobre la que se va a realizar la acción
     * @param tablero --> tablero en el que se va a realizar la acción
     */
    public static boolean mover(Pieza p, Tablero tablero) {
        boolean movimientoRealizado = false;
        VistaConsola.imprimirEnVista("Intentando mover " + p.getClass().getSimpleName() + "(" + p.getFila() + "," + p.getColumna() + ")");
        while (!movimientoRealizado) {
            try {
                int nuevaFila = Util.pideEnteroRango("Fila destino: ", "Error, debe ser entre 0 y 7", 0, 7);
                int nuevaColumna = Util.pideEnteroRango("Columna destino: ", "Error, debe ser entre 0 y 7", 0, 7);

                p.validarDestino(nuevaFila, nuevaColumna, tablero);
                Pieza victima = tablero.obtenerPiezaEnCasilla(nuevaFila, nuevaColumna);
                VistaConsola.imprimirEnVista("Intentando mover " + p.getClass().getSimpleName() + " a (" + nuevaFila + "," + nuevaColumna + ")");
                VistaConsola.imprimirEnVista("Moviendo...");
                if (p.puedeMover(nuevaFila, nuevaColumna, tablero)) {

                    if (victima != null) {
                        if (!(p instanceof Atacadora)) {
                            movimientoRealizado = atacarEnMovimiento(p, victima, tablero, nuevaFila, nuevaColumna);

                        }else{
                            VistaConsola.imprimirEnVista("El peon no puede comer a una pieza de delante");
                        }
                    } else {
                        p.mover(nuevaFila, nuevaColumna);
                        movimientoRealizado = true;
                    }
                }
                VistaConsola.movimientoCorrectoOIncorrecto(movimientoRealizado);

                if (movimientoRealizado) {
                    tablero.setContadorTurnos(tablero.getContadorTurnos() + 1);

                    Pieza.Color colorOponente;

                    if (p.getColor() == Pieza.Color.BLANCA) {
                        colorOponente = Pieza.Color.NEGRA;
                    } else {
                        colorOponente = Pieza.Color.BLANCA;
                    }

                    if (tablero.comprobarJaque(tablero, colorOponente)) {
                        VistaConsola.imprimirEnVista("El rey " + colorOponente + "esta en jaque");
                    }
                } else {
                    return false;
                }

            } catch (IllegalArgumentException e) {
                VistaConsola.imprimirEnVista("ERROR: " + e.getMessage());
                return false;

            }
        }
        return movimientoRealizado;
    }

    /**
     * Realiza la acción de capturar una pieza enemiga y ocupar su posición, llevando la pieza víctima al
     * array de eliminadas
     *
     * @param p       --> pieza seleccionada sobre la que se va a realizar la acción
     * @param victima --> pieza que va a ser capturada
     * @param tablero --> tablero en el que se va a realizar la acción
     * @param fila    --> fila donde se encontraba la víctima
     * @param columna --> columna donde se encontraba la víctima
     * @return --> devuelve true si ha podido realizar el ataque, false si no lo ha hecho
     */
    public static boolean atacarEnMovimiento(Pieza p, Pieza victima, Tablero tablero, int fila, int columna) {
        if (victima != null) {
            tablero.matarPieza(victima);
            p.mover(fila, columna);
            return true;

        }
        return false;
    }

    /**
     * Gestiona la lógica específica de ataque para los peones.
     * Solicita las coordenadas de la casilla de destino, valida las reglas de cada pieza para saber si puede realizar el movimiento,
     * que la pieza víctima no sea del mismo color o un rey, gestión de ataques, actualización del contador de turnos y
     * comprobación de situaciones de Jaque al oponente.
     *
     * @param p       --> pieza seleccionada sobre la que se va a realizar la acción
     * @param tablero --> tablero en el que se va a realizar la acción
     * @return --> devuelve true si ha podido realizar el ataque, false si no lo ha hecho
     */
    public static boolean atacaPeon(Pieza p, Tablero tablero) {
        boolean ataqueRealizado = false;

        do {
            try {
                int nuevaFila = Util.pideEnteroRango("Fila destino: ", "Error, debe ser entre 0 y 7", 0, 7);
                int nuevaColumna = Util.pideEnteroRango("Columna destino: ", "Error, debe ser entre 0 y 7", 0, 7);
                Pieza victima = tablero.obtenerPiezaEnCasilla(nuevaFila, nuevaColumna);

                p.validarDestino(nuevaFila, nuevaColumna, tablero);

                int distanciaFila = p.getFila() - nuevaFila;
                int distanciaColum = p.getColumna() - nuevaColumna;
                boolean rangoCorrecto = false;

                if (p.getColor() == Pieza.Color.BLANCA){
                    if(distanciaFila == -1 && (distanciaColum == 1 || distanciaColum == -1)){
                        rangoCorrecto = true;
                    }
                }
                if (p.getColor() == Pieza.Color.NEGRA) {
                    if (distanciaFila == 1 && (distanciaColum == -1 || distanciaColum == 1)) {
                        rangoCorrecto = true;
                    }
                }

                if (victima != null && rangoCorrecto) {
                    ((Atacadora) p).ataque(nuevaFila, nuevaColumna);
                    tablero.matarPieza(victima);

                    ataqueRealizado = true;
                }
                VistaConsola.movimientoCorrectoOIncorrecto(ataqueRealizado);

                if (ataqueRealizado) {
                    tablero.setContadorTurnos(tablero.getContadorTurnos() + 1);

                    Pieza.Color colorOponente;

                    if (p.getColor() == Pieza.Color.BLANCA) {
                        colorOponente = Pieza.Color.NEGRA;
                    } else {
                        colorOponente = Pieza.Color.BLANCA;
                    }

                    if (tablero.comprobarJaque(tablero, colorOponente)) {

                        VistaConsola.imprimirEnVista("El rey " + colorOponente + " esta en jaque");
                    }
                }

            } catch (IllegalArgumentException e) {
                VistaConsola.imprimirEnVista("ERROR: " + e.getMessage());
            }

        } while (!ataqueRealizado);
        return ataqueRealizado;
    }


}
