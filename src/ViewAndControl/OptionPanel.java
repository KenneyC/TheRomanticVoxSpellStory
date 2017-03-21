package ViewAndControl;

import javax.swing.JPanel;

import Models.FreePlayModel;
import Models.StoryQuizModel;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

/**
 * OptionPanel is the option window that the user can access during the quiz. The option panel allows the user to 
 * change the voice at run time, mute the music or look at the stats during the story mode.
 * @author kc
 *
 */
public class OptionPanel extends JDialog {
	
	private JPanel op = new JPanel();
	private StoryQuizModel _quiz;
	private FreePlayModel _Fquiz;
	private FreePlayQuizPanel _Fpanel;
	private StoryQuizPanel _panel;
	private String _voice;
	private OptionPanel option;
	private boolean _BGP;
	private StoryStatsPanel ssp;
	
	public OptionPanel(StoryQuizModel quiz, StoryQuizPanel panel) {
		_voice = "Default";
		_quiz = quiz;
		_BGP = true;
		getContentPane().setBackground(new Color(0, 51, 102));
		op.setBackground(new Color(0, 51, 102));
		option = this;
		_panel = panel;
		getContentPane().add(op);
		setSize(342,411);
		op.setSize(342, 411);
		setBackground(new Color(0, 51, 102));
		getContentPane().setLayout(null);
		op.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Default", "New Zealand"}));
		comboBox.setBounds(111, 98, 122, 25);
		op.add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(!comboBox.getSelectedItem().equals(_voice)){
					String currentLevel = comboBox.getSelectedItem().toString();
					String _svoice = comboBox.getSelectedItem().toString();
					if (_svoice.equals("New Zealand")) {
						_voice = "New Zealand";
					} else if (_svoice.equals("Default")) {
						_voice = "Default";
					}
				}

			}
		});
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setForeground(new Color(255, 255, 255));
		lblOptions.setFont(new Font("Purisa", Font.PLAIN, 20));
		lblOptions.setBounds(129, 6, 77, 36);
		op.add(lblOptions);
		
		JLabel lblChangeVoice = new JLabel("Change Voice:");
		lblChangeVoice.setFont(new Font("Purisa", Font.PLAIN, 12));
		lblChangeVoice.setForeground(new Color(255, 255, 255));
		lblChangeVoice.setBounds(129, 77, 91, 21);
		op.add(lblChangeVoice);
		
		JButton btnMute = new JButton("Mute Music");
		btnMute.setBounds(110, 163, 138, 27);
		op.add(btnMute);
		btnMute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (_BGP == true) {
					_panel.playBGMusic(false);
					_BGP = false;
					btnMute.setText("Unmute");
				}else {
					_BGP = true;
					_panel.playBGMusic(true);
					btnMute.setText("Mute Music");
				}
			}
		});
		
		JButton btnCurrentStats = new JButton("Statistics");
		btnCurrentStats.setBounds(111, 237, 138, 27);
		op.add(btnCurrentStats);
		btnCurrentStats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ssp = new StoryStatsPanel();
				ssp.setVisible(true);
			}
		});
		
		
		JButton btnQuit = new JButton("Ok");
		btnQuit.setBounds(108, 345, 98, 27);
		op.add(btnQuit);
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (_voice.equals("New Zealand")) {
					_quiz.setVoice("akl_nz_jdt_diphone");
					option.setVisible(false);
				} else if (_voice.equals("Default")) {
					_quiz.setVoice("kal_diphone");
					option.setVisible(false);
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(222, 345, 100, 27);
		op.add(btnCancel);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				option.setVisible(false);
			}
		});
		
		op.setVisible(true);
	}
	public OptionPanel(FreePlayModel quiz, FreePlayQuizPanel panel) {
		_voice = "Default";
		_Fquiz = quiz;
		_BGP = true;
		getContentPane().setBackground(new Color(0, 51, 102));
		op.setBackground(new Color(0, 51, 102));
		option = this;
		_Fpanel = panel;
		getContentPane().add(op);
		setSize(342,411);
		op.setSize(342, 411);
		setBackground(new Color(0, 51, 102));
		getContentPane().setLayout(null);
		op.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Default", "New Zealand"}));
		comboBox.setBounds(111, 98, 122, 25);
		op.add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(!comboBox.getSelectedItem().equals(_voice)){
					String currentLevel = comboBox.getSelectedItem().toString();
					String _svoice = comboBox.getSelectedItem().toString();
					if (_svoice.equals("New Zealand")) {
						_voice = "New Zealand";
					} else if (_svoice.equals("Default")) {
						_voice = "Default";
					}
				}

			}
		});
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setForeground(new Color(255, 255, 255));
		lblOptions.setFont(new Font("Purisa", Font.PLAIN, 20));
		lblOptions.setBounds(129, 6, 77, 36);
		op.add(lblOptions);
		
		JLabel lblChangeVoice = new JLabel("Change Voice:");
		lblChangeVoice.setFont(new Font("Purisa", Font.PLAIN, 12));
		lblChangeVoice.setForeground(new Color(255, 255, 255));
		lblChangeVoice.setBounds(129, 77, 91, 21);
		op.add(lblChangeVoice);
		
		JButton btnMute = new JButton("Mute Music");
		btnMute.setBounds(110, 163, 138, 27);
		op.add(btnMute);
		btnMute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (_BGP == true) {
					_Fpanel.playBGMusic(false);
					_BGP = false;
					btnMute.setText("Unmute");
				}else {
					_BGP = true;
					_Fpanel.playBGMusic(true);
					btnMute.setText("Mute Music");
				}
			}
		});
		
		JButton btnCurrentStats = new JButton("Statistics");
		btnCurrentStats.setBounds(111, 237, 138, 27);
		op.add(btnCurrentStats);
		btnCurrentStats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ssp = new StoryStatsPanel();
				ssp.setVisible(true);
			}
		});
		
		
		JButton btnQuit = new JButton("Ok");
		btnQuit.setBounds(108, 345, 98, 27);
		op.add(btnQuit);
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (_voice.equals("New Zealand")) {
					_Fquiz.setVoice("akl_nz_jdt_diphone");
					option.setVisible(false);
				} else if (_voice.equals("Default")) {
					_Fquiz.setVoice("kal_diphone");
					option.setVisible(false);
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(222, 345, 100, 27);
		op.add(btnCancel);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				option.setVisible(false);
			}
		});
		
		op.setVisible(true);
	}
}

	
