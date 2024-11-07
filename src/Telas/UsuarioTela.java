package Telas;

import java.awt.EventQueue;

import Conexao.UsuarioDAO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalIconFactory.PaletteCloseIcon;
import javax.swing.text.MaskFormatter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.awt.Color;

import biblioteca.Usuario;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class UsuarioTela {

	private JFrame frmUsuario;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtTelefone;
	private boolean valido=false;
	private String medico,paciente;
	private JRadioButton btnMedico;
	private JRadioButton btnPaciente;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioTela window = new UsuarioTela();
					window.frmUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UsuarioTela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		SimpleDateFormat data = new SimpleDateFormat ("dd/MM/yyyy");
		Date d = new Date();
		frmUsuario = new JFrame();
		frmUsuario.getContentPane().setBackground(new Color(230, 230, 230));
		frmUsuario.setTitle("CadastroDeUsuario");
		frmUsuario.setBounds(100, 100, 536, 407);
		frmUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUsuario.getContentPane().setLayout(null);

		JLabel lblData = new JLabel("Data Cadastro");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblData.setBounds(10, 24, 156, 27);
		frmUsuario.getContentPane().add(lblData);


		//CPF:
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCPF.setBounds(10, 85, 46, 27);
		frmUsuario.getContentPane().add(lblCPF);

		txtCPF = new JFormattedTextField(setMascara("###.###.###-##"));
		txtCPF.setBounds(82, 85, 268, 28);
		frmUsuario.getContentPane().add(txtCPF);


		//NOME :
		JLabel lblnome = new JLabel("Nome:");
		lblnome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblnome.setBounds(10, 139, 60, 17);
		frmUsuario.getContentPane().add(lblnome);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(82, 136, 268, 27);
		frmUsuario.getContentPane().add(txtNome);

		//TELEFONE:
		JLabel lbltelefone = new JLabel("Telefone:");
		lbltelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbltelefone.setBounds(10, 188, 76, 14);
		frmUsuario.getContentPane().add(lbltelefone);

		txtTelefone = new JFormattedTextField(setMascara("(##) #####-####"));
		txtTelefone.setBounds(82, 184, 268, 27);
		frmUsuario.getContentPane().add(txtTelefone);

		// EMAIL :
		JLabel lblemail = new JLabel("E-mail:");
		lblemail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblemail.setBounds(10, 237, 46, 14);
		frmUsuario.getContentPane().add(lblemail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(82, 232, 268, 29);
		frmUsuario.getContentPane().add(txtEmail);


		JLabel lblDataa = new JLabel("New label");
		lblDataa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDataa.setBounds(138, 24, 109, 27);
		frmUsuario.getContentPane().add(lblDataa);
		lblDataa.setText(data.format(d) );

		JButton btnlimpar = new JButton("Limpar");
		btnlimpar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				limpar();
				txtCPF.requestFocus();
			}
		});
		btnlimpar.setBounds(180, 323, 101, 34);
		frmUsuario.getContentPane().add(btnlimpar);

		JButton btnEndereco = new JButton("Endere\u00E7o");
		btnEndereco.setBounds(305, 323, 101, 34);
		frmUsuario.getContentPane().add(btnEndereco);

		JLabel lblMouP = new JLabel("Voc\u00EA \u00E9 :");
		lblMouP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMouP.setBounds(10, 282, 76, 17);
		frmUsuario.getContentPane().add(lblMouP);

		btnMedico = new JRadioButton("M\u00E9dico");
		btnMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnMedico.setBounds(85, 281, 109, 23);
		frmUsuario.getContentPane().add(btnMedico);

		btnPaciente = new JRadioButton("Paciente");
		btnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
			
		});
		btnPaciente.setBounds(241, 281, 109, 23);
		frmUsuario.getContentPane().add(btnPaciente);

		//cria o grupo de botoes : 
		ButtonGroup group = new ButtonGroup();
		group.add(btnMedico);
		group.add(btnPaciente);

		JButton btnsalvar = new JButton("Salvar");
		btnsalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!validarEmail(txtEmail.getText())) {
					JOptionPane.showMessageDialog(null, "E-mail inválido");
					txtEmail.requestFocus();
				}
				else if(verificarCampo(txtCPF.getText(), txtNome.getText(), txtTelefone.getText() ,txtEmail.getText(), btnMedico, btnPaciente )){
					preencherDados(d);
				}
//				JOptionPane.showInternalMessageDialog(null, UsuarioDAO.solicitarCodigo(txtCPF.getText()));

			}
		});
		btnsalvar.setBounds(64, 323, 89, 34);
		frmUsuario.getContentPane().add(btnsalvar);

		btnEndereco.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(verificarCampo(txtCPF.getText(), txtNome.getText(), txtTelefone.getText() ,txtEmail.getText(), btnMedico, btnPaciente )) {
					try {
						EnderecoTela window = new EnderecoTela(txtCPF.getText());
						window.frmEndereco.setVisible(true);
					}catch ( Exception err) {
						err.printStackTrace();
					}
				}
			}
		});




	}

	void preencherDados  (Date data) {
		Usuario usu = new Usuario(txtCPF.getText(), txtNome.getText(), txtTelefone.getText() ,txtEmail.getText() ,data, btnMedico.isSelected(), btnPaciente.isSelected());
		//		JOptionPane.showMessageDialog(null, usu);
		UsuarioDAO.create(usu);
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

	private boolean verificarCampo(String cpf, String nome, String telefone, String email, JRadioButton btnMedico, JRadioButton btnPaciente) {
		String textocpf = txtCPF.getText().replaceAll("[^0-9]", ""); 
		//txtCPF.getText().trim().replaceAll("[^0-9]", "");
		if(cpf.isEmpty() || textocpf.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Campo CPF está vazio");
			txtCPF.requestFocus();
			return false;
		}
		else if(nome.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Campo NOME está vazio");
			txtNome.requestFocus();
			return false;
		}
		else if (telefone.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Campo TELEFONE está vazio");
			txtTelefone.requestFocus();
			return false;
		}
		else if (email.isEmpty()) {
			JOptionPane.showMessageDialog(null,"Campo E-MAIL está vazio");
			txtEmail.requestFocus();
			return false;
		}
		else if ( !btnMedico.isSelected()  && !btnPaciente.isSelected()) {
			JOptionPane.showMessageDialog(null,"Selecione se você é médico ou paciente");
			return false;
		}
		return true;
	}
	void limpar() {
		txtCPF.setText("");
		txtNome.setText("");
		txtTelefone.setText(null);
		txtEmail.setText(null);
	}

	private boolean validarEmail(String email) {
		boolean isEmailIdValid = false;
		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailIdValid = true;
			}
		}
		return isEmailIdValid;
	}
}
