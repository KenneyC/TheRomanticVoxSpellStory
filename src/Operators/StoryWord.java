package Operators;

/**
 * StoryWord represents a particular episodes word and its correct and incorrect.
 */
public class StoryWord implements Comparable<StoryWord>{
	private String _name;
	private String _correct;
	private String _incorrect;
	private String _level;
	
	public StoryWord(String name, String correct, String incorrect, String level){		
		_name = name;
		_correct = correct;
		_incorrect = incorrect;
		_level = level;
	}
	
	/**
	 * This method updates the number of time the word has been made correct from stats list.
	 * @param correct number of time spelt correct
	 */
	public void updateCorrect(String correct) {
		_correct = correct;
	}
	
	/**
	 * This method updates the number of time the word  has been made incorrect from stats list.
	 * @param incorrect number of time spelt incorrect.
	 */
	public void updateIncorrect(String incorrect) {
		_incorrect = incorrect;
	}

	/**
	 * This method gets the number of time the word has been spelt correctly.
	 * @return returns the number in string
	 */
	public String getCorrect(){
		return _correct;
	}
	
	/**
	 * This method increments the number of time the word is spelt correctly by one.
	 */
	public void addCorrect(){
		_correct = (Integer.parseInt(_correct) + 1) + "";
	}
	
	/**
	 * This method gets the number of time the word has been spelt incorrectly.
	 * @return returns the number in string
	 */
	public String getIncorrect(){
		return _incorrect;
	}
	
	/**
	 * This method increments the number of time the word is spelt incorrectly by one.
	 */
	public void addIncorrect(){
		_incorrect = (Integer.parseInt(_incorrect) + 1) + "";

	}
	/**
	 * This method returns the epsiode or level of the word.
	 * @return
	 */
	public String getLevel(){
		return _level;
	}
	
	/**
	 * This method returns the name of the word as a string.
	 * @return
	 */
	public String getName(){
		return _name;
	}
	
	/**
	 * This method returns the current record of correct and incorrect along with its name. usually used 
	 * to update stats table
	 * @return returns the record as a string.
	 */
	public String getCurrentRecord() {
		return _name + " " + _correct + " " + _incorrect + " ";
	}
	
	/**
	 * This method is used to compare the ordering of the word.
	 */
	@Override
	public int compareTo(StoryWord o) {
		String compareName = o.getName();
		return _name.compareTo(compareName);
	}
}

