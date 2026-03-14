package controladores.MenusNum;


import controladores.ControllerTablero;

public enum GestEstadoPartida{
    MOSTRAR_FICHAS_BLANCAS(1),
    MOSTRAR_FICHAS_NEGRAS(2),
    ELIMINADAS(3),
    PUNTOS_FICHAS_BLANCAS(4),
    PUNTOS_FICHAS_NEGRAS(5),
    VOLVER(0);

    private final int index;


    GestEstadoPartida(int index) {
        this.index = index;
    }

    public static GestEstadoPartida getGestEstadoPartida(){
        return GestEstadoPartida.fromIndex(ControllerTablero.readInt(GestEstadoPartida.values().length));
    }

    public static GestEstadoPartida fromIndex(int index) {
        for (GestEstadoPartida gestor : values()) if (gestor.index == index) return gestor;
        throw new IllegalArgumentException("Opción Invalida");
    }
}
