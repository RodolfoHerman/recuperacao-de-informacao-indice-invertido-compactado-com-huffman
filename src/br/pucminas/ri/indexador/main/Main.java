/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.indexador.main;

import br.pucminas.ri.indexador.merge.MontarListaInvertida;
import br.pucminas.ri.indexador.indexacao.Indexacao;
import br.pucminas.ri.utilidades.RecuperarTermos;
import java.io.RandomAccessFile;

import br.pucminas.ri.queryprocessor.processador.ProcessarComPesquisaBinaria;
import br.pucminas.ri.queryprocessor.processador.ProcessarComPesquisaSequencial;
import java.util.Collections;
import java.util.List;

//import br.pucminas.ri.indexer.leitor.Leitor;
public class Main {
    
    private static final String query = "Access Code " +
            "Unique combination of characters used as identification for " +
            "gaining to remote computer " +
            "The is generally referred user ID " +
            "password";

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {

            Indexacao c = new Indexacao();

            long tempoInicial = System.currentTimeMillis();
            c.criarListas();

            System.out.println("criar listas o metodo executou em " + (System.currentTimeMillis() - tempoInicial));

            boolean indexar = c.getIndexar();
            if (indexar) {
                new MontarListaInvertida();
            }

            System.out.println("o metodo executou em " + (System.currentTimeMillis() - tempoInicial));


        
            ProcessarComPesquisaSequencial s = new ProcessarComPesquisaSequencial (RecuperarTermos.listaTermos());
            s.avaliarComparacoesComp();
            s.avaliarComparacoesOrig();
            System.out.println( "SEQUENCIAL : " + String.format("%.2f", s.comparacaoReducaoPesquisa()) + "%");
   
            ProcessarComPesquisaBinaria b = new ProcessarComPesquisaBinaria (RecuperarTermos.listaTermos());
            b.avaliarComparacoesComp();
            b.avaliarComparacoesOrig();
            System.out.println("BINARIO: " + String.format("%.2f", b.comparacaoReducaoPesquisa()) + "%" );            
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
