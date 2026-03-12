package controladores.MenusNum;


import utils.Util;

public enum GestEstadoPartida{
    MOSTRAR_FICHAS_BLANCAS(1),
    MOSTRAR_FICHAS_NEGRAS(2),
    ELIMINADAS(3),
    PUNTOS_FICHAS_BLANCAS(4),
    PUNTOS_FICHAS_NEGRAS(5),
    VOLVER(0);

    private int index;

    GestEstadoPartida(int index) {
        this.index = index;
    }

    public static GestEstadoPartida gestEstadoFromIndex(int index) {
        for (GestEstadoPartida gestor : values()) if (gestor.index == index) return gestor;
        throw new IllegalArgumentException("Opción Invalida");
    }
}
