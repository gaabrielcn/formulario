package biblioteca;

public class Endereco {
	private String rua, cep, cidade, estado, complemento , bairro;
	private String numero;
	public Endereco(String rua, String cep, String cidade, String estado, String complemento, String bairro,
			String numero) {
		super();
		this.rua = rua;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.complemento = complemento;
		this.bairro = bairro;
		this.numero = numero;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		return "Endereco [rua=" + rua + ", cep=" + cep + ", cidade=" + cidade + ", estado=" + estado + ", complemento="
				+ complemento + ", bairro=" + bairro + ", numero=" + numero + "]";
	}

}
