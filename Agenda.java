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
		frame = new JFrame("Alô Mundo");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		defineEvents();

		frame.pack();
		frame.setBounds(0,0,400,400);
		frame.setVisible(true);
	}

	private void initComponents(){
		menuBar = new JMenuBar();
		opcoes = new JMenu("Opções");
		sobre = new JMenuItem("Sobre");
		exit = new JMenuItem("Exit");
		opcoes.add(sobre);
		opcoes.add(exit);
		menuBar.add(opcoes);
		frame.setJMenuBar(menuBar);
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