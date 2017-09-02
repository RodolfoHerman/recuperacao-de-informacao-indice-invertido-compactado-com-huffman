/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucminas.ri.indexador.merge;


/**
* Classe que cria o OBJETO que guarda os estados de fibonacci
*/
public class EstadoGravacao {
	//declaracao dos atributos
	private int [] estados;
	private int soma;
	
	/**
	* Contrutor da classe, quando ja possui um estado inicial de arquivos com os blocos
	* @param qtArquivos recebe a quantidade de arquivos existenetes
	* @param anterior recebe a sequencia anterior do tamanho dos blocos para calcular a sequencia
	*/
	public EstadoGravacao ( int qtArquivos, int [] anterior ) {
		//cria o novo array de estados
		this.estados = new int [qtArquivos];	
		//para realizar a soma de fibonacci
		this.soma = 0;
		//metodo para gerar os novos blocos dos arquivos
		gerarBlocos (anterior);
	}
	
	/**
	* Contrutor da classe (polimorfismo parametrico), caso seja os primeiros arquivos
	* @param qtArquivos recebe a quantidade de arquivos existenetes
	*/
	public EstadoGravacao ( int qtArquivos ) {
		//cria o novo array de estados
		this.estados = new int [qtArquivos];
		//a primeira soma de fibonacci
		this.soma = 1;
		//o primeiro arquivo se inicia com bloco de tamanho 1
		this.estados[0] = 1;
		//for para preencher os demias arquivos com blocos de tamanho 0
		for ( int x = 1; x < qtArquivos; x++ ) {
			this.estados[x] = 0;
		}
		
	}
	
	
	/**
	* Metodo que gera os blocos dos arquivos 
	* @param anterior recebe os blocos dos arquivos anteriores
	*/
	private void gerarBlocos ( int [] anterior ) {
		//declaracao de variaveis
		int maior = 0;
		int posicao = 0;
		//for para pecorrer o array
		for ( int x = 0; x < anterior.length; x++ ) {
			//pegar o maior bloco da sequencia anterior e sua posicao
			if ( anterior[x] > maior ) {
				maior = anterior[x];
				posicao = x;
			}
		}
		
		//for para preencher o novo estado dos blocos dos arquivos
		for ( int x = 0; x < this.estados.length; x++ ) {
			//econtrada a maior posicao do anterior e colocado 0 na nova sequencia
			if ( posicao == x ) {
				this.estados[x] = 0;
			} else {
				//realiza o calculo dos blocos
				this.estados[x] = anterior[x] + maior;
			}
			//calcula a soma de fibonacci
			this.soma = this.soma + this.estados[x];
		}
	
	}

	/**
	* Funcao para pegar a soma de fibonacci
	* @return retorna a soma
	*/
	public int getSoma () {
		return (this.soma);
	}
	
	/**
	* Funcao para pegar o tamanho dos blocos dos arquivos
	* @return retorna os blocos dos arquivos
	*/
	public int [ ] getEstado () {
		return (this.estados);
	}
	
	/**
	* Metodo que mostra os blocos dos arquivos
	*/
	public void mostrar () {
		for ( int x = 0; x < this.estados.length; x++ ) {
			System.out.print ( this.estados[x] + "\t");
		}
		System.out.println();
	}
	
}

