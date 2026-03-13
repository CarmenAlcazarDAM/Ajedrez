package controladores.MenusNum;

public enum GestMATKEnum {
    MOVER(1),
    ATACAR(2),
    VOLVER(0);

    private final int index;

    GestMATKEnum(int index) {
        this.index = index;
    }

    public static GestMATKEnum gestorMATKFromIndex(int index) {
        for (GestMATKEnum gestor : values()) if (gestor.index == index) return gestor;
        throw new IllegalArgumentException("Opción Invalida");
    }
}
