/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.indexador.modelos;

public class Lista {

    private String nomDoc;
    private int qtVezes;
    private int tamSeek;
    private String posicoes;

    public Lista() {
    }

    public Lista(String nomDoc, int posicao) {
        this.nomDoc = nomDoc;
        this.qtVezes = 1;
        this.posicoes = "" + posicao + ",";
        this.tamSeek = 0;
    }

    public int getTamSeek() {
        return this.tamSeek;
    }

    public void setTamSeek(int tamSeek) {
        this.tamSeek += tamSeek;
    }

    public void atualizarFrequencia(int posicao) {
        posicoes += posicao + ",";
        qtVezes++;
    }

    public int getTamByte() {
        String vezes = "" + qtVezes;
        return ((nomDoc + vezes + posicoes).length() + 3);
    }

    public String getNomDoc() {
        return nomDoc;
    }

    public int getQtVezes() {
        return qtVezes;
    }

    public String getPosicoes() {
        return posicoes;
    }

}
