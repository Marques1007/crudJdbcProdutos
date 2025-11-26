package controllers;

import java.util.Scanner;

import entities.Produto;
import factories.ConnectionFactory;
import repositories.ProdutoRepository;

/*
 * classe para realizar as interações com o usuário da aplicação
 */
public class ProdutoController {
	
	//atributo para capturar os dados a serem gravados no banco
	private Scanner scaner = new Scanner(System.in);
		
	/*
	 *metodo para capturar os dados para realizxar o cadastro de um produto 
	 */

	public void cadastrarProduto() throws Exception {

	System.out.println("\nCADASTRO DE PRODUTOS:\n");
	
	var produto = new Produto(); //criando o objeto
	
	System.out.println("NOME DO PRODUTO............:");
	produto.setNome(scaner.nextLine());
	
	System.out.println("PRECO......................:");
//	produto.setPreco(Double.parseDouble(scaner.nextLine()));
	String precoX = (String.valueOf(scaner.nextLine()));
	precoX = precoX.replace(".", "").replace(",", ".");
	produto.setPreco(Double.parseDouble(precoX));
	
	System.out.println("QUANTIDADE.................:");
	produto.setQuantidade(Integer.parseInt(scaner.nextLine()));
	
	var produtoRepository = new ProdutoRepository();
	produtoRepository.inserir(produto);
	
	System.out.println("\nPRODUTO CADASTRADO COM SUCESSO!");
	
	}
	
	/*
	 *metodo para capturar os dados para atualizar o cadastro de um produto 
	 */

	public void atualizarProduto() throws Exception {

	System.out.println("\nATUALIZAR DE PRODUTOS:\n");
	
	var produto = new Produto(); //criando o objeto
	
	System.out.println("ID DO PRODUTO..............:");
	produto.setId(Integer.parseInt(scaner.nextLine()));
	
	System.out.println("NOME DO PRODUTO............:");
	produto.setNome(scaner.nextLine());
	
	System.out.println("PRECO......................:");
//	produto.setPreco(Double.parseDouble(scaner.nextLine()));
	String precoX = (String.valueOf(scaner.nextLine()));
//	precoX = precoX.replace(",", ".").replace(".", "");
	precoX = precoX.replace(",", ".");
	produto.setPreco(Double.parseDouble(precoX));
	
	System.out.println("QUANTIDADE.................:");
	produto.setQuantidade(Integer.parseInt(scaner.nextLine()));
	
	var produtoRepository = new ProdutoRepository();
	if (produtoRepository.atualizar(produto)) {
    	System.out.println("\nPRODUTO ATUALIZADO COM SUCESSO!");
	}
	else {
    	System.out.println("\nNENHUM PRODUTO ATUALIZADO!");
	}
	
	}
}
