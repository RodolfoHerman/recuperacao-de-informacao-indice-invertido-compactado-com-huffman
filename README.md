# Melhoria de recuperação de informação baseado na codificação de Huffman

Este trabalho consiste na impelementação do artigo: 
```
Query Retrieval Enhancement Based on Huffman Index Terms Encoding
Autores: 
1. Ameen A. Al-Jedady 
2. Izzat M. Alsmadi 
```

Tal trabalho corresponde ao último trabalho da matéria de Recuperação de Informação cursada no 7º período do curso de ciência da Computação na PUC-Minas.

## Descrição do problema

O professor exigiu dos alunos que pesquisassem artigos na internete que possuiam temas referentes à matéria dele. Os artigos deveriam seguir os critérios 1) Artigos de 2010 até a data atual e 2) Artigos com fator qualis-caps B3 para cima. Após a seleção cada aluno deveria reproduzir o artigo selecionado, gerar os resultados e apresenta-lo em sala de aula. Sendo assim segue a descrição do trabalho:

## Organização do código

O projeto pode ser separado em três pacotes. 

- O primeiro pacote possui a nomenclatura “br.pucminas.ri.indexador.*”, com a função de realizar a indexação das palavras contidas nos documentos e também por gerar dados para a realização do estudo. 
- O segundo grande pacote possui a nomenclatura “br.pucminas.ri.queryprocessor.*”, com a função de processar a query e gerar os dados de análise para a realização do estudo. 
- O terceiro pacote, o menor entre eles, de nomenclatura “br.pucminas.ri.utilidades.*”, possui classes que manipulam alguns tipos de dados para a realização do estudo, como o de comparações entre as palavras (termos).

1. Primeiro Pacote ```br.pucminas.ri.indexador.*``` (separado em seis partes)

 - Huffman: responsável por realizar a compactação dos termos seguindo a codificação de Huffman.
 - Indexacao: responsável por realizar a indexação de cada termo (palavra) dos documentos, montar os vocabulários, original e comprimido, em memória e depois salva-los em arquivos.
 - Leitor: responsável por ler os documentos a serem indexados, retirar qualquer caractere que não seja letras do alfabeto e também passar todas as letras para minúsculas.
 - Main: responsável por realizar a chamada do programa (execução).
 - Merge: responsável por realizar a ordenação, em disco, das listas invertidas em um único arquivo, realizar o merge entre elas, e por fim, a geração do arquivo de índices invertidos.
 - Modelos: responsável por manipular os dados referentes aos termos e suas listas invertidas, assim como, a representação da palavra, as posições das palavras nos documentos, o ponteiro de seek, entre outros.
