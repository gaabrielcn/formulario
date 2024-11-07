package Conexao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import biblioteca.Endereco;
import biblioteca.Usuario;

public class EnderecoDAO {
	public static void create (Endereco end, String cpf) {
		Conexao con = new Conexao();
		con.conectar();
		
		try {
			int codigo = UsuarioDAO.solicitarCodigo(cpf);
			
			if (codigo > 0) {
				PreparedStatement pst = con.conexao.prepareStatement(
					    "INSERT INTO endereco (rua, cep, cidade, estado, complemento, bairro, numero, codUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
					);
				pst.setString (1, end.getRua());
				pst.setString(2, end.getCep());
				pst.setString (3,end.getCidade());
				pst.setString(4,end.getEstado());
				pst.setString(5,end.getComplemento());
				pst.setString(6,end.getBairro());
				pst.setString(7,end.getNumero());
				pst.setInt(8,codigo);
				pst.executeUpdate();
			} else {
				JOptionPane.showMessageDialog(null, "Usuário não encontrado") ;
			}
			con.desconectar();
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro na inserção" + ex.getMessage()) ;
		}
	}
}
