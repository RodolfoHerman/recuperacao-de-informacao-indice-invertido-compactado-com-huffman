/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.indexador.modelos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Termo {

    private String termo;
    private int tamListInvertida;
    private String seeks;
    private int qtTotalNi;
    private Lista list;
    private int tamTermo;
    
    private BigInteger num ;
    private byte[] transf;
    private int bytesLength;
    
    public Termo(String termo, String nomDoc, int posicao) {
        this.tamTermo = termo.length();
        this.termo = termo;
        this.qtTotalNi = 1;
        this.seeks = "";
        this.list = new Lista(nomDoc, posicao);
    }
    
    public int getTamTermo() {
        return tamTermo;
    }

    public void addSeek(long seek) {
        this.seeks = "" + seek;
    }
    
    public String getNiString() {
        return ("" + qtTotalNi);
    }

    public char getPonteiroArq() {
        if (termo.length() != 0) {
            return (this.termo.charAt(0));
        } else {
            return ('#');
        }
    }

    public String getTermo() {
        return (this.termo);
    }

    public Lista getList() {
        return (this.list);
    }

    public String getSeeks() {
        return (this.seeks);
    }

    public int getTamListInvertida() {
        return tamListInvertida;
    }

    public void setTamListInvertida(int tamListInvertida) {
        this.tamListInvertida = tamListInvertida;
    }  

    public BigInteger getNum() {
        return num;
    }

    public void setNum(BigInteger num) {
        this.num = num;
    }

    public byte[] getTransf() {
        return transf;
    }

    public void setTransf(byte[] transf) {
        this.transf = transf;
    }

    public int getBytesLength() {
        return bytesLength;
    }

    public void setBytesLength(int bytesLength) {
        this.bytesLength = bytesLength;
    }
    
    public String getIntString () {
        String resp = "";
        for (int x = 0; x < this.transf.length; x++) {
            resp += (0xff & this.transf[x]);
        }
        return (resp);
    }
    
    public String getDados() {
        
        String resposta;
        String s = "";
        if (this.termo.length() < 10) {
            resposta = "0" + this.termo.length() + " ";
        } else {
            resposta = this.termo.length() + " ";
        }
        resposta += this.termo + "\t";
        for (int x = 0; x < this.transf.length; x++) {
            int temp = 0xff & this.transf[x];
            s += ("" + temp + "  ");
        }
        resposta += "\t\t" + s + "\t" + num;
        resposta += "\t\t" + this.termo.length();
        resposta += "\t\t" + this.transf.length;
        
        return (resposta);
    }
    
    

}
