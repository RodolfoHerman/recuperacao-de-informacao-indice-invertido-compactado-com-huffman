/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.queryprocessor.carregador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import br.pucminas.ri.queryprocessor.modelo.MetaDataTerm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LoaderInMemory {

    private final static String FIELD_SEPARETOR = " ";
    public final static int TERM_INDEX = 0;
    public final static int NI_INDEX = 1;
    public final static int BEGIN_OFFSET_INDEX = 2;
    public final static int LENGTH_READER_INDEX = 3;

    //@SuppressWarnings("resource")
    public static ArrayList<MetaDataTerm> getVocabulary(File file) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        ArrayList<MetaDataTerm> inMemoryStruct = new ArrayList<MetaDataTerm>();
        String vocabularyRegister;
        String[] vocabularyField;

        while ((vocabularyRegister = reader.readLine()) != null) {
            vocabularyField = vocabularyRegister.split(FIELD_SEPARETOR);
            inMemoryStruct.add(new MetaDataTerm(vocabularyField[TERM_INDEX],Integer.parseInt(vocabularyField[NI_INDEX]), Integer.parseInt(vocabularyField[BEGIN_OFFSET_INDEX]), Integer.parseInt(vocabularyField[LENGTH_READER_INDEX])));

        }
        return inMemoryStruct;

    }

}
