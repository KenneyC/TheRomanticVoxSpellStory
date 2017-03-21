package Operators;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ViewAndControl.FreePlayQuizPanel;
import ViewAndControl.FreePlayChoicePanel;
import ViewAndControl.MenuPanel;
import ViewAndControl.MenuStats;
import ViewAndControl.MoviePanel;
import ViewAndControl.StoryStatsPanel;
import ViewAndControl.StoryChoicePanel;
import ViewAndControl.StoryQuizPanel;
import ViewAndControl.WelcomePanel;


/**
 * Spelling teaching aid integrated with story telling. Has a free play mode which allows user to practice 
 * spelling with a twist of "gamification".
 * 
 * Author: Kenney Chan kcha582
 *
 */
public class VoxSpell {

	public static void main(String[] args) {
		new VoxSpell();

	}

	private MenuPanel _menu;
	private JFrame _frame;
	public static WelcomePanel _welcome;

	public VoxSpell(){

		Executors.newFixedThreadPool(1);
		_frame = new JFrame();
		_frame.setSize(586, 615);
		_frame.setResizable(false);
		_frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		_frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				int res=JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit? This could delete some data.");

				if(res==JOptionPane.YES_OPTION){
					_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				}
			}
		});
		_welcome = new WelcomePanel();
		_welcome.playSound(true);
		_welcome.setVisible(true);
		_frame.setVisible(true);
		_frame.add(_welcome);
		_frame.repaint();
		
		_menu = new MenuPanel(this);
		

		_frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					_frame.remove(_welcome);
					_frame.add(_menu);
					_frame.setSize(586, 615);
					_frame.repaint();
					_frame.removeKeyListener(this);
			}
		});
		
	}
	
	/**
	 * Disposes the main program frame when the method is called.
	 */
	public void Quit() {
		_frame.dispose();
	}
	
	/**
	 * When the user enters a new free play game, this method is called to play the introduction video.
	 * It creates a new MoviePanel and plays the Introduction for free play mode.
	 * @param old: used to remove the panel that called this method.
	 * @param file: spelling list that the user has selected for the free play mode.
	 */
	public void newFreePlayMovie(JPanel old, File file) {
		_frame.remove(old);
		_frame.setSize(647, 400);
		MoviePanel mp = new MoviePanel(this, 5,0,file);
		_frame.add(mp);
		mp.playNewIntro(5);
		_frame.repaint();
	}
	/**
	 * When the user selects story mode this method is called and the user is given a selection of episodes
	 * to play, by being taken to the story choice panel.
	 * @param old used to remove the panel that called this method.
	 */
	public void addStoryChoicePanel(JPanel old) {
		_frame.remove(old);
		_frame.setSize(586, 615);
		StoryChoicePanel scp = new StoryChoicePanel(this, _welcome);
		scp.setVisible(true);
		_frame.add(scp);
		_frame.repaint();
	}
	
	/**
	 * When the user finishes watching the introduction to free play mode and proceeds to the the free play 
	 * quiz, this is called to move to the quiz panel.
	 * @param old used to remove the panel that called this method.
	 * @param file the file the user has selected for free play mode.
	 */
	public void addFreeQuiz (JPanel old,File file) {
		_frame.remove(old);
		_frame.setSize(626,702);
		FreePlayQuizPanel fp = new FreePlayQuizPanel(this, file);
		fp.setVisible(true);
		_frame.add(fp);
		fp.playSequence(2);
		_frame.repaint();
	}
	
	/**
	 * When the user selects a episode to play, or enters free play mode, a movie panel is created to play the 
	 * movie according to the user's choice.
	 * @param old used to remove the panel that called this method
	 * @param ep the episode that the player selected, or has progressed to. Free play mode is also a episode
	 * @param seq after the introduction video, a video is played.
	 * @param file if the user selects free play mode, then this is the file the user selected for the game.
	 */
	public void addMovie(JPanel old, int ep, int seq,File file) {
		_frame.remove(old);
		_frame.setSize(647, 400);
		MoviePanel mp = new MoviePanel(this, ep,seq,file);
		_frame.add(mp);
		mp.playNewIntro(ep);
		_frame.repaint();
	}
	
	/**
	 * When the user selects free play mode from the menu, this method takes them to the free play menu to 
	 * select a spelling list.
	 * @param old used to remove the panel that called this method.
	 */
	public void addFreePlay(JPanel old) {
		_frame.remove(old);
		_frame.setSize(586, 615);
		FreePlayChoicePanel fpsp = new FreePlayChoicePanel(this);
		_frame.add(fpsp);
		_frame.repaint();
		
	}

	/**
	 * When the user selects a episode to play and finishes the introduction, this method takes them to the 
	 * quiz game panel. 
	 * @param old used to remove the panel that called this method.
	 * @param ep the episode that the user selected or has progressed to.
	 * @param seq which short video to play first.
	 */
	public void addNewQuiz(JPanel old, int ep, int seq) {
		_frame.remove(old);
		_frame.setSize(626,702);
		StoryQuizPanel sqp = new StoryQuizPanel(this, ep);
		_frame.add(sqp);
		sqp.playSequence(seq);
		_frame.repaint();
	}

	/**
	 * When the user has failed to progress the game, a fail video is played on the movie panel
	 * @param old used to remove the panel that called this method.
	 * @param ep selects which fail video to play
	 */
	public void addFailPanel(JPanel old, int ep) {
		_frame.remove(old);
		_frame.setSize(616, 400);
		MoviePanel mp = new MoviePanel(this,ep,0,null);
		_frame.add(mp);
		mp.playNewFail(ep);
		_frame.repaint();
	}

	/**
	 * When the user selects to look at the statistics during gameplay, this method takes the user to the statistics panel.
	 * @param old used to remove the panel that called this method
	 */
	public void addStoryStats(JPanel old) {
		_frame.remove(old);
		_frame.add(new StoryStatsPanel());
		_frame.repaint();
		_frame.setVisible(true);
	}
	
	/**
	 * When the user selects to look at the statistics at the menu, this method takes the user to the statistics
	 * panel.
	 * @param old used to remove the panel that called this method.
	 */
	public void addMenuStats(JPanel old) {
		_frame.remove(old);
		_frame.add(new MenuStats(this));
		_frame.repaint();
		_frame.setVisible(true);
	}

	/**
	 * When the user has finished a game, this method takes the user back to the menu. 
	 * @param panel used to remove the panel that called this method.
	 */
	public void addMenu(JPanel panel) {
		_frame.remove(panel);
		_frame.add(_menu);
		_frame.setSize(586, 615);
		_frame.repaint();
		_frame.setVisible(true);
	}

	public void addHelp() {
		
	}




}
