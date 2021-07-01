package menu;

import game.Game;
import game.Pontuacao;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
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

public class SelectPlayer extends JFrame implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon ImgFundo;
	private Icon pushStart;
	private Icon backMenu;
	private Icon portrait01[];
	private Icon portrait02[];
	private Icon portrait03[];
	private Icon portrait04[];
	private Icon Icon_n, Icon_s, Icon_k, Icon_y;
	
	private JLabel background = new JLabel();
	private JLabel l_n = new JLabel();
	private JLabel l_s = new JLabel();
	private JLabel l_k = new JLabel();
	private JLabel l_y = new JLabel();
	private JLabel record;
	
	
	private JButton b_naruto;
	private JButton b_sasuke;
	private JButton b_kakashi;
	private JButton b_yamato;
	private JButton start;
	private JButton backToMenu;
	
	private AudioClip selectNinja;
	private AudioClip pStart;
	private AudioClip back;
	
	private int player_1, player_2;
	private boolean cliked[];
	private int players, modo;
	private Pontuacao recorde;
	
	public SelectPlayer(int quant) throws MalformedURLException {
		super("Naruto JBattle");
		setSize(800, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/menu/imagens menu/icone.png"))
		.getImage());
		
		this.players = quant;
		this.modo = quant;
		
		CarregarImagens_e_Som();
		CriarBotoes();
		
		selectNinja.loop();
		background.setIcon(ImgFundo);
		background.setBounds(0, 0, ImgFundo.getIconWidth(), ImgFundo.getIconHeight());

		b_naruto.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if(VerificarQuantidade(1)){
						b_naruto.setEnabled(false);
						b_naruto.setDisabledIcon(portrait01[1]);
						
						if(player_1 == 0) player_1 = 1;
						else player_2 = 1;
						
						if(VerificarQuantidade(0) == false) start.setVisible(true);
						
					}
					
					
				}
		});
		
		
		b_sasuke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VerificarQuantidade(1)){
					b_sasuke.setEnabled(false);
					b_sasuke.setDisabledIcon(portrait02[1]);
					
					if(player_1 == 0) player_1 = 2;
					else player_2 = 2;
					
					if(VerificarQuantidade(0) == false) start.setVisible(true);
				}
				
			}
		});
		
		b_kakashi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VerificarQuantidade(1)){
					b_kakashi.setEnabled(false);
					b_kakashi.setDisabledIcon(portrait03[1]);
					
					if(player_1 == 0) player_1 = 3;
					else player_2 = 3;
					
					if(VerificarQuantidade(0) == false) start.setVisible(true);
				}
				
			}
		});
		
		b_yamato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VerificarQuantidade(1)){
					b_yamato.setEnabled(false);
					b_yamato.setDisabledIcon(portrait04[1]);

					if(player_1 == 0) player_1 = 4;
					else player_2 = 4;
					
					cliked[3] = true;
					if(VerificarQuantidade(0) == false) start.setVisible(true);
				}
				
			}
		});
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pStart.play();
				try {
					if(modo == 1){
						new Game(player_1, 1, 0, 0, 0, 1, 1, 0);
					}
					else new Game(player_1,player_2, 0, 0, 0, 2, 1, 0);
					
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				selectNinja.stop();
				dispose();
			}
		});
		
		backToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				back.play();
				selectNinja.stop();
				dispose();
				try {
					new Menu();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		if(modo == 1){
			recorde = new Pontuacao();
			Recorde();
			add(record);
		}
		
		
		b_naruto.addMouseListener(this);
		b_sasuke.addMouseListener(this);
		b_kakashi.addMouseListener(this);
		b_yamato.addMouseListener(this);
		
		
		add(l_n);
		add(l_s);
		add(l_k);
		add(l_y);
		add(b_naruto);
		add(b_sasuke);
		add(b_kakashi);
		add(b_yamato);
		add(start);
		add(backToMenu);
		add(background);
		
		
		start.setVisible(false);
		setVisible(true);
		
	}
	
	private void Recorde(){
		
		Font fonte = new Font("Serif", Font.BOLD, 22);
		record = new JLabel("Record: "+recorde.getPontuacaoAtual());
		record.setFont(fonte);
		record.setForeground(Color.WHITE);
		record.setBounds(65, 265, 200, 50);
		
	}
	
	private boolean VerificarQuantidade(int qt){
			if(this.players != 0){
				this.players -= qt;
				return true;
			}
			else{
				if(this.players > 0) return true;
				else return false;
			}
			
	}
	
	private void CriarBotoes(){
		
		b_naruto = new JButton(portrait01[0]);
		b_sasuke = new JButton(portrait02[0]);
		b_kakashi = new JButton(portrait03[0]);
		b_yamato = new JButton(portrait04[0]);
		start = new JButton(pushStart);
		backToMenu = new JButton(backMenu);
		
		b_naruto.setBorder(null);
		b_naruto.setContentAreaFilled(false);
		b_sasuke.setBorder(null);
		b_sasuke.setContentAreaFilled(false);
		b_kakashi.setBorder(null);
		b_kakashi.setContentAreaFilled(false);
		b_yamato.setBorder(null);
		b_yamato.setContentAreaFilled(false);
		start.setBorder(null);
		start.setContentAreaFilled(false);
		backToMenu.setBorder(null);
		backToMenu.setContentAreaFilled(false);
		
		b_naruto.setBounds(280+28, 98+50, portrait01[0].getIconWidth(), portrait01[0].getIconHeight());
		b_sasuke.setBounds(380+28, 97+50, portrait02[0].getIconWidth(), portrait02[0].getIconHeight());
		b_kakashi.setBounds(329+28, 50+50, portrait03[0].getIconWidth(), portrait03[0].getIconHeight());
		b_yamato.setBounds(330+28, 145+50, portrait04[0].getIconWidth(), portrait04[0].getIconHeight());
		start.setBounds(310, 290, pushStart.getIconWidth(), pushStart.getIconHeight());
		backToMenu.setBounds(325, 50, backMenu.getIconWidth(), backMenu.getIconHeight());
		
		cliked = new boolean[4];
		
		for (int i = 0; i < cliked.length; i++) {
			cliked[i] = false;
		}
	}
	
	private void CarregarImagens_e_Som() throws MalformedURLException{
		
		ImgFundo = new ImageIcon(getClass().getResource("/menu/imagens menu/PSelect.png"));
		pushStart = new ImageIcon(getClass().getResource("/menu/imagens menu/start.png"));
		backMenu = new ImageIcon(getClass().getResource("/menu/imagens menu/backToMenu.png"));
		
		portrait01 = new ImageIcon[2];
		portrait02 = new ImageIcon[2];
		portrait03 = new ImageIcon[2];
		portrait04 = new ImageIcon[2];
		
		for (int i = 0; i < portrait01.length; i++) {
			portrait01[i] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Portraits/Naruto_"+(i+1)+".png"));
			portrait02[i] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Portraits/Sasuke_"+(i+1)+".png"));
			portrait03[i] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Portraits/Kakashi_"+(i+1)+".png"));
			portrait04[i] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Portraits/Yamato_"+(i+1)+".png"));
		}
			
		Icon_n = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Portraits/Naruto_4.png"));
		Icon_s = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Portraits/sasuke.png"));
		Icon_k = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Portraits/Kakashi_4.png"));
		Icon_y = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Portraits/Yamato_5.png"));
		
		selectNinja = Applet.newAudioClip(this.getClass().getResource("/Audio/Frame wav/SelectNinja.wav"));
		back = Applet.newAudioClip(this.getClass().getResource("/Audio/Frame wav/botaoClick_1.wav"));
		pStart = Applet.newAudioClip(this.getClass().getResource("/Audio/Frame wav/hit.wav"));
	}

	
	public void mouseClicked(MouseEvent e) {
		
			
			if(e.getSource() == b_naruto){
				this.cliked[0] = true;
			}
			if(e.getSource() == b_sasuke){
				this.cliked[1] = true;
			}
			if(e.getSource() == b_kakashi){
				this.cliked[2] = true;
			}
			if(e.getSource() == b_yamato){
				this.cliked[3] = true;
			}
		
	}

	public void mouseEntered(MouseEvent e) {
		
		if(e.getSource() == b_naruto && players > 0){
			l_n.setIcon(Icon_n);
			if(player_1 == 0) l_n.setBounds(52, 120, Icon_n.getIconWidth(), Icon_n.getIconHeight());
			else if(cliked[0] == false)l_n.setBounds(612, 120, Icon_n.getIconWidth(), Icon_n.getIconHeight());
	
			l_n.setVisible(true);
		}
		if(e.getSource() == b_sasuke && players > 0){
			l_s.setIcon(Icon_s);
			if(player_1 == 0) l_s.setBounds(54,106,Icon_s.getIconWidth(),Icon_s.getIconHeight());
			else l_s.setBounds(612,106,Icon_s.getIconWidth(),Icon_s.getIconHeight());
			l_s.setVisible(true);
		}
		if(e.getSource() == b_kakashi && players > 0){
			l_k.setIcon(Icon_k);
			if(player_1 == 0) l_k.setBounds(40, 113, Icon_k.getIconWidth(), Icon_k.getIconHeight());
			else l_k.setBounds(602, 113, Icon_k.getIconWidth(), Icon_k.getIconHeight());
			l_k.setVisible(true);
		}
		if(e.getSource() == b_yamato && players > 0){
			l_y.setIcon(Icon_y);
			if(player_1 == 0) l_y.setBounds(47, 120, Icon_y.getIconWidth(), Icon_y.getIconHeight());
			else l_y.setBounds(607, 120, Icon_y.getIconWidth(), Icon_y.getIconHeight());
			l_y.setVisible(true);
		}
	}

	public void mouseExited(MouseEvent e) {

		if(e.getSource() == b_naruto && cliked[0] == false){
			l_n.setVisible(false);
		}
		if(e.getSource() == b_sasuke && cliked[1] == false){
			l_s.setVisible(false);
		}
		if(e.getSource() == b_kakashi && cliked[2] == false){
			l_k.setVisible(false);
		}
		if(e.getSource() == b_yamato && cliked[3] == false){
			l_y.setVisible(false);
		}
			
	}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}
