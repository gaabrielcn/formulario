package Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import biblioteca.Consulta;
import Conexao.ConsultaDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ConsultaTela {

	private JFrame frame;
	private JTextField textVlrTotal;
	private JTextField txtMedico;
	private JTextField txtPaciente;
	JFormattedTextField txtFCpf = new JFormattedTextField();
	JFormattedTextField txtFDtConsulta = new JFormattedTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaTela window = new ConsultaTela();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConsultaTela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblDtConsulta = new JLabel("Data da Consulta:");
		lblDtConsulta.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDtConsulta.setBounds(207, 25, 116, 14);
		frame.getContentPane().add(lblDtConsulta);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCpf.setBounds(10, 24, 26, 14);
		frame.getContentPane().add(lblCpf);

		JLabel lblvlrTotal = new JLabel("Valor da Consulta:");
		lblvlrTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblvlrTotal.setBounds(10, 56, 118, 20);
		frame.getContentPane().add(lblvlrTotal);

		textVlrTotal = new JTextField();
		textVlrTotal.setColumns(10);
		textVlrTotal.setBounds(132, 57, 147, 20);
		frame.getContentPane().add(textVlrTotal);

		JLabel lblMedico = new JLabel("Medico:");
		lblMedico.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMedico.setBounds(10, 87, 53, 17);
		frame.getContentPane().add(lblMedico);

		txtMedico = new JTextField();
		txtMedico.setColumns(10);
		txtMedico.setBounds(132, 88, 147, 20);
		frame.getContentPane().add(txtMedico);

		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPaciente.setBounds(10, 120, 68, 14);
		frame.getContentPane().add(lblPaciente);

		txtPaciente = new JTextField();
		txtPaciente.setColumns(10);
		txtPaciente.setBounds(132, 118, 147, 20);
		frame.getContentPane().add(txtPaciente);

		JButton btnsalvar = new JButton("Salvar");
		btnsalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificarCampo(txtFCpf.getText(), txtMedico.getText(), txtFDtConsulta.getText(), textVlrTotal.getText())) {
					// Criar objeto Consulta com os dados fornecidos
					Consulta consulta = new Consulta(txtFCpf.getText(), "", txtMedico.getText(), txtFDtConsulta.getText(), textVlrTotal.getText());

					// Chamar o método create da ConsultaDAO para inserir no banco e obter o codUsuario
					int codUsuario = ConsultaDAO.create(consulta);

					// Preencher automaticamente o campo txtPaciente com o codUsuario
					txtPaciente.setText(Integer.toString(codUsuario));
				}
			}
		});
		btnsalvar.setBounds(39, 190, 89, 23);
		frame.getContentPane().add(btnsalvar);


		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener ( new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if(verificarCampo(txtFCpf.getText(), txtMedico.getText(), txtFDtConsulta.getText(), textVlrTotal.getText())) {
					limpar();
				}
			}	

		});
		btnLimpar.setBounds(177, 190, 89, 23);
		frame.getContentPane().add(btnLimpar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(313, 190, 89, 23);
		frame.getContentPane().add(btnVoltar);

		txtFCpf = new JFormattedTextField(setMascara("###.###.###-##"));
		txtFCpf.setBounds(41, 23, 156, 20);
		frame.getContentPane().add(txtFCpf);

		//chat:
		txtFCpf.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// Nada a fazer quando o campo ganha foco
			}

			@Override
			public void focusLost(FocusEvent e) {
				preencherPacienteAutomaticamente(txtFCpf.getText());
			}
		});

		txtFDtConsulta = new JFormattedTextField(setMascara("##/##/####"));
		txtFDtConsulta.setBounds(323, 23, 100, 20);
		frame.getContentPane().add(txtFDtConsulta);
	}
	private MaskFormatter setMascara(String mascara) {
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
	private boolean verificarCampo(String cpf, String medico, String dataConsulta, String vlrTotal) {
		if (cpf.isEmpty() || medico.isEmpty() || dataConsulta.isEmpty() || vlrTotal.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos");
			return false;
		}
		return true;

	}
	void limpar() {
		txtFDtConsulta.setText("");
		textVlrTotal.setText("");
		txtMedico.setText("");
		txtFCpf.setText("");
		txtPaciente.setText("");
	}
	private void preencherPacienteAutomaticamente(String cpf) {
		int codUsuario = ConsultaDAO.solicitarCodigo(cpf);
		txtPaciente.setText(Integer.toString(codUsuario));
	}
}
