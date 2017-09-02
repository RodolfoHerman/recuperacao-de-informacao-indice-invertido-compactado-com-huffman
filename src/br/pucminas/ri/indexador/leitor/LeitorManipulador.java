/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.indexador.leitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class LeitorManipulador {

	private BufferedReader leitor;

	public LeitorManipulador(String indiceFilePath) throws IOException {

		this.leitor = new BufferedReader(new InputStreamReader(new FileInputStream(indiceFilePath)));
	}


	public String obterProximaLinha() throws IOException  {

		String linha = leitor.readLine();

		if (linha != null) {
			return (linha.replaceAll("[^\\p{Alpha}]", " ").toLowerCase());
		} else {
			return null;
		}
	}

}
