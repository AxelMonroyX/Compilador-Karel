package com.axelmonroyx.GenerarCodigoObjeto;

import java.io.*;
import java.util.Stack;


/**
 * Created by axelmonroyx on 13/11/15.
 */
public class GenerarOBJ {
    Lista_polish lista_polish;

    public GenerarOBJ(Lista_polish lista_polish) throws IOException {
        this.lista_polish = lista_polish;

        generarArchivo();
    }


    private void generarArchivo() throws IOException {
        //System.out.println("SADSADSADSAD");
        String head = obtenerPlantilla("head");
        String bottom = obtenerPlantilla("bottom");
        // System.out.println("HEAD    "+ head);

        File f;
        f = new File("codigo" + ".asm");
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            wr.write(head);//escribimos en el archivo
            wr.write(";COMPI");
            String resultadocompilador = obtenerResultado();
            wr.append(resultadocompilador);
            wr.append(bottom); //concatenamos
            wr.close();
            bw.close();
        } catch (IOException e) {
        }
    }

    private String obtenerResultado() {
        lista_polish.Mostrar();
        StringBuilder codigoASM = new StringBuilder();
        Stack pila = new Stack();
        nodo_polish Actual = lista_polish.Pri;
        while (Actual != null) {
            if (Actual.tipo.equals("operando")) {
                pila.push(Actual);
            } else if (Actual.tipo.equals("operador")) {
                if (Actual.lexema.equals("print")) {
                    codigoASM.append(System.lineSeparator());
                    codigoASM.append("\t");
                    nodo_polish elementopila = (nodo_polish) pila.pop();
                    codigoASM.append("\t");
                    codigoASM.append("WRITE " + elementopila.lexema);
                    codigoASM.append(System.lineSeparator());
                    codigoASM.append("\t");
                    codigoASM.append("\t");
                    codigoASM.append("WRITELN ");
                }


            } else if (Actual.tipo.equals("puntero")) {
                codigoASM.append(System.lineSeparator());
                codigoASM.append("\t");
                codigoASM.append(Actual.lexema + ":");
            }


            Actual = Actual.sig;
        }
        return codigoASM.toString();
    }

    private String obtenerPlantilla(String nombreArchivo) throws IOException {
        File myCodeFile = new File("/home/axelmonroyx/Karel/Karel/src/com/axelmonroyx/template/" + nombreArchivo + ".asm");

        String everything = " ";

        try (BufferedReader br = new BufferedReader(new FileReader(myCodeFile))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        }
        return everything;

    }


}
