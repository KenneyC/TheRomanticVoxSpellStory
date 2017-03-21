package ViewAndControl;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import Models.FreePlayModel;
import Operators.VoxSpell;
import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayerMode;

/**
 * FreePlayQuizPanel is the view and control of the the free play mode quiz panel. 
 * @author Kenney Chan kcha582
 *
 */
public class FreePlayQuizPanel extends JPanel{
	
	private JTextField textField;
	private JProgressBar progressBar = new JProgressBar();
	private JLabel lblNewLabel_1;
	private JLabel lblHint;
	private VoxSpell _vox;
	private AudioInputStream audio;
	private Clip clip;
	private EmbeddedMediaPlayer mediaPlayer;
	private MediaListPlayer medialistPlayer;
	private JButton btnRelisten;
	private JLabel label_1;
	private MediaList mediaList;
	private MediaList mediaList2;
	private OptionPanel op;
	private FreePlayModel _fpm;
	private FreePlayQuizPanel _fpqp;
	public FreePlayQuizPanel(VoxSpell vs, File file) {
		_vox= vs;
		_fpm = new FreePlayModel(_vox,this, file);
		_fpqp = this;
		setLayout(null);
		setSize(626,702);
		
		ImageIcon img = new ImageIcon(this.getClass().getResource("/Resource/backgroundF.png"));
		ImageIcon imageIcon = new ImageIcon(img.getImage().getScaledInstance(650, 365, Image.SCALE_SMOOTH));
		
		ImageIcon img2 = new ImageIcon(this.getClass().getResource("/Resource/heart.png"));
		ImageIcon imageIcon2 = new ImageIcon(img2.getImage().getScaledInstance(64, 60, Image.SCALE_SMOOTH));
		
		ImageIcon img3 = new ImageIcon(this.getClass().getResource("/Resource/correct.png"));
		new ImageIcon(img3.getImage().getScaledInstance(39, 40, Image.SCALE_SMOOTH));
		
		ImageIcon img4 = new ImageIcon(this.getClass().getResource("/Resource/relisten.png"));
		ImageIcon imageIcon4 = new ImageIcon(img4.getImage().getScaledInstance(39, 40, Image.SCALE_SMOOTH));
		

		ImageIcon img5 = new ImageIcon(this.getClass().getResource("/Resource/background2.png"));
		new ImageIcon(img5.getImage().getScaledInstance(650, 365, Image.SCALE_SMOOTH));
		
		ImageIcon img6 = new ImageIcon(this.getClass().getResource("/Resource/backround3.png"));
		new ImageIcon(img6.getImage().getScaledInstance(650, 365, Image.SCALE_SMOOTH));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 626, 339);
		panel.setVisible(true);
		add(panel);
		
		Canvas canvas = new Canvas();
	    canvas.setBounds(-5,0, 626, 339);
	    canvas.setVisible(true);
	     
		
	    MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
	    CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
	    mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
	    mediaPlayer.setVideoSurface(videoSurface);
	    
	    
	    medialistPlayer = mediaPlayerFactory.newMediaListPlayer();
	    medialistPlayer.setMediaPlayer(mediaPlayer);
		    
		panel.add(canvas);
		
		mediaList = mediaPlayerFactory.newMediaList();
	    mediaList2 = mediaPlayerFactory.newMediaList();
	    
	    
		textField = new JTextField();
		textField.setBounds(195, 499, 223, 42);
		add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){

					String textString =textField.getText().trim().toLowerCase();
					if (textString.equals("")) {
						JOptionPane.showMessageDialog(new JFrame(),
							    "The text field is empty!");
					} else {
						_fpm.compareWord(textString);
					}
					textField.setText("");
				}
			}
		});
		
		label_1 = new JLabel("");
		label_1.setBounds(525, 452, 47, 53);
		add(label_1);
		label_1.setIcon(imageIcon4);
		label_1.setVisible(true);
		label_1.setSize(47, 53);
		
		btnRelisten = new JButton("Re-listen");
		btnRelisten.setBounds(485, 507, 117, 25);
		add(btnRelisten);
		btnRelisten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				_fpm.repeatWord();
				btnRelisten.setVisible(false);
				label_1.setVisible(false);
			}
		});
		
		JLabel lblPleasePressEnter = new JLabel("Please press enter to input your attempt");
		lblPleasePressEnter.setFont(new Font("Purisa", Font.BOLD, 12));
		lblPleasePressEnter.setForeground(Color.WHITE);
		lblPleasePressEnter.setBounds(160, 553, 332, 15);
		add(lblPleasePressEnter);
		
		JLabel lblProgress = new JLabel("Life meter:");
		lblProgress.setFont(new Font("Purisa", Font.BOLD, 12));
		lblProgress.setForeground(Color.WHITE);
		lblProgress.setBounds(46, 599, 108, 15);
		add(lblProgress);
		
		progressBar.setForeground(new Color(255, 51, 51));
		progressBar.setBounds(147, 599, 332, 14);
		add(progressBar);
		progressBar.setValue(100);
		
		lblHint = new JLabel("Hint:");
		lblHint.setForeground(Color.WHITE);
		lblHint.setBounds(246, 633, 70, 15);
		add(lblHint);
		lblHint.setVisible(false);
		
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(310, 633, 123, 15);
		add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		
		JLabel lblMuscleheads = new JLabel();

		lblMuscleheads.setFont(new Font("Purisa", Font.BOLD, 12));
		lblMuscleheads.setForeground(Color.WHITE);
		lblMuscleheads.setBounds(46, 580, 117, 15);
		add(lblMuscleheads);
		
		JLabel lblNewLabel = new JLabel("hello");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(56, 568, 64, 60);
		add(lblNewLabel);
		lblNewLabel.setIcon(imageIcon2);
		lblNewLabel.setVisible(true);
		lblNewLabel.setSize(64, 60);
		playBGMusic(true);
		
		JLabel lblGetMuscleheadsLike = new JLabel("Spell as much correct words as you can!");
		
		lblGetMuscleheadsLike.setFont(new Font("Purisa", Font.BOLD, 12));
		lblGetMuscleheadsLike.setForeground(new Color(255, 255, 255));
		lblGetMuscleheadsLike.setBounds(148, 452, 344, 15);
		add(lblGetMuscleheadsLike);
		
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(485, 653, 117, 25);
		add(btnQuit);
		btnQuit.setVisible(true);
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int res=JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit? This might remove all your current progress!");

				if(res==JOptionPane.YES_OPTION){
					playBGMusic(false);
					_vox._welcome.playSound(true);
					_fpqp.setVisible(false);
					_vox.addMenu(_fpqp);
				}
			}
		});
		
		
		JButton btnOptions = new JButton("Options");
		op = new OptionPanel(_fpm, this);
		op.setVisible(false);
		btnOptions.setBounds(485, 368, 117, 25);
		add(btnOptions);
		btnOptions.setVisible(true);
		btnOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				op.setVisible(true);
			}
		});
		
		
		JLabel label = new JLabel("New label");
		label.setForeground(Color.WHITE);
		label.setBounds(-33, 337, 650, 365);
		add(label);
		label.setIcon(imageIcon);
		
		add(label);
		label.setVisible(true);
		label.setSize(586, 615);
		label.setBounds(0, 337, 626, 365);
		
	}
	
	/**
	 * This method reveals a hint for the user when a specific of incorrect spellings are made.
	 * @param hint the hint to be revealed as a string.
	 */
	public void giveHint(String hint) {
		lblHint.setVisible(true);
		lblNewLabel_1.setVisible(true);
		lblNewLabel_1.setText(hint);
		
	}
	
	/**
	 * Once the user has spelled correctly this hides the hint until it is needed again.
	 */
	public void hideHint() {
		lblHint.setVisible(false);
		lblNewLabel_1.setVisible(false);
	}
	
	/**
	 * When the user has incorrectly spelt a word, this method updates its life bar by decreasing.
	 * @param percent
	 */
	public void updateLifeBar(int percent) {
		progressBar.setValue(100 - percent);
	}
	
	/**
	 * Plays the short video of the character talking.
	 * @param sequence for open mouth or closed mouth
	 */
	public void playSequence(int sequence) {
		if (sequence == 1 ) {
			medialistPlayer.setMediaList(mediaList);
			mediaList.addMedia("Resource/incorrect.avi");
			medialistPlayer.setMode(MediaListPlayerMode.DEFAULT);
			medialistPlayer.playItem(0);
			playSequence(2);
		}
		if (sequence == 2) {
			medialistPlayer.setMediaList(mediaList2);
			mediaList2.addMedia("Resource/talk1.avi");
			medialistPlayer.setMode(MediaListPlayerMode.LOOP);
			medialistPlayer.setMediaPlayer(mediaPlayer);
			medialistPlayer.playItem(0);
		}
	}
	
	/**
	 * This method controls whether if the background music is playing or stopping. 
	 * @param play true means to play background music. false means to stop playing.
	 */
	public void playBGMusic(boolean play) {
		if (play == true) {
			try {
				audio = AudioSystem.getAudioInputStream(new File("Resource/The_Show_Must_Be_Go.wav"));
				clip = AudioSystem.getClip();
				clip.open(audio);
				FloatControl gainControl = 
					    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-17.0f);
				clip.loop(clip.LOOP_CONTINUOUSLY);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			clip.stop();
		}
	}
}
