import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class Cadastro {
	private JFrame frame;
	private static Cadastro tela;
	private JMenuBar menuBar;
	private JMenu opcoes;
	private JMenuItem sobre;
	private JMenuItem exit;
	private BD bd;

	private JTextField username, confirmar, senha;
	private JLabel titulo, userlabel, senhalabel, confirmarlabel;
	private JButton salvar;

	private PreparedStatement statement;
	private ResultSet resultSet;
	private Users usuario;	


	public Cadastro(){
		montaTela();
	}

	private void montaTela(){
		frame = new JFrame("Cadastro");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		defineEvents();

		frame.pack();
		frame.setBounds(0,0,400,400);
		frame.setVisible(true);
		bd = new BD();
	}

	private void initComponents(){
		// Adicionando componentes da Barra de menu
		
		menuBar = new JMenuBar();
		opcoes = new JMenu("Opções");
		sobre = new JMenuItem("Sobre");
		exit = new JMenuItem("Exit");
		opcoes.add(sobre);
		opcoes.add(exit);
		menuBar.add(opcoes);
		frame.setJMenuBar(menuBar);
		
		// Adicionando Campos de texto e respectivos labels
		//Titulo
		Font f = new Font("SansSerif", Font.BOLD, 20);
		titulo = new JLabel("Cadastro");
		titulo.setFont(f);
		titulo.setBounds(150, 15, 150, 30);
		frame.add(titulo);

		// Nome de Usuário
		userlabel = new JLabel("Nome de Usuário");
		username = new JTextField();
		userlabel.setBounds(50,60,150,30);
		username.setBounds(50,90,300,30);
		frame.add(username);
		frame.add(userlabel);

		// Senha 
		senhalabel = new JLabel("Senha");
		senha = new JPasswordField();
		senhalabel.setBounds(50,120,150,30);
		senha.setBounds(50,150,300,30);
		frame.add(senha);
		frame.add(senhalabel);

		//confirmar senha
		confirmarlabel = new JLabel("Senha");
		confirmar = new JPasswordField();
		confirmarlabel.setBounds(50,180,150,30);
		confirmar.setBounds(50,210,300,30);
		frame.add(confirmar);
		frame.add(confirmarlabel);



		//Butão de Logar
		f = new Font("SansSerif",1, 15);
		salvar = new JButton("Salvar");
		salvar.setFont(f);
		salvar.setBounds(250,250,100,30);
		frame.add(salvar);
	}

	private void limpaCampos(){
		username.setText("");
		senha.setText("");
		confirmar.setText("");
	}


	private void defineEvents(){
		sobre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "Agenda, Todos os Direitos Reservados, 2017.");
			}
		});

		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
	 	salvar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					
				if((username.getText().trim().equals("")) || (senha.getText().trim().equals("")) || (confirmar.getText().trim().equals(""))){
						JOptionPane.showMessageDialog(null, "Verifique se algum campo está vazio!");
				} else {
					if(!(senha.getText().equals(confirmar.getText()))){
						System.out.println("Senha: " + senha.getText() + " | Confirmar Senha: " + confirmar.getText());
						JOptionPane.showMessageDialog(null, "Os dois campos de senham não combinam!");
					} else {
						try {
							if(!bd.getConnection()){
								JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
								System.exit(0);
							}

							String url = "INSERT INTO users (nome, senha) VALUES(?,?)";
							statement = bd.connection.prepareStatement(url);
							statement.setString(1, username.getText());
							statement.setString(2, senha.getText());
							statement.execute();
							statement.close();
							bd.close();
							JOptionPane.showMessageDialog(null, "Usuário Cadastrado com sucesso!");
							limpaCampos();
						} catch(Exception erro) {
							JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
						}
					}
					
				}
			}
		});
	}

	public static void main(String[] args){
		tela = new Cadastro();
	}
}