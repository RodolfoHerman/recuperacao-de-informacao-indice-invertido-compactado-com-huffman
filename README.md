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

onde R representa a redução do tamanho, T representa o tamnho do termo.

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

Para conduzir o estudo a seguinte fórmula foi utilizada:

![eq2](https://github.com/RodolfoHerman/recuperacao-de-informacao-indice-invertido-compactado-com-huffman/blob/master/img_README/eq2.png)

onde C representa número de comparações, Q representa o número de comprações para processar a query.

Essa fórmula representa a eficiência do sistema, sendo que é calculado a porcentagem da redução do número de comparações necessárias para processar cada termo na query. O programa realiza a coleta de dados à medida que ele é executado, e esses dados são utilizados para o emprego da fórmula acima. Esses dados são os seguintes:

a)	Número de comparações necessárias para processar a query original.
b)	Número de comparações necessárias para processar a query compactada.

Dois algoritmos de pesquisa foram implementados para realizar a pesquisa no vocabulário, tanto no original quanto no compactado. O primeiro algoritmo é o de pesquisa sequencial. O segundo algoritmo é o de pesquisa binária. 

A Tabela 3 mostra a porcentagem de ganho.

![tab3](https://github.com/RodolfoHerman/recuperacao-de-informacao-indice-invertido-compactado-com-huffman/blob/master/img_README/tabela3.png)

A comparação é realizada caractere por caractere, isto é, foi contabilizada essas comparações. Para a query compactada as comparações são feita de acordo com a representação do número, expresso em inteiro, e cada comparação é contabilizada como sendo uma. A tabela 4, retirada do artigo, é utilizada para fazer as devidas observações.

![tab4](https://github.com/RodolfoHerman/recuperacao-de-informacao-indice-invertido-compactado-com-huffman/blob/master/img_README/tabela4.png)

## Estudo de caso

O estudo de caso foi realizado em cima de um pequeno trecho de texto. Esse texto, por motivos de comparação, é o mesmo utilizado no artigo.

``` 
Access Code
Unique combination of characters used as identification for gaining access to a remote computer. The access code is generally referred to as user ID & password.
```

Esse pequeno trecho possui 26 palavras, sendo que, 21 são indexadas pelo fato de a indexação ocorrer apenas em palavras acima de tamanho 1. A Tabela 5 mostra cada palavra com sua representação de compactação e também o número em bytes gastos para cada palavra, tanto ela original tanto ela compactada. A tabela está ordenada de forma crescente de acordo com o número de bytes necessários para representar a palavra original.

![tab5](https://github.com/RodolfoHerman/recuperacao-de-informacao-indice-invertido-compactado-com-huffman/blob/master/img_README/tabela5.png)

Obs: os resultados alcançados nessa tabela são os mesmos contidos no artigo.

Para efeitos de melhor visualização o Gráfico 1 mostra as curvas de ganhos com a compactação dos termos.

![graf1](https://github.com/RodolfoHerman/recuperacao-de-informacao-indice-invertido-compactado-com-huffman/blob/master/img_README/grafico1.png)

Para o número de comparações necessárias para processar a consulta, que indica eficiência, foram realizados dois gráficos. O Gráfico 2 representa a realização do processamento da consulta utilizando o algoritmo de pesquisa sequencial. O Gráfico 3 representa o processamento da consulta utilizando o algoritmo de pesquisa binária. 

![graf2](https://github.com/RodolfoHerman/recuperacao-de-informacao-indice-invertido-compactado-com-huffman/blob/master/img_README/grafico2.png)

![graf3](https://github.com/RodolfoHerman/recuperacao-de-informacao-indice-invertido-compactado-com-huffman/blob/master/img_README/grafico3.png)

Nota-se que em alguns momentos a pesquisa sequencial para as palavras originais são melhores do que as palavras compactadas, isso se dá pelo fato de que as palavras compactadas estão ordenadas de forma ascendente de acordo com a representação em inteiro, isto é, palavras com menor tamanho estão nas primeiras posições. Por exemplo, a palavra “characters” no vocabulário original é uma das primeiras palavras, pois começa com a letra ‘c’, já no vocabulário compactado é uma das ultimas, pois ao compacta-la torna-se um número inteiro muito grande, devido ao seu tamanho. Nota-se o ganho utilizando a pesquisa binária, em média ocorrem 4 comparações para processar a consulta.

## Conclusão

O estudo mostra uma redução de 40% no tamanho dos termos no vocabulário contribuindo para a sua redução de tamanho. Ajudando, caso seja necessário, na transferência do vocabulário para outra unidade de armazenamento, ou ainda, seu carregamento na memória, já que os termos compactados reduzem o número de símbolos que representam cada termo. Essa redução de tamanho contribuiu para a redução do número de comparações necessárias para processar cada consulta, diminuindo-se o tempo gasta pelo processador de consultas. O número de comparações para processar a consulta foi reduzido em 36% no estudo realizado pelo artigo, já para o meu estudo a redução foi em média de 60%, essa diferença foi pelo fato de que em meu estudo realizei a comparação dos termos compactados como se fossem números inteiros.
