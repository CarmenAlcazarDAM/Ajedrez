package controladores;

import modelo.Tablero;

public class ControllerPrincipal {
    private static int contador = 1;

    public boolean contadorTurnos(){
        contador ++;
        if (this.contador/2 == 0 ){
            return true;
        } else {
            return false;
        }
    }

    public void gestionarMenuPrincipal(int opcion){
        switch (opcion){
            case 1 -> ControllerTablero.iniciarTablero();
            case 2 -> //Aqui se carga una partida
        }
    }

    public void gestionarMenuJuego(int opcion){
        switch (opcion){
            case 1 -> ;
            case 2 -> ControllerTablero.iniciarTablero();
            case 2 -> Tablero.clonarTablero(Tablero t);
            case 3 ->
        }
    }


}
