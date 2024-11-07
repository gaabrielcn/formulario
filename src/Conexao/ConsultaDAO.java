package Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import biblioteca.Consulta;

public class ConsultaDAO {


	public static int create(Consulta consulta) {
		Conexao con = new Conexao();
	    con.conectar();
	    
	    int codUsuario = 0;

	    try {
	        // Inserir na tabela consulta
	        PreparedStatement pst = con.conexao.prepareStatement(
	                "insert into consulta (codUsuario, dataConsulta, vlrTotal) values (?,?,?)",
	                PreparedStatement.RETURN_GENERATED_KEYS);

	        pst.setString(1, consulta.getPaciente());
	       // pst.setString(2, consulta.getMedico());
	        pst.setString(2, consulta.getDataConsulta());
	        pst.setString(3, consulta.getVlrTotal());
	        pst.executeUpdate(); // Executar a atualização no banco

	        ResultSet generatedKeys = pst.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            codUsuario = generatedKeys.getInt(1);
	        }

	        con.desconectar();

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null,
	                "Erro na inserção!\nErro: " + ex.getMessage());
	    }

	    return codUsuario;
	}
		
		
	public static int solicitarCodigo(String cpf) {
	    Conexao con = new Conexao();
	    con.conectar();
	    int codUsuario = 0;

	    try {
	        // Utilize PreparedStatement para evitar injeção de SQL
	        PreparedStatement pst = con.conexao.prepareStatement(
	            "SELECT codUsuario FROM usuario WHERE cpf = ?"
	        );

	        pst.setString(1, cpf);

	        ResultSet res = pst.executeQuery();

	        if (res.next()) {
	            codUsuario = res.getInt("codUsuario");
	        }

	        con.desconectar();
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Erro na consulta: " + ex.getMessage());
	    }

	    return codUsuario;
	}
	
}
