package controllers;

import java.util.Scanner;

import entities.Produto;
import repositories.ProdutoRepository;

/*
 * classe para realizar as interações com o usuário da aplicação
 */
public class ProdutoController {
	
	//atributo para capturar os dados a serem gravados no banco
	private Scanner scanner = new Scanner(System.in);
	
	/*
	 * metodo para que o usuario possa escolher
	 * que opcao do projeto deseja executar
	 */
	
	public void gerenciarProdutos() throws Exception {
		
		System.out.println("\nGERENCIAMENTO DE PRODUTOS:\n");
		System.out.println("(01) CADASTRAR PRODUTO");
		System.out.println("(02) ATUALIZAR PRODUTO");
		System.out.println("(03) EXCLUIR PRODUTO");
		System.out.println("(04) CONSULTAR PRODUTO");

	    System.out.print("\nINFORME A OPCAO DESeJADA:  ");
	    var opcao = Integer.parseInt(scanner.nextLine());
	    
	    switch(opcao) {
	    case 1 :
	    	cadastrarProduto();
	    	break;
	    case 2 :
	    	atualizarProduto();
	    	break;
	    case 3 :
	    	excluirProduto();
	    	break;
	    case 4 :
	    	consultarProdutos();
	    	break;
	    default :
	    	System.out.println("\nOPCAO INVALIDA");
	    	break;
	    }
	    System.out.println("\nDESEJA REALIZAR OUTRA OPERAÇÃO? (S/N):");
	    var continuar = scanner.nextLine();
	    //equalsIgnoreCase("S") ignora se é s maius ou s minusc o equals tem que ser literal
	    if(continuar.equalsIgnoreCase("S")) {
	    	gerenciarProdutos(); //recursividade.. 
	    }
	    else {
	    	System.out.println("\nFIM DO PROGRAMA");
	    }
	
	
	}
		
	/*
	 *metodo para capturar os dados para realizxar o cadastro de um produto 
	 */

	private void cadastrarProduto() throws Exception {

	System.out.println("\nCADASTRO DE PRODUTOS:\n");
	
	var produto = new Produto(); //criando o objeto
	
	System.out.println("NOME DO PRODUTO............:");
	produto.setNome(scanner.nextLine());
	
	System.out.println("PRECO......................:");
//	produto.setPreco(Double.parseDouble(scanner.nextLine()));
	String precoX = (String.valueOf(scanner.nextLine()));
	precoX = precoX.replace(".", "").replace(",", ".");
	produto.setPreco(Double.parseDouble(precoX));
	
	System.out.println("QUANTIDADE.................:");
	produto.setQuantidade(Integer.parseInt(scanner.nextLine()));
	
	var produtoRepository = new ProdutoRepository();
	produtoRepository.inserir(produto);
	
	System.out.println("\nPRODUTO CADASTRADO COM SUCESSO!");
	
	}
	
	/*
	 *metodo para capturar os dados para atualizar o cadastro de um produto 
	 */

	private void atualizarProduto() throws Exception {

	System.out.println("\nATUALIZAR DE PRODUTOS:\n");
	
	var produto = new Produto(); //criando o objeto
	
	System.out.println("ID DO PRODUTO..............:");
	produto.setId(Integer.parseInt(scanner.nextLine()));
	
	System.out.println("NOME DO PRODUTO............:");
	produto.setNome(scanner.nextLine());
	
	System.out.println("PRECO......................:");
//	produto.setPreco(Double.parseDouble(scanner.nextLine()));
	String precoX = (String.valueOf(scanner.nextLine()));
//	precoX = precoX.replace(",", ".").replace(".", "");
	precoX = precoX.replace(",", ".");
	produto.setPreco(Double.parseDouble(precoX));
	
	System.out.println("QUANTIDADE.................:");
	produto.setQuantidade(Integer.parseInt(scanner.nextLine()));
	
	var produtoRepository = new ProdutoRepository();
	if (produtoRepository.atualizar(produto)) {
    	System.out.println("\nPRODUTO ATUALIZADO COM SUCESSO!");
	}
	else {
    	System.out.println("\nNENHUM PRODUTO ATUALIZADO!");
	}
	
	}
	
	/*
	 * metodo para capaturar os dados 
	 * para excluir um produto
	 */
	private void excluirProduto() throws Exception {
		System.out.println("\nEXCLUSAO DO PRODUTO:\n");
		
		System.out.println("ID DO PRODUTO..............:");
		var id = (Integer.parseInt(scanner.nextLine()));
		
		var produtoRepository = new ProdutoRepository();
		
		if (produtoRepository.excluir(id)) {
	    	System.out.println("\nPRODUTO EXCLUIDO COM SUCESSO!");
		}
		else {
	    	System.out.println("\nNENHUM PRODUTO EXCLUIDO!");
		}

	}
	/* 
	 * metodo para listar os produtos
	 * 
	 */
	private void consultarProdutos() throws Exception {
		System.out.println("\nCONSULTA DE PRODUTOs:\n");

		var produtoRepository = new ProdutoRepository();
		produtoRepository.consultar();
		
	}
	
}
