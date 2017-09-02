/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.utilidades;

import java.math.BigInteger;

/**
 *
 * @author h-e-r
 */
public class TermCompare {
    
    public static int [] compareTo(String s, String str) {
        //definicao de variavel
        int [] resposta = new int [2];
        resposta [0] = 0;
        resposta [1] = 0;
        //condicao para pegar o tamanho de referencia
        if (str.length() > s.length()) {
            //usar metodo recursivo para verificar se as palavras sao iguais
            compareTo(s.toLowerCase(), str.toLowerCase(), s.length(), 0, resposta);
            //verificar a partir do tamanho qual a palavra maior, caso as primeiras letras sao iguais
            if (resposta [0] == 0) {
                resposta [0] = 1;
            }//fim if
            //condicao para pegar o tamanho de referencia
        } else if (str.length() < s.length()) {
            //usar metodo recursivo para verificar se as palavras sao iguais
            compareTo(s.toLowerCase(), str.toLowerCase(), str.length(), 0, resposta);
            //verificar a partir do tamanho qual a palavra maior, caso as primeiras letras sao iguais
            if (resposta [0] == 0) {
                resposta [0] = -1;
            }//fim if
        } else {
            //usar metodo recursivo para verificar se as palavras sao iguais
            compareTo(s.toLowerCase(), str.toLowerCase(), s.length(), 0, resposta);
        }//fim if	
        //retornar resposta
        return (resposta);
    }//fim compareTo

    private static void compareTo(String s, String str, int tamanho, int x, int [] resposta) {
        //StrCompare verifica = new StrCompare(this.str);
        //Verificar tamanho, caso a primeira palavra seja maior retornara 1
        if (x < tamanho) {
            //Verificar se caractere na posicao x da palavra 1 e maior do que da palavra 2, se for retorna 1
            if (str.charAt(x) > s.charAt(x)) {
                resposta [0] = 1;
                resposta [1]++;
                //Verificar se caractere na posicao x da palavra 1 e menor do que da palavra 2, se for retorna -1
            } else if (str.charAt(x) < s.charAt(x)) {
                resposta [0] = - 1;
                resposta [1]++;
                //Se os caracteres forem iguais, passar para a proxima posicao de forma recursiva
            } else {
                resposta [1]++;
                compareTo(s, str, tamanho, (x + 1), resposta);
            }//fim if
        }//fim if
    }//fim compareTo
    
    
    public static int [] compareTo (BigInteger s, BigInteger str) {
        int [] resposta = new int [2];
        resposta [0] = 0;
        resposta [1] = 0;
        
        if (s.compareTo(str) > 0) {
            resposta[0] = -1;
            resposta[1]++;
        } else if (s.compareTo(str) < 0) {
            resposta[0] = 1;
            resposta[1]++;            
        } else {
            resposta[1]++; 
        }
        
        return (resposta);
    }
    
    
    public static int [] compareTo(int [] s, int [] str) {
        //definicao de variavel
        int [] resposta = new int [2];
        resposta [0] = 0;
        resposta [1] = 0;
        //condicao para pegar o tamanho de referencia
        if (str.length > s.length) {
            //usar metodo recursivo para verificar se as palavras sao iguais
            compareTo(s, str, s.length, 0, resposta);
            //verificar a partir do tamanho qual a palavra maior, caso as primeiras letras sao iguais
            if (resposta [0] == 0) {
                resposta [0] = 1;
            }//fim if
            //condicao para pegar o tamanho de referencia
        } else if (str.length < s.length) {
            //usar metodo recursivo para verificar se as palavras sao iguais
            compareTo(s, str, str.length, 0, resposta);
            //verificar a partir do tamanho qual a palavra maior, caso as primeiras letras sao iguais
            if (resposta [0] == 0) {
                resposta [0] = -1;
            }//fim if
        } else {
            //usar metodo recursivo para verificar se as palavras sao iguais
            compareTo(s, str, s.length, 0, resposta);
        }//fim if	
        //retornar resposta
        return (resposta);
    }//fim compareTo

    private static void compareTo(int [] s, int [] str, int tamanho, int x, int [] resposta) {
        //StrCompare verifica = new StrCompare(this.str);
        //Verificar tamanho, caso a primeira palavra seja maior retornara 1
        if (x < tamanho) {
            //Verificar se caractere na posicao x da palavra 1 e maior do que da palavra 2, se for retorna 1
            if (str[x] > s[x]) {
                resposta [0] = 1;
                resposta [1]++;
                //Verificar se caractere na posicao x da palavra 1 e menor do que da palavra 2, se for retorna -1
            } else if (str[x] < s[x]) {
                resposta [0] = - 1;
                resposta [1]++;
                //Se os caracteres forem iguais, passar para a proxima posicao de forma recursiva
            } else {
                resposta [1]++;
                compareTo(s, str, tamanho, (x + 1), resposta);
            }//fim if
        }//fim if
    }//fim compareTo    
    
    
}
