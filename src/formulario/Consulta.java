package biblioteca;

import java.util.Date;

public class Consulta {

	private String cpf, paciente, medico, vlrTotal, dataConsulta;
	
	public Consulta(String cpf, String paciente ,String  medico, String  dataConsulta, String  vlrTotal) {
		super();
		this.cpf = cpf;
		this.paciente = paciente;
		this.medico = medico;
		this.dataConsulta = dataConsulta;
		this.vlrTotal = vlrTotal;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getMedico() {
		return medico;
	}
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getVlrTotal() {
		return vlrTotal;
	}
	public void setVlrTotal(String vlrTotal) {
		this.vlrTotal = vlrTotal;
	}
	public String getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	@Override
	public String toString() {
		return "Consulta [cpf=" + cpf + ", paciente=" + paciente + ", medico=" + medico + ", vlrTotal=" + vlrTotal
				+ ", dataConsulta=" + dataConsulta + "]";
	}
	

	
	
}
