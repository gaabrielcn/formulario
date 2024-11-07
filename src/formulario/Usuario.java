package biblioteca;

import java.util.Date;


public class Usuario {
	private String cpf , nome , telefone , email;
	boolean medico,paciente;
	private Date dataCadastro;
	
	
	public Usuario(String cpf, String nome, String telefone, String email, Date dataCadastro, boolean medico, boolean paciente) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.dataCadastro = dataCadastro;
		this.medico = medico;
		this.paciente = paciente;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public boolean isMedico() {
		return medico;
	}
	public void setMedico(boolean medico) {
		this.medico = medico;
	}
	public boolean isPaciente() {
		return paciente;
	}
	public void setPaciente(boolean paciente) {
		this.paciente = paciente;
	}

	
	public String toString() {
		return "Usuario [cpf=" + cpf + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email
				+ ", dataCadastro=" + dataCadastro + "]";
	}
	
}
