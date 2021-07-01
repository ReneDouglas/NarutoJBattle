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

public class Credits extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon bg = new ImageIcon(getClass().getResource("/menu/imagens menu/bg.png"));
	private JLabel imgBg = new JLabel();
	private JLabel sprites;
	private JLabel audio;
	private JLabel creditosSprite;
	private JLabel creditosAudio;
	private JLabel projeto;
	private JLabel projetoNome;
	private JLabel programacao;
	private JLabel nome;
	private Font fonte;

	private JButton retornar;
	private Icon backMenu = new ImageIcon(getClass()
			.getResource("/menu/imagens menu/backToMenu.png"));
	private AudioClip themeCredits;
	
	private AudioClip back;

	public Credits() throws MalformedURLException {
		super("Naruto JBattle");
		setSize(800, 428);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setIconImage(new ImageIcon(getClass().getResource("/menu/imagens menu/icone.png"))
		.getImage());

		imgBg.setIcon(bg);
		imgBg.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());

		themeCredits = Applet.newAudioClip(this.getClass().getResource("/Audio/Frame wav/credits.wav"));
		back = Applet.newAudioClip(this.getClass().getResource("/Audio/Frame wav/botaoClick_1.wav"));
		
		themeCredits.loop();

		retornar = new JButton(backMenu);
		retornar.setBorder(null);
		retornar.setContentAreaFilled(false);

		retornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				back.play();
				themeCredits.stop();
				dispose();
				try {
					new Menu();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		});

		retornar.setBounds(460, 310, backMenu.getIconWidth(),
				backMenu.getIconHeight());

		Escrever();

		add(sprites);
		add(creditosSprite);
		add(audio);
		add(creditosAudio);
		add(projeto);
		add(projetoNome);
		add(programacao);
		add(nome);
		add(retornar);
		add(imgBg);
		setVisible(true);
	}

	private void Escrever() {

		fonte = new Font("Serif", Font.BOLD, 36);
		sprites = new JLabel("Imagens");
		sprites.setFont(fonte);
		sprites.setBounds(100, 40, 150, 50);

		fonte = new Font("Serif", Font.BOLD, 15);
		creditosSprite = new JLabel("<html>http://spritedatabase.net/<br>"
				+ "NEIMAD<br>" + "Zebas1<br>" + "Kramlack<br>" + "DX-Yondy<br>"
				+ "DragonBallSpiderNaru<br>" + "Roger-Kun<br>"
				+ "Kazuchi</html>");
		creditosSprite.setFont(fonte);
		creditosSprite.setBounds(100, 85, 220, 190);

		fonte = new Font("Serif", Font.BOLD, 36);
		audio = new JLabel("Audio");
		audio.setFont(fonte);
		audio.setBounds(100, 260, 150, 50);

		fonte = new Font("Serif", Font.BOLD, 15);
		creditosAudio = new JLabel("<html>http://spritedatabase.net/<br>"
				+ "http://www.flashkit.com</html>");
		creditosAudio.setFont(fonte);
		creditosAudio.setBounds(100, 310, 220, 35);

		fonte = new Font("Serif", Font.BOLD, 36);
		projeto = new JLabel("Projeto");
		projeto.setFont(fonte);
		projeto.setBounds(410, 40, 220, 50);

		fonte = new Font("Arial", Font.BOLD, 14);
		projetoNome = new JLabel("<html>Projeto para a Disciplina:<br>"
				+ "<html>Modelagem e Programação Orientada a Objetos<br>"
				+ "Orientador: Prof. Richarlyson Alves D'Emery<br>"
				+ "Universidade Federal Rural de Pernambuco<br>"
				+ "Unidade Acadêmica de Serra Talhada<br>"
				+ "Curso de Sistemas de Informação");
		projetoNome.setFont(fonte);
		projetoNome.setBounds(410, 90, 350, 130);

		fonte = new Font("Serif", Font.BOLD, 28);
		programacao = new JLabel("Programação");
		programacao.setFont(fonte);
		programacao.setBounds(410, 220, 330, 50);

		fonte = new Font("Serif", Font.BOLD, 18);
		nome = new JLabel("Renê Douglas Nobre de Morais");
		nome.setFont(fonte);
		nome.setBounds(410, 260, 250, 40);
	}
}
