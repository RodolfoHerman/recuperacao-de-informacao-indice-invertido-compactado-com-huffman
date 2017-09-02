/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.queryprocessor.carregador;

import br.pucminas.ri.queryprocessor.modelo.MetaDataTerm;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;


public class LoaderInMemoryCompac {

    private final static String FIELD_SEPARETOR = " ";
    public final static int NI_INDEX = 0;
    public final static int BEGIN_OFFSET_INDEX = 1;
    public final static int LENGTH_READER_INDEX = 2;

    //@SuppressWarnings("resource")
    public static ArrayList<MetaDataTerm> getVocabulary(File file) throws IOException {

        //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        RandomAccessFile reader = new RandomAccessFile (file,"rw");
        
        ArrayList<MetaDataTerm> inMemoryStruct = new ArrayList<MetaDataTerm>();
        String[] vocabularyField;
        int qt;
        
        while ((qt = reader.read()) != -1) {
            byte[] palavra = new byte[qt];
            reader.read(palavra);
            vocabularyField = reader.readLine().split(FIELD_SEPARETOR);
            inMemoryStruct.add(new MetaDataTerm(new BigInteger(1, palavra), palavra, Integer.parseInt(vocabularyField[NI_INDEX]), Integer.parseInt(vocabularyField[BEGIN_OFFSET_INDEX]), 
                    Integer.parseInt(vocabularyField[LENGTH_READER_INDEX])));
            
        }  


        return inMemoryStruct;

    }    
    
}
