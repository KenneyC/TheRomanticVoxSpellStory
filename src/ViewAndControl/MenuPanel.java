package ViewAndControl;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;

import Operators.VoxSpell;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * MenuPanel is a simple interface, so the user can choice what they want to do next.
 * Story to play spelling quiz in story mode, free play lets the user play freely,
 * statistics is used to show statistics, and quit to close the application.
 * Author: Kenney Chan
 */
public class MenuPanel extends JPanel {

	private JPanel _menuPanel;
	private VoxSpell _vs;
	private helpPanel _hp;
	/**
	 * Create the panel.
	 */
	public MenuPanel(VoxSpell vs) {
				
		_menuPanel = this;
		_vs = vs;
		
		setSize(586, 615);
		
		ImageIcon bkg = new ImageIcon(this.getClass().getResource("/Resource/menuba.jpg"));
		ImageIcon bkgIcon = new ImageIcon(bkg.getImage().getScaledInstance(586, 615, Image.SCALE_SMOOTH));
		
		ImageIcon img = new ImageIcon(this.getClass().getResource("/Resource/buttonFP.png"));
		ImageIcon imageIcon = new ImageIcon(img.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
		
		ImageIcon img2 = new ImageIcon(this.getClass().getResource("/Resource/buttonFPglow.png"));
		new ImageIcon(img2.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
		
		ImageIcon NS = new ImageIcon(this.getClass().getResource("/Resource/buttonNS.png"));
		final ImageIcon NSIcon = new ImageIcon(NS.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
		
		ImageIcon NSglow = new ImageIcon(this.getClass().getResource("/Resource/buttonNSglow.png"));
		new ImageIcon(NSglow.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
		
		ImageIcon ST = new ImageIcon(this.getClass().getResource("/Resource/buttonSTglow.png"));
		final ImageIcon STIcon = new ImageIcon(ST.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
		
		ImageIcon STglow = new ImageIcon(this.getClass().getResource("/Resource/buttonSTglow.png"));
		new ImageIcon(STglow.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
		
		ImageIcon QU = new ImageIcon(this.getClass().getResource("/Resource/buttonQU.png"));
		final ImageIcon QUIcon = new ImageIcon(QU.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
		
		ImageIcon help = new ImageIcon(this.getClass().getResource("/Resource/buttonHelp.png"));
		final ImageIcon helpIcon = new ImageIcon(help.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
		
		
		ImageIcon menu = new ImageIcon(this.getClass().getResource("/Resource/menu.png"));
		final ImageIcon menuIcon = new ImageIcon(menu.getImage().getScaledInstance(140, 60, Image.SCALE_SMOOTH));
		
		
		JLabel label = new JLabel("");
		label.setLocation(0, 0);
		label.setIcon(bkgIcon);
		label.setVisible(true);
		
		final JLabel lblNewLabel = new JLabel("New free play");
		lblNewLabel.setLocation(78, 44);
		lblNewLabel.setForeground(Color.WHITE);
		add(lblNewLabel);
		lblNewLabel.setBounds(166, 244, 302, 60);
		lblNewLabel.setIcon(imageIcon);
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
					vs.addFreePlay(_menuPanel);
				}
				
			});		
		
		final JLabel lblNewLabel2 = new JLabel("New story");
		lblNewLabel2.setLocation(78, 44);
		lblNewLabel2.setForeground(Color.WHITE);
		add(lblNewLabel2);
		lblNewLabel2.setBounds(34, 244, 302, 60);
		lblNewLabel2.setIcon(NSIcon);
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
				vs.addStoryChoicePanel(_menuPanel);
			}
			
		});
		
		final JLabel lblNewLabel3 = new JLabel("statistics");
		lblNewLabel3.setLocation(78, 44);
		lblNewLabel3.setForeground(Color.WHITE);
		add(lblNewLabel3);
		lblNewLabel3.setBounds(298, 244, 302, 60);
		lblNewLabel3.setIcon(STIcon);
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
				vs.addMenuStats(_menuPanel);
			}
			
		});
		
		final JLabel lblNewLabel4 = new JLabel("quit");
		lblNewLabel4.setLocation(78, 44);
		lblNewLabel4.setForeground(Color.WHITE);
		add(lblNewLabel4);
		lblNewLabel4.setBounds(430, 244, 302, 60);
		lblNewLabel4.setIcon(QUIcon);
		lblNewLabel4.setVisible(true);
		lblNewLabel4.setSize(120, 120);
		
		lblNewLabel4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel4.setSize(130,130);
				lblNewLabel4.setVisible(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel4.setSize(120, 120);
				lblNewLabel4.setVisible(true);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				_vs._welcome.playSound(false);
				_vs.Quit();
			}

		});
		
		JLabel lblHelp = new JLabel();
		lblHelp.setBounds(451, 487, 70, 15);
		add(lblHelp);

		lblHelp.setIcon(helpIcon);
		lblHelp.setVisible(true);
		lblHelp.setSize(80, 80);
		lblHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblHelp.setSize(130,130);
				lblHelp.setVisible(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblHelp.setSize(120, 120);
				lblHelp.setVisible(true);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				_hp = new helpPanel();
				_hp.setVisible(true);
			}

		});
		
		label.setSize(586, 615);
		add(label,BorderLayout.CENTER);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(220, 33, 70, 15);
		add(lblTitle);
		lblTitle.setIcon(menuIcon);
		lblTitle.setVisible(true);
		lblTitle.setSize(140, 60);
		add(label);
	
		
		setLayout(null);

		
	}	
}


