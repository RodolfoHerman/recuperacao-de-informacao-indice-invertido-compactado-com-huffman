/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.queryprocessor.modelo;

import java.math.BigInteger;

public class MetaDataTerm {

    private BigInteger termo;
    private byte [] transf;
    private int ni;
    private int beginOffeset;
    private int lengthReader;
    private String palavra;

    public MetaDataTerm(String palavra, int ni, int beginOffeset, int lengthReader) {
        this.ni = ni;
        this.palavra = palavra;
        this.beginOffeset = beginOffeset;
        this.lengthReader = lengthReader;
    }

    public MetaDataTerm(BigInteger termo, byte [] transf, int ni, int beginOffeset, int lengthReader) {
        this.termo = termo;
        this.transf = transf;
        this.ni = ni;
        this.beginOffeset = beginOffeset;
        this.lengthReader = lengthReader;
    }

    public int getNi() {
        return ni;
    }

    public void setNi(int ni) {
        this.ni = ni;
    }

    public int getBeginOffeset() {
        return beginOffeset;
    }

    public void setBeginOffeset(int beginOffeset) {
        this.beginOffeset = beginOffeset;
    }

    public int getLengthReader() {
        return lengthReader;
    }

    public void setLengthReader(int lengthReader) {
        this.lengthReader = lengthReader;
    }

    public BigInteger getTermo() {
        return termo;
    }

    public void setTermo(BigInteger termo) {
        this.termo = termo;
    }

    public String getPalavra() {
        return palavra;
    }

    public int[] getTransf() {
        int [] resp = new int [this.transf.length];
        for (int x = 0; x < this.transf.length; x++) {
            resp[x] = (0xff & this.transf[x]);
        }
        return resp;
    }
    
}
