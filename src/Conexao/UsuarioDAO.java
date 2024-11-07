package Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import biblioteca.Usuario;

public class UsuarioDAO {
	public static void create (Usuario usu) {
		Conexao con = new Conexao();
		con.conectar();

		try {
			PreparedStatement pst = 
					con.conexao.prepareStatement(
							"insert into usuario (cpf, "
									+ "nome, telefone, email,tipo) "
									+ "values (?,?,?,?,?)");
			pst.setString (1, usu.getCpf());
			pst.setString(2, usu.getNome());
			pst.setString (3,usu.getTelefone());
			pst.setString(4,usu.getEmail());
			char tipo = (usu.isMedico() ? 'M' : 'P');
			pst.setString(5, String.valueOf(tipo));
			JOptionPane.showMessageDialog(null, usu.isMedico());
			
			pst.execute();
			con.desconectar();
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro na inserção" + ex.getMessage()) ;
		}
	}

	public static int solicitarCodigo(String cpf) {
		Conexao con = new Conexao();
		con.conectar();
		int codUsuario = 0;
		

		try {
			PreparedStatement pst = 
					con.conexao.prepareStatement(
							"select codUsuario from usuario where cpf = '" + cpf + "';");
			ResultSet res = pst.executeQuery();
			res.next();
			codUsuario = res.getInt("codUsuario");
			con.desconectar();
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro na inserção" + ex.getMessage()) ;
		}		
		return codUsuario;
	}
	
	


}
