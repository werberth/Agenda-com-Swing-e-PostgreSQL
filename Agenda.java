import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Agenda {
	private JFrame frame;
	private static Agenda tela;
	private JMenuBar menuBar;
	private JMenu opcoes;
	private JMenuItem sobre;
	private JMenuItem exit;


	public Agenda(){
		montaTela();
	}

	private void montaTela(){
		frame = new JFrame("Atividades");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0, 174, 176));
		initComponents();
		defineEvents();

		frame.pack();
		frame.setBounds(200,60,900,650);
		frame.setVisible(true);
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
	}

	public static void main(String[] args){
		tela = new Agenda();
	}
}