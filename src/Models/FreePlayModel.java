package Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Operators.StoryWord;
import Operators.VoxSpell;
import ViewAndControl.FreePlayQuizPanel;

/**
 * Free play model is the model that controls the free play quiz mode, word checking, file IO  and updating is 
 * done in this method 
 * 
 * @author Kenney Chan kcha582
 *
 */
public class FreePlayModel {

	private VoxSpell _vs;
	private int _incorrect;
	private String _voice;
	private ScheduledExecutorService _es;
	private ArrayList<StoryWord> _SpellingList;
	private File _spellistList;
	private FreePlayQuizPanel quizPanel;
	private StoryWord currentWord;
	private int numWords;
	private int tries;
	private String[] word;
	
	public FreePlayModel(VoxSpell vs, FreePlayQuizPanel quiz,File file) {
		_vs = vs;
		_voice = "kal_diphone";
		_incorrect = 0;
		_es = Executors.newScheduledThreadPool(1);
		Executors.newFixedThreadPool(1);
		_SpellingList = new ArrayList<StoryWord>();
		_spellistList = file;
		numWords = 0;
		tries = 0;
		getEpWords();
		getRandomWord();
		fileChecker();
		speakWord(2);
		quizPanel = quiz;
	}
	
	/**
	 * Compares the string that the user has entered, if it is correct, it gives a feedback and selects another
	 * word. If incorrect the life bar is updated and the word is repeated. Correct and incorrect records are saved here.
	 * Hints are given when the user has attempted more than 3, 6 or 9 times, also depends on the length of the word.
	 * @param textString the String the user entered.
	 */
	public void compareWord(String textString) {
		textString = textString.toLowerCase();
		if (textString.equals(currentWord.getName())) {
			tries = 0;
			_es.submit(new Speaker("\"(correct.)\"", _voice));
			currentWord.addCorrect();
			quizPanel.hideHint();
			updateStats();
			getRandomWord();
			speakWord(0);
			
		} else if (!(textString.equals(currentWord.getName()))) {
			_es.submit(new Speaker("\"(incorrect, please try again)\"", _voice));
			playSequence();
			tries++;
			currentWord.addIncorrect();
			_incorrect++;
			updateStats();
			updatePanel("incorrect");
			repeatWord();
			if (tries == 3) {
				word = currentWord.getName().split("(?!^)");
				if (word.length > 5) {
					for (int i = 0; i<4 ; i++) {
						word[0 + (int)(Math.random() * word.length-1)] = "_";
					}
				} else if (word.length < 5) {
					for (int i = 0; i<3 ; i++) {
						word[0 + (int)(Math.random() * word.length-1)] = "_";
					}
				}
				quizPanel.giveHint(Arrays.toString(word));
			} else if (tries == 6) {
				word = currentWord.getName().split("(?!^)");
				if (word.length > 5) {
					for (int i = 0; i<3 ; i++) {
						word[0 + (int)(Math.random() * word.length-1)] = "_";
					}
				} else if( word.length < 6) {
					for (int i = 0; i<3 ; i++) {
						word[0 + (int)(Math.random() * word.length-1)] = "_";
					}
				}
				quizPanel.giveHint(Arrays.toString(word));
			} else if (tries == 9) {
				word = currentWord.getName().split("(?!^)");
					for (int i = 0; i<1; i++) {
						word[0 + (int)(Math.random() * word.length-1)] = "_";
					}
				quizPanel.giveHint(Arrays.toString(word));
			}
			if (_incorrect == 10) {
				failed();
			}
		}
	}
	
	/**
	 * Play sequence plays a short video.
	 */
	public void playSequence() {
		quizPanel.playSequence(2);
	}
	
	/**
	 * This method randomly selects a word from the list that the user chose for them to spell.
	 */
	public void getRandomWord() {
		currentWord = _SpellingList.get(0 + (int)(Math.random() * numWords));
	}
	
	/**
	 * If the user gets 10 incorrect words, this method takes them to the game over movie panel.
	 */
	public void failed () {
		quizPanel.playBGMusic(false);
		_vs.addFailPanel(quizPanel, 5);
	}
	
	/**
	 * This method updates the life bar when the user gets an incorrect word.
	 * @param option
	 */
	public void updatePanel(String option) {
		if (option.equals("incorrect")) { 
			quizPanel.updateLifeBar(_incorrect * 10);
		}
	}
//FILE IO ================================================================================================================
	/**
	 * This methods gets the words the users has selected to test themselves.
	 */
	private void getEpWords() {

		try {
			FileInputStream fileStream = new FileInputStream(_spellistList);
			BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));

			String line;
			while ((line = br.readLine()) != null) {
				String[] currentLine = line.split(" ");
				if (!(currentLine[0].equals("%Level"))) {
					numWords++;
					boolean inThere = false;
					for (StoryWord w : _SpellingList) {
						if (w.getName().equals(currentLine[0])) {
							inThere = true;
						}
					}
					if (inThere == false) {
						_SpellingList.add(new StoryWord(currentLine[0], "0","0","0"));
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException es) {

		}
	}
//=================================================================================================================
	/**
	 * If a stats list exist, this method read the statsList record.
	 */
	private void readStats() {
		
			FileInputStream fileStream;
			try {
				fileStream = new FileInputStream("FPStatsList");
				BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));

				String line;

				while ((line = br.readLine())!= null) {
					String[] currentLine = line.split(" ");
					for (StoryWord w : _SpellingList) {
						if (w.getName().equals(currentLine[0])) {
							w.updateCorrect(currentLine[1]);
							w.updateIncorrect(currentLine[2]);
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
//=================================================================================================================
	/**
	 * This method checks if a stats file exist. 
	 */	
	private void fileChecker(){

		File FPStatsList = new File("FPStatsList");
		if(FPStatsList.exists() && !FPStatsList.isDirectory()){
			readStats();
		} else {
			makeFile(FPStatsList);
		}
	}

//=================================================================================================================
	/**
	 * If the stats list does not exist this method creates a stats list.
	 * @param file
	 */
	private void makeFile(File file){
		try {
			file.createNewFile();
			FileWriter fw;
			fw = new FileWriter(file, true);
				for (StoryWord w : _SpellingList) {
					fw.write(w.getName() + " " + "0 " + "0" + "\n");
				}
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//=================================================================================================================
	/**
	 * Everytime a word is spelt right or wrong, this method updates the record in the stats list.
	 */
	public void updateStats() {
		File statsList = new File("FPStatsList");
		statsList.delete();

		try {
			statsList.createNewFile();
		}catch (IOException e) {
		}
		
		try {
			FileWriter fw;
			fw = new FileWriter(statsList, true);
			int i = 1;
			for (StoryWord w : _SpellingList) {
				fw.write(w.getCurrentRecord() + "\n");
			}
			fw.flush();
		} catch (Exception e) {

		}
	}
	
//=================================================================================================================
	/**
	 * This calls the speaker method to say the word the user needs to spell. The speaking delays for a specific time 
	 * depending on the sequence to be played
	 * @param delay number of seconds for delay 
	 */
	public void speakWord(int delay) {
		_es.schedule((new Speaker("\"(Please spell " + currentWord.getName() + ")\"",_voice)), delay, TimeUnit.SECONDS);
	}
	
//=================================================================================================================
	/**
	 * This methods repeats the word when the user wants to repeat the word again. Warning! the word can only be repeated
	 * once, this is intentional.
	 */
	public void repeatWord() {
			_es.submit((new Speaker("\"(Please spell " + currentWord.getName() + ")\"",_voice)));
	}
	
//=================================================================================================================
	/**
	 * This method changes the voice of the speaker at runtime. 
	 * @param voice The voice the user wants.ss
	 */
	public void setVoice(String voice) {
		_voice = voice;
	}	
}
	
