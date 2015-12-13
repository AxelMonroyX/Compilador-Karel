package com.axelmonroyx.GenerarCodigoObjeto;

/**
 * Created by axelmonroyx on 13/11/15.
 */
public class nodo_polish {
    public String lexema;
    public int token;
    public String tipo; //operador operando
    public String salto;
    public String valor;


    // operador+-  BRF BRI
    //   operando ab T1 T2

    public nodo_polish sig = null;

    public nodo_polish(String lexema, int token, String tipo) {
        this.lexema = lexema;
        this.token = token;
        this.tipo = tipo;
    }

    public nodo_polish(String lexema, int token, String tipo, String valor) {
        this.lexema = lexema;
        this.token = token;
        this.tipo = tipo;
        this.valor = valor;
    }


}
