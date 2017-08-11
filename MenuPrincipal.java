import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuPrincipal {
	private JFrame frame;
	private static MenuPrincipal janela;
	private static AtividadesCadastro telaCadastro;
	private static Agenda telaAgenda;
	private MenuBarClass bar;
	private JMenuBar menuBar;

	private JButton pesquisarAtividadeButton, cadastrarAtividadeButton;

	public MenuPrincipal(){
		montaTela();
	}

	private void montaTela(){
		frame = new JFrame("Menu Principal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0, 174, 176));
		frame.setLayout(null);

		initComponents();
		defineEvents();

		frame.pack();
		frame.setBounds(300,100,800,600);
		frame.setVisible(true);
	}


	private void initComponents(){
		// Adicionando componentes da Barra de menu
		bar = new MenuBarClass();
		menuBar = bar.newMenuBar();
		frame.setJMenuBar(menuBar);;
		//Jbuttons do Menu

		pesquisarAtividadeButton = new JButton(" Pesquisar Atividades");
		pesquisarAtividadeButton.setBackground(new Color(0, 130, 156));
		pesquisarAtividadeButton.setForeground(new Color(255, 255, 255));
		pesquisarAtividadeButton.setFont(new Font("SansSerif",1, 35));
		pesquisarAtividadeButton.setBounds(100, 80, 600, 150);
		pesquisarAtividadeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png")));
		pesquisarAtividadeButton.setFocusPainted(false);
		frame.add(pesquisarAtividadeButton);

		cadastrarAtividadeButton = new JButton(" Registrar Atividades");
		cadastrarAtividadeButton.setBackground(new Color(0, 130, 156));
		cadastrarAtividadeButton.setForeground(new Color(255, 255, 255));
		cadastrarAtividadeButton.setFont(new Font("SansSerif",1, 35));
		cadastrarAtividadeButton.setBounds(100, 270, 600, 150);
		cadastrarAtividadeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/atividade.png")));
		cadastrarAtividadeButton.setFocusPainted(false);

		frame.add(cadastrarAtividadeButton);

	}

	private void defineEvents(){
		pesquisarAtividadeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				telaAgenda = new Agenda(); 
			}
		});

		cadastrarAtividadeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				telaCadastro = new AtividadesCadastro(); 
			}
		});
	}

	public static void main(String[] args){
		janela = new MenuPrincipal();
	}
}