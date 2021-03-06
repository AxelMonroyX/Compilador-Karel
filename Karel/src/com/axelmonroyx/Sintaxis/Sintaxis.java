/*
 Creado: 31-may-2015 , Hora: 12:56:33
 */
package com.axelmonroyx.Sintaxis;

//import javax.swing.JOptionPane;

import com.axelmonroyx.GenerarCodigoObjeto.GenerarOBJ;
import com.axelmonroyx.GenerarCodigoObjeto.Lista_polish;
import com.axelmonroyx.GenerarCodigoObjeto.nodo_polish;
import com.axelmonroyx.Lexico.nodo;
import com.axelmonroyx.Semantica.Lista_nodo_define_new_instruction;
import com.axelmonroyx.Semantica.Lista_nodo_external;

import javax.swing.*;
import java.io.IOException;
import java.util.Stack;

/**
 * @author Axel Monroy <xaxelmonroyx@gmail.com>
 */
public class Sintaxis {

    public boolean sintaxis_correcta = false;
    public boolean semantica_correcta = true;
    nodo p;
    String errores[][] = {
            // 0              						 1   <------numero de columna
        /*0*/{"Se esperaba *", "500"},
        /*1*/ {"Se esperaba * ó /", "501"},
        /*2*/ {"Fin de archivo inesperado", "502"},
        /*3*/ {"Simbolo no valido", "503"},
        /*4*/ {"Se esperaba la palabra beginning-of-program", "504"},
        /*5*/ {"Sintaxis de MethodDeclaration erronea", "505"},
        /*6*/ {"Sintaxis de LinkDeclaration erronea", "506"},
        /*7*/ {"Se esperaba la palabra define-new-instruction", "507"},
        /*8*/ {"Se esperaba un identificador", "508"},
        /*9*/ {"Se esperaba un Statement despues del idenficador", "509"},
        /*10*/ {"Se esperaba la palabra  end-of-program", "510"},
        /*11*/ {"Sintaxis de MainDeclaration", "511"},
        /*12*/ {"Se esperaba la palabra end-of-execution", "512"},
        /*13*/ {"Statement mal formado", "513"},
        /*14*/ {"Se esperaba la palabrabeggining-of-execution", "514"},
        /*15*/ {"Se esperaba un ; ", "515"},
        /*16*/ {"Se esperaba la palabra if ", "516"},
        /*17*/ {"Expresion mal formada ", "517"},
        /*18*/ {"Se espera una o mas sentencias dentro del bloque begin  ", "518"},
        /*19*/ {"Block statement mal formado  ", "519"},
        /*20*/ {"If statement mal formado  ", "520"},
        /*21*/ {"While statement mal formado  ", "521"},
        /*22*/ {"Iterate statement mal formado  ", "522"},
        /*23*/ {"Call statement mal formado  ", "523"},
            //Errores de Semantica
        /*600*/ {"Verificar que no exista una instrucción ya declarada con el mismo nombre", "600"},
        /*601*/ {"Verificar que no exista otra linea de external con el mismo identificador", "601"},
        /*602*/ {"Verifica que la instrucción haya sido declarada anteriormente con define-new-instruction", "602"}

    };
    Lista_nodo_define_new_instruction Lista_new_instructions = new Lista_nodo_define_new_instruction();
    Lista_nodo_external Lista_externals = new Lista_nodo_external();
    Lista_polish lista_polish = new Lista_polish();
    Lista_polish lista_expresiones = new Lista_polish();
    GenerarOBJ generarOBJ;
    Stack<String> pila = new Stack<String>();
    int punteroIF_P = 0;
    private int punteroIF_Q;
    private JRadioButton frontIsClearRadioButton;
    private JRadioButton frontIsBlockedRadioButton;
    private JRadioButton leftIsClearRadioButton;
    private JRadioButton leftIsBlockedRadioButton;
    private JRadioButton rightIsClearRadioButton;
    private JRadioButton rightIsBlockedRadioButton;
    private JRadioButton nextToABeeperRadioButton;
    private JRadioButton noBeepersInBeeperRadioButton;
    private JRadioButton notNextToARadioButton;
    private JRadioButton facingSouthRadioButton;
    private JRadioButton anyBeepersInBeeperRadioButton;
    private JRadioButton facingNorthRadioButton;
    private JRadioButton facingEastRadioButton;
    private JRadioButton facingWestRadioButton;
    private JRadioButton notFacingNorthRadioButton;
    private JRadioButton notFacingEastRadioButton;
    private JRadioButton notFacingSouthRadioButton;
    private JRadioButton notFacingWestRadioButton;
    private int punteroIterate_S;

    public Sintaxis(nodo cabeza) {
        p = cabeza;
        System.out.println("----------------------------------------");
        if (identificarProgramDeclaration()) {
            System.out.println("Sintaxis correcta");
            sintaxis_correcta = true;
            //OBJ
            try {
                generarOBJ = new GenerarOBJ(lista_polish);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //lista_polish.Mostrar();

        } else {
            sintaxis_correcta = false;

        }


    }

    public Sintaxis(nodo cabeza, JRadioButton frontIsClearRadioButton, JRadioButton frontIsBlockedRadioButton, JRadioButton leftIsClearRadioButton, JRadioButton leftIsBlockedRadioButton, JRadioButton rightIsClearRadioButton, JRadioButton rightIsBlockedRadioButton, JRadioButton nextToABeeperRadioButton, JRadioButton noBeepersInBeeperRadioButton, JRadioButton notNextToARadioButton, JRadioButton anyBeepersInBeeperRadioButton, JRadioButton facingNorthRadioButton, JRadioButton facingSouthRadioButton, JRadioButton facingEastRadioButton, JRadioButton facingWestRadioButton, JRadioButton notFacingNorthRadioButton, JRadioButton notFacingEastRadioButton, JRadioButton notFacingSouthRadioButton, JRadioButton notFacingWestRadioButton) {
        this.frontIsClearRadioButton = frontIsClearRadioButton;
        this.frontIsBlockedRadioButton = frontIsBlockedRadioButton;
        this.leftIsClearRadioButton = leftIsClearRadioButton;
        this.leftIsBlockedRadioButton = leftIsBlockedRadioButton;
        this.rightIsClearRadioButton = rightIsClearRadioButton;
        this.rightIsBlockedRadioButton = rightIsBlockedRadioButton;
        this.nextToABeeperRadioButton = nextToABeeperRadioButton;
        this.noBeepersInBeeperRadioButton = noBeepersInBeeperRadioButton;
        this.notNextToARadioButton = notNextToARadioButton;
        this.anyBeepersInBeeperRadioButton = anyBeepersInBeeperRadioButton;
        this.facingNorthRadioButton = facingNorthRadioButton;
        this.facingSouthRadioButton = facingSouthRadioButton;
        this.facingEastRadioButton = facingEastRadioButton;
        this.facingWestRadioButton = facingWestRadioButton;
        this.notFacingNorthRadioButton = notFacingNorthRadioButton;
        this.notFacingEastRadioButton = notFacingEastRadioButton;
        this.notFacingSouthRadioButton = notFacingSouthRadioButton;
        this.notFacingWestRadioButton = notFacingWestRadioButton;
        p = cabeza;
        System.out.println("----------------------------------------");
        if (identificarProgramDeclaration()) {
            System.out.println("Sintaxis correcta");
            sintaxis_correcta = true;
            //OBJ
            try {
                generarOBJ = new GenerarOBJ(lista_polish);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //lista_polish.Mostrar();

        } else {
            sintaxis_correcta = false;

        }
    }

    private void ImprimirError(int errorIdentificado) {

        for (int i = 0; i < errores.length; i++) {
            if (errorIdentificado == Integer.valueOf(errores[i][1])) {
                String color = "\u001B[31m";
                String mensaje = ("Error:     " + errores[i][0] + "    " + errores[i][1] + "  EN EL RENGLON     " + p.num_renglon);
                System.out.println(color + mensaje);
                //JOptionPane.showMessageDialog(null, mensaje, "Sintaxis", 1);
                // error_encontrado=true;
                if (errorIdentificado >= 600) {
                    semantica_correcta = false;
                }

            }
        }

    }

    private boolean identificarProgramBody() {
        if (p.token == 203 || p.token == 205) {
            if (idetificarMethod_Link_Declarations()) {
                if (p.token == 207) { //beggining-of-execution
                    //p = p.sig;
                    if (identificarMainDeclaration()) {

                        return true;
                    } else {
                        ImprimirError(511);
                    }
                } else {
                    ImprimirError(514);
                }
            }
        } else if (p.token == 207) { //beggining-of-execution
            if (identificarMainDeclaration()) {
                //p = p.sig;
                return true;
            } else {
                ImprimirError(511);
            }
        }
        return false;
    }

    private boolean identificarProgramDeclaration() {
        if (p.token == 201) {// beginning-of-program
            p = p.sig;

            if (identificarProgramBody()) {
                if (p.token == 202) {//end-of-program
                    // p = p.sig;
                    return true;
                } else {
                    ImprimirError(510);
                }
            }
        } else {
            ImprimirError(504);
            return false;
        }
        return false;

    }

    private boolean identificarMethodDeclaration() {
        nodo aux3 = p;
        if (p.token == 203) {//define-new-instruction
            p = p.sig;
            if (p.token == 101) { // id
                aux3 = p;
                p = p.sig;
                //Linea de semantica Error 600 define-new-instruction opcion #2
                if (p.token == 204) {// as
                    //Semantica
                    if (Lista_new_instructions.nodoEncontrado(aux3.lexema, false)) {
                        ImprimirError(600);
                    } else {
                        Lista_new_instructions.Insertar_Nodo_Final(aux3.lexema, false, p.sig.lexema);

                    }

                    System.out.println("Insertar nodo");
                    p = p.sig;
                    if (identificarStatement()) {
                        //p = p.sig;
                        return true;
                    } else {
                        ImprimirError(509);
                    }
                } else {
                    if (p.token == 103) { //(
                        p = p.sig;
                        if (p.token == 101) { // id
                            p = p.sig;
                            if (p.token == 104) {//)
                                p = p.sig;
                                //Semantica
                                if (Lista_new_instructions.nodoEncontrado(aux3.lexema, true)) {
                                    ImprimirError(600);
                                } else {
                                    Lista_new_instructions.Insertar_Nodo_Final(aux3.lexema, true, p.sig.lexema);
                                }

                                if (p.token == 204) {// as
                                    p = p.sig;
                                    if (identificarStatement()) {
                                        //p = p.sig;
                                        return true;
                                    } else {
                                        ImprimirError(509);
                                    }
                                }
                            }
                        }

                    }
                }
            } else {
                ImprimirError(508);
            }
        } else {
            ImprimirError(507);
        }
        return false;
    }

    private boolean idetificarMethod_Link_Declarations() {
        if (p.token == 203) { //define-new-instruction
            //Linea de semantica Error 600
            if (identificarMethodDeclaration()) {
                if (p.token == 105) {  // ;
                    nodo aux = p.sig;
                    if (aux.token == 203 || aux.token == 205) {
                        p = p.sig;
                    }

                    if (p.token == 203 || p.token == 205) {// define-new-instruction   external
                        if (idetificarMethod_Link_Declarations()) {
                            //p = p.sig;
                            return true;
                        }
                    } else {
                        p = p.sig;
                        return true;
                    }
                } else {
                    ImprimirError(515);
                }
            } else {
                ImprimirError(505);
            }
        }
        if (p.token == 205) { //external
            //p = p.sig;
            //Linea de semantica Error 601
            if (identificarLinkDeclaration()) {
                if (p.token == 105) {  // ;
                    p = p.sig;
                    if (p.token == 203 || p.token == 205) {// define-new-instruction   external
                        if (idetificarMethod_Link_Declarations()) {
                            //p = p.sig;
                            return true;
                        }
                    } else {
                        p = p.sig;
                        return true;
                    }
                }
            } else {
                ImprimirError(506);
            }
        }

        return false;

    }

    private boolean identificarLinkDeclaration() {
        nodo aux5 = p;
        if (p.token == 205) { //external
            p = p.sig;
            if (p.token == 101) { // id
                aux5 = p;
                p = p.sig;
                if (p.token == 105) { //;
                    if (Lista_externals.nodoEncontrado(aux5.lexema, false, false)) {
                        ImprimirError(601);
                    } else {
                        Lista_externals.Insertar_Nodo_Final(aux5.lexema, false, false);
                    }
                    //Linea de semantica Error 601 external opcion #1
                    return true;
                } else {
                    if (p.token == 103) {//(
                        p = p.sig;
                        if (p.token == 101) { //id
                            p = p.sig;
                            if (p.token == 104) { // )
                                p = p.sig;
                                if (p.token == 105) { //;
                                    //Linea de semantica Error 601 external opcion #3
                                    if (Lista_externals.nodoEncontrado(aux5.lexema, true, false)) {
                                        ImprimirError(601);
                                    } else {
                                        Lista_externals.Insertar_Nodo_Final(aux5.lexema, true, false);
                                    }
                                    return true;
                                } else {
                                    if (p.token == 206) { //using
                                        p = p.sig;
                                        if (p.token == 106) { //string
                                            p = p.sig;
                                            if (p.token == 105) { //;
                                                //Linea de semantica Error 601 external opcion #4
                                                if (Lista_externals.nodoEncontrado(aux5.lexema, true, true)) {
                                                    ImprimirError(601);
                                                } else {
                                                    Lista_externals.Insertar_Nodo_Final(aux5.lexema, true, true);
                                                }
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (p.token == 206) { //using
                            p = p.sig;
                            if (p.token == 106) {//string
                                p = p.sig;
                                if (p.token == 105) { //;
                                    if (Lista_externals.nodoEncontrado(aux5.lexema, false, true)) {
                                        ImprimirError(601);
                                    } else {
                                        Lista_externals.Insertar_Nodo_Final(aux5.lexema, false, true);
                                    }
                                    //Linea de semantica Error 601 external opcion #2
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean identificarStatement() {
        if (p.token == 209) { //begin   del BLOCK
            //p = p.sig;
            if (identificarBlock_Statement()) {
                return true;
            } else {
                ImprimirError(519);
            }
        }
        if (p.token == 217) { //if   del ifstatement
            //p = p.sig;
            if (identificarIf_Statement()) {
                return true;
            } else {
                ImprimirError(520);
            }
        }
        if (p.token == 220) { //while del whilestatement
            //p = p.sig;
            if (identificarWhile_Statement()) {
                return true;
            } else {
                ImprimirError(521);
            }
        }
        if (p.token == 222) { //iterate   del iterate statement
            //p = p.sig;
            if (identificarIterate_Statement()) {
                return true;
            } else {
                ImprimirError(522);
            }
        }
        if (p.token == 224) { //turnoff   del turnoff statement
            p = p.sig;
            if (identificarTurnoff_Statement()) {
                return true;
            }
        }
        if (p.token == 225) { //turnleft  del turnleft statement
            p = p.sig;
            if (identificarTurnleft_Statement()) {
                return true;
            }
        }
        if (p.token == 226) { //move  del move statement
            p = p.sig;
            if (identificarMove_Statement()) {
                return true;
            }
        }
        if (p.token == 227) { //pickbeeper  del pickbeeper statement
            p = p.sig;
            if (identificarPickbeeper_Statement()) {
                return true;
            }
        }
        if (p.token == 228) { //putbeeper  del putbeeper statement
            //p = p.sig;
            if (identificarPutbeeper_Statement()) {
                return true;
            }
        }
        if (p.token == 101) { //call staement
            //p = p.sig;
            if (identificarCall_Statement()) {
                return true;
            } else {
                ImprimirError(523);
            }
        }
        return false;
    }

    private boolean identificarMainDeclaration() {
        if (p.token == 207) { //beggining-of-execution
            p = p.sig;
            if (identificarStatements()) {

                //p = p.sig;
                if (p.token == 208) { //end-of-execution
                    p = p.sig;
                    return true;
                } else {
                    ImprimirError(512);
                }

            }
        }
        return false;
    }

    private boolean identificarStatements() {
        if (identificarStatement()) {
            //p=p.sig;
            if (p.token == 105) { //;) 
                p = p.sig;
                if (p.token == 208) {
                    //p=p.sig;
                    return true;
                } else if (identificarStatements()) {
                    return true;
                }
            } else {
                ImprimirError(515);
            }
        } else {
            ImprimirError(513);
        }
        return false;
    }

    private boolean identificarBlock_Statement() {
        if (p.token == 209) { //begin
            p = p.sig;
            if (identificarStatements_Block()) {

                if (p.token == 210) { //end
                    p = p.sig;
                    return true;
                } else {
                    ImprimirError(512);
                }

            }
            ImprimirError(518);
        }
        return false;
    }

    private boolean identificarIf_Statement() {
        if (p.token == 217) { //if
            p = p.sig;
            if (identificarExpresion()) {
                insertarExpresionIfPolish();

                if (p.token == 218) {//then
                    p = p.sig;
                    //BRF P
                    punteroIF_P++;
                    int punteroIF_P_interno = punteroIF_P;
                    lista_polish.Insertar_Brinco("BRF", 100, "operador", ("P" + punteroIF_P_interno));

                    if (identificarStatement()) { //S1
                        // lista_polish.Mostrar();
                        //BRF P
                        punteroIF_Q++;
                        lista_polish.Insertar_Brinco("BRI", 100, "operador", ("Q" + punteroIF_Q));
                        lista_polish.Insertar_Nodo_Final("P" + punteroIF_P_interno, 100, "puntero");


                        if (p.token == 105) {
                            nodo aux = p.sig;

                            if (aux.token == 219) {//else
                                p = p.sig;
                            }

                            if (p.token == 219) { //else
                                p = p.sig;
                                //lista_polish.Insertar_Nodo_Final("P" + punteroIF_P, 100, "puntero");
                                if (identificarStatement()) {

                                    //BRF Q
                                    lista_polish.Insertar_Nodo_Final("Q" + punteroIF_Q, 100, "puntero");
                                    return true;
                                }
                            } else {
                                //BRF Q
                                lista_polish.Insertar_Nodo_Final("Q" + punteroIF_Q, 100, "puntero");

                                return true;
                            }
                        }

                    }
                }
            } else {
                ImprimirError(517);
            }

        } else {
            ImprimirError(516);
        }
        return false;
    }

    private void insertarExpresionIfPolish() {
        //Se tiene lista_expresion y pila


        if (pila.empty() && !lista_expresiones.Lista_polishVacia()) {

            nodo_polish Actual = lista_expresiones.Pri;
            if (lista_expresiones.Lista_polishVacia()) {
                System.out.println("La " + "lista" + " esta vacia");
            }
            System.out.println("Lexema" + "\t" + "Tipo" + "\t" + "Salto" + "\t" + "Valor");
            while (Actual != null) {
                lista_polish.Insertar_Nodo_Final(Actual.lexema,
                        Actual.token, Actual.tipo,
                        Actual.valor);
                //System.out.println(Actual.valor);

                Actual = Actual.sig;
            }

            lista_expresiones = new Lista_polish();

        }


    }

    private boolean identificarWhile_Statement() {
        if (p.token == 220) { //while
            p = p.sig;
            if (identificarExpresion()) {
                if (p.token == 221) { //do
                    p = p.sig;
                    if (identificarStatement()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean identificarIterate_Statement() {
        this.punteroIterate_S++;
        int punteroIterate_local_S = punteroIterate_S;
        if (p.token == 222) { //iterate
            p = p.sig;
            if (p.token == 102) { // num


                //Exp
                lista_polish.Insertar_Nodo_Final(p.lexema, p.token, "operando");
                lista_polish.Insertar_Nodo_Final("repeat", 1000, "operador");
                lista_polish.Insertar_Nodo_Final("S" + punteroIterate_local_S, 100, "puntero");
                //BRF
                // lista_polish.Insertar_Brinco("BRF", 1200, "operador", ("S" + punteroIterate_local_S));
                p = p.sig;
                if (p.token == 223) { //times
                    p = p.sig;
                    if (identificarStatement()) {//S1
                        //lista_polish.Insertar_Brinco("BRI", 1200, "operador", ("R" + punteroIterate_local_S));
                        lista_polish.Insertar_Nodo_Final("S" + punteroIterate_local_S, 100, "loop");
                        //lista_polish.Insertar_Nodo_Final("R" + punteroIterate_local_S, 100, "puntero");

                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean identificarTurnoff_Statement() {
        lista_polish.Insertar_Nodo_Final("turnoff", 100, "operando");
        lista_polish.Insertar_Nodo_Final("print", 100, "operador");
        return true;
    }

    private boolean identificarTurnleft_Statement() {
        lista_polish.Insertar_Nodo_Final("turnleft", 100, "operando");
        lista_polish.Insertar_Nodo_Final("print", 100, "operador");
        return true;
    }

    private boolean identificarMove_Statement() {
        lista_polish.Insertar_Nodo_Final("move", 100, "operando");
        lista_polish.Insertar_Nodo_Final("print", 100, "operador");
        return true;
    }

    private boolean identificarPickbeeper_Statement() {

        lista_polish.Insertar_Nodo_Final("pickbeeper", 100, "operando");
        lista_polish.Insertar_Nodo_Final("print", 100, "operador");
        return true;
    }

    private boolean identificarPutbeeper_Statement() {
        lista_polish.Insertar_Nodo_Final("putbeeper", 100, "operando");
        lista_polish.Insertar_Nodo_Final("print", 100, "operador");
        return true;
    }

    private boolean identificarStatements_Block() {
        if (identificarStatement()) {
            if (p.token == 105) { //;) 
                p = p.sig;
                if (p.token == 210) { //end
                    //p = p.sig;
                    return true;
                } else if (identificarStatements_Block()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean identificarExpresion() {
        if (identificarAndClause()) {
            if (p.token == 213) { //or
                insertarPila("or");

                p = p.sig;
                if (identificarExpresion()) {
                    Completar_Lista_Expresiones();
                    return true;
                }
            } else {
                Completar_Lista_Expresiones();
                return true;
            }
        }
        return false;
    }

    private void Completar_Lista_Expresiones() {
        //OBJ
        //System.out.println(pila.toString());
        while (!pila.empty() && pila.peek() != null) {
            //lista_polish.Insertar_Nodo_Final(p.lexema, p.token, "operando", valor);
            String elemntopila = pila.peek();

            lista_expresiones.Insertar_Nodo_Final(pila.peek(), 12555, "operador");
            pila.pop();
        }


        //lista_expresiones.Mostrar();


    }

    private boolean identificarAndClause() {
        if (identificarNotClause()) {
            if (p.token == 214) { //and
                insertarPila("and");
                p = p.sig;
                if (identificarAndClause()) {
                    Completar_Lista_Expresiones();
                    return true;
                }
            } else {
                Completar_Lista_Expresiones();
                return true;
            }
        }
        return false;
    }

    private boolean identificarNotClause() {
        if (p.token == 215) { //not
            insertarPila("not");
            p = p.sig;
            if (identificarAtomClause()) {
                Completar_Lista_Expresiones();
                return true;
            }

        } else {
            if (identificarAtomClause()) {
                Completar_Lista_Expresiones();
                return true;
            }
        }
        return false;
    }

    private void insertarPila(String operador) {
        // and or not
        if (pila.empty()) {
            pila.push(operador);
        } else {
            if (operador.equals("or") || operador.equals("and")
                    || operador.equals("not")) {
                if (pila.peek().equals("or") ||
                        pila.peek().equals("and") || pila.peek().equals("not")) {
                    lista_expresiones.Insertar_Nodo_Final(pila.peek(), 102, "operador");
                    pila.pop();
                    pila.push(operador);

                } else if (operador.equals(")")) {
                    //  en caso de ser  o   )
                    pila.push(operador);
                } else if (operador.equals("(")) {
                    while (!pila.empty() && pila.peek().equals(")")) {
                        lista_expresiones.Insertar_Nodo_Final(pila.peek(), 100, operador);
                        pila.pop();

                    }
                    if (pila.empty() && pila.peek().equals(")")) pila.pop();


                }

            }


        }
    }

    private boolean identificarAtomClause() {
        if (p.token == 216) { //iszero
            p = p.sig;
            if (p.token == 103) { // (
                p = p.sig;
                if (p.token == 102) { // num
                    lista_expresiones.Insertar_Nodo_Final(p.lexema, 102, "operando");
                    lista_expresiones.Insertar_Nodo_Final("0", 102, "operando");
                    if (p.lexema.equals("0")) {
                        lista_expresiones.Insertar_Nodo_Final("iszero", 216, "operando", "true");
                    } else {
                        lista_expresiones.Insertar_Nodo_Final("iszero", 216, "operando", "false");
                    }

                    p = p.sig;
                    if (p.token == 104) { // )
                        p = p.sig;
                        return true;
                    }
                }
            }
        } else {
            if (p.token == 103) { // (
                p = p.sig;
                if (identificarExpresion()) {
                    if (p.token == 104) { // )
                        p = p.sig;
                        return true;
                    }
                }
            } else {
                if (identificarBooleanFunction()) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean identificarBooleanFunction() {
        if (p.token >= 229 && p.token < 246) {
            boolean valor = false;
            //OBJ
/*
*          {"front-is-clear", "229"},
         {"front-is-blocked", "230"},
         {"left-is-clear", "231"},
         {"left-is-blocked", "232"},
         {"right-is-clear", "233"},
         {"right-is-blocked", "234"},
         {"next-to-a-beeper", "235"},
         {"not-next-to-a-beeper", "236"},
         {"any-beepers-in-beeper-bag", "237"},
         {"no-beepers-in-beeper-bag", "238"},
         {"facing-north", "239"},
         {"facing-south", "240"},
         {"facing-east", "241"},
         {"facing-west", "242"},
         {"not-facing-north", "243"},
         {"not-facing-south", "244"},
         {"not-facing-east", "245"},
         {"not-facing-west", "246"}
* */
            if (p.token == 229) {//front-is-clear
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(frontIsClearRadioButton.isSelected()));
            }
            if (p.token == 230) {//front-is-blocked
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(frontIsBlockedRadioButton.isSelected()));
            }
            if (p.token == 231) {//left-is-clear
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(leftIsClearRadioButton.isSelected()));
            }
            if (p.token == 232) {//left-is-blocked
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(leftIsBlockedRadioButton.isSelected()));
            }
            if (p.token == 233) {//right-is-clear
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(rightIsClearRadioButton.isSelected()));

            }
            if (p.token == 234) {//right-is-blocked
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(rightIsBlockedRadioButton.isSelected()));

            }
            if (p.token == 235) {//next-to-a-beeper
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(nextToABeeperRadioButton.isSelected()));
            }
            if (p.token == 236) {//no-to-a-beeper
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(notNextToARadioButton.isSelected()));
            }
            if (p.token == 237) {//any-beepers-in-beeper-bag
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(anyBeepersInBeeperRadioButton.isSelected()));
            }
            if (p.token == 238) {//no-beepers-in-beeper-bag
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(noBeepersInBeeperRadioButton.isSelected()));
            }
            if (p.token == 239) {//facing-north
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(facingNorthRadioButton.isSelected()));
            }
            if (p.token == 240) {//facing-south
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(facingSouthRadioButton.isSelected()));
            }
            if (p.token == 241) {//facing-east
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(facingEastRadioButton.isSelected()));
            }
            if (p.token == 242) {//facing-west
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(facingWestRadioButton.isSelected()));
            }
            if (p.token == 243) {//not-facing-north
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(notFacingNorthRadioButton.isSelected()));
            }
            if (p.token == 244) {//not-facing-south
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(notFacingSouthRadioButton.isSelected()));
            }
            if (p.token == 245) {//not-facing-east
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(notFacingEastRadioButton.isSelected()));
            }
            if (p.token == 246) {//not-facing-west
                lista_expresiones.Insertar_Nodo_Final(p.lexema, p.token,
                        "operando", Boolean.toString(notFacingWestRadioButton.isSelected()));
            }


            p = p.sig;
            return true;
        }

        return false;
    }

    private boolean identificarCall_Statement() {
        nodo aux4 = p;
        if (p.token == 101) { //id
            //Linea de semantica Error 602
            aux4 = p;
            p = p.sig;
            if (p.token == 105) { // ;
                if (!Lista_new_instructions.nodoEncontrado(aux4.lexema, false)) {
                    ImprimirError(602);
                } else {
                    //OBJ
                    lista_polish.Insertar_Nodo_Final(aux4.lexema, aux4.token, "operando");
                    lista_polish.Insertar_Nodo_Final("print", aux4.token, "operador");
                }


                //Linea de semantica Error 602 Call Statement opcion #2
                return true;
            } else {
                if (p.token == 103) { // (
                    p = p.sig;
                    if (p.token == 102) { //num
                        p = p.sig;
                        if (p.token == 104) { // )
                            p = p.sig;
                            if (p.token == 105) { //;
                                if (!Lista_new_instructions.nodoEncontrado(aux4.lexema, true)) {
                                    ImprimirError(602);
                                } else { //OBJ
                                    lista_polish.Insertar_Nodo_Final(aux4.lexema, aux4.token, "operando");
                                    lista_polish.Insertar_Nodo_Final("print", aux4.token, "operador");
                                }
                                //Linea de semantica Error 602 Call Statement opcion #1
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void mandarRadioButtons(JRadioButton frontIsClearRadioButton, JRadioButton frontIsBlockedRadioButton, JRadioButton leftIsClearRadioButton, JRadioButton leftIsBlockedRadioButton, JRadioButton rightIsClearRadioButton, JRadioButton rightIsBlockedRadioButton, JRadioButton nextToABeeperRadioButton, JRadioButton noBeepersInBeeperRadioButton, JRadioButton notNextToARadioButton, JRadioButton anyBeepersInBeeperRadioButton, JRadioButton facingNorthRadioButton, JRadioButton facingSouthRadioButton, JRadioButton facingEastRadioButton, JRadioButton facingWestRadioButton, JRadioButton notFacingNorthRadioButton, JRadioButton notFacingEastRadioButton, JRadioButton notFacingSouthRadioButton, JRadioButton notFacingWestRadioButton) {

        this.frontIsClearRadioButton = frontIsClearRadioButton;
        this.frontIsBlockedRadioButton = frontIsBlockedRadioButton;
        this.leftIsClearRadioButton = leftIsClearRadioButton;
        this.leftIsBlockedRadioButton = leftIsBlockedRadioButton;
        this.rightIsClearRadioButton = rightIsClearRadioButton;
        this.rightIsBlockedRadioButton = rightIsBlockedRadioButton;
        this.nextToABeeperRadioButton = nextToABeeperRadioButton;
        this.noBeepersInBeeperRadioButton = noBeepersInBeeperRadioButton;
        this.notNextToARadioButton = notNextToARadioButton;
        this.anyBeepersInBeeperRadioButton = anyBeepersInBeeperRadioButton;
        this.facingNorthRadioButton = facingNorthRadioButton;
        this.facingSouthRadioButton = facingSouthRadioButton;
        this.facingEastRadioButton = facingEastRadioButton;
        this.facingWestRadioButton = facingWestRadioButton;
        this.notFacingNorthRadioButton = notFacingNorthRadioButton;
        this.notFacingEastRadioButton = notFacingEastRadioButton;
        this.notFacingSouthRadioButton = notFacingSouthRadioButton;
        this.notFacingWestRadioButton = notFacingWestRadioButton;

    }
}
