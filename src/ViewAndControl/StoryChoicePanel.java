package ViewAndControl;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Operators.VoxSpell;

/**
 * StoryChoicePanel is the panel that allows the user to select which episode to play.
 * @author Kenney Chan kcha582
 *
 */
public class StoryChoicePanel extends JPanel {
	
	private JPanel _StoryChoicePanel;
	private VoxSpell _vs;
	private WelcomePanel _wp;
	/**
	 * Create the panel.
	 */
	public StoryChoicePanel(VoxSpell vs, WelcomePanel wp) {
		_vs = vs;
		_wp = wp;
		_StoryChoicePanel = this;
		setSize(586, 615);
		
		ImageIcon bkg = new ImageIcon(this.getClass().getResource("/Resource/menuba.jpg"));
		ImageIcon bkgIcon = new ImageIcon(bkg.getImage().getScaledInstance(586, 615, Image.SCALE_SMOOTH));
		
		ImageIcon EP1 = new ImageIcon(this.getClass().getResource("/Resource/EP1.png"));
		ImageIcon EP1Icon = new ImageIcon(EP1.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
		
		ImageIcon EP2 = new ImageIcon(this.getClass().getResource("/Resource/EP2.png"));
		ImageIcon EP2Icon = new ImageIcon(EP2.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
		
		ImageIcon EP3 = new ImageIcon(this.getClass().getResource("/Resource/EP3.png"));
		ImageIcon EP3Icon = new ImageIcon(EP3.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
		
		ImageIcon EP4 = new ImageIcon(this.getClass().getResource("/Resource/buttonB.png"));
		ImageIcon EP4Icon = new ImageIcon(EP4.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
		
		ImageIcon EP5 = new ImageIcon(this.getClass().getResource("/Resource/episodes.png"));
		ImageIcon EP5Icon = new ImageIcon(EP5.getImage().getScaledInstance(140, 60, Image.SCALE_SMOOTH));
		
		JLabel label = new JLabel("");
		label.setSize(586, 615);
		label.setLocation(0, 0);
		label.setIcon(bkgIcon);
		label.setVisible(true);
		setLayout(null);
			

		JLabel lblNewLabel2 = new JLabel("EP2");
		lblNewLabel2.setLocation(78, 44);
		lblNewLabel2.setForeground(Color.WHITE);
		add(lblNewLabel2);
		lblNewLabel2.setBounds(240, 253, 222, 120);
		lblNewLabel2.setIcon(EP2Icon);
		lblNewLabel2.setVisible(true);
		lblNewLabel2.setSize(120, 120);
		lblNewLabel2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel2.setSize(130,130);
				lblNewLabel2.setVisible(true);
			}
					
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel2.setSize(120, 120);
				lblNewLabel2.setVisible(true);
			}
					
			@Override
			public void mousePressed(MouseEvent e) {
				_wp.playSound(false);
				vs.addMovie(_StoryChoicePanel,2,6,null);
			}
					
		});
					
				

			JLabel lblNewLabel3 = new JLabel("EP3");
			lblNewLabel3.setLocation(78, 44);
			lblNewLabel3.setForeground(Color.WHITE);
			add(lblNewLabel3);
			lblNewLabel3.setBounds(411, 253, 222, 120);
			lblNewLabel3.setIcon(EP3Icon);
			lblNewLabel3.setVisible(true);
			lblNewLabel3.setSize(120, 120);
			lblNewLabel3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblNewLabel3.setSize(130,130);
					lblNewLabel3.setVisible(true);
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					lblNewLabel3.setSize(120, 120);
					lblNewLabel3.setVisible(true);
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					_wp.playSound(false);
					vs.addMovie(_StoryChoicePanel,3,11,null);
				}
				
			});
		
			JLabel lblNewLabel = new JLabel("EP1");
			lblNewLabel.setLocation(78, 44);
			lblNewLabel.setForeground(Color.WHITE);
			add(lblNewLabel);
			lblNewLabel.setBounds(54, 253, 222, 120);
			lblNewLabel.setIcon(EP1Icon);
			lblNewLabel.setVisible(true);
			lblNewLabel.setSize(120, 120);
				
			lblNewLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblNewLabel.setSize(130,130);
					lblNewLabel.setVisible(true);
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					lblNewLabel.setSize(120, 120);
					lblNewLabel.setVisible(true);
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					_wp.playSound(false);
					vs.addMovie(_StoryChoicePanel,1,1,null);
				}
						
			});	
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setBounds(436, 451, 70, 15);
		add(lblBack);
		lblBack.setIcon(EP4Icon);
		lblBack.setVisible(true);
		lblBack.setSize(120, 120);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBack.setSize(130,130);
				lblBack.setVisible(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblBack.setSize(120, 120);
				lblBack.setVisible(true);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				vs.addMenu(_StoryChoicePanel);
			}
			
		});	
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(220, 33, 70, 15);
		add(lblTitle);
		lblTitle.setIcon(EP5Icon);
		lblTitle.setVisible(true);
		lblTitle.setSize(140, 60);
		add(label);
		
		
		
	}
}

