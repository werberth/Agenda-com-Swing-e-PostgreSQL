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
		frame.getContentPane().setBackground(new Color(0, 174, 176));
		initComponents();
		defineEvents();

		frame.pack();
		frame.setBounds(0,0,600,600);
		frame.setVisible(true);
		bd = new BD();
	}

	private void initComponents(){
		// Adicionando componentes da Barra de menu
		
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 130, 156));
		opcoes = new JMenu("Opções");
		opcoes.setForeground(new Color(255, 255, 255));
		opcoes.setBackground(new Color(0, 130, 156));
		sobre = new JMenuItem("Sobre");
		sobre.setForeground(new Color(255, 255, 255));
		sobre.setBackground(new Color(0, 130, 156));
		exit = new JMenuItem("Exit");
		exit.setForeground(new Color(255, 255, 255));
		exit.setBackground(new Color(0, 130, 156));
		opcoes.add(sobre);
		opcoes.add(exit);
		menuBar.add(opcoes);
		frame.setJMenuBar(menuBar);
		
		// Adicionando Campos de texto e respectivos labels
		//Titulo
		titulo = new JLabel("Cadastro");
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setFont(new Font("SansSerif", Font.BOLD, 25));
		titulo.setBounds(240, 40, 150, 30);
		frame.add(titulo);

		// Nome de Usuário
		userlabel = new JLabel("Nome de Usuário");
		userlabel.setForeground(new Color(255, 255, 255));
		username = new JTextField();
		username.setForeground(new Color(255, 255, 255));
		username.setBackground(new Color(0, 130, 156));
		username.setFont(new Font("SansSerif", Font.BOLD, 15));
		userlabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		userlabel.setBounds(150,110,150,30);
		username.setBounds(150,140,300,30);
		frame.add(username);
		frame.add(userlabel);

		// Senha 
		senhalabel = new JLabel("Senha");
		senhalabel.setForeground(new Color(255, 255, 255));
		senha = new JPasswordField();
		senha.setBackground(new Color(0, 130, 156));
		senha.setForeground(new Color(255, 255, 255));
		senha.setFont(new Font("SansSerif", Font.BOLD, 15));
		senhalabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		senhalabel.setBounds(150,180,150,30);
		senha.setBounds(150,210,300,30);
		frame.add(senha);
		frame.add(senhalabel);

		//confirmar senha
		confirmarlabel = new JLabel("Confirmar Senha");
		confirmarlabel.setForeground(new Color(255, 255, 255));
		confirmarlabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		confirmar = new JPasswordField();
		confirmar.setBackground(new Color(0, 130, 156));
		confirmar.setForeground(new Color(255, 255, 255));
		confirmar.setFont(new Font("SansSerif", Font.BOLD, 15));
		confirmarlabel.setBounds(150,250,150,30);
		confirmar.setBounds(150,280,300,30);
		frame.add(confirmar);
		frame.add(confirmarlabel);



		//Butão de Logar
		salvar = new JButton("Salvar");
		salvar.setFont(new Font("SansSerif", Font.BOLD, 15));
		salvar.setBounds(250,330,100,30);
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
							//limpaCampos();
							frame.dispose();
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