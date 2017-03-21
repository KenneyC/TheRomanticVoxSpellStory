package ViewAndControl;
import javax.swing.JPanel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import javax.swing.JTextField;

import Models.StoryQuizModel;
import Operators.VoxSpell;
import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;

import javax.swing.JProgressBar;
import java.awt.Font;

/**
 * Story Quiz panel is the main UI panel that interacts with the user. This panel includes a video panel, progress bars,
 * replay button, option button and quit button.
 * @author Kenney Chan Kcha582
 *
 */
public class StoryQuizPanel extends JPanel {
	
	private JTextField textField;
	private JProgressBar progressBar = new JProgressBar();
	private JProgressBar progressBar_1 = new JProgressBar();
	private VoxSpell _vox;
	private StoryQuizModel quiz;
	private StoryQuizPanel _panel;
	private AudioInputStream audio;
	private Clip clip;
	private EmbeddedMediaPlayer mediaPlayer;
	private MediaListPlayer medialistPlayer;
	private JButton btnRelisten;
	private JLabel label_1;
	private MediaList mediaList;
	private MediaList mediaList2;
	private MediaList mediaList3;
	private MediaList mediaList4;
	private MediaList mediaList5;
	private MediaList mediaList6;
	private MediaList mediaList7;
	private MediaList mediaList8;
	private OptionPanel op;
	
	
	/**
	 * Create the panel.
	 */
	public StoryQuizPanel(VoxSpell vox, int ep) {
		_vox = vox;
		quiz = new StoryQuizModel(this, _vox, ep);
		_panel = this;
		Executors.newFixedThreadPool(1);
		setLayout(null);
		setSize(626,702);
		
		
		ImageIcon img = new ImageIcon(this.getClass().getResource("/Resource/background.png"));
		ImageIcon imageIcon = new ImageIcon(img.getImage().getScaledInstance(650, 365, Image.SCALE_SMOOTH));
		
		ImageIcon img2 = new ImageIcon(this.getClass().getResource("/Resource/heart.png"));
		ImageIcon imageIcon2 = new ImageIcon(img2.getImage().getScaledInstance(64, 60, Image.SCALE_SMOOTH));
		
		ImageIcon img3 = new ImageIcon(this.getClass().getResource("/Resource/correct.png"));
		ImageIcon imageIcon3 = new ImageIcon(img3.getImage().getScaledInstance(39, 40, Image.SCALE_SMOOTH));
		
		ImageIcon img4 = new ImageIcon(this.getClass().getResource("/Resource/relisten.png"));
		ImageIcon imageIcon4 = new ImageIcon(img4.getImage().getScaledInstance(39, 40, Image.SCALE_SMOOTH));
		

		ImageIcon img5 = new ImageIcon(this.getClass().getResource("/Resource/background2.png"));
		ImageIcon imageIcon5 = new ImageIcon(img5.getImage().getScaledInstance(650, 365, Image.SCALE_SMOOTH));
		
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
	    
	    
	    mediaList3 = mediaPlayerFactory.newMediaList();
	    
	    mediaList4 = mediaPlayerFactory.newMediaList();
	    
	    mediaList5 =  mediaPlayerFactory.newMediaList();
	    
	    mediaList6 = mediaPlayerFactory.newMediaList();
	    
	    mediaList7 = mediaPlayerFactory.newMediaList();
	    
	    mediaList8 = mediaPlayerFactory.newMediaList();
	    
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
						quiz.compareWord(textString);
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
				quiz.repeatWord();
				btnRelisten.setVisible(false);
				label_1.setVisible(false);
			}
		});
		
		JLabel lblPleasePressEnter = new JLabel("Please press enter to input your attempt");
		lblPleasePressEnter.setFont(new Font("Purisa", Font.BOLD, 12));
		lblPleasePressEnter.setForeground(Color.WHITE);
		lblPleasePressEnter.setBounds(160, 553, 332, 15);
		add(lblPleasePressEnter);
		
		JLabel lblProgress = new JLabel("Like meter:");
		lblProgress.setFont(new Font("Purisa", Font.BOLD, 12));
		lblProgress.setForeground(Color.WHITE);
		lblProgress.setBounds(46, 599, 108, 15);
		add(lblProgress);
		
		progressBar.setForeground(new Color(255, 51, 51));
		progressBar.setBounds(147, 599, 332, 14);
		add(progressBar);
		if (ep == 1) {
			progressBar.setMaximum(100);
		} else if (ep == 2) {
			progressBar.setMaximum(90);
		} else if (ep == 3) {
			progressBar.setMaximum(110);
		}
		
		progressBar_1.setForeground(new Color(0, 102, 153));
		progressBar_1.setBounds(238, 639, 148, 14);
		add(progressBar_1);
		progressBar_1.setMaximum(3);
		progressBar_1.setValue(3);
		if (ep == 3) {
			progressBar_1.setVisible(false);
		}
		
		JLabel lblMuscleheads = new JLabel();
		if (ep == 1) {
			lblMuscleheads.setText("Musclehead's");
		} else if (ep == 2) {
			lblMuscleheads.setText("Sweety's");
		} else if (ep == 3) {
			lblMuscleheads.setText("Couple's");
		}
		lblMuscleheads.setFont(new Font("Purisa", Font.BOLD, 12));
		lblMuscleheads.setForeground(Color.WHITE);
		lblMuscleheads.setBounds(46, 580, 117, 15);
		add(lblMuscleheads);
		
		JLabel lblAttempts = new JLabel("Attempts:");
		lblAttempts.setFont(new Font("Purisa", Font.BOLD, 12));
		lblAttempts.setForeground(new Color(255, 255, 255));
		lblAttempts.setBounds(150, 639, 70, 15);
		add(lblAttempts);
		if (ep == 3) {
			lblAttempts.setVisible(false);
		}
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(56, 568, 64, 60);
		add(lblNewLabel);
		lblNewLabel.setIcon(imageIcon2);
		lblNewLabel.setVisible(true);
		lblNewLabel.setSize(64, 60);
		playBGMusic(true);
		
		JLabel lblGetMuscleheadsLike = new JLabel();
		if (ep == 1) {
			lblGetMuscleheadsLike.setText("Get Musclehead's like meter to 100%!");
		} else if (ep == 2) {
			lblGetMuscleheadsLike.setText("Get Sweety's like meter to 100%");
		} else if (ep == 3) {
			lblGetMuscleheadsLike.setText("Get the Couple's love meter to 100%!");
		}
		
		lblGetMuscleheadsLike.setFont(new Font("Purisa", Font.BOLD, 12));
		lblGetMuscleheadsLike.setForeground(new Color(255, 255, 255));
		lblGetMuscleheadsLike.setBounds(148, 452, 344, 15);
		add(lblGetMuscleheadsLike);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(160, 625, 39, 40);
		add(lblNewLabel_1);
		lblNewLabel_1.setIcon(imageIcon3);
		lblNewLabel_1.setVisible(true);
		lblNewLabel_1.setSize(39, 40);
		if ( ep == 3) {
			lblNewLabel_1.setVisible(false);
		}
		
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
					_panel.setVisible(false);
					vox.addStoryChoicePanel(panel);
				}
			}
		});
		
		
		JButton btnOptions = new JButton("Options");
		op = new OptionPanel(quiz,this);
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
		System.out.println(ep);
		if (ep == 1) {
			label.setIcon(imageIcon);
		}
		if (ep == 2) {
			label.setIcon(imageIcon);
		} 
		if (ep == 3 ) {
			label.setIcon(imageIcon5);
		}
		add(label);
		label.setVisible(true);
		label.setSize(586, 615);
		label.setBounds(0, 337, 626, 365);
		
	}
	
	/**
	 * This method updates the like bar (or progress bar) to allow the user to see their progress in episode 1.
	 * @param percent percent that the progress bar needs to be updated to.
	 */
	public void updateLikeBar(int percent){
		btnRelisten.setVisible(true);
		label_1.setVisible(true);
		progressBar.setVisible(true);
		progressBar.setValue(percent);
		progressBar.setStringPainted(true);
		if (percent > 0 && percent < 20) {
			progressBar.setString("Um.. who are you?");
		} else if (percent > 20 && percent < 40) {
			progressBar.setString("hmmmm... ok");
		} else if (percent > 40 && percent < 60 ) {
			progressBar.setString("She's pretty nice..");
		} else if (percent > 60 && percent < 80) {
			progressBar.setString("She's a cool person, I like her!");
		} else if (percent > 80) {
			progressBar.setString("*Blushes*");
		}
		progressBar.repaint();
	}
	
	/**
	 * This method updates the like bar (or progress bar) to allow the user to see their progress in episode 2.
	 * @param percent percent that the progress bar needs to be updated to.
	 */
	public void updateLikeBarEP2(int percent){
		btnRelisten.setVisible(true);
		label_1.setVisible(true);
		progressBar.setVisible(true);
		progressBar.setValue(percent);
		progressBar.setStringPainted(true);
		if (percent > 0 && percent < 20) {
			progressBar.setString("Oh! he's talking to me");
		} else if (percent > 20 && percent < 40) {
			progressBar.setString("He's pretty nice");
		} else if (percent > 40 && percent < 60 ) {
			progressBar.setString("Damn, look at those abs");
		} else if (percent > 60 && percent < 80) {
			progressBar.setString("Am I..... in Love?");
		} else if (percent > 80) {
			progressBar.setString("*Blushes*");
		}
		progressBar.repaint();
	}
	
	/**
	 * This method updates the like bar (or progress bar) to allow the user to see their progress in episode 3.
	 * @param percent percent that the progress bar needs to be updated to.
	 */
	public void updateLikeBarEP3(int percent){
		btnRelisten.setVisible(true);
		label_1.setVisible(true);
		progressBar.setVisible(true);
		progressBar.setValue(percent);
		progressBar.setStringPainted(true);
		if (percent > 0 && percent < 30) {
			progressBar.setString("Wow, he is sweaty..");
		} else if (percent > 30 && percent < 50) {
			progressBar.setString("Why is he..stuttering");
		} else if (percent > 50 && percent < 70 ) {
			progressBar.setString("Oh! He's better now");
		} else if (percent > 70 && percent < 90) {
			progressBar.setString("Here we go!");
		} else if (percent > 90) {
			progressBar.setString("*Blushes*");
		}
		progressBar.repaint();
	}
	
	/**
	 * This method updates the attempt bar that allows the user to see how many attempts they still have 
	 * left before they lose the game.
	 * @param percent percent that the progress bar needs to be updated to.
	 */
	public void updateAttemptBar(int percent) {
		progressBar_1.setVisible(true);
		progressBar_1.setValue(percent);
		progressBar_1.setStringPainted(true);
		progressBar_1.setString(""+percent+"/3.");
		progressBar_1.repaint();
	} 
	
	/**
	 * This method controls whether if the background music is playing or stopping. 
	 * @param play true means to play background music. false means to stop playing.
	 */
	public void playBGMusic(boolean play) {
		if (play == true) {
			try {
				audio = AudioSystem.getAudioInputStream(new File("Resource/BGM1.wav"));
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
	
	/**
	 * Plays the short video of the character talking according to the number of words spelt.
	 * @param sequence which video to play at specific words spelled.
	 */
	public void playSequence(int sequence) {
		if (sequence < 6) {
			medialistPlayer.setMediaList(mediaList);
			if (sequence == 1) {
				mediaList.addMedia("Resource/Hello.avi");
				medialistPlayer.playItem(0);
				quiz.speakWord(2);
			}else if (sequence == 2) {
				mediaList.addMedia("Resource/Name.avi");
				medialistPlayer.playItem(1);
			}else if (sequence == 3) { 
				mediaList.addMedia("Resource/Sweety.avi");
				medialistPlayer.playItem(2);
			} else if (sequence == 4) {
				mediaList.addMedia("Resource/very.avi");
				medialistPlayer.playItem(3);
			} else if (sequence == 5) {
				mediaList.addMedia("Resource/talk.avi");
				medialistPlayer.playItem(4);
			}
		} 
		if(sequence >5 && sequence < 9) {
			medialistPlayer.setMediaList(mediaList4);
			if (sequence == 6) {
				mediaList4.addMedia("Resource/thankyou.avi");
				medialistPlayer.playItem(0);
				quiz.speakWord(2);
			} else if (sequence == 7) {
				mediaList4.addMedia("Resource/be.avi");
				medialistPlayer.playItem(1);
			} else if (sequence == 8) {
				mediaList4.addMedia("Resource/to.avi");
				medialistPlayer.playItem(2);
			}
		}
		 if (sequence == 9) {
			medialistPlayer.setMediaList(mediaList2);
			mediaList2.addMedia("Resource/andM.avi");
		    mediaList2.addMedia("Resource/you.avi");
		    mediaList2.addMedia("Resource/are.avi");
			medialistPlayer.playItem(0);
		} if (sequence == 10) {
			medialistPlayer.setMediaList(mediaList3);
			mediaList3.addMedia("Resource/be.avi");
		    mediaList3.addMedia("Resource/my.avi");
			medialistPlayer.playItem(0);
		} if (sequence > 10) {
			medialistPlayer.setMediaList(mediaList5);
			if (sequence == 11) {
				mediaList5.addMedia("Resource/willyou.avi");
				medialistPlayer.playItem(0);
				quiz.speakWord(2);
			}if (sequence == 12) {
				mediaList5.addMedia("Resource/tolove.avi");
				medialistPlayer.playItem(1);
			}if (sequence == 13) {
				mediaList5.addMedia("Resource/and.avi");
				medialistPlayer.playItem(2);
			} if (sequence == 14) {
				mediaList5.addMedia("Resource/heflex.avi");
				mediaList5.addMedia("Resource/willyou2.avi");
				mediaList5.addMedia("Resource/promiseto.avi");
				medialistPlayer.playItem(3);
			}
			if (sequence > 14) {
				medialistPlayer.setMediaList(mediaList6);
				if (sequence == 15){
					mediaList6.addMedia("Resource/SweetyF.avi");
					medialistPlayer.playItem(0);
				} if (sequence == 16) {
					mediaList6.addMedia("Resource/withorwithout.avi");
					medialistPlayer.playItem(1);
				}  if (sequence == 17) {
					mediaList6.addMedia("Resource/ido1.avi");
					medialistPlayer.playItem(2);
				}  if (sequence == 18) {
					medialistPlayer.setMediaList(mediaList7);
					mediaList7.addMedia("Resource/ido2.avi");
					mediaList7.addMedia("Resource/youtwo.avi");
					medialistPlayer.playItem(0);
				}if (sequence == 19){
					medialistPlayer.setMediaList(mediaList8);
					mediaList8.addMedia("Resource/ringsand.avi");
					medialistPlayer.playItem(0);
				}
			}
		}
	}
	
	/**
	 * If the user has failed to progress with the quiz they are taken to a game over video by this method.
	 * @param ep the fail video to play according to the episode.
	 */
	public void failed(int ep) {
		clip.stop();
		_vox.addFailPanel(this, ep);
	}
}
