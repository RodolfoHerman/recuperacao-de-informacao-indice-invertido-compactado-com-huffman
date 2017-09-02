/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.queryprocessor.processador;

/**
 *
 * @author h-e-r
 */
import br.pucminas.ri.indexador.huffman.Compactadora;
import br.pucminas.ri.utilidades.Pesquisas;
import br.pucminas.ri.utilidades.TermCompare;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import br.pucminas.ri.queryprocessor.carregador.LoaderInMemory;
import br.pucminas.ri.queryprocessor.carregador.LoaderInMemoryCompac;
import br.pucminas.ri.queryprocessor.modelo.MetaDataTerm;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ProcessarComPesquisaSequencial {

    private int comparacoesOrig;
    private int comparacoesComp;

    private final String VOCABULARY_PATH2 = "./Vocabulario/vocabularioCompac.txt";
    private final String VOCABULARY_PATH1 = "./Vocabulario/vocabulario.txt";

    private final String DADOS_COMPARACOESORIG = "./Controle/TermosComparacoesOrigSeq.txt";
    private final String DADOS_COMPARACOESCOMP = "./Controle/TermosComparacoesCompSeq.txt";

    private File vocabularyFile;
    private ArrayList<String> vocabulario;

    public ProcessarComPesquisaSequencial(ArrayList<String> vocabulario) {
        this.vocabulario = vocabulario;
        this.comparacoesOrig = 0;
        this.comparacoesComp = 0;
    }

    public void avaliarComparacoesOrig() throws IOException {
        vocabularyFile = new File(VOCABULARY_PATH1);
        BufferedWriter escritor = new BufferedWriter(new FileWriter(DADOS_COMPARACOESORIG));
        ArrayList<MetaDataTerm> vocabularyOirg = LoaderInMemory.getVocabulary(vocabularyFile);

        ArrayList<String> dados = new ArrayList<String>();

        for (String string : vocabulario) {

            int[] test = null;

            test = Pesquisas.pesquisaSequencial(vocabularyOirg, string);

            if (test[0] == 1) {

                if (test[1] < 10) {
                    dados.add("0" + test[1] + " : " + string);
                } else {
                    dados.add(test[1] + " : " + string);
                }

                comparacoesOrig += test[1];
            }

        }

        Collections.sort(dados);
        for (int x = 0; x < dados.size(); x++) {
            escritor.write(dados.get(x));
            escritor.newLine();
        }
        escritor.write("" + comparacoesOrig);
        escritor.flush();
        escritor.close();

    }

    public void avaliarComparacoesComp() throws IOException {
        vocabularyFile = new File(VOCABULARY_PATH2);
        BufferedWriter escritor = new BufferedWriter(new FileWriter(DADOS_COMPARACOESCOMP));
        ArrayList<MetaDataTerm> vocabularyComp = LoaderInMemoryCompac.getVocabulary(vocabularyFile);

        ArrayList<String> dados = new ArrayList<String>();

        for (String string : vocabulario) {

            int[] test = null;

            Compactadora comp = new Compactadora(string);
            comp.compac();
            test = Pesquisas.pesquisaSequencial(vocabularyComp, comp.getTermoC());

            if (test[0] == 1) {

                if (test[1] < 10) {
                    dados.add("0" + test[1] + " : " + string);
                } else {
                    dados.add(test[1] + " : " + string);
                }

                comparacoesComp += test[1];
            }

        }

        Collections.sort(dados);
        for (int x = 0; x < dados.size(); x++) {
            escritor.write(dados.get(x));
            escritor.newLine();
        }
        escritor.write("" + comparacoesComp);
        escritor.flush();
        escritor.close();

    }

    public double comparacaoReducaoPesquisa() {
        return ((1.0-(comparacoesComp/(comparacoesOrig * 1.0)))* 100);
    }

}
