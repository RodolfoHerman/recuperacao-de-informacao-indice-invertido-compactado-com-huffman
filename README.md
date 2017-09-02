# Melhoria de recuperação de informação baseado na codificação de Huffman

Este trabalho consiste na impelementação do artigo: 
```
Query Retrieval Enhancement Based on Huffman Index Terms Encoding

1. Ameen A. Al-Jedady 
2. Izzat M. Alsmadi 
```

Tal trabalho corresponde ao último trabalho da matéria de Recuperação de Informação cursada no 7º período do curso de ciência da Computação na PUC-Minas.

## Descrição do problema

O professor exigiu dos alunos que pesquisassem artigos na internete que possuiam temas referentes à matéria dele. Os artigos deveriam seguir os critérios 1) Artigos de 2010 até a data atual e 2) Artigos com fator qualis-caps B3 para cima. Após a seleção, cada aluno deveria reproduzir o artigo selecionado, gerar os resultados e apresenta-lo em sala de aula. Sendo assim segue a descrição do trabalho:

## Organização do código

O projeto pode ser separado em três pacotes. 

- O primeiro pacote possui a nomenclatura “br.pucminas.ri.indexador.*”, com a função de realizar a indexação das palavras contidas nos documentos e também por gerar dados para a realização do estudo. 
- O segundo grande pacote possui a nomenclatura “br.pucminas.ri.queryprocessor.*”, com a função de processar a query e gerar os dados de análise para a realização do estudo. 
- O terceiro pacote, o menor entre eles, de nomenclatura “br.pucminas.ri.utilidades.*”, possui classes que manipulam alguns tipos de dados para a realização do estudo, como o de comparações entre as palavras (termos).

#### 1. Primeiro Pacote ```br.pucminas.ri.indexador.*``` (separado em seis partes)

 - Huffman: responsável por realizar a compactação dos termos seguindo a codificação de Huffman.
 - Indexacao: responsável por realizar a indexação de cada termo (palavra) dos documentos, montar os vocabulários, original e comprimido, em memória e depois salva-los em arquivos.
 - Leitor: responsável por ler os documentos a serem indexados, retirar qualquer caractere que não seja letras do alfabeto e também passar todas as letras para minúsculas.
 - Main: responsável por realizar a chamada do programa (execução).
 - Merge: responsável por realizar a ordenação, em disco, das listas invertidas em um único arquivo, realizar o merge entre elas, e por fim, a geração do arquivo de índices invertidos.
 - Modelos: responsável por manipular os dados referentes aos termos e suas listas invertidas, assim como, a representação da palavra, as posições das palavras nos documentos, o ponteiro de seek, entre outros.

#### 2. Segundo Pacote ```br.pucminas.ri.queryprocessor.*``` (separado em três partes)

 - Carregador: responsável por realizar a leitura de ambos os arquivos vocabulários, original e compactado, para a memória principal.
 - Modelo: responsável por manipular os dados referentes aos termos, assim como, a representação da palavra, o ponteiro de seek, entre outros.
 - Processador: responsável por realizar o processamento da query e coletar os dados para o estudo. Ele realiza o processamento da query no vocabulário, carregado em memoria principal, de forma que a pesquisa no vocabulário ocorra de forma utilizando algoritmo de pesquisa sequencial ou algoritmo de pesquisa binária.
 
#### 3. Terceiro pacote ```br.pucminas.ri.utilidades.*```

 - Utilidades: responsável por manipular os dados para a realização do estudo. Esse pacote possui três classes. A primeira, ```Pesquisas.java```, é responsável por implementar os métodos de pesquisa sequencial e pesquisa binária. A segunda, ```RecuperarTermos.java```, realiza a leitura do arquivo “vocabulario.txt” e retorna apenas os termos do vocabulário sem o ponteiro da lista invertida. A terceira, ```TermCompare.java```, é uma classe que manipula o termo (palavra) para a realização de comparações, essa classe implementa o “compareTo()” para os termos.
 
## Objetivo do trabalho

Pode-se dividir o objetivo em duas partes:

#### 1. Realização da comparação do tamanho dos termos originais com os termos compactados

Para conduzir o estudo a seguinte fórmula foi utilizada:
![eq1](https://github.com/RodolfoHerman/recuperacao-de-informacao-indice-invertido-compactado-com-huffman/blob/master/img_README/eq1.png)
onde R representa a redução do tamanho, T representa o tamnho do termo 

Essa fórmula representa a porcentagem de ganho do termo compactado para com o termo original. O programa realiza a coleta de dados à medida que ele é executado, e esses dados são utilizados para o emprego da fórmula acima. Esses dados são os seguintes:

a)	Tamanho do termo compactado.
b)	Tamanho do termo original.
c)	Total de palavras no documento.
d)	Total de palavras indexadas do documento.

Com esses dados é possível calcular a média. Ao final a seguinte tabela (Tabela 1) foi gerada.

![tab1](https://github.com/RodolfoHerman/recuperacao-de-informacao-indice-invertido-compactado-com-huffman/blob/master/img_README/tabela1.png)

Podem-se fazer comparações com a tabela retirada do artigo (Tabela 2). Nota-se que o total de palavras nos documentos e o de palavras indexadas possuem uma pequena variação. Por esse motivo há mudanças no tamanho médio das palavras e também na porcentagem da redução do tamanho. Outra comparação é o fato da utilização de apenas 2 dos 3 documentos do caso de estudo do artigo e também o acréscimo de outros 3 realizados nesse trabalho.

![tab2](https://github.com/RodolfoHerman/recuperacao-de-informacao-indice-invertido-compactado-com-huffman/blob/master/img_README/tabela2.png)
#### 2. Realização da comparação do número de comparações para processar a query



