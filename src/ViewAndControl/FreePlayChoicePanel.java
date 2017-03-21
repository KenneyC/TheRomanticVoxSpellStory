package ViewAndControl;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Operators.StoryWord;
import Operators.VoxSpell;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * FreePlayStatsPanel is the panel the user uses to select a spelling words list before the game. It also 
 * has instructions to explain how free play works.
 * @author kc
 *
 */
public class FreePlayChoicePanel extends JPanel {
	
	private VoxSpell _vox;
	private JTable _statsTable;
	private JScrollPane _scrollPane;
	private String _oldValue;
	private String _oldValueFP;
	private FreePlayChoicePanel _FPSP;
	private File _selectedFile;
	
	
	public FreePlayChoicePanel(VoxSpell vs) {
		
		_vox = vs;
		setLayout(null);
		_FPSP = this;
		
		setSize(586, 615);
		
		
		ImageIcon bkg = new ImageIcon(this.getClass().getResource("/Resource/freeplayB.png"));
		ImageIcon bkgIcon = new ImageIcon(bkg.getImage().getScaledInstance(586, 615, Image.SCALE_SMOOTH));
		
		
		ImageIcon EP4 = new ImageIcon(this.getClass().getResource("/Resource/buttonB.png"));
		ImageIcon EP4Icon = new ImageIcon(EP4.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		
		ImageIcon EP5 = new ImageIcon(this.getClass().getResource("/Resource/buttonPL.png"));
		ImageIcon EP5Icon = new ImageIcon(EP5.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		
		JFileChooser fileChooser = new JFileChooser(".");
		add(fileChooser);
		
		JLabel label = new JLabel("");
		label.setSize(586, 615);
		label.setLocation(0, 0);
		label.setIcon(bkgIcon);
		label.setVisible(true);
		
		JButton btnSelectList = new JButton("Select List");
		btnSelectList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSelectList.setBounds(241, 290, 117, 25);
		add(btnSelectList);
		btnSelectList.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				int status = fileChooser.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
				      _selectedFile = fileChooser.getSelectedFile();
				    } else if (status == JFileChooser.CANCEL_OPTION) {
				    }
			}
			
		});
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setBounds(32, 521, 70, 15);
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
				_vox.addMenu(_FPSP);
			}
			
		});
		
		JLabel lblPlay = new JLabel("play");
		lblPlay.setBounds(498, 521, 70, 15);
		add(lblPlay);
		lblPlay.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lblPlay.setSize(70,70);
				lblPlay.setVisible(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblPlay.setSize(60, 60);
				lblPlay.setVisible(true);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				 if (_selectedFile == null) {
					 JOptionPane.showMessageDialog(new JFrame(),
							    "No Spelling list was selected!");
				 } else {
					 _vox._welcome.playSound(false);
					 _vox.newFreePlayMovie(_FPSP, _selectedFile);
				 }
			}
			
		});
		lblPlay.setIcon(EP5Icon);
		lblPlay.setVisible(true);
		lblPlay.setSize(60, 60);
		add(label);
		
		
		
	}
}