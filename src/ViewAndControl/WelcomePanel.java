package ViewAndControl;

import javax.swing.JPanel;

import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import java.awt.Image;

import javax.swing.border.MatteBorder;

/**
 * Welcome Panel is the first panel to be loaded. It displays a welcome.
 * Author: Kenney Chan Kcha582
 */
public class WelcomePanel extends JPanel {

	
	private AudioInputStream audio;
	private Clip clip;
	/**
	 * Create the panel.
	 */
	public WelcomePanel() {
		setSize(586, 615);
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		setLayout(new BorderLayout());
		ImageIcon img = new ImageIcon(this.getClass().getResource("/Resource/Title.png"));
		ImageIcon imageIcon = new ImageIcon(img.getImage().getScaledInstance(586, 615, Image.SCALE_SMOOTH));
		JLabel label = new JLabel("");
		label.setIcon(imageIcon);
		label.setVisible(true);
		label.setSize(586, 615);
		add(label,BorderLayout.CENTER);
	}
	
	/**
	 * This method controls whether if the background music is playing or stopping. 
	 * @param play true means to play background music. false means to stop playing.
	 */
	public void playSound(boolean menu) {
			if (menu == true) {
				try {
					audio = AudioSystem.getAudioInputStream(new File("Resource/In_Your_Arms.wav"));
					clip = AudioSystem.getClip();
					clip.open(audio);
					clip.loop(clip.LOOP_CONTINUOUSLY);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else  {
				clip.stop();
			}
		
	}
}


