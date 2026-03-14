package controladores.MenusNum;

import utils.Util;

public enum GestMATKEnum {
    // hecho por mi entero
    MOVER(1),
    ATACAR(2),
    VOLVER(0);

    private final int index;

    GestMATKEnum(int index) {
        this.index = index;
    }

    public static GestMATKEnum getGestMATKEnum() {
        return GestMATKEnum.fromIndex(Util.pideEnteroRango(
                "", "Numero invalido. Vuelve a introducir un numero.", 1, GestMATKEnum.values().length) );

    }

    public static GestMATKEnum fromIndex(int index) {
        for (GestMATKEnum gestor : values()) if (gestor.index == index) return gestor;
        throw new IllegalArgumentException("Opción Invalida");
    }
}
