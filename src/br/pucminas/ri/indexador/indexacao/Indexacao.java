/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.indexador.indexacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


import br.pucminas.ri.indexador.leitor.LeitorManipulador;
import br.pucminas.ri.indexador.huffman.Compactadora;
import br.pucminas.ri.indexador.modelos.Termo;
import java.math.BigInteger;
import java.util.Collections;

public class Indexacao {

    private final String[] caminhos = {"./Alfabeto/", "./Controle/", "./Indices/", "./Vocabulario/", "./Documentos/"};

    private BufferedWriter[] ptAlfabeto;
    private RandomAccessFile caminho;
    //private BufferedWriter vocabularioArquivo;
    private RandomAccessFile vocabularioArquivo;

    private String nomeDocumento;
    private boolean indexar = true;

    private LeitorManipulador getDoc;

    public Indexacao() throws IOException {

        criarPastas();
        File pastaIndices = new File(caminhos[2]);

        if (pastaIndices.list().length == 0) {

            File documentos = new File(caminhos[4]);

            if (documentos.list().length != 0) {

                Scanner entrada = new Scanner(System.in);
                System.out.print("Informar nome do arquivo indice da colecao : ");
                this.nomeDocumento = entrada.nextLine();

                abrirPonteiros();

                entrada.close();

            } else {
                System.out.println("Erro, nao ha' documentos para analisar, verifique a pasta DOCUMENTOS");
                System.exit(0);
            }
        } else {
            this.indexar = false;
        }
    }

    public void criarListas() throws Exception {

        if (this.indexar) {
            this.caminho = new RandomAccessFile(caminhos[3] + "vocabularioCompac.txt", "rw");

            ArrayList<String> dados = new ArrayList<String>();
            TreeMap<String, Termo> vocabularioMemoria = new TreeMap<String, Termo>();
            TreeMap<BigInteger, Termo> vocabularioCompri = new TreeMap<BigInteger, Termo>();
            String linha;

            int totalTamComp = 0;
            int totalTamNorm = 0;
            int totalPalavrasInd = 0;
            int totalPalavrasDoc = 0;
            int posicao = 1;
            while ((linha = this.getDoc.obterProximaLinha()) != null) {

                String[] termo = linha.split(" ");

                for (String t : termo) {

                    if ((!t.equals(" ")) && (!t.equals(""))) {

                        

                        if ((t.length() > 1 && t.length() < 46)) {

                            Termo temp = new Termo(t, this.nomeDocumento, posicao++);
                            Compactadora comp = new Compactadora(t);
                            comp.compac();
                            temp.setBytesLength(comp.getBytesLength());
                            temp.setNum(comp.getTermoC());
                            temp.setTransf(comp.getTransf());
                            //temp.compac();
                            totalPalavrasDoc++;
                            if (!vocabularioMemoria.containsKey(t)) {
                                String sDados = temp.getDados();
                                dados.add(sDados);
                                totalPalavrasInd++;
                                totalTamComp += temp.getBytesLength();
                                totalTamNorm += temp.getTamTermo();
                                vocabularioMemoria.put(t, temp);
                                vocabularioCompri.put(temp.getNum(), temp);

                            } else {
                                vocabularioMemoria.get(t).getList().atualizarFrequencia(posicao++);
                            }
                        }
                    }
                }

            }

            long seek = 0;

            for (Iterator<Termo> t = vocabularioMemoria.values().iterator(); t.hasNext();) {
                Termo temp = t.next();

                char ponteiro = temp.getPonteiroArq();

                salvarAlfabeto(temp, ponteiro);

                seek = salvarVocabulario(temp, vocabularioMemoria, seek);

                t.remove();
            }

            SalvarVocabularioCompac(vocabularioCompri);

            salvarDados(dados);
            salvarTabela(totalTamComp, totalTamNorm, totalPalavrasDoc, totalPalavrasInd);
        }

    }

    private void SalvarVocabularioCompac(TreeMap<BigInteger, Termo> vocabularioCompri) throws IOException {
        for (Iterator<Termo> t = vocabularioCompri.values().iterator(); t.hasNext();) {
            Termo temp = t.next();
            
            this.caminho.write(temp.getBytesLength());
            this.caminho.write(temp.getTransf());
            this.caminho.writeBytes(temp.getNiString() + " " + temp.getSeeks() + " " + temp.getTamListInvertida());
            //this.caminho.writeBytes(temp.getTermo() + "\t\t\t" + temp.getNum());
            this.caminho.write(System.lineSeparator().getBytes());
            t.remove();
        }
    }

    private void salvarAlfabeto(Termo temp, char ponteiro) throws IOException {
        int pos;

        pos = ponteiro - 'a';

        this.ptAlfabeto[pos]
                .write(temp.getTermo() + " " + temp.getList().getNomDoc() + " " + temp.getList().getPosicoes() + " " + temp.getList().getQtVezes());
        this.ptAlfabeto[pos].newLine();
        this.ptAlfabeto[pos].flush();

    }

    private long salvarVocabulario(Termo temp, TreeMap<String, Termo> vocabularioMemoria, long seek)
            throws IOException {

        vocabularioMemoria.get(temp.getTermo()).getList().setTamSeek(temp.getList().getTamByte());

        int tamListInvertida = temp.getList().getTamSeek();
        temp.addSeek(seek);
        temp.setTamListInvertida(tamListInvertida);
        this.vocabularioArquivo.writeBytes(temp.getTermo());
        this.vocabularioArquivo.writeBytes(" " + temp.getNiString() + " " + temp.getSeeks() + " " + temp.getTamListInvertida());
        this.vocabularioArquivo.write(System.lineSeparator().getBytes());
        seek = seek + tamListInvertida;

        return (seek);
    }

    private void salvarTabela(int totalTamComp, int totalTamNorm, int totalPalavrasDoc,
            int totalPalavrasInd) throws IOException {

        BufferedWriter escreve = new BufferedWriter(new FileWriter(caminhos[1] + "tabela.txt", true));
        double mOriginal = (totalTamNorm / (totalPalavrasInd * 1.0));
        double mCompacta = (totalTamComp / (totalPalavrasInd * 1.0));
        double reTamanho = (1.0 - (totalTamComp / (totalTamNorm * 1.0))) * 100;
        escreve.write("******************" + this.nomeDocumento + "***********************");
        escreve.newLine();
        escreve.write("TAM PALAVRAS ORIGINAIS : " + totalTamNorm + "\t\tTAM PALAVRAS COMPACTADAS : " + totalTamComp);
        escreve.newLine();
        escreve.write("MEDIA TAM. PALAVRA ORIGINAL : " + String.format("%.2f", mOriginal) + "\tMEDIA TAM. PALAVRA COMPAC : " + String.format("%.2f", mCompacta));
        escreve.newLine();
        escreve.write("TOTAL PALAVRAS DOCUM : " + totalPalavrasDoc);
        escreve.newLine();
        escreve.write("TOTAL PALAVRAS INDEX : " + totalPalavrasInd);
        escreve.newLine();
        escreve.write("REDUCAO TAM : " + String.format("%.2f", reTamanho) + "%");
        escreve.newLine();
        escreve.write("****************************************************");
        escreve.newLine();
        escreve.newLine();
        escreve.flush();
        escreve.close();
        fecharPonteiros();
    }

    private void salvarDados(ArrayList<String> dados) throws IOException {
        BufferedWriter escreveDados = new BufferedWriter(new FileWriter(caminhos[1] + "dados.txt"));
        Collections.sort(dados);
        for (Iterator<String> t = dados.iterator(); t.hasNext();) {
            String temp = t.next();
            temp = temp.substring(temp.indexOf(" ") + 1, temp.length());
            escreveDados.write(temp);
            escreveDados.newLine();
            t.remove();
        }
        escreveDados.flush();
        escreveDados.close();
    }

    private void criarPastas() {
        new File(caminhos[0]).mkdirs();
        new File(caminhos[1]).mkdirs();
        new File(caminhos[2]).mkdirs();
        new File(caminhos[3]).mkdirs();
        new File(caminhos[4]).mkdirs();
    }

    private void abrirPonteiros() throws IOException {
        this.vocabularioArquivo = new RandomAccessFile(caminhos[3] + "vocabulario.txt", "rw");
        this.getDoc = new LeitorManipulador(caminhos[4] + nomeDocumento);
        this.ptAlfabeto = new BufferedWriter[26];

        for (int x = 0; x < 26; x++) {
            ptAlfabeto[x] = new BufferedWriter(new FileWriter(caminhos[0] + ((char) ('a' + x)) + ".txt", true));

        }
    }

    private void fecharPonteiros() throws IOException {
        this.vocabularioArquivo.close();
        this.caminho.close();
        for (int x = 0; x < 26; x++) {
            ptAlfabeto[x].close();

        }
    }

    public boolean getIndexar() {
        return (this.indexar);
    }

}
