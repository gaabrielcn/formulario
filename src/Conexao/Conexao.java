package Conexao;

//import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {
	private String Url =
			"jdbc:sqlserver://10.109.8.9:1433;databaseName=POO_gp11;user=POO_gp11;password=;encrypt=false;trustServerCertificate=true;loginTimeout=30;";
			public Connection conexao; // Conecta com o banco de dados
			public void conectar() { // Efetua a conex�o
				try {

					// Conex�o com o banco

					conexao = DriverManager.getConnection(Url);

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Erro de conex�o!\nERRO: " + ex.getMessage());

				}
			}
			public void desconectar() { // Fecha a conex�o

				try {
					conexao.close(); // Fechar conex�o
					JOptionPane.showMessageDialog(null, "Conex�o fechada com sucesso!");
				} catch (SQLException ex) {

					JOptionPane.showMessageDialog(null, "Erro ao fechar a conex�o!\nERRO: " + ex.getMessage());

				}
			}
}
