package com.axelmonroyx;

import com.axelmonroyx.Lexico.Lexico;
import com.axelmonroyx.Sintaxis.Sintaxis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by axelmonroyx on 11/11/15.
 */
public class MainKarel {
    public JPanel JPanel1;
    public JTable table1;
    DefaultTableModel model = new DefaultTableModel();
    private JButton seleccionarArchivoButton;
    private JRadioButton frontIsClearRadioButton;
    private JRadioButton frontIsBlockedRadioButton;
    private JRadioButton leftIsClearRadioButton;
    private JRadioButton leftIsBlockedRadioButton;
    private JRadioButton rightIsClearRadioButton;
    private JRadioButton rightIsBlockedRadioButton;
    private JRadioButton nextToABeeperRadioButton;
    private JButton lexicoButton;
    private JButton generarCodigoObjetoButton;
    private JButton sintaxisYSemanticaButton;
    private JRadioButton noBeepersInBeeperRadioButton;
    private JRadioButton notNextToARadioButton;
    private JRadioButton anyBeepersInBeeperRadioButton;
    private JRadioButton facingNorthRadioButton;
    private JRadioButton facingSouthRadioButton;
    private JRadioButton facingEastRadioButton;
    private JRadioButton facingWestRadioButton;
    private JRadioButton notFacingNorthRadioButton;
    private JRadioButton notFacingEastRadioButton;
    private JRadioButton notFacingSouthRadioButton;
    private JRadioButton notFacingWestRadioButton;
    private JLabel LabelRuta;
    private JScrollPane tablescroll;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel LabelLexico;
    private JLabel LabelSemantica;
    private JLabel LabelGenerarCodigo;
    private File myCodeFile = new File("/home/axelmonroyx/Karel/Karel/src/com/axelmonroyx/codigo2.txt");
    private Lexico _Lexico;
    private Sintaxis _Sintaxis;

    public MainKarel() {
        LabelRuta.setText("Archivo: " + myCodeFile.getPath());
        resetTableLex();


        seleccionarArchivoButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

//

                JFileChooser _FileChooser = new JFileChooser("/home/axelmonroyx/Karel/Karel/src/com/axelmonroyx/");
                int respuesta = _FileChooser.showOpenDialog(null);
                if (respuesta == JFileChooser.APPROVE_OPTION) {

                    File myChoosenCode = _FileChooser.getSelectedFile();


                    myCodeFile = myChoosenCode;
                    LabelRuta.setText("Archivo: " + myChoosenCode.getPath());
                    //System.out.println(myCodeFile);
                    //trabajarLexico_Sintaxis(ArchivoCentral);

                }
            }
        });
        lexicoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runLexico();
                System.out.println(table1.getRowCount());
                //table1.setModel(model);
                table1.createDefaultColumnsFromModel();


            }
        });
        sintaxisYSemanticaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (_Lexico != null && !_Lexico.error_encontrado) runSintaxisSemantica();
            }
        });
    }

    private void runSintaxisSemantica() {
        _Sintaxis = new Sintaxis(_Lexico.cabeza);
        if (_Sintaxis.sintaxis_correcta && _Sintaxis.semantica_correcta) {
            LabelSemantica.setText("Sintaxis y semantica correctos");
        } else {
            LabelSemantica.setText("Sintaxis y semantica incorrectos");
        }

    }

    public void resetTableLex() {
        //model.setColumnCount(0);
        model.setRowCount(0);
        model.addColumn("Lexema");
        model.addColumn("Token");
        model.addColumn("Renglon");
        table1.setModel(model);

    }

    public void addnodo(String lexema, int token, int num_renglon) {
        Object[] row = {lexema, token, num_renglon};
        model.addRow(row);
    }

    public void runLexico() {
        try {

            this._Lexico = new Lexico(myCodeFile);
            _Lexico.p = _Lexico.cabeza;
            model.setRowCount(0);

            while (_Lexico.p.sig != null) {

                if ((int) _Lexico.p.token >= 200) {
                    String color = "\u001B[34m";
                    System.out.println(color + "Imprimir nodo: \t\t\t" + _Lexico.p.lexema + " \t\t\t\t\t" + _Lexico.p.token + " \t\t" + _Lexico.p.num_renglon);
                    addnodo(_Lexico.p.lexema, _Lexico.p.token, _Lexico.p.num_renglon);
                } else {
                    System.out.println("Imprimir nodo: \t\t\t" + _Lexico.p.lexema + " \t\t\t\t\t" + _Lexico.p.token + " \t\t" + _Lexico.p.num_renglon);
                    addnodo(_Lexico.p.lexema, _Lexico.p.token, _Lexico.p.num_renglon);

                }

                _Lexico.p = _Lexico.p.sig;
            }
            if (!this._Lexico.error_encontrado) {
                LabelLexico.setText("Léxico Correcto");
            } else {
                LabelLexico.setText("Léxico Incorrecto");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void createUIComponents() {

    }
}
