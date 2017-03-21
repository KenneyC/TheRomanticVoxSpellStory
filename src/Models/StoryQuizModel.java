package Models;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Models.Speaker;
import Operators.StoryWord;
import Operators.VoxSpell;
import ViewAndControl.StoryQuizPanel;

/**
 * Story quiz model controls story quiz mode, word comparison, file IO, and stats update are done in this 
 * class.
 * @author kc
 *
 */
public class StoryQuizModel {
	
	private int _wordsDone;
	private int _wordsCorrect;
	private ScheduledExecutorService _es;
	private StoryQuizPanel quizPanel;
	private String _voice;
	private int _attempts;
	private VoxSpell _vox;
	private int _ep;
	private File _wordList;
	private ArrayList<ArrayList<StoryWord>> levelList;
	
	public StoryQuizModel(StoryQuizPanel quiz, VoxSpell vox, int ep) {
		_vox = vox;
		_ep = ep;
		_voice = "kal_diphone";
		_wordsDone = 0;
		_wordsCorrect = 0;
		_attempts = 3;
		_es = Executors.newScheduledThreadPool(1);
		Executors.newFixedThreadPool(1);
		new ArrayList<StoryWord>();
		levelList = new ArrayList<ArrayList<StoryWord>>();
		getEpWords();
		fileChecker();
		quizPanel = quiz;
		
	}
	
	/**
	 * Compares the word the user entered with the word that the user needs to spell. Updates the stats on the panel
	 * and the stats list according to if the word is spelt correctly.
	 * @param textString the string that the user has entered.
	 */
	public void compareWord(String textString) {
		textString = textString.toLowerCase();
		if (textString.equals(levelList.get(_ep-1).get(_wordsDone-1).getName())) {
			_wordsCorrect++;
			_es.submit(new Speaker("\"(correct.)\"", _voice));
			levelList.get(_ep-1).get(_wordsDone-1).addCorrect();
			updateStats();
			_attempts = 3;
			updatePanel("correct");
			if (_ep == 3) {
				if (_wordsDone == 11) {
					quizPanel.playBGMusic(false);
					_vox.addMovie(quizPanel, 4, 11,null);
				}
			}
			if (_ep == 2 ) {
				if (_wordsDone == 9) {
					quizPanel.playBGMusic(false);
					_vox.addMovie(quizPanel, 3, 11,null);
				}
			}
			if(_ep == 1) {
				if (_wordsDone == 10) {
					quizPanel.playBGMusic(false);
					_vox.addMovie(quizPanel, 2, 6,null);
				}
			}
			if (_ep == 1 ) {
				if (_wordsDone == 1 || _wordsDone == 2 || _wordsDone == 5 || _wordsDone == 8) {
					playSequenceEP1(_wordsDone);
				} else if (_wordsDone != 10){
					speakWord(2);
				} 
			} else if (_ep == 2) {
				if (_wordsDone == 3 || _wordsDone == 4 || _wordsDone == 5 || _wordsDone == 8) {
					playSequenceEP2(_wordsDone);
				} else if (_wordsDone != 9){
					speakWord(2);
				}
			} else if (_ep == 3) {
				if (_wordsDone == 3 || _wordsDone == 8) {
					speakWord(2);
				} else {
					playSequenceEP3(_wordsDone);
				}
			}
		} else if (!textString.equals(levelList.get(_ep-1).get(_wordsDone-1).getName())) {
			if (failed() == false) {
				_es.submit(new Speaker("\"(incorrect, please try again.)\"", _voice));
				_attempts--;
				updatePanel("incorrect");
				levelList.get(_ep-1).get(_wordsDone-1).addIncorrect();
				updateStats();
				repeatWord();
				if (failed() == true) {
					if (_ep == 1) {
						quizPanel.failed(1);
					} else if (_ep == 2) {
						quizPanel.failed(2);
					}
				}
			}
		}
	}
	
	/**
	 * Plays a short video according the the amount of words done for episode 1.
	 * @param _wordsDone the number of words done.
	 */
	public void playSequenceEP1(int _wordsDone) {
		if (_wordsDone == 1) {
			quizPanel.playSequence(2);
			speakWord(2);
		} else if (_wordsDone == 2) {
			quizPanel.playSequence(3);
			speakWord(2);
		} else if (_wordsDone == 5) {
			quizPanel.playSequence(4);
			speakWord(2);
		} else if (_wordsDone == 8) {
			quizPanel.playSequence(5);
			speakWord(2);
		}
	}
	
	/**
	 * Plays a short video according the the amount of words done for episode 2.
	 * @param _wordsDone the number of words done.
	 */
	public void playSequenceEP2(int _wordsDone) {
		if (_wordsDone == 3) {
			quizPanel.playSequence(7);
			speakWord(2);
		} else if (_wordsDone == 4) {
			quizPanel.playSequence(8);
			speakWord(2);
		} else if (_wordsDone == 5) {
			quizPanel.playSequence(9);
			speakWord(6);
		} else if (_wordsDone == 8) {
			quizPanel.playSequence(10);
			speakWord(4);
		}
	}
	
	/**
	 * Plays a short video according the the amount of words done for episode 3.
	 * @param _wordsDone the number of words done.
	 */
	public void playSequenceEP3(int _wordDone) {
		if (_wordsDone == 1) {
			quizPanel.playSequence(12);
			speakWord(2);
		}else if (_wordsDone == 2) {
			quizPanel.playSequence(13);
			speakWord(2);
		} else if (_wordsDone == 4) {
			quizPanel.playSequence(14);
			speakWord(6);
		} else if (_wordsDone == 5) {
			quizPanel.playSequence(15);
			speakWord(2);
		}else if (_wordsDone == 6) {
			quizPanel.playSequence(16);
			speakWord(2);
		}else if (_wordsDone == 7) {
			quizPanel.playSequence(17);
			speakWord(2);
		}else if (_wordsDone == 9) {
			quizPanel.playSequence(18);
			speakWord(4);
		} else if (_wordsDone == 10) {
			quizPanel.playSequence(19);
			speakWord(2);
		}
	}
	
	/**
	 * Checks if the current stats that the user has failed to spell all the words.
	 * @return a boolean that indicates if the user has failed.
	 */
	public boolean failed () {
		if (_attempts > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Updates the panel to allow the user to see their progress according to if they are correct or incorrect.
	 * @param option whether the panel should update for correct or incorrect.
	 */
	public void updatePanel(String option) {
		if (option.equals("incorrect")) { 
			quizPanel.updateAttemptBar(_attempts);
		} else if (option.equals("correct")) {
			if (_ep == 1) {
				quizPanel.updateLikeBar(_wordsCorrect*10);
			} else if (_ep == 2) {
				quizPanel.updateLikeBarEP2(_wordsCorrect*10);
			} else if (_ep == 3) {
				quizPanel.updateLikeBarEP3(_wordsCorrect*10);
			}
		}
	}
//FILE IO ================================================================================================================
	
	/**
	 * Gets the words that the user needs to spell according to the episode that have selected
	 */
	private void getEpWords() {

		try {

			FileInputStream fileStream = new FileInputStream("StorySpellingList");
			BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));

			String line = br.readLine();

				
				int ep = 1;
				while (true){
					if (ep == 4) {
						break;
					}
					if (line.equals("%EP" + ep)) {
						ArrayList<StoryWord> wordList = new ArrayList<StoryWord>();
						while ((line = br.readLine()) != null) {
							
							if (line.equals("%EP" + (ep+1))) {
								break;
							}
							
							if (!(line.equals("%EP" + ep))){
								if (!(line.equals("%EP" + (ep+1)))) {
									wordList.add(new StoryWord(line, "0" , "0",Integer.toString(ep)));
								}
							}
						}
						levelList.add(wordList);
						ep++;
					}
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException es) {

		}
	}
//=================================================================================================================
	
	/**
	 * If the story stats list exists this method reads the stats list.
	 */
	private void readStats() {
		
			FileInputStream fileStream;
			try {
				fileStream = new FileInputStream("StoryStatsList");
				BufferedReader br = new BufferedReader(new InputStreamReader(fileStream));

				String line;

				while ((line = br.readLine())!= null) {
					if (line.equals("%EP" + _ep)) {
						while (true) {
							line = br.readLine();
							if (line == null) {
								break;
							}
							
							if (line.equals("%EP" + (_ep+1))) {
								break;
							}
							String[] currentLine = line.split(" ");
							if (!(line.equals("%EP" + _ep))){
								if (!(line.equals("%EP" + (_ep+1)))) {
									for (StoryWord w : levelList.get(_ep-1)) {
										if (w.getName().equals(currentLine[0])) {
											w.updateCorrect(currentLine[1]);
											w.updateIncorrect(currentLine[2]);
										}
									}
								}
							}
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
	 * Checks if the stats file exist. If not then the method creates a new one. 
	 */
	private void fileChecker(){

		File StoryStatsList = new File("StoryStatsList");
		if(StoryStatsList.exists() && !StoryStatsList.isDirectory()){
			readStats();
		} else {
			makeFile(StoryStatsList);
		}
	}

//=================================================================================================================
	
	/**
	 * Makes a new file according the what file is needed. In this case only the Stats list is created, which is always
	 * the same so the words are hard coded.
	 * @param file file to make.
	 */
	private void makeFile(File file){
		try {
			file.createNewFile();
			FileWriter fw;
			fw = new FileWriter(file, true);
					fw.write("%EP"+1+"\n");
					fw.write("my 0 0" + "\n");
					fw.write("is 0 0"+ "\n");
					fw.write("and 0 0"+ "\n");
					fw.write("you 0 0"+ "\n");
					fw.write("are 0 0"+ "\n");
					fw.write("hot 0 0"+ "\n");
					fw.write("can 0 0"+ "\n");
					fw.write("i 0 0"+ "\n");
					fw.write("to 0 0"+ "\n");
					fw.write("you 0 0"+ "\n");
					fw.write("%EP"+2+"\n");
					fw.write("your 0 0"+"\n");
					fw.write("beauty 0 0"+"\n");
					fw.write("cannot 0 0"+"\n");
					fw.write("compared 0 0"+"\n");
					fw.write("anyone 0 0"+"\n");
					fw.write("very 0 0"+"\n");
					fw.write("nice 0 0"+"\n");
					fw.write("please 0 0"+"\n");
					fw.write("girlfriend 0 0"+"\n");
					fw.write("%EP"+3+"\n");
					fw.write("promise 0 0"+"\n");
					fw.write("forever 0 0"+"\n");
					fw.write("scream 0 0"+"\n");
					fw.write("whenever 0 0"+"\n");
					fw.write("compliment 0 0"+"\n");
					fw.write("beauty 0 0"+"\n");
					fw.write("makeup 0 0"+"\n");
					fw.write("i 0 0"+"\n");
					fw.write("do 0 0"+"\n");
					fw.write("exchange 0 0"+"\n");
					fw.write("kiss 0 0"+"\n");
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//=================================================================================================================
	/**
	 * Updates the stats list according to correct or incorrect. Called everytime a word is done.
	 */
	public void updateStats() {
		File statsList = new File("StoryStatsList");
		statsList.delete();

		try {
			statsList.createNewFile();
		}catch (IOException e) {
		}
		
		try {
			FileWriter fw;
			fw = new FileWriter(statsList, true);
			int i = 1;
			for (ArrayList<StoryWord> w : levelList) {
				if (i != 4) {
					fw.write("%EP" + i + "\n");
					for (StoryWord wo: w) {
						fw.write(wo.getCurrentRecord() + "\n");
					}
					i++;
				}
			}
			fw.flush();
		} catch (Exception e) {

		}
	}
	
//=================================================================================================================
	/**
	 * This method speaks the current word the user needs to spell. It delays for a few seconds according to the sequence
	 * to be played.
	 * @param delay the number of seconds to delay
	 */
	public void speakWord(int delay) {
		if (_wordsDone < 15) {
			_es.schedule((new Speaker("\"(Please spell " + levelList.get(_ep-1).get(_wordsDone).getName() + ")\"",_voice)), delay, TimeUnit.SECONDS);
		}
		_wordsDone++;
	}
	
//=================================================================================================================
	/**
	 * This methods repeats the word when the user wants to repeat the word again. Warning! the word can only be repeated
	 * once, this is intentional.
	 */
	public void repeatWord() {
		if (_wordsDone < 15) {
			_es.submit((new Speaker("\"(Please spell " + levelList.get(_ep-1).get(_wordsDone-1).getName() + ")\"",_voice)));
		}
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
