package factories;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * classe responsavel por fazer a conexão com o banco de dados do projeto
 */
public class ConnectionFactory {
	/*
	 * metodo para retornar uma conexão com m banco de dados do PostgreeSQL
	 * metodo que tem retorno Connection
	 */
	public Connection getConnection() throws Exception {
		//getConnection é um nome que poderia ser qualquer um
		//get na frente é para manter o padrão do JAVA, quando tem um metodo que retorna algo
		
		var host = "jdbc:postgresql://localhost:5432/bd_produtos";
		var user = "postgres";
		var pass = "coti";
		
		//abrir e retornar a conexao com o banco de dados
		return DriverManager.getConnection(host, user, pass);
		
		
		
	}

	
}
