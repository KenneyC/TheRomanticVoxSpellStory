package ViewAndControl;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;

import Operators.VoxSpell;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;

/**
 * MoviePanel is the panel that plays the introduction movie before each episode and free play mode. There is a 
 * replay button that allows the user to rewatch the video, a back button to go back, and a go to quiz button 
 * that goes to the quiz mode straight after the video. It must be pressed in order to proceed with the game.
 * @author Kenney Chan
 *
 */
public class MoviePanel extends JPanel {
	
	
	private VoxSpell _voxSpell;
	private MoviePanel _moviePanel;
	private EmbeddedMediaPlayer mediaPlayer;
	private JButton btnGoBackmenu;
	private int _intro;
	private JButton btnNewButton;

	
	public MoviePanel(VoxSpell voxspell, int intro, int seq, File file) {
		_voxSpell = voxspell;
		_moviePanel = this;
		_intro = intro;
		this.setVisible(true);
		setBackground(new Color(0,0,0));
		
		setLayout(null);

		setSize(647, 400);
		
	    Canvas canvas = new Canvas();
	    canvas.setBounds(10,0, 626, 339);
	    canvas.setVisible(true);
	    
	    
	    MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
	    CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
	    mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
	    mediaPlayerFactory.newMediaListPlayer();
	    mediaPlayer.setVideoSurface(videoSurface);
	   	
	    add(canvas);
	    
		btnGoBackmenu = new JButton("Go to quiz");
		btnGoBackmenu.setBounds(412, 363, 106, 25);
		if (intro == 4) {
			btnGoBackmenu.setVisible(false);
		}
		btnGoBackmenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				stopForGood();
				if (_intro !=5) {
					_voxSpell.addNewQuiz(_moviePanel,intro, seq);
				} else if (_intro == 5){
					_voxSpell.addFreeQuiz(_moviePanel,file);
				}
			}
		});
		add(btnGoBackmenu);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(530, 363, 106, 25);
		add(btnBack);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				stopForGood();
				_voxSpell._welcome.playSound(true);
				if (_intro == 5) {
					_voxSpell.addMenu(_moviePanel);
				} else {
				_voxSpell.addStoryChoicePanel(_moviePanel);
				}
				btnBack.setText("Go to Menu");
			}
		});
		
		btnNewButton = new JButton("Replay");
		btnNewButton.setBounds(289, 363, 106, 25);
		add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				stop();
				playNewIntro(_intro);
			}
		});
	}

	/**
	 * Stop for good completely stops to media player and removes the video
	 */
	public void stopForGood() {
		mediaPlayer.stop();
		mediaPlayer.release();
	}
	
	/**
	 * Play new intro plays the intro video according to the episode, or if it is free play mode.
	 * @param intro Indicates which video to play.
	 */
	public void playNewIntro(int intro) {
		_intro = intro;
		if (_intro == 1) {
			mediaPlayer.playMedia("Resource/206Ass4.avi");
		}else if (_intro == 2) {
			mediaPlayer.playMedia("Resource/206Ass4st2.avi");
		} else if (intro == 3) {
			mediaPlayer.playMedia("Resource/206Ass4st3.avi");
		} else if (intro == 4) {
			mediaPlayer.playMedia("Resource/theend.avi");
		} else if (intro == 5) {
			mediaPlayer.playMedia("Resource/freeplay.avi");
		}
	}
	
	/**
	 * Play new fail plays the fail video according to the episode or if it is free play mode.
	 * @param intro Indicates which video to play.
	 */
	public void playNewFail(int intro) {
		if (intro == 1) {
			mediaPlayer.playMedia("Resource/failed.avi");	
			btnGoBackmenu.setText("Retry");
		} else if (intro == 2) {
			mediaPlayer.playMedia("Resource/MFaile.avi");	
			btnGoBackmenu.setText("Retry");
		} if (intro == 5) {
			mediaPlayer.playMedia("Resource/gameover.avi");
			btnGoBackmenu.setVisible(false);
			btnNewButton.setVisible(false);
		}
	}
	
	/**
	 * Stops the video playing.
	 */
	private void stop(){
		mediaPlayer.stop();
	}
	
}
