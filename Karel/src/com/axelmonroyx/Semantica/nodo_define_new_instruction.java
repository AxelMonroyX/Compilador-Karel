/*
 Creado: 17/09/2015 , Hora: 11:08:42 AM
 */
package com.axelmonroyx.Semantica;

/**
 *
 * @author axelmonroyx
 */
public class nodo_define_new_instruction {

    String lexema;
    boolean parametro;
    nodo_define_new_instruction sig = null;

    public nodo_define_new_instruction(String lexema, boolean parametro) {
        this.lexema = lexema;
        this.parametro = parametro;
    }

}
