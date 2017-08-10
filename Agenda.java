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
	private Arquivo file;

	private JButton filtrarButton;

	private JLabel titulo, filtrarlabel, dialabel, meslabel, anolabel;
	private JComboBox <String> dia, mes, ano;
	private JCheckBox diaCheckbox, mesCheckbox, anoCheckbox;

	private JScrollPane scrollTable;
	private JTable table;

	private PreparedStatement statement;
	private ResultSet resultSet;
	DefaultTableModel tableModel;

	public Agenda(){
		montaTela();
		filtrarButton.doClick();
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

		filtrarButton = new JButton("Filtrar");
		filtrarButton.setForeground(new Color(255, 255, 255));
		filtrarButton.setBackground(new Color(0, 130, 156));
		filtrarButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		filtrarButton.setBounds(750, 35, 100,30);
		frame.add(filtrarButton);

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

		table = newTable();

		
		scrollTable = new JScrollPane();
		scrollTable.setBounds(50, 160, 820, 420);
		scrollTable.getViewport().setBackground(new Color(0, 130, 156));
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
		
		filtrarButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String day = dia.getSelectedItem().toString();
				String month = mes.getSelectedItem().toString();
				String year = ano.getSelectedItem().toString();
				String date = day + "/" + month + "/" + year;

					try {
						if(!bd.getConnection()){
							JOptionPane.showMessageDialog(null, "Falha na conexão, o sistem será fechado!");
							System.exit(0);
						}

						String usuario = file.lerArquivo();

						if((diaCheckbox.isSelected() || mesCheckbox.isSelected() || anoCheckbox.isSelected()) && checkDateIsValid(day, month, year)){
							if(diaCheckbox.isSelected() && !mesCheckbox.isSelected() && !anoCheckbox.isSelected()){
								String url = "SELECT * FROM atividade WHERE EXTRACT(DAY from data)=? AND (EXTRACT(YEAR from data) >= EXTRACT(YEAR from CURRENT_DATE)) AND usuario=?";
								statement = bd.connection.prepareStatement(url);
								statement.setDouble(1, Double.parseDouble(day));
								statement.setString(2, usuario);
							} else if(!diaCheckbox.isSelected() && mesCheckbox.isSelected() && !anoCheckbox.isSelected()){
								String url = "SELECT * FROM atividade WHERE EXTRACT(MONTH from data)=? AND (EXTRACT(YEAR from data) >= EXTRACT(YEAR from CURRENT_DATE)) AND usuario=?";
								statement = bd.connection.prepareStatement(url);
								statement.setDouble(1,  Double.parseDouble(month));
								statement.setString(2, usuario);
							} else if(!diaCheckbox.isSelected() && !mesCheckbox.isSelected() && anoCheckbox.isSelected()){
								String url = "SELECT * FROM atividade WHERE EXTRACT(YEAR from data)=? AND usuario=?";
								statement = bd.connection.prepareStatement(url);
								statement.setDouble(1,  Double.parseDouble(year));
								statement.setString(2, usuario);
							} else if(diaCheckbox.isSelected() && mesCheckbox.isSelected() && !anoCheckbox.isSelected()){
								String url = "SELECT * FROM atividade WHERE EXTRACT(DAY from data)=? AND EXTRACT(MONTH from data)=? AND (EXTRACT(YEAR from data) >= EXTRACT(YEAR from CURRENT_DATE)) AND usuario=?";
								statement = bd.connection.prepareStatement(url);
								statement.setDouble(1, Double.parseDouble(day));
								statement.setDouble(2,  Double.parseDouble(month));
								statement.setString(3, usuario);
							} else if(diaCheckbox.isSelected() && !mesCheckbox.isSelected() && anoCheckbox.isSelected()){
								String url = "SELECT * FROM atividade WHERE EXTRACT(DAY from data)=? AND EXTRACT(YEAR from data)=? AND usuario=?";
								statement = bd.connection.prepareStatement(url);
								statement.setDouble(1, Double.parseDouble(day));
								statement.setDouble(2,  Double.parseDouble(year));
								statement.setString(3, usuario);
							} else if(!diaCheckbox.isSelected() && mesCheckbox.isSelected() && anoCheckbox.isSelected()){
								String url = "SELECT * FROM atividade WHERE EXTRACT(MONTH from data) = ? AND EXTRACT(YEAR from data) = ? AND usuario=?";
								statement = bd.connection.prepareStatement(url);
								statement.setDouble(1, Double.parseDouble(month));
								statement.setDouble(2, Double.parseDouble(year));
								statement.setString(3, usuario);
							} else {
								String url = "SELECT * FROM atividade WHERE data = TO_DATE(?, 'DD/MM/YYYY') AND usuario=?";
								statement = bd.connection.prepareStatement(url);
								System.out.println(date);
								statement.setString(1, date);
								statement.setString(2, usuario);
							}
						} else {
							String url = "SELECT * FROM atividade WHERE data >= CURRENT_DATE AND usuario=?";
							statement = bd.connection.prepareStatement(url);
							statement.setString(1, usuario);
						} 

						
						resultSet = statement.executeQuery();

						table = newTable();
						DefaultTableModel dtm = (DefaultTableModel) table.getModel();

						if(resultSet.next()){
							do {
								try {
									String[] data = new String[3];
									for (int i=1; i <= 3; i++){
										data[i-1] = resultSet.getString(i);
										System.out.println(resultSet.getString(i));
									}
									dtm.addRow(data);
									System.out.println();
								} catch (SQLException erro){

								}
							} while(resultSet.next());
						} else {
							table = new JTable(tableModel);
						}

						scrollTable.setViewportView(table);


						statement.close();
						resultSet.close();
						bd.close();


					} catch(Exception erro) {
						JOptionPane.showMessageDialog(null, "Algo de errado aconteceu:\n " + erro.toString());
						System.out.println(erro.toString());
					}
		
			}

		});
	}


	private boolean checkDateIsValid(String day, String month, String year){
		String[] meses_invalidos = {"04", "06", "09", "11"};
		String[] february_invalid_days = {"29", "30", "31"};
		if((day == "31") && (Arrays.asList(meses_invalidos).contains(month))){
			JOptionPane.showMessageDialog(null, "Não existe dia 31 no mês " + month);
			return false;
		} else if(((day == "Selecione") && diaCheckbox.isSelected()) || ((month == "Selecione") && mesCheckbox.isSelected()) || ((year == "Selecione") && anoCheckbox.isSelected())){
			JOptionPane.showMessageDialog(null, "Algum campo de data não preenchido!\n Verifique e tente novamente!");
			return false;
		} else if(Arrays.asList(february_invalid_days).contains(day) && (month == "04")){
			if(!checkIsLeapYear(Integer.parseInt(year))){
				JOptionPane.showMessageDialog(null, "O mês de fevereiro não contém o dia " + day);
				return false;
			}
		}
		return true;
 	}

 	private JTable newTable(){
 		tableModel = new DefaultTableModel(new String[]{"Atividade", "Data", "Anotação"}, 0){ 
	      @Override
	      public boolean isCellEditable(int row, int col) 
	      { 
	             return false; 
	      } 
		};

		table = new JTable(tableModel);
		table.setBackground(new Color(0, 130, 156));
		table.setForeground(new Color(255, 255, 255));
		table.setFont(new Font("SansSerif", Font.BOLD, 12));

		table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int selectedRowIndex = table.getSelectedRow();

            String title_selected = model.getValueAt(selectedRowIndex, 0).toString();
            String date_selected = model.getValueAt(selectedRowIndex, 1).toString();
            String anotacao_selected = model.getValueAt(selectedRowIndex, 2).toString();

            JOptionPane.showMessageDialog(null, "Titulo: " + title_selected + "\nData: " + date_selected + "\nAnotação: " + anotacao_selected); 
            }
        });

		return table;
 	}

 	private boolean checkIsLeapYear(Integer year){
 		if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))){
			return true;
		}
		else{
			return false;
		}
 	}

	public static void main(String[] args){
		tela = new Agenda();
	}
}