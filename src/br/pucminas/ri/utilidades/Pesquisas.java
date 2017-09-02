/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.utilidades;

import br.pucminas.ri.queryprocessor.modelo.MetaDataTerm;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author h-e-r
 */
public class Pesquisas {

    public static int [] pesquisaBinaria(ArrayList<MetaDataTerm> vocabulary, String term) {
        int [] resultado = new int [3];
        
        resultado [0] = 0;
        resultado [1] = 0;
        resultado [2] = 0;
        
        int meio;
        int esq = 0;
        int dir = vocabulary.size() - 1;

        while (esq <= dir) {

            meio = (esq + dir) / 2;
            
            MetaDataTerm registro = vocabulary.get(meio);
                       
            int [] comparar = TermCompare.compareTo(registro.getPalavra(), term);
            
            resultado[1] += comparar[1];


            if (comparar[0] == 0) {
                resultado [0] = 1;
                resultado [2] = meio;
                return (resultado);
            } else if (comparar [0] < 0) {
                dir = meio - 1;
            } else {
                esq = meio + 1;
            }
        }

        return resultado;
    }
    
    public static int [] pesquisaBinaria(ArrayList<MetaDataTerm> vocabulary, int [] term) {
        int [] resultado = new int [3];
        
        resultado [0] = 0;
        resultado [1] = 0;
        resultado [2] = 0;
        
        int meio;
        int esq = 0;
        int dir = vocabulary.size() - 1;
        //System.out.println (dir);
        while (esq <= dir) {

            meio = (esq + dir) / 2;
            
            MetaDataTerm registro = vocabulary.get(meio);
                       
            int [] comparar = TermCompare.compareTo(registro.getTransf(), term);
            
            resultado[1] += comparar[1];


            if (comparar[0] == 0) {
                resultado [0] = 1;
                resultado [2] = meio;
                return (resultado);
            } else if (comparar [0] < 0) {
                dir = meio - 1;
            } else {
                esq = meio + 1;
            }
        }

        return resultado;
    }
    
    
    public static int [] pesquisaBinaria(ArrayList<MetaDataTerm> vocabulary, BigInteger term) {
        int [] resultado = new int [3];
        
        resultado [0] = 0;
        resultado [1] = 0;
        resultado [2] = 0;
        
        int meio;
        int esq = 0;
        int dir = vocabulary.size() - 1;
        //System.out.println (dir);
        while (esq <= dir) {

            meio = (esq + dir) / 2;
            
            MetaDataTerm registro = vocabulary.get(meio);
                       
            int [] comparar = TermCompare.compareTo(registro.getTermo(), term);
            
            resultado[1] += comparar[1];

            //System.out.println (registro.getTermo() + "\ttermo : " + term);
            //System.out.println(resultado[1]);

            if (comparar[0] == 0) {
                resultado [0] = 1;
                resultado [2] = meio;
                return (resultado);
            } else if (comparar [0] < 0) {
                dir = meio - 1;
            } else {
                esq = meio + 1;
            }
        }

        return resultado;
    }    
    
    public static int[] pesquisaSequencial(ArrayList<MetaDataTerm> vocabulary, int [] term) {
        int[] resp = new int[3];
        resp[0] = 0;
        resp[1] = 0;
        resp[2] = 0;
        for (int x = 0; x < vocabulary.size(); x++) {
            int [] temp = TermCompare.compareTo(vocabulary.get(x).getTransf(), term);
            resp [1] += temp [1]; 
             
            if (temp [0] == 0) {
                resp[0] = 1;
                break;
            }
            resp [2]++;
        }
        return (resp);
    }
    
    
    public static int[] pesquisaSequencial(ArrayList<MetaDataTerm> vocabulary, BigInteger term) {
        int[] resp = new int[3];
        resp[0] = 0;
        resp[1] = 0;
        resp[2] = 0;
        for (int x = 0; x < vocabulary.size(); x++) {
            int [] temp = TermCompare.compareTo(vocabulary.get(x).getTermo(), term);
            resp [1] += temp [1]; 
             
            if (temp [0] == 0) {
                resp[0] = 1;
                break;
            }
            resp [2]++;
        }
        return (resp);
    }    

    public static int[] pesquisaSequencial(ArrayList<MetaDataTerm> vocabulary, String term) {
        int[] resp = new int[3];
        resp[0] = 0;
        resp[1] = 0;
        resp[2] = 0;
        for (int x = 0; x < vocabulary.size(); x++) {
            int [] temp = TermCompare.compareTo(vocabulary.get(x).getPalavra(), term);
            resp [1] += temp [1]; 
            
            if (temp [0] == 0) {
                resp[0] = 1;
                break;
            }
            resp [2]++; 
        }
        return (resp);
    }    
    

}
