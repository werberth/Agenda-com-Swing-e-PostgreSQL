import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;


public class Login {
	private JFrame frame;
	private static Login tela;
	private JMenuBar menuBar;
	private JMenu opcoes;
	private JMenuItem sobre;
	private JMenuItem exit;
	private BD bd;
	private Arquivo file;

	private JTextField username;
	private JPasswordField senha;
	private JLabel titulo, userlabel, senhalabel;
	private JButton entrar, cadastrar;

	private PreparedStatement statement;
	private ResultSet resultSet;


	public Login(){
		montaTela();
	}

	private void montaTela(){
		frame = new JFrame("Login");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0, 174, 176));
		initComponents();
		defineEvents();

		frame.pack();
		frame.setBounds(0,0,400,400);
		frame.setVisible(true);
		bd = new BD();
		file = new Arquivo();
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
		frame.setJMenuBar(menuBar);;
		
		// Adicionando Campos de texto e respectivos labels
		//Titulo
		Font f = new Font("SansSerif", Font.BOLD, 20);
		titulo = new JLabel("Login");
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setFont(f);
		titulo.setBounds(150, 15, 150, 30);
		frame.add(titulo);

		// Nome de Usuário
		userlabel = new JLabel("Nome de Usuário");
		userlabel.setForeground(new Color(255, 255, 255));
		userlabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		username = new JTextField();
		username.setForeground(new Color(255, 255, 255));
		username.setBackground(new Color(0, 130, 156));
		username.setFont(new Font("SansSerif", Font.BOLD, 14));
		userlabel.setBounds(50,60,150,30);
		username.setBounds(50,90,300,30);
		frame.add(username);
		frame.add(userlabel);

		// Senha 
		senhalabel = new JLabel("Senha");
		senhalabel.setForeground(new Color(255, 255, 255));
		senha = new JPasswordField();
		senha.setBackground(new Color(0, 130, 156));
		senha.setForeground(new Color(255, 255, 255));
		senha.setFont(new Font("SansSerif", Font.BOLD, 14));
		senhalabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		senhalabel.setBounds(50,130,150,30);
		senha.setBounds(50,160,300,30);
		frame.add(senha);
		frame.add(senhalabel);

		//Butão de Logar
		f = new Font("SansSerif",1, 15);
		entrar = new JButton("Entrar");
		entrar.setBackground(new Color(0, 130, 156));
		entrar.setForeground(new Color(255, 255, 255));
		entrar.setFont(f);
		entrar.setBounds(220,210,100,30);
		frame.add(entrar);

		//Botão de Cadastro
		f = new Font("SansSerif",1, 15);
		cadastrar = new JButton("Cadastre-se");
		cadastrar.setBackground(new Color(0, 130, 156));
		cadastrar.setForeground(new Color(255, 255, 255));
		cadastrar.setFont(f);
		cadastrar.setBounds(65,210,150,30);
		frame.add(cadastrar);
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

		entrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if((username.getText().trim().equals("")) && (senha.getText().trim().equals(""))){
					JOptionPane.showMessageDialog(null, "Verifique se algum campo está vazio!");
				} else {
					try {
						if(!bd.getConnection()){
							JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
							System.exit(0);
						}
						String url = "SELECT * FROM users WHERE nome=? AND senha=?";
						statement = bd.connection.prepareStatement(url);
						statement.setString(1, username.getText());
						statement.setString(2, senha.getText());
						resultSet = statement.executeQuery();
						if(resultSet.next()){
							String nome = resultSet.getString("nome");
							file.escreverArquivo(nome);
							JOptionPane.showMessageDialog(null, "Usuaro logado com sucesso \n Nome: " + nome);
						} else {
							JOptionPane.showMessageDialog(null, "Nenhum usuário com esse username e senha foi encontrado!");
						}
						resultSet.close();
						statement.close();
						bd.close();
					} catch(Exception erro) {
						JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
					}
					
				}
			}
		});

		cadastrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Cadastro cadastroFrame = new Cadastro();
			}
		});
	}

	public static void main(String[] args){
		tela = new Login();
	}
}