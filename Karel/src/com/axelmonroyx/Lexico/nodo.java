package com.axelmonroyx.Lexico;

/*
 Creado: 31-may-2015 , Hora: 12:54:46
 */

/**
 *
 * @author Axel Monroy <xaxelmonroyx@gmail.com>
 */
public class nodo {

    public String lexema;
    public int num_renglon;
    public int token;
    public nodo sig = null;

    nodo(String lexema, int token, int num_renglon) {
        this.lexema = lexema;
        this.token = token;
        this.num_renglon = num_renglon;
    }
}
