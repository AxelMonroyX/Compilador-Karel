package com.axelmonroyx.GenerarCodigoObjeto;

/**
 * Created by axelmonroyx on 13/11/15.
 */
public class nodo_polish {
    String lexema;
    int token;
    String tipo; //operador operando
    String salto;
    String valor;


    // operador+-  BRF BRI
    //   operando ab T1 T2

    nodo_polish sig = null;

    public nodo_polish(String lexema, int token, String tipo) {
        this.lexema = lexema;
        this.token = token;
        this.tipo = tipo;
    }

    public nodo_polish(String lexema, int token, String tipo, String salto) {
        this.lexema = lexema;
        this.token = token;
        this.tipo = tipo;
        this.salto = salto;
    }


}
