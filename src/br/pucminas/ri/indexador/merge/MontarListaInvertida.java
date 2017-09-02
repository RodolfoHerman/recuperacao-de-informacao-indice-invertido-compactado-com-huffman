/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.indexador.merge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;



public class MontarListaInvertida {
	private final String[] caminhos = { "./Alfabeto/", "./Controle/", "./Indices/" };

	// Parametros do algoritmo:
	private int NUMERO_ARQUIVOS = 15;
	private int MEMORIA = 1500;

	private String ARQUIVO = "Desordenado";
	private String NOME_ARQUIVOS = "Arquivo";
	private String EXTENSAO_ARQUIVOS = ".txt";

	// cria um array de objetos do tipo todosEstados, onde se encontram os
	// blocos dos arquivos
	private ArrayList<EstadoGravacao> todosEstados;
	// numero de elementos para calcular a parada da soma de fibonacci
	private int numElementos = 0;

	public MontarListaInvertida() {
		try {
			// criacao de variaveis
			BufferedReader leitor = null;
			BufferedWriter escritor = null;
			BufferedReader[] leitores = new BufferedReader[NUMERO_ARQUIVOS];
			BufferedWriter[] escritores = new BufferedWriter[NUMERO_ARQUIVOS];
			long tempoInicial = System.currentTimeMillis();
			// chamada de metodos

			desordenado(leitor, escritor);

			System.out.println("o metodo desordenado executou em " + (System.currentTimeMillis() - tempoInicial));

			tempoInicial = System.currentTimeMillis();
			
			criarEstodosDeGravacao();

			System.out.println("o metodo criarEstodosDeGravacao executou em " + (System.currentTimeMillis() - tempoInicial));

			tempoInicial = System.currentTimeMillis();
			
			desordenadoArqAuxiliares(leitor, escritores);

			System.out.println("o metodo desordenadoArqsAux executou em " + (System.currentTimeMillis() - tempoInicial));

			tempoInicial = System.currentTimeMillis();
			
			ordernarPolifasica(leitores, escritor);

			System.out.println("o metodo ordernarPolifasico executou em " + (System.currentTimeMillis() - tempoInicial));

			tempoInicial = System.currentTimeMillis();
			
			arqAuxArqFinal(leitor, escritor);

			System.out.println("o metodo arqAuxArqFinal executou em " + (System.currentTimeMillis() - tempoInicial));

		} catch (IOException erro) {
			System.err.println(erro);
		}
	}

	private void desordenado(BufferedReader leitor, BufferedWriter escritor) throws IOException {
		// abrir arquivo para gravar
		escritor = new BufferedWriter(new FileWriter(caminhos[1] + "Desordenado.txt"));
		File alfabeto = new File(caminhos[0]);

		if ((alfabeto.isDirectory()) && (alfabeto.list().length != 0)) {
			String[] docs = alfabeto.list();

			for (String doc : docs) {
				leitor = new BufferedReader(new FileReader(caminhos[0] + doc));
				String termo;
				while ((termo = leitor.readLine()) != null) {
					escritor.write(termo);
					escritor.newLine();
					numElementos++;
				}
				escritor.flush();
			}

			leitor.close();
			escritor.close();
		} else {
			escritor.close();
			System.out.println("Pasta Alfabeto nao existe ou esta' sem arquivos");
			System.exit(0);
		}
	}

	/**
	 * Metodo que cria todos os estados, os tamanhos dos blocos em cada
	 * sequencia
	 */
	private void criarEstodosDeGravacao() {
		// condicao de parada da sequencia de fibonacci
		double parada = ((double) numElementos / (double) MEMORIA);

		int posicao = 0;
		// criar a primeira sequencia de arquivos com seus blocos
		todosEstados = new ArrayList<EstadoGravacao>();
		// adicionar ao array de objetos
		todosEstados.add((new EstadoGravacao(NUMERO_ARQUIVOS)));
		// enquanto a soma de blocos do estado corrente nao alcancar a condicao
		// de parada
		while (((double) (todosEstados.get(posicao)).getSoma()) <= parada) {
			// fazer a nova sequencia
			todosEstados.add((new EstadoGravacao(NUMERO_ARQUIVOS, (todosEstados.get(posicao)).getEstado())));
			posicao++;
		}
	}

	/**
	 * Metodo que cria os primeiros arquivos auxiliares, com o tamanho de bloco
	 * igual a MEMORIA selecionada
	 * 
	 * @param leitor
	 *            recebe um objeto para abrir o arquivo
	 * @param escritores
	 *            recebe objetos para gravarem em um arquivos
	 */
	private void desordenadoArqAuxiliares(BufferedReader leitor, BufferedWriter[] escritores) throws IOException {
		// recebe a configuracao dos blocos de cada arquivo
		int[] confInicial;
		// declaracao de variaveis
		int maior = 0;
		int arqGravar = 0;
		ArrayList<String> palavras;
		String palavra = null;
		// abrir arquivo que contem todos os registros
		leitor = new BufferedReader(new FileReader(caminhos[1] + ARQUIVO + EXTENSAO_ARQUIVOS));
		// abrir todos os arquivos solicitados pelo usuario
		for (int x = 0; x < NUMERO_ARQUIVOS; x++) {
			escritores[x] = new BufferedWriter(new FileWriter(caminhos[1] + NOME_ARQUIVOS + x + EXTENSAO_ARQUIVOS));
		}
		// recebe a configuracao dos blocos de cada arquivo
		confInicial = (todosEstados.remove(todosEstados.size() - 1)).getEstado();
		// for para passar por todos os arquivos
		for (int x = 0; x < NUMERO_ARQUIVOS; x++) {
			palavras = new ArrayList<String>();
			// for para pecorrer o array das configuracoes dos blocos
			for (int y = 0; y < confInicial.length; y++) {
				// pegar a maior configuracao de bloco e a sua posicao
				if (confInicial[y] > maior) {
					maior = confInicial[y];
					arqGravar = y;
				}
			}
			// passar a configuracao de bloco corrente para 0
			confInicial[arqGravar] = 0;
			// fazer ate acabar os blocos do arquivo selecionado
			while (maior != 0) {
				// for para pegar os registros ate encher a memoria solicitada
				for (int z = 0; z < MEMORIA; z++) {
					palavra = leitor.readLine();
					if (palavra != null) {
						// colocar registros no array
						palavras.add(palavra);
					}
				}
				// ordernar registros
				Collections.sort(palavras);
				// grava o bloco em arquivo
				while (palavras.size() != 0) {
					escritores[arqGravar].write(palavras.remove(0));
					escritores[arqGravar].newLine();
					//escritores[arqGravar].flush();
				}
				// passar para proimo bloco
				escritores[arqGravar].write("~");
				escritores[arqGravar].newLine();
				
				// diminuir a quantidade de blocos
				maior--;

			}
			escritores[arqGravar].flush();

		}
		// fechar arquivos
		for (int x = 0; x < NUMERO_ARQUIVOS; x++) {
			escritores[x].close();
		}

		leitor.close();

	}

	/**
	 * Metodo que realiza a intercalacao dos arquivos no modelo polifasico, com
	 * os registros ordenados
	 * 
	 * @param leitores
	 *            recebe um array de objetos com os leitores dos arquivos
	 * @param escritor
	 *            recebe um objeto para escrever em arquivo
	 */
	private void ordernarPolifasica(BufferedReader[] leitores, BufferedWriter escritor) throws IOException {

		// declaracao de variaveis
		boolean mudarBloco = false;
		boolean ObjetoIgual = false;
		String[] palavra = new String[NUMERO_ARQUIVOS];
		boolean[] controle = new boolean[NUMERO_ARQUIVOS];
		int semLeitura = 0;
		String gravacao = null;
		int blocoMaiorAtual = 0;
		boolean objSem = true;

		int[] configuracaoAtual;
		int arqGravar = 0;
		ArrayList<String> palavras;
		// abrir os arquivos
		for (int x = 0; x < NUMERO_ARQUIVOS; x++) {
			leitores[x] = new BufferedReader(new FileReader(caminhos[1] + NOME_ARQUIVOS + x + EXTENSAO_ARQUIVOS));
			controle[x] = true;
		}

		// enquanto nao acabar as sequencias dos blocos dos arquivos
		while (todosEstados.size() != 0) {
			// pegar a configuracao dos blocos dos arquivos
			configuracaoAtual = (todosEstados.remove(todosEstados.size() - 1)).getEstado();
			// for para percorrer o array que contem os blocos dos arquivos
			for (int x = 0; x < NUMERO_ARQUIVOS; x++) {
				// pegar o maior bloco e sua posicao
				if (blocoMaiorAtual < configuracaoAtual[x]) {
					blocoMaiorAtual = configuracaoAtual[x];
					arqGravar = semLeitura = x;
				}
			}
			// fechar o arquivo com maior bloco atual
			leitores[semLeitura].close();

			// abrir o arquivo para receber os registros
			escritor = new BufferedWriter(new FileWriter(caminhos[1] + NOME_ARQUIVOS + arqGravar + EXTENSAO_ARQUIVOS));

			// fazer ate o numero de blocos acabar
			while (blocoMaiorAtual != 0) {

				// colocar o ponteiro do arquivo fechado em falso
				controle[semLeitura] = false;
				palavras = new ArrayList<String>();
				// for para percorrer os arquivos com seus ponteiros
				for (int x = 0; x < NUMERO_ARQUIVOS; x++) {
					if (controle[x]) {
						palavra[x] = leitores[x].readLine();
					}
					if ((palavra[x] != null) && (x != semLeitura)) {
						if ((!palavra[x].equals("~"))) {
							// adicionar registro apontado pelo ponteiro
							// corrente do arquivo
							palavras.add(palavra[x]);
						}
					}
				}

				// verificar se foi adicionado algum registro
				if (palavras.size() != 0) {
					// ordenar os registros
					Collections.sort(palavras);
					// pegar o menor registro
					gravacao = palavras.remove(0);
					// verificar se tem registro igual
					ObjetoIgual = palavras.contains(gravacao);
					objSem = true;
					// gravar em arquivo
					escritor.write(gravacao);
					escritor.newLine();
				}

				// condicao para mudar de bloco
				mudarBloco = true;

				// for para percorrer os ponteiros dos arquivos, sinalizando
				// onde pode andar
				for (int x = 0; x < NUMERO_ARQUIVOS; x++) {
					controle[x] = false;
					// atualizar os ponteiros dos arquivos
					if (palavra[x] != null) {
						if (ObjetoIgual && palavra[x].equals(gravacao)) {
							controle[x] = true;
							ObjetoIgual = false;
							objSem = false;
						} else if (objSem && palavra[x].equals(gravacao)) {
							controle[x] = true;
						}
					}
					// se possui registro a serlido, manter no bloco atual
					if (controle[x] == true) {
						mudarBloco = false;
					}
				}

				// verificar se pode mudar de bloco
				if (mudarBloco) {
					// atualizar os ponteiros dos arquivos
					for (int x = 0; x < NUMERO_ARQUIVOS; x++) {
						controle[x] = true;
					}
					// mudar de bloco
					escritor.write("~");
					escritor.newLine();
					escritor.flush();
					// diminuir a quantidade de blocos
					blocoMaiorAtual--;

				}

			}

			escritor.close();

			// abrir arquivo que foi fechado para escritura
			leitores[semLeitura] = new BufferedReader(
					new FileReader(caminhos[1] + NOME_ARQUIVOS + semLeitura + EXTENSAO_ARQUIVOS));
		}

		for (int x = 0; x < NUMERO_ARQUIVOS; x++) {
			leitores[x].close();
		}

	}

	/**
	 * Metodo que tranfere os registros para o arquivo final "Ordenado.txt"
	 * 
	 * @param leitor
	 *            recebe objeto para realizar a leitura do arquivo auxiliar
	 * @param escritor
	 *            recebe obejeto que realiza a escrita no arquivo Ordenado.txt
	 */
	private void arqAuxArqFinal(BufferedReader leitor, BufferedWriter escritor) throws IOException {
		// abre arquivo para leitura
		leitor = new BufferedReader(new FileReader(caminhos[1] + NOME_ARQUIVOS + 0 + EXTENSAO_ARQUIVOS));
		// cria arquivo para escrita
		escritor = new BufferedWriter(new FileWriter(caminhos[2] + "IndiceInvertido.txt"));
		String palavra;
		// fazer enquanto nao achar a condicao de parada
		while ((!(palavra = leitor.readLine()).equals("~"))) {
			String[] temp = palavra.split(" ");
			escritor.write(/**temp [0] + " " + */temp[1] + " " + temp[2] + " " + temp[3] + "~");
			//escritor.newLine();
			
		}
		escritor.flush();
		// fechar arquivos
		escritor.close();
		leitor.close();

	}

}

