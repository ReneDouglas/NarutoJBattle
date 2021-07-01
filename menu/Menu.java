package menu;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon Imgfundo;
	private Icon imgb1;
	private Icon imgb2;
	private Icon imgb3;
	private Icon imgb4;
	private Icon select1;
	private Icon select2;

	private JLabel menu = new JLabel();
	private JLabel s1 = new JLabel();
	private JLabel s2 = new JLabel();
	private JButton singlePlayer, battleMode, instructions, credits;

	private AudioClip menuTheme;
	private AudioClip botao;

	public Menu() throws MalformedURLException {
		super("Naruto JBattle");
		setSize(800, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setIconImage(new ImageIcon(getClass().getResource("/menu/imagens menu/icone.png"))
				.getImage());

		CarregarImagens_e_Som();

		menuTheme.loop();
		menu.setIcon(Imgfundo);
		menu.setBounds(0, 0, Imgfundo.getIconWidth(), Imgfundo.getIconHeight());

		singlePlayer = new JButton(imgb1);
		battleMode = new JButton(imgb2);
		instructions = new JButton(imgb3);
		credits = new JButton(imgb4);

		singlePlayer.setBorder(null);
		singlePlayer.setContentAreaFilled(false);
		battleMode.setBorder(null);
		battleMode.setContentAreaFilled(false);
		instructions.setBorder(null);
		instructions.setContentAreaFilled(false);
		credits.setBorder(null);
		credits.setContentAreaFilled(false);

		singlePlayer.setBounds(300, 150, imgb1.getIconWidth(),
				imgb1.getIconHeight());
		battleMode.setBounds(300, 200, imgb2.getIconWidth(),
				imgb2.getIconHeight());
		instructions.setBounds(315, 250, imgb3.getIconWidth(), imgb3.getIconHeight());
		credits.setBounds(335, 295, imgb4.getIconWidth(), imgb4.getIconHeight());

		singlePlayer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				botao.play();
				menuTheme.stop();
				dispose();

				try {
					new SelectPlayer(1);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		});

		battleMode.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botao.play();
				menuTheme.stop();
				dispose();

				try {
					new SelectPlayer(2);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				// mata processo do frame atual
			}
		});

		instructions.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				 botao.play();
				 menuTheme.stop();
				 dispose();
				 new Instrucoes();
				 

			}
		});

		credits.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				botao.play();
				menuTheme.stop();
				dispose();
				try {
					new Credits();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		});

		singlePlayer.addMouseListener(this);
		battleMode.addMouseListener(this);
		instructions.addMouseListener(this);
		credits.addMouseListener(this);

		add(credits);
		add(instructions);
		add(battleMode);
		add(singlePlayer);
		add(s1);
		add(s2);
		add(menu);
		setVisible(true);
	}
	
	public Icon getImgfundo() {return Imgfundo;}
	public void setImgfundo(Icon imgfundo) {Imgfundo = imgfundo;}

	public Icon getImgb1() {return imgb1;}
	public void setImgb1(Icon imgb1) {this.imgb1 = imgb1;}

	public Icon getImgb2() {return imgb2;}
	public void setImgb2(Icon imgb2) {this.imgb2 = imgb2;}

	public Icon getImgb3() {return imgb3;}
	public void setImgb3(Icon imgb3) {this.imgb3 = imgb3;}

	public Icon getImgb4() {return imgb4;}
	public void setImgb4(Icon imgb4) {this.imgb4 = imgb4;}

	public Icon getSelect1() {return select1;}
	public void setSelect1(Icon select1) {this.select1 = select1;}

	public Icon getSelect2() {return select2;}
	public void setSelect2(Icon select2) {this.select2 = select2;}

	public JLabel getMenu() {return menu;}
	public void setMenu(JLabel menu) {this.menu = menu;}

	public JLabel getS1() {return s1;}
	public void setS1(JLabel s1) {this.s1 = s1;}

	public JLabel getS2() {return s2;}
	public void setS2(JLabel s2) {this.s2 = s2;}

	public JButton getSinglePlayer() {return singlePlayer;}
	public void setSinglePlayer(JButton singlePlayer) {this.singlePlayer = singlePlayer;}

	public JButton getBattleMode() {return battleMode;}
	public void setBattleMode(JButton battleMode) {this.battleMode = battleMode;}

	public JButton getOptions() {return instructions;}
	public void setOptions(JButton options) {this.instructions = options;}

	public JButton getCredits() {return credits;}
	public void setCredits(JButton credits) {this.credits = credits;}

	public AudioClip getMenuTheme() {return menuTheme;}
	public void setMenuTheme(AudioClip menuTheme) {this.menuTheme = menuTheme;}

	private void CarregarImagens_e_Som() throws MalformedURLException {

		Imgfundo = new ImageIcon(getClass().getResource("/menu/imagens menu/Menu01.png"));
		imgb1 = new ImageIcon(getClass().getResource("/menu/imagens menu/single02.png"));
		imgb2 = new ImageIcon(getClass().getResource("/menu/imagens menu/battle02.png"));
		imgb3 = new ImageIcon(getClass().getResource("/menu/imagens menu/instructions.png"));
		imgb4 = new ImageIcon(getClass().getResource("/menu/imagens menu/credits02.png"));
		select1 = new ImageIcon(getClass().getResource("/menu/imagens menu/kunaiSelect.png"));
		select2 = new ImageIcon(getClass()
				.getResource("/menu/imagens menu/kunaiSelect2.png"));

		menuTheme = Applet.newAudioClip(this.getClass().getResource(
				"/Audio/Frame wav/menuTheme.wav"));
		botao = Applet.newAudioClip(this.getClass().getResource(
		"/Audio/Frame wav/botaoClick_3.wav"));
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		s1.setIcon(select1);
		s2.setIcon(select2);

		if (e.getSource() == singlePlayer) {

			s1.setBounds(singlePlayer.getX() + 225, singlePlayer.getY() + 15,
					select1.getIconWidth(), select1.getIconHeight());
			s2.setBounds(singlePlayer.getX() - 100, singlePlayer.getY() + 15,
					select1.getIconWidth(), select1.getIconHeight());
		} else if (e.getSource() == battleMode) {

			s1.setBounds(battleMode.getX() + 225, battleMode.getY() + 15,
					select1.getIconWidth(), select1.getIconHeight());
			s2.setBounds(battleMode.getX() - 100, battleMode.getY() + 15,
					select1.getIconWidth(), select1.getIconHeight());
		} else if (e.getSource() == instructions) {

			s1.setBounds(instructions.getX() + 205, instructions.getY() + 15,
					select1.getIconWidth(), select1.getIconHeight());
			s2.setBounds(instructions.getX() - 105, instructions.getY() + 15,
					select1.getIconWidth(), select1.getIconHeight());
		} else if (e.getSource() == credits) {

			s1.setBounds(credits.getX() + 145, credits.getY() + 15,
					select1.getIconWidth(), select1.getIconHeight());
			s2.setBounds(credits.getX() - 100, credits.getY() + 15,
					select1.getIconWidth(), select1.getIconHeight());

		}
		s1.setVisible(true);
		s2.setVisible(true);

	}

	public void mouseExited(MouseEvent e) {
		if ((e.getSource() == singlePlayer) || (e.getSource() == battleMode)
				|| (e.getSource() == instructions) || (e.getSource() == credits)) {
			s1.setVisible(false);
			s2.setVisible(false);
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public static void main(String[] args) throws MalformedURLException {
		new Menu();
	}

}
