import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;


public class Login {
	private JFrame frame;
	private static Login tela;
	private static MenuPrincipal telaInicial;
	private MenuBarClass bar;
	private JMenuBar menuBar;
	private BD bd;
	private Arquivo file;

	private JTextField username;
	private JPasswordField senha;
	private JLabel titulo, titleImageLabel, userlabel, senhalabel;
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
		frame.setBounds(350,150,700,500);
		frame.setVisible(true);
		bd = new BD();
		file = new Arquivo();
	}

	private void initComponents(){
		// Adicionando componentes da Barra de menu
		bar = new MenuBarClass();
		menuBar = bar.newMenuBar();
		frame.setJMenuBar(menuBar);
		
		// Adicionando Campos de texto e respectivos labels
		//Titulo
		Font f = new Font("SansSerif", Font.BOLD, 40);
		titulo = new JLabel("Login");
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setFont(f);
		titulo.setBounds(230, 60, 230, 45);
		frame.add(titulo);

		// Imagem de titulo
		titleImageLabel = new JLabel();
		titleImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_login.png")));
		titleImageLabel.setBounds(55, 100, 250, 140);
		frame.add(titleImageLabel);


		// Nome de Usuário
		userlabel = new JLabel("Nome de Usuário");
		userlabel.setForeground(new Color(255, 255, 255));
		userlabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		username = new JTextField();
		username.setForeground(new Color(255, 255, 255));
		username.setBackground(new Color(0, 130, 156));
		username.setFont(new Font("SansSerif", Font.BOLD, 16));
		userlabel.setBounds(235,115,240,35);
		username.setBounds(235,145,385,35);
		frame.add(username);
		frame.add(userlabel);

		// Senha 
		senhalabel = new JLabel("Senha");
		senhalabel.setForeground(new Color(255, 255, 255));
		senha = new JPasswordField();
		senha.setBackground(new Color(0, 130, 156));
		senha.setForeground(new Color(255, 255, 255));
		senha.setFont(new Font("SansSerif", Font.BOLD, 14));
		senhalabel.setFont(new Font("SansSerif", Font.BOLD, 17));
		senhalabel.setBounds(235,190,150,30);
		senha.setBounds(235,220,385,35);
		frame.add(senha);
		frame.add(senhalabel);

		cadastrar = new JButton("Cadastre-se");
		cadastrar.setBackground(new Color(0, 130, 156));
		cadastrar.setForeground(new Color(255, 255, 255));
		cadastrar.setFont(new Font("SansSerif",1, 16));
		cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/users.png")));
		cadastrar.setHorizontalTextPosition(JButton.LEFT);
		cadastrar.setFocusPainted(false);
		cadastrar.setBounds(245,275,200,50);
		frame.add(cadastrar);

		//Butão de Logar
		entrar = new JButton("Entrar");
		entrar.setBackground(new Color(0, 130, 156));
		entrar.setForeground(new Color(255, 255, 255));
		entrar.setFont(new Font("SansSerif",1, 16));
		entrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/login.png")));
		entrar.setHorizontalTextPosition(JButton.LEFT);
		entrar.setFocusPainted(false);

		entrar.setBounds(450,275,156,50);
		frame.add(entrar);
	}


	private void defineEvents(){

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
							telaInicial = new MenuPrincipal();
							frame.dispose();
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