package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Conexao.EnderecoDAO;
import Conexao.UsuarioDAO;
import biblioteca.Endereco;
import biblioteca.Usuario;

import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Color;

public class EnderecoTela {

	JFrame frmEndereco;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtComplemento;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JFormattedTextField txtCep;
	JFormattedTextField txtNumero ;
	private JButton btnLimpar;
	private JButton btnVoltar;
	private String cpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnderecoTela window = new EnderecoTela("");
					window.frmEndereco.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EnderecoTela(String cpf) {
		initialize();
		this.cpf = cpf;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEndereco = new JFrame();
		frmEndereco.getContentPane().setBackground(new Color(230, 230, 230));
		frmEndereco.setTitle("Endereco");
		frmEndereco.setBounds(100, 100, 539, 426);
		frmEndereco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEndereco.getContentPane().setLayout(null);
		//frmEndereo.setExtendedState(JFrame.MAXIMIZED_BOTH);   tela grande



		JLabel lblCep = new JLabel("CEP :");
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCep.setBounds(10, 11, 60, 28);
		frmEndereco.getContentPane().add(lblCep);

		txtCep = new JFormattedTextField(setMascara("#####-###"));  
		txtCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				buscarCep(txtCep.getText());
			}
		});
		txtCep.setBounds(126, 17, 227, 20);
		frmEndereco.getContentPane().add(txtCep);


		JLabel lblRua = new JLabel("Rua :");
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRua.setBounds(10, 50, 60, 28);
		frmEndereco.getContentPane().add(lblRua);

		txtRua = new JTextField();
		txtRua.setEnabled(false);
		txtRua.setBounds(126, 56, 227, 20);
		frmEndereco.getContentPane().add(txtRua);
		txtRua.setColumns(10);


		txtNumero = new JFormattedTextField();
		txtNumero.setBounds(416, 56, 78, 20);
		frmEndereco.getContentPane().add(txtNumero);



		JLabel lvlBairro = new JLabel("Bairro :");
		lvlBairro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lvlBairro.setBounds(10, 89, 60, 28);
		frmEndereco.getContentPane().add(lvlBairro);

		txtBairro = new JTextField();
		txtBairro.setEnabled(false);
		txtBairro.setColumns(10);
		txtBairro.setBounds(126, 95, 227, 20);
		frmEndereco.getContentPane().add(txtBairro);


		JLabel lvlComplemento = new JLabel("Complemento :");
		lvlComplemento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lvlComplemento.setBounds(10, 128, 100, 28);
		frmEndereco.getContentPane().add(lvlComplemento);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(126, 134, 227, 20);
		frmEndereco.getContentPane().add(txtComplemento);


		JLabel lvlCidade = new JLabel("Cidade :");
		lvlCidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lvlCidade.setBounds(10, 167, 60, 28);
		frmEndereco.getContentPane().add(lvlCidade);

		txtCidade = new JTextField();
		txtCidade.setEnabled(false);
		txtCidade.setColumns(10);
		txtCidade.setBounds(126, 173, 227, 20);
		frmEndereco.getContentPane().add(txtCidade);



		JLabel lvlEstado = new JLabel("Estado :");
		lvlEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lvlEstado.setBounds(10, 206, 60, 28);
		frmEndereco.getContentPane().add(lvlEstado);

		txtEstado = new JTextField();
		txtEstado.setEnabled(false);
		txtEstado.setColumns(10);
		txtEstado.setBounds(126, 212, 227, 20);
		frmEndereco.getContentPane().add(txtEstado);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(80, 320, 105, 35);
		frmEndereco.getContentPane().add(btnSalvar);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
				txtCep.requestFocus();
			}
		});
		btnLimpar.setBounds(212, 320, 105, 35);
		frmEndereco.getContentPane().add(btnLimpar);

		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(348, 320, 105, 35);
		frmEndereco.getContentPane().add(btnVoltar);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verificarCampo(txtRua.getText(), txtCep.getText(), txtCidade.getText() ,txtEstado.getText() , txtBairro.getText(), txtNumero.getText())){
					preencherDados();
				}
			}
		});
	}

	private MaskFormatter setMascara (String mascara) {
		MaskFormatter F_Mascara = new MaskFormatter();
		try {
			F_Mascara.setMask(mascara);
			F_Mascara.setPlaceholderCharacter(' ');

		}
		catch(Exception excecao) {
			excecao.printStackTrace();
		}
		return F_Mascara;
	}

	public void buscarCep(String cep){
		String json;
		try {
			URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
			URLConnection urlConnection = url.openConnection();


			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder jsonSb = new StringBuilder();

			br.lines().forEach(l -> jsonSb.append(l.trim()));
			json = jsonSb.toString();
			json = json.replaceAll("[{},:]", "");
			json = json.replaceAll("\"", "\n");
			String array[] = new String[30];


			array = json.split("\n");
			//Atenção para os nomes dos seus textField's
			if (array[1].equals("cep")) {
				txtRua.setText(array[7]);
				txtBairro.setText(array[15]);
				txtCidade.setText(array[19]);
				txtEstado.setText(array[23]);
			} else {
				JOptionPane.showMessageDialog(null, "Cep inválido");
				txtCep.setText("");
				txtCep.requestFocus();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	void preencherDados  () {
		Endereco end = new Endereco(txtRua.getText(), txtCep.getText(), txtCidade.getText() ,txtEstado.getText() ,txtComplemento.getText(), txtBairro.getText(), txtNumero.getText());
		JOptionPane.showMessageDialog(null, end);
		EnderecoDAO.create(end, cpf);

	}
	private boolean verificarCampo(String rua, String cep, String cidade, String estado, String bairro, String numero) {
		if(rua.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Campo rua está vazio");
			return false;
		}
		else if(cep.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Campo CEP está vazio");
			return false;
		}
		else if (cidade.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Campo cidade está vazio");
			return false;
		}
		else if (estado.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Campo estado está vazio");
			return false;
		}
		else if (bairro.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Campo bairro está vazio");
			return false;
		}
		else if (numero.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Campo número está vazio");
			return false;
		}
		return true;
	}
	void limpar() {
		txtRua.setText("");
		txtCep.setText("");
		txtCidade.setText(null);
		txtEstado.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtNumero.setText(null);
	}
	public void fecharTela() {
		frmEndereco.dispose();
	}
}



