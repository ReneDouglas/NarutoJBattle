package menu;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Instrucoes extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon imgFundo;
	private JButton retornar;
	private JLabel player_1, player_2;
	private JLabel instructions;
	private JLabel up_1, left_1, right_1, up_2, left_2, right_2;
	private JLabel atk_1, shuriken_1, special_1, rec_1, atk_2, shuriken_2, special_2, rec_2;
	private AudioClip themeInstructions;
	private AudioClip back;
	
	public Instrucoes() {
		super("Naruto JBattle");
		setSize(800,428);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setIconImage(new ImageIcon(getClass().getResource("/menu/imagens menu/icone.png")).getImage());
		
		instructions = new JLabel();
		imgFundo = new ImageIcon(getClass().getResource("/menu/imagens menu/bg.png"));
		instructions.setIcon(imgFundo);
		instructions.setBounds(0, 0, imgFundo.getIconWidth(), imgFundo.getIconHeight());
		
		themeInstructions = Applet.newAudioClip(this.getClass().getResource("/Audio/Frame wav/Instructions.wav"));
		back = Applet.newAudioClip(this.getClass().getResource("/Audio/Frame wav/botaoClick_1.wav"));
		themeInstructions.loop();
		
		Icon backMenu = new ImageIcon(getClass().getResource("/menu/imagens menu/backToMenu.png"));
		retornar = new JButton(backMenu);
		retornar.setBorder(null);
		retornar.setContentAreaFilled(false);

		retornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				back.play();
				themeInstructions.stop();
				dispose();
				try {
					new Menu();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		});

		retornar.setBounds(300, 310, backMenu.getIconWidth(),
				backMenu.getIconHeight());
		
		Escrever();
		
		add(player_1);
		add(player_2);
		add(up_1);
		add(up_2);
		add(left_1);
		add(left_2);
		add(right_1);
		add(right_2);
		add(atk_1);
		add(atk_2);
		add(shuriken_1);
		add(shuriken_2);
		add(special_1);
		add(special_2);
		add(rec_1);
		add(rec_2);
		add(retornar);
		add(instructions);
		setVisible(true);
		
	}
	
	
	private void Escrever(){
		
		// PLAYER 1
		
		Font fonte_1 = new Font("Serif", Font.BOLD, 36);
		player_1 = new JLabel("Player 1");
		player_1.setFont(fonte_1);
		player_1.setBounds(100, 40, 150, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		up_1 = new JLabel("UP ==> Seta Cima");
		up_1.setFont(fonte_1);
		up_1.setBounds(100, 100, 200, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		left_1 = new JLabel("LEFT ==> Seta Esquerda");
		left_1.setFont(fonte_1);
		left_1.setBounds(100, 130, 200, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		right_1 = new JLabel("RIGHT ==> Seta Direita");
		right_1.setFont(fonte_1);
		right_1.setBounds(100, 160, 200, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		atk_1 = new JLabel("ATAQUE ==> A");
		atk_1.setFont(fonte_1);
		atk_1.setBounds(100, 190, 200, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		shuriken_1 = new JLabel("SHURIKEN ==> S");
		shuriken_1.setFont(fonte_1);
		shuriken_1.setBounds(100, 220, 200, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		special_1 = new JLabel("JUTSU ==> D");
		special_1.setFont(fonte_1);
		special_1.setBounds(100, 250, 200, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		rec_1 = new JLabel("RECUPERAR ==> X");
		rec_1.setFont(fonte_1);
		rec_1.setBounds(100, 280, 200, 50);
		
		// PLAYER 2
		
		fonte_1 = new Font("Serif", Font.BOLD, 36);
		player_2 = new JLabel("Player 2");
		player_2.setFont(fonte_1);
		player_2.setBounds(500, 40, 150, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		up_2 = new JLabel("UP ==> 8");
		up_2.setFont(fonte_1);
		up_2.setBounds(500, 100, 200, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		left_2 = new JLabel("LEFT ==> 4");
		left_2.setFont(fonte_1);
		left_2.setBounds(500, 130, 200, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		right_2 = new JLabel("RIGHT ==> 6");
		right_2.setFont(fonte_1);
		right_2.setBounds(500, 160, 200, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		atk_2 = new JLabel("ATAQUE ==> I");
		atk_2.setFont(fonte_1);
		atk_2.setBounds(500, 190, 200, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		shuriken_2 = new JLabel("SHURIKEN ==> O");
		shuriken_2.setFont(fonte_1);
		shuriken_2.setBounds(500, 220, 200, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		special_2 = new JLabel("JUTSU ==> P");
		special_2.setFont(fonte_1);
		special_2.setBounds(500, 250, 200, 50);
		
		fonte_1 = new Font("Serif", Font.BOLD, 18);
		rec_2 = new JLabel("RECUPERAR ==> L");
		rec_2.setFont(fonte_1);
		rec_2.setBounds(500, 280, 200, 50);
		
	}

}
