package repositories;

import entities.Produto;
import factories.ConnectionFactory;

/*
 * classe responsavel por gravar alterar e excluir consultar 
 * dados de produtos no banco de dados do PostgreSQL (CRUD)
 */
public class ProdutoRepository {
	
	//atributo
	private ConnectionFactory connectionFactory = new ConnectionFactory();
	
	
	/*
	 * metodo para inserir um produto novo na tabela
	 * do banco dedados 
    * void metodo não retorna  nada
    * throws quem for chamar esse metodo terá que fazer o tratamento de exceção, nao precisa usar try catch por causa do throws
	*/
	public void inserir(Produto produto) throws Exception {
		
		//abrindo conexao com o banco de dados
//		var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bd_produtos","postgres","coti");
// substituiu pelo abaixo depois que criou a connectionfactory
		var connection = connectionFactory.getConnection();
		
		//escrevendo um comando sql para inserir um produto no banco de dados
		var statement = connection.prepareStatement("INSERT INTO produtos(nome,preco, quantidade) VALUES(?,?,?)");
		
		//Passando os parametros para o comando SQL
		statement.setString(1, produto.getNome());
		
//		String precoX = (String.valueOf(produto.getPreco()));
//		precoX = precoX.replace(",", ".").replace(" ", "");
//		produto.setPreco(Double.parseDouble(precoX));        

		statement.setDouble(2, produto.getPreco());
		statement.setInt(3,  produto.getQuantidade());
		statement.execute(); //executando o comando SQL
		
		//fechando a conexao com o banco de dados
		connection.close();
		
	}
	
	/*
	 * metodo para atualizar um produto existente
	 * na tabela do banco de dados
	 */
	
	public boolean atualizar(Produto produto) throws Exception {
		
		//abrindo conexao com o banco de dados
		var connection = connectionFactory.getConnection();
		
		//escrevendo um comando SQL para atualizar um produto no bd
		var statement = connection.prepareStatement
		("UPDATE produtos SET nome=?, preco=?, quantidade=? WHERE id =?");
		//passando os parametros para o comando SQL
		statement.setString(1,  produto.getNome());
		statement.setDouble(2, produto.getPreco());
		statement.setInt(3, produto.getQuantidade());
		statement.setInt(4, produto.getId());
		var rowsAffected = statement.executeUpdate();
		//rowsAffected poderia ser qq nome (flag) 1=atualizou algum registro,0=nenhum
        
		
		//fechando
		connection.close();
		//retornar verdadeiro se algum produto foi atualizxado
		//porque o retorno é boolean void boolean o return tem 
		//que fazer referencia de que está voltando um boolean
		return rowsAffected == 1; //true ou false
		
	}
	
	/*
	 * metodo para excluir um produto existente
	 */
	public boolean excluir(Integer id) throws Exception {
		
		//abrir conexao com o banco de dados
		var connection = connectionFactory.getConnection();
		
		//escrevendo um comando SQL para excluir o produto no bd
		var statement = connection.prepareStatement 
		("DELETE FROM produtos where id = ?");
		//passando os parametros para o comando SQL
		statement.setInt(1,  id);
		var rowsAffected = statement.executeUpdate();
		//fechando
		connection.close();
		return rowsAffected == 1; // true(1) or false(0)
	}
	/*
	 * metodo para consultar todos os produtos existentes no banco de dados
	 */
	public void consultar() throws Exception {
		
		//abrir conexao com o banco de dados
		var connection = connectionFactory.getConnection();
		
		//escrevendo um comando SQL para excluir o produto no bd
		var statement = connection.prepareStatement 
		("SELECT * FROM produtos ORDER BY id");
		var result = statement.executeQuery();

		//percorrer cada registro obtido do banco de dados
		while(result.next()) {
			System.out.println("ID...............:" + result.getInt("id"));
			System.out.println("NOME.............:" + result.getString("nome"));
			System.out.println("PRECO............:" + result.getDouble("preco"));
			System.out.println("QUANTIDADE.......:" + result.getInt("quantidade"));
		}
		
		
		//fechando
		connection.close();
	
	
	}
	

}
