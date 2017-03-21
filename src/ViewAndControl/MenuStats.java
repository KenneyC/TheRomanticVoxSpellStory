package ViewAndControl;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Operators.StoryWord;
import Operators.VoxSpell;

/**
 * Menu stats shows the statistics of the current progress of the user from the menu panel, it has a option box
 * to see stats of story or free play. Statistics for story has a extra combobox to select the episode's statistics.
 * @author kc
 *
 */
public class MenuStats extends JPanel {
	
	
	private VoxSpell _vox;
	private JTable _statsTable;
	private JScrollPane _scrollPane;
	private String _oldValue;
	private String _oldValueFP;
	private MenuStats _menustats;
	
	public MenuStats(VoxSpell vs) {
		
		_vox = vs;
		setLayout(null);
		_menustats = this;
		
		_statsTable=createTable(1);
		_scrollPane = new JScrollPane();
		//ADD THESE _scrollPane.setToolTipText("");
		_scrollPane.setBounds(67, 131, 453, 369);
		add(_scrollPane);
		_scrollPane.setViewportView(_statsTable);
		
		setSize(586, 615);
		
		ImageIcon bkg = new ImageIcon(this.getClass().getResource("/Resource/menuba.jpg"));
		ImageIcon bkgIcon = new ImageIcon(bkg.getImage().getScaledInstance(586, 615, Image.SCALE_SMOOTH));
		
		ImageIcon title = new ImageIcon(this.getClass().getResource("/Resource/statistics.png"));
		ImageIcon titleIcon = new ImageIcon(title.getImage().getScaledInstance(115, 50, Image.SCALE_SMOOTH));
		
		ImageIcon EP4 = new ImageIcon(this.getClass().getResource("/Resource/buttonB.png"));
		ImageIcon EP4Icon = new ImageIcon(EP4.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		
		JLabel lblTitle = new JLabel("");
		lblTitle.setBounds(239, 27, 200, 50);
		lblTitle.setIcon(titleIcon);
		lblTitle.setVisible(true);
		add(lblTitle);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setBounds(475, 521, 70, 15);
		add(lblBack);
		lblBack.setIcon(EP4Icon);
		lblBack.setVisible(true);
		lblBack.setSize(60, 60);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBack.setSize(70,70);
				lblBack.setVisible(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblBack.setSize(60, 60);
				lblBack.setVisible(true);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				vs.addMenu(_menustats);
			}
			
		});
		
		
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
		comboBox.setBounds(316, 84, 160, 24);
		add(comboBox);
		
		final JComboBox comboBoxFP = new JComboBox();
		_oldValueFP = "Story Statistics";
		comboBoxFP.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(!comboBoxFP.getSelectedItem().equals(_oldValueFP)){
					String currentLevel = comboBoxFP.getSelectedItem().toString();
					_oldValueFP = comboBoxFP.getSelectedItem().toString();
					comboBox.setVisible(false);
					String[] levelInfoArray = _oldValueFP.split(" ");
					_statsTable = createFPTable("fp");
					_scrollPane.setViewportView(_statsTable);
				}

			}
		});
		
		comboBoxFP.setBackground(new Color(255, 255, 255));
		comboBoxFP.setModel(new DefaultComboBoxModel(new String[] {"Story Statistics", "Free-play Statistics"}));
		comboBoxFP.setBounds(112, 84, 160, 24);
		add(comboBoxFP);
		
		
		
		
		JLabel label = new JLabel("");
		label.setSize(586, 615);
		label.setLocation(0, 0);
		label.setIcon(bkgIcon);
		label.setVisible(true);
		add(label);
		
		
	}
	
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
	
	
	private JTable createFPTable(String fp) {
		ArrayList<StoryWord> wordList = readInWordFPStats(fp);
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
	
	private ArrayList<StoryWord> readInWordFPStats(String fp){
		
		ArrayList<StoryWord> wordList= new ArrayList<StoryWord>();
		try {
			
			FileReader fileReader = new FileReader("FPStatsList");			
			BufferedReader br = new BufferedReader(fileReader);


			String wordEntry = null;
			while((wordEntry = br.readLine()) != null){
				String[] wordArray = wordEntry.split(" ");
				StoryWord word = null;
				word = new StoryWord(wordArray[0], wordArray[1], wordArray[2], 0+"");
				wordList.add(word);
				
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return wordList;
	}


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






