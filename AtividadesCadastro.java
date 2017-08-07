import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AtividadesCadastro {
	private JFrame frame;
	private static AtividadesCadastro tela;
	private JMenuBar menuBar;
	private JMenu opcoes;
	private JMenuItem sobre;
	private JMenuItem exit;

	private JButton salvar;
	private JLabel titulolabel, anotacaolabel, datalabel, atividadelabel;
	private JTextField  titulo, data;
	private JTextArea anotacao;
	private JScrollPane anotacaoScroll;


	public AtividadesCadastro(){
		montaTela();
	}

	private void montaTela(){
		frame = new JFrame("Agendar Tarefa");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0, 174, 176));
		initComponents();
		defineEvents();

		frame.pack();
		frame.setBounds(280,60,740,650);
		frame.setVisible(true);
	}

	private void initComponents(){
		//Declarações da barra de menu
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

		//Titulo da tela
		atividadelabel = new JLabel("Agendar Tarefa");
		atividadelabel.setForeground(new Color(255, 255, 255));
		atividadelabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		atividadelabel.setBounds(100, 40, 550, 50);
		frame.add(atividadelabel);

		//Titulo da tarefa 
		titulolabel = new JLabel("Titulo da Tarefa");
		titulolabel.setForeground(new Color(255, 255, 255));
		titulo = new JTextField();
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setBackground(new Color(0, 130, 156));
		titulolabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		titulo.setFont(new Font("SansSerif", Font.BOLD, 15));
		titulolabel.setBounds(100,110,200,30);
		titulo.setBounds(100,140,550,30);
		frame.add(titulolabel);
		frame.add(titulo);

		// Campo de texto Data 
		datalabel = new JLabel("Data (DD/MM/YYYY)");
		datalabel.setForeground(new Color(255, 255, 255));
		data = new JTextField();
		data.setForeground(new Color(255, 255, 255));
		data.setBackground(new Color(0, 130, 156));
		datalabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		data.setFont(new Font("SansSerif", Font.BOLD, 15));
		datalabel.setBounds(100,180,200,30);
		data.setBounds(100,210,550,30);
		frame.add(datalabel);
		frame.add(data);

		// Campo de texto Anotacao 
		anotacaolabel = new JLabel("Anotações");
		anotacaolabel.setForeground(new Color(255, 255, 255));
		anotacao = new JTextArea();
		anotacao.setForeground(new Color(255, 255, 255));
		anotacao.setBackground(new Color(0, 130, 156));
		anotacaolabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		anotacao.setFont(new Font("SansSerif", Font.BOLD, 13));
		anotacaoScroll = new JScrollPane(anotacao);
		anotacaolabel.setBounds(100,240,200,30);
		anotacaoScroll.setBounds(100,270,550,250);
		frame.add(anotacaolabel);
		frame.add(anotacaoScroll);

		//Botão de Salvar 
		salvar = new JButton("Salvar");
		salvar.setFont(new Font("SansSerif", Font.BOLD, 15));
		salvar.setBackground(new Color(0, 130, 156));
		salvar.setForeground(new Color(255, 255, 255));
		salvar.setFont(new Font("SansSerif",1, 17));
		salvar.setBounds(550,530,100,40);
		frame.add(salvar);

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
		tela = new AtividadesCadastro();
	}
}