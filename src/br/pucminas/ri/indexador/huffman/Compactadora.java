/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.indexador.huffman;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author h-e-r
 */
public class Compactadora {

    private final String[] simbHuffman = {"0000", "011101", "11111", "00010", "001", "01111", "011100", "1010", "1110",
        "000110101", "0001100", "10110", "10111", "1000", "0100", "11110", "0001101001", "0110", "0101", "110",
        "10010", "100111", "000111", "00011011", "100110", "0001101000"};

    private int bytesLength;
    private ArrayList<Byte> termBytes;
    private String termo;
    private byte [] transf;
    
    public Compactadora (String termo) {
        this.termo = termo;
        this.termBytes = new ArrayList <Byte>();
        this.bytesLength = 0;
    }

    private int converte(String num) {
        int numero = 0;
        String temp = "00000000";
        if (num.length() < 8) {
            num += temp.substring(0, temp.length() - num.length());
        }
        for (int x = 0; x < num.length(); x++) {
            numero = numero * 2;
            if (num.charAt(x) == '1') {
                numero += 1;
            }
        }
        //System.out.println (numero);
        return (numero);
    }

    public void compac() {
        // Declaracao de variaveis

        String[] bitsSimb;
        StringBuffer bits = new StringBuffer();

        // criar a arvore dos bits dos simbols
        bitsSimb = simbHuffman;
        // for para concatenar cada representacao binaria
        for (int x = 0; x < this.termo.length(); x++) {
            bits.append(bitsSimb[this.termo.charAt(x) - 'a']);
        }
        // for para gravar no arquivo
        for (int x = 0; x < bits.length(); x++) {
            String temp = "";
            int c = x;
            // pega cada 8 bits da string formando o byte para ser salvo
            for (int oitoBits = 0; oitoBits < 8; oitoBits++) {
                // condicao para nao acessar posicoes invalidas
                if (x < bits.length() && c < bits.length()) {
                    // pegar bit a bit
                    temp += bits.charAt(c++);
                    // colocar na posicao correta para a proxima iteracao
                    x = c - 1;
                }
            }
            this.termBytes.add((byte) converte(temp));
            this.bytesLength++;
        }
    }

    public BigInteger getTermoC() {
        return (new BigInteger(1, getTransf()));
    }

    public int getBytesLength() {
        return bytesLength;
    }

    public byte[] getTransf() {
       Iterator<Byte> iterator = this.termBytes.iterator();
        this.transf = new byte[this.termBytes.size()];
        int pos = 0;
        while (iterator.hasNext()) {
            this.transf[pos++] = iterator.next();
        }        
        return transf;
    }
    
    

    public int [] getTransfInt() {
        Iterator<Byte> iterator = this.termBytes.iterator();
        int [] temp = new int [this.termBytes.size()];
        int pos = 0;
        while (iterator.hasNext()) {
            temp[pos++] = (0xff & iterator.next());
        }        
        return temp;
    }

}
