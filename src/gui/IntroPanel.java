package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

/** 
 * @author MerleHiort, MichaelSandritter BenjaminChristiani, JoergEinfeldt
 *
 * IntroPanel zeigt ein LayeredPane, das eine kurze Anleitung zum Projekt beinhaltet
 */
@SuppressWarnings("serial")
public class IntroPanel extends JLayeredPane{

	private BufferedImage bg;
	private JButton skip;
	private JButton drag;
	private JButton drop;
	private JButton listen;
	private ImageIcon skipImg = new ImageIcon("img/intro/skipButton.png");
	private ImageIcon skipOver = new ImageIcon("img/intro/skipButton_over.png");
	private ImageIcon skipPress = new ImageIcon("img/intro/skipButton_press.png");
	private ImageIcon one = new ImageIcon("img/intro/one.png");
	private ImageIcon oneOver = new ImageIcon("img/intro/one_over.png");
	private ImageIcon two = new ImageIcon("img/intro/two.png");
	private ImageIcon twoOver = new ImageIcon("img/intro/two_over.png");
	private ImageIcon three = new ImageIcon("img/intro/three.png");
	private ImageIcon threeOver = new ImageIcon("img/intro/three_over.png");
	
	/**
	 * IntroPanelKonstruktor
	 */
	public IntroPanel() {
		try {
			bg = ImageIO.read(new File("img/intro/bg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		skip = new JButton(skipImg);
		skip.setBounds(512-skipImg.getIconWidth()/2, 695, skipImg.getIconWidth(), skipImg.getIconHeight());
		skip.setBorder(BorderFactory.createEmptyBorder());
		skip.setContentAreaFilled(false);
		skip.setPressedIcon(skipPress);
		skip.setRolloverIcon(skipOver);

		drag = new JButton(one);
		drag.setBounds(146, 200, one.getIconWidth(), one.getIconHeight());
		drag.setRolloverIcon(oneOver);

		drop = new JButton(two);
		drop.setRolloverIcon(twoOver);
		drop.setBounds(146 + one.getIconWidth(), 200, one.getIconWidth(),one.getIconHeight());

		listen = new JButton(three);
		listen.setRolloverIcon(threeOver);
		listen.setBounds(146 + 2 * one.getIconWidth(), 200, one.getIconWidth(), one.getIconHeight());
		add(drag);
		add(drop);
		add(listen);
		add(skip);
		setPreferredSize(new Dimension(1024, 768));
	}
	
	/**
	 * 
	 * @param action
	 */
	public void addSkipCallBack(ActionListener action){
		skip.addActionListener(action);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, this);
	}
}
