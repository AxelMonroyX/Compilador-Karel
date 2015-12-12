package com.axelmonroyx.GenerarCodigoObjeto;

import java.io.*;
import java.util.Date;


/**
 * Created by axelmonroyx on 13/11/15.
 */
public class GenerarOBJ {
    Lista_polish lista_polish;

    public GenerarOBJ(Lista_polish lista_polish) {
        this.lista_polish = lista_polish;
        transformarCuadruplos();
        generarArchivo();
    }

    private void transformarCuadruplos() {

    }

    private void generarArchivo() {
        Date date = new Date();

        File f;
        f = new File("codigo" + date.getTime() + ".asm");
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            wr.write("Esta es una linea de codigo");//escribimos en el archivo
            wr.append(" - y aqui continua         "); //concatenamos
            wr.close();
            bw.close();
        } catch (IOException e) {
        }
    }
}
