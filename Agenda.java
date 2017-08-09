import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.Arrays;

public class Agenda {
	private JFrame frame;
	private static Agenda tela;
	private JMenuBar menuBar;
	private JMenu opcoes;
	private JMenuItem sobre;
	private JMenuItem exit;
	private BD bd;

	private JButton pesquisarButton;

	private JLabel titulo, filtrarlabel, dialabel, meslabel, anolabel;
	private JComboBox <String> dia, mes, ano;
	private JCheckBox diaCheckbox, mesCheckbox, anoCheckbox;

	private JScrollPane scrollTable;
	private JTable table;

	private PreparedStatement statement;
	private ResultSet resultSet;

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
		frame.setBounds(200,60,900,660);
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
		frame.setJMenuBar(menuBar);;

		//Titulo
		titulo = new JLabel("Atividades");
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
		titulo.setBounds(50, 35, 200, 30);
		frame.add(titulo);

		//Barra de pesquisa
		filtrarlabel = new JLabel("Filtrar por: ");
		filtrarlabel.setForeground(new Color(255, 255, 255));
		filtrarlabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		filtrarlabel.setBounds(400, 35, 150, 30);
		frame.add(filtrarlabel);

		//Adicionando JCheckbox da barra de filtragem
		diaCheckbox = new JCheckBox("Dia");
		diaCheckbox.setForeground(new Color(255, 255, 255));
		diaCheckbox.setBackground(new Color(0, 174, 176));
		diaCheckbox.setFont(new Font("SansSerif", Font.BOLD, 15));
		diaCheckbox.setBounds(555, 35, 60, 30);
		frame.add(diaCheckbox);

		mesCheckbox = new JCheckBox("Mês");
		mesCheckbox.setForeground(new Color(255, 255, 255));
		mesCheckbox.setBackground(new Color(0, 174, 176));
		mesCheckbox.setFont(new Font("SansSerif", Font.BOLD, 15));
		mesCheckbox.setBounds(620, 35, 60, 30);
		frame.add(mesCheckbox);


		anoCheckbox = new JCheckBox("Ano");
		anoCheckbox.setForeground(new Color(255, 255, 255));
		anoCheckbox.setBackground(new Color(0, 174, 176));
		anoCheckbox.setFont(new Font("SansSerif", Font.BOLD, 15));
		anoCheckbox.setBounds(680, 35, 60, 30);
		frame.add(anoCheckbox);

		// JComboBox Dia
		String[] dia_string = { "Selecione","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", 
								"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
								"28", "29", "30", "31"};
		dia = new JComboBox<>(dia_string);
		dia.setBackground(new Color(0, 130, 156));
		dia.setForeground(new Color(255, 255, 255));
		dialabel = new JLabel("Dia: ");
		dialabel.setForeground(new Color(255, 255, 255));
		dialabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		dialabel.setBounds(400,80,80,30);
		dia.setBounds(445,80,100,30);
		frame.add(dialabel);
		frame.add(dia);

		//JComboBox Mes
		String[] mes_string = { "Selecione", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		mes = new JComboBox<>(mes_string);
		mes.setBackground(new Color(0, 130, 156));
		mes.setForeground(new Color(255, 255, 255));
		meslabel = new JLabel("Mes: ");
		meslabel.setForeground(new Color(255, 255, 255));
		meslabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		meslabel.setBounds(555,80,80,30);
		mes.setBounds(605,80,100,30);
		frame.add(mes);
		frame.add(meslabel);

		//JComboBox Ano
		String[] ano_string = { "Selecione", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", 
								"2026", "2027", "2028", "2029", "2030"};
		ano = new JComboBox<>(ano_string);
		ano.setBackground(new Color(0, 130, 156));
		ano.setForeground(new Color(255, 255, 255));
		anolabel = new JLabel("Ano: ");
		anolabel.setForeground(new Color(255, 255, 255));
		anolabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		anolabel.setBounds(715,80,80,30);
		ano.setBounds(760,80,100,30);
		frame.add(ano);
		frame.add(anolabel);

		DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Atividade", "Data", "Anotação"}, 0){};

		JTable table = new JTable(tableModel);
		scrollTable = new JScrollPane(table);
		table.setBackground(new Color(0, 130, 156));
		table.setForeground(new Color(255, 255, 255));
		table.setFont(new Font("SansSerif", Font.BOLD, 16));
		scrollTable.getViewport().setBackground(new Color(0, 130, 156));

		scrollTable.setBounds(50, 160, 820, 420);
		frame.add(scrollTable);

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