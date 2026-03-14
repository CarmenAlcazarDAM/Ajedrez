package controladores.MenusNum;


import controladores.ControllerTablero;
import utils.Util;

public enum GestEstadoPartida{
    // hecho por mi entero
    MOSTRAR_FICHAS_BLANCAS(1),
    MOSTRAR_FICHAS_NEGRAS(2),
    ELIMINADAS(3),
    PUNTOS_FICHAS_NEGRAS(4),
    PUNTOS_FICHAS_BLANCAS(5),
    VOLVER(0);

    private final int index;


    GestEstadoPartida(int index) {
        this.index = index;
    }

    public static GestEstadoPartida getGestEstadoPartida(){
        return GestEstadoPartida.fromIndex(Util.pideEnteroRango(
                "", "Numero invalido. Vuelve a introducir un numero.", 1, GestEstadoPartida.values().length) );
    }

    public static GestEstadoPartida fromIndex(int index) {
        for (GestEstadoPartida gestor : values()) if (gestor.index == index) return gestor;
        throw new IllegalArgumentException("Opción Invalida");
    }
}
