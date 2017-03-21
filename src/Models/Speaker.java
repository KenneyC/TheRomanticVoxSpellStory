package Models;

import javax.swing.SwingWorker;

/**
 * Speaker is a class that does all the speech synthesis, it runs a small bash script which will make a text to speech
 * call to what ever a message and the voice the application wants.
 * @author Kenney Chan kcha582
 *
 */
public class Speaker extends SwingWorker<Void,Void> {
	
	private boolean _doneSpeaking;
	private Process _runningProcess;
	private Runtime _runtime;
	private String _command;
	private String _voice;
	
	public Speaker(String message) {
		_doneSpeaking = true;
		_runtime = Runtime.getRuntime();
		_command = message;
		_voice = "kal_diphone";
	}
	
	public Speaker(String message,String voice) {
		_doneSpeaking = true;
		_runtime = Runtime.getRuntime();
		_command = message;
		_voice = voice;
	}
	
	/**
	 * This methods runs the bash script that calls on the speech synthesis on a new thread.
	 */
	@Override
	protected Void doInBackground() throws Exception {
		_runningProcess = _runtime.exec(new String[] {"/bin/bash", "-c", "./.speak.sh " +_command + " " + _voice});
		_runningProcess.waitFor();
		return null;
	}
	
	/**
	 * This method allows the user to change the voice of the speaker at run time/
	 * @param voice The voice the user wants.
	 */
	public void changeVoice(String voice) {
		_voice = voice;
	}
}
