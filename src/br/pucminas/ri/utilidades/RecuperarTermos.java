/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author mg4140es
 */
public class RecuperarTermos {
    
    private static final String VOCABULARY_PATH = "./Vocabulario/vocabulario.txt";

    public static ArrayList<String> listaTermos() throws FileNotFoundException, IOException  {
        ArrayList <String> termos = new ArrayList <String>();
        BufferedReader leitor = new BufferedReader (new FileReader (VOCABULARY_PATH));
        String leitura;
        
        while ((leitura = leitor.readLine()) != null){
            String [] t = leitura.split(" ");
            termos.add(t[0]);
        }
        return (termos);
    }
    
}
