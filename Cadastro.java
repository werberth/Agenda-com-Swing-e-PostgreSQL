import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class Cadastro {
	private JFrame frame;
	private MenuBarClass bar;
	private JMenuBar menuBar;
	private BD bd;

	private JTextField username, confirmar, senha;
	private JLabel titulo, userlabel, senhalabel, confirmarlabel;
	private JButton salvar;

	private PreparedStatement statement;
	private ResultSet resultSet;	


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
		frame.setBounds(300,100,600,600);
		frame.setVisible(true);
		bd = new BD();
	}

	private void initComponents(){
		// Adicionando componentes da Barra de menu
		bar = new MenuBarClass();
		menuBar = bar.newMenuBar();
		frame.setJMenuBar(menuBar);;
		
		// Adicionando Campos de texto e respectivos labels
		//Titulo
		titulo = new JLabel("Cadastro");
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setFont(new Font("SansSerif", Font.BOLD, 40));
		titulo.setBounds(200, 40, 250, 50);
		frame.add(titulo);

		// Nome de Usuário
		userlabel = new JLabel("Nome de Usuário");
		userlabel.setForeground(new Color(255, 255, 255));
		username = new JTextField();
		username.setForeground(new Color(255, 255, 255));
		username.setBackground(new Color(0, 130, 156));
		username.setFont(new Font("SansSerif", Font.BOLD, 17));
		userlabel.setFont(new Font("SansSerif", Font.BOLD, 17));
		userlabel.setBounds(160,130,180,30);
		username.setBounds(160,160,300,30);
		frame.add(username);
		frame.add(userlabel);

		// Senha 
		senhalabel = new JLabel("Senha");
		senhalabel.setForeground(new Color(255, 255, 255));
		senha = new JPasswordField();
		senha.setBackground(new Color(0, 130, 156));
		senha.setForeground(new Color(255, 255, 255));
		senha.setFont(new Font("SansSerif", Font.BOLD, 17));
		senhalabel.setFont(new Font("SansSerif", Font.BOLD, 17));
		senhalabel.setBounds(160,200,150,30);
		senha.setBounds(160,230,300,30);
		frame.add(senha);
		frame.add(senhalabel);

		//confirmar senha
		confirmarlabel = new JLabel("Confirmar Senha");
		confirmarlabel.setForeground(new Color(255, 255, 255));
		confirmarlabel.setFont(new Font("SansSerif", Font.BOLD, 17));
		confirmar = new JPasswordField();
		confirmar.setBackground(new Color(0, 130, 156));
		confirmar.setForeground(new Color(255, 255, 255));
		confirmar.setFont(new Font("SansSerif", Font.BOLD, 15));
		confirmarlabel.setBounds(160,270,190,30);
		confirmar.setBounds(160,300,300,30);
		frame.add(confirmar);
		frame.add(confirmarlabel);



		//Butão de Logar
		salvar = new JButton("Salvar ");
		salvar.setBackground(new Color(0, 130, 156));
		salvar.setForeground(new Color(255, 255, 255));
		salvar.setFont(new Font("SansSerif",1, 18));
		salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("icons/save.png")));
		salvar.setHorizontalTextPosition(JButton.LEFT);
		salvar.setMargin(new Insets(0, 0, 0, 0));
		salvar.setBounds(235,340,145,50);
		frame.add(salvar);
	}

	private void limpaCampos(){
		username.setText("");
		senha.setText("");
		confirmar.setText("");
	}


	private void defineEvents(){
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
}