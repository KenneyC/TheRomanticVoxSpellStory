package ViewAndControl;

import javax.swing.JPanel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Operators.StoryWord;
import Operators.VoxSpell;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;

/**
 * Displays the statistics corresponding to a statslist file and episode the user is playing at runtime.
 * Author: Kenney Chan Kcha582 
 */
public class StoryStatsPanel extends JDialog {

	private VoxSpell _vox;
	private File _wordStats;
	private JTable _statsTable;
	private String _oldValue;
	private JScrollPane _scrollPane;
	private JPanel _statsPanel;
	private JDialog _ssp;

	/**
	 * Create the panel.
	 */
	public StoryStatsPanel() {
		getContentPane().setBackground(new Color(0, 51, 102));
		_ssp = this;
		_statsPanel = new JPanel();
		getContentPane().add(_statsPanel);
		setBackground(new Color(0, 51, 102));
		setSize(647, 400);
		getContentPane().setLayout(null);

		_wordStats = new File("StoryStatsList");
		if(_wordStats.exists() && !_wordStats.isDirectory()){
		} else {
			try {
				_wordStats.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		_statsTable=createTable(1);

		JButton btnGotoMenu = new JButton("Go back");
		btnGotoMenu.setBackground(new Color(153, 204, 255));
		btnGotoMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				_ssp.setVisible(false);
			}
		});
		btnGotoMenu.setBounds(434, 336, 181, 36);
		getContentPane().add(btnGotoMenu);

		_scrollPane = new JScrollPane();
		//ADD THESE _scrollPane.setToolTipText("");
		_scrollPane.setBounds(34, 53, 581, 260);
		getContentPane().add(_scrollPane);



		_scrollPane.setViewportView(_statsTable);

		JLabel lblStatistics = new JLabel("Statistics");
		lblStatistics.setForeground(new Color(255, 255, 255));
		lblStatistics.setFont(new Font("Purisa", Font.PLAIN, 20));
		lblStatistics.setBounds(34, 12, 117, 22);
		getContentPane().add(lblStatistics);

		final JComboBox comboBox = new JComboBox();
		_oldValue = "Episode 1";
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(!comboBox.getSelectedItem().equals(_oldValue)){
					String currentLevel = comboBox.getSelectedItem().toString();
					_oldValue = comboBox.getSelectedItem().toString();

					String[] levelInfoArray = _oldValue.split(" ");
					int level = Integer.parseInt((levelInfoArray[1]));
					_statsTable = createTable(level);
					_scrollPane.setViewportView(_statsTable);

				}

			}
		});

		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Episode 1", "Episode 2", "Episode 3"}));
		comboBox.setBounds(384, 15, 231, 26);
		getContentPane().add(comboBox);



	}

	/**
	 * Creates a JTable of statistics according to the episode
	 * @param level the episode
	 * @return a JTable of the statistics 
	 */
	private JTable createTable(int level) {
		ArrayList<StoryWord> wordList = readInWordStats(level);
		DefaultTableModel model = new DefaultTableModel();
		_statsTable = new JTable(model);
		_statsTable.setSelectionBackground(new Color(255, 204, 0));
		_statsTable.setEnabled(false);
		model.addColumn("Name"); 
		model.addColumn("Correct");
		model.addColumn("Incorrect");
		for(StoryWord w : wordList){
			String[] rowArray = new String[4];
			rowArray[0]= w.getName();
			rowArray[1]= w.getCorrect();
			rowArray[2]= w.getIncorrect();
			model.addRow(rowArray);
		}
		_statsTable.setVisible(true);
		return _statsTable;
	}	

	/**
	 * This method reads the stats list text file to insert information of the current statistics according to
	 * the episode selected
	 * @param level episode selected.
	 * @return
	 */
	private ArrayList<StoryWord> readInWordStats(int level){
		
		ArrayList<StoryWord> wordList= new ArrayList<StoryWord>();
		try {


			FileReader fileReader = new FileReader("StoryStatsList");
			BufferedReader br = new BufferedReader(fileReader);

			String EPString = null; 
			while((EPString = br.readLine()) != null){
				if(EPString.equals("%EP"+level)){
					break;
				}
			}

			String wordEntry = null;
			while((wordEntry = br.readLine()) != null){
				String[] wordArray = wordEntry.split(" ");
				StoryWord word = null;
				if (!(wordEntry.equals("%EP" + level))){
					if (!(wordEntry.equals("%EP" + (level+1)))) {
						word = new StoryWord(wordArray[0], wordArray[1], wordArray[2], (level+""));
					}
				}
				if (wordEntry.equals("%EP" + (level+1))) {
					break;
				}
				wordList.add(word);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return wordList;
	}
}




