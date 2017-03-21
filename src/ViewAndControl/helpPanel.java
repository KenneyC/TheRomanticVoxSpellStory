package ViewAndControl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Color;

public class helpPanel extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JDialog dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			helpPanel dialog = new helpPanel();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public helpPanel() {
		dialog = this;
		getContentPane().setBackground(new Color(0, 51, 102));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JLabel lblHelp = new JLabel("Help");
			getContentPane().add(lblHelp, BorderLayout.NORTH);
		}
		contentPanel.setBackground(new Color(51, 102, 153));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextPane txtpnButtons = new JTextPane();
		txtpnButtons.setText("Functionality of each buttons:\nStory mode: If you wish to play story mode, selecting this button will take you to the episode selection panel.\nFree play mode: If you wish to practice spelling , but with a twist of challenge, choosing this button will take you to the free play menu button.\nStatistics: If you wish to see your current progress, selecting this button will take you to a statistics panel.\nHelp: Pressing help will open this help dialogue.\nQuit: Pressing quit will stop close the application.\n\nAuthor: Kenney Chan\n\n\n\n");
		txtpnButtons.setBounds(12, 12, 426, 225);
		contentPanel.add(txtpnButtons);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 51, 102));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mousePressed(MouseEvent e) {
					dialog.dispose();
				}
				
			});		
			}
		}
	}
}
