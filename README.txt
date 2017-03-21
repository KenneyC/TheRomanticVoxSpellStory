The Romantic VoxSpell story README:

---------------------------------------------------------------------------------------------------
Start VoxSpell by:

running the following terminal command:

./runVoxspellStory.sh

(if you are denied permission to run runVoxspellStory.sh, please type 
chmod 777 runVoxspellStory.sh
and press enter, this will give you permission to run the script. Remember to type 
the ./ command above to run start the program)

or

/usr/lib/jvm/jdk1.8.0_91/bin/java -jar TheRomanticVSStory.jar

or
--The path the the java 8 file-- -jar TheRomanticVSStory.jar
---------------------------------------------------------------------------------------------------


Welcome screen will open:

	Press any key on the keyboard to continue..

A main menu screen will open:
	
	In this Panel you are given the 5 options to press.

Pressing Free play Mode:

	Will lead you to the episodes menu to select an episode.

After selecing an episode:
	
	A movie panel will open to play the introduction video.

Pressing the go to quiz button:
	
	In center, there is a text box, listen to what the voic tells you to spell,
	and type your attempt into the text box. Press enter to submit your attempt.

	Next to the text box is the "Re-Listen" button, you are allowed to press
	this button once every unique word to listen to the word you are spelling again.

	"MuscleHead's Like meter" is a meter that tracks your progress and determines
	if you pass the quiz or not, the bar will slowly fill up as you complete more 
	spelling words. The higher it is, the more Musclehead likes you!
	If you look closely at the bar, there will be text showing what Musclehead
	thinks of Sweety, use this to see if Sweety is making a good impression!
	
	You are helping Sweety to spell and say words she is unable to, but this does not
	mean she is unable to speak at all! What Sweety says above your quiz screen is 
	connected to what you are about to spell. If she says "hello", and you 
	are asked to spell "there", this means she is trying to say "hello there" 

	You can make 3 mistakes which are also tracked on the the "Attempts bar",
	if you have lost all attempts, then you will be taken to the "Game Over" screen.

Game Over Screen will open:
	
	If you have failed to impress Musclehead, a game over video will play. You
	are welcome to click on the "retry" button to make an another attempt.

Selecting the free play mode option:
	
	Will open the free play menu, here you need to select a list of spelling wordls, by pressing
	the "select a word list" button, this is crucial as you cannot progress to the game without 
	a spelling list. Press play to proceed to game play.

Selecting the statistics button:

	Will open a panel with the history of the records made in spelling.

Selecting the quit button:

	Will close the application.

Selecting the help button:
	
	Will display a frame, that explains what each button will do.


------------------------------------------------------------------------------------------------------------
Requirements:

Please make sure that your computer is able to run Java 8 as the application uses Java 8 libraries and will 
not run with older versions of Java.

Please make sure that main folder you can find another folder called "Resource" with .avi videos in there
This is important as these are the cutscene and you will not be able to progress with the story without them!

Please make sure you have VLC installed on your computer as the application uses VLCJ to play the videos.

------------------------------------------------------------------------------------------------------------
Warnings:

Please make sure you have the FPStatsList, StorySpellingList, and StoryStatsList. 

DO NOT DELETE the StorySpellingList, or else the story mode will not be functional.

DO NOT DELETE the TheRomanticVSStory.jar file, this is the main application!

If you see a file called speak.sh DO NOT DELETE THIS FILE. Or else the speech synthesis of the application
will not be functional.

When playing the videos the console will display warnings and fail messages, these messgaes can be ignored.

------------------------------------------------------------------------------------------------------------
Created by 
Kenney Chan
for final submission - SE206 
