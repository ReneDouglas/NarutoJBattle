package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import menu.SelectPlayer;

import personagens.Kakashi;
import personagens.Naruto;
import personagens.Ninja;
import personagens.Sasuke;
import personagens.Yamato;

import cenarios.Cenario;

public class Game extends JFrame implements Runnable, Cenario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon image[];
	private Icon Player_1life, Player_2life, bLife1, bLife2, bChakra1, bChakra2,
			port_p1, port_p2;
	private Icon icon_r[], icon_fight;
	private Icon r1, r2, r3;
	private Icon lb_shurikenP1[], lb_shurikenP2[];
	private JLabel estagio;
	private JLabel PLife_1, PLife_2;
	private JLabel barraLife1, barraLife2;
	private JLabel barraChakra1, barraChakra2;
	private JLabel portrait_p1, portrait_p2;
	private JLabel r_1, r_2, r_3;
	private JLabel rounds[];
	private JLabel p1_shuriken, p2_shuriken;
	private JLabel fight;

	private Ninja Player_1, Player_2;

	private Thread p1, p2;
	private Thread jogo;
	private Controlador pc;

	private ArrayList<Integer> ar1, ar2;

	private int i = 0;
	private int jogador_1, jogador_2, round;
	private int player01, player02;
	private int modoDeJogo;
	private int adversario;
	private int gerado;
	private int pontuacao;
	private double ajustarLabelVida = 1.65;
	private double ajustarLabelChakra = 1.05;
	private boolean rodando = true;
	private boolean e1 = true;

	private AudioClip aux, aux_win;
	private AudioClip battleSong[];
	private Random audio;
	private AudioClip audioRandom;
	private Pontuacao pontuacaoJogador;
	
	private TheFinalCountdown contagem;

	public Game(int player_1, int player_2, int round, int j1, int j2,
			int modoDeJogo, int adversario, int pontuacao) throws MalformedURLException {
		super("Naruto JBattle");
		setSize(800, 428);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setIconImage(new ImageIcon(getClass().getResource("/menu/imagens menu/icone.png"))
		.getImage());
		
		player01 = player_1;
		player02 = player_2;
		this.round = round;
		this.adversario = adversario;
		this.pontuacao = pontuacao;
		
		this.jogador_1 = j1;
		this.jogador_2 = j2;
		this.modoDeJogo = modoDeJogo;
		
		pontuacaoJogador = new Pontuacao();
		contagem = new TheFinalCountdown();

		CriarLabels();
		CarregarPersonagens(player_1, player_2);
		if(modoDeJogo == 1) UpgradeAdversario();
		CarregarImagens_e_Som();

		ar1 = new ArrayList<Integer>();
		ar2 = new ArrayList<Integer>();

		audio = new Random();
		gerado = audio.nextInt(3);

		if (gerado == 0)
			audioRandom = battleSong[0];
		if (gerado == 1)
			audioRandom = battleSong[1];
		if (gerado == 2)
			audioRandom = battleSong[2];

		audioRandom.loop();

		jogo = new Thread(this);
		jogo.start();

		add(Player_1);
		add(Player_2);
		add(PLife_1);
		add(PLife_2);
		add(barraLife1);
		add(barraLife2);
		add(barraChakra1);
		add(barraChakra2);
		add(portrait_p1);
		add(portrait_p2);
		add(r_1);
		add(r_2);
		add(r_3);
		for (int i = 0; i < rounds.length; i++)
			add(rounds[i]);
		add(fight);
		add(p1_shuriken);
		add(p2_shuriken);
		add(contagem.getNumero());
		add(estagio);

		setVisible(true);
		
	}
	
	public double getAjustarLabelVida() {return ajustarLabelVida;}
	public void setAjustarLabelVida(double ajustarLabelVida) {this.ajustarLabelVida = ajustarLabelVida;}

	public double getAjustarLabelChakra() {return ajustarLabelChakra;}
	public void setAjustarLabelChakra(double ajustarLabelChakra) {this.ajustarLabelChakra = ajustarLabelChakra;}

	public int getAdversario() {return adversario;}
	public void setAdversario(int adversario) {this.adversario = adversario;}

	public Icon[] getImage() {return image;}
	public void setImage(Icon[] image) {this.image = image;}

	public Icon getPlayer_1life() {return Player_1life;}
	public void setPlayer_1life(Icon player_1life) {Player_1life = player_1life;}

	public Icon getPlayer_2life() {return Player_2life;}
	public void setPlayer_2life(Icon player_2life) {Player_2life = player_2life;}

	public Icon getbLife1() {return bLife1;}
	public void setbLife1(Icon bLife1) {this.bLife1 = bLife1;}

	public Icon getbLife2() {return bLife2;}
	public void setbLife2(Icon bLife2) {this.bLife2 = bLife2;}

	public Icon getbChakra1() {return bChakra1;}
	public void setbChakra1(Icon bChakra1) {this.bChakra1 = bChakra1;}

	public Icon getbChakra2() {return bChakra2;}
	public void setbChakra2(Icon bChakra2) {this.bChakra2 = bChakra2;}

	public Icon getPort_p1() {return port_p1;}
	public void setPort_p1(Icon port_p1) {this.port_p1 = port_p1;}

	public Icon getPort_p2() {return port_p2;}
	public void setPort_p2(Icon port_p2) {this.port_p2 = port_p2;}

	public Icon[] getIcon_r() {return icon_r;}
	public void setIcon_r(Icon[] icon_r) {this.icon_r = icon_r;}

	public Icon getIcon_fight() {return icon_fight;}
	public void setIcon_fight(Icon icon_fight) {this.icon_fight = icon_fight;}

	public Icon getR1() {return r1;}
	public void setR1(Icon r1) {this.r1 = r1;}

	public Icon getR2() {return r2;}
	public void setR2(Icon r2) {this.r2 = r2;}

	public Icon getR3() {return r3;}
	public void setR3(Icon r3) {this.r3 = r3;}

	public Icon[] getLb_shurikenP1() {return lb_shurikenP1;}
	public void setLb_shurikenP1(Icon[] lb_shurikenP1) {this.lb_shurikenP1 = lb_shurikenP1;}

	public Icon[] getLb_shurikenP2() {return lb_shurikenP2;}
	public void setLb_shurikenP2(Icon[] lb_shurikenP2) {this.lb_shurikenP2 = lb_shurikenP2;}

	public JLabel getEstagio() {return estagio;}
	public void setEstagio(JLabel estagio) {this.estagio = estagio;}

	public JLabel getPLife_1() {return PLife_1;}
	public void setPLife_1(JLabel pLife_1) {PLife_1 = pLife_1;}
	
	public JLabel getPLife_2() {return PLife_2;}
	public void setPLife_2(JLabel pLife_2) {PLife_2 = pLife_2;}

	public JLabel getBarraLife1() {return barraLife1;}
	public void setBarraLife1(JLabel barraLife1) {this.barraLife1 = barraLife1;}

	public JLabel getBarraLife2() {return barraLife2;}
	public void setBarraLife2(JLabel barraLife2) {this.barraLife2 = barraLife2;}

	public JLabel getBarraChakra1() {return barraChakra1;}
	public void setBarraChakra1(JLabel barraChakra1) {this.barraChakra1 = barraChakra1;}

	public JLabel getBarraChakra2() {return barraChakra2;}
	public void setBarraChakra2(JLabel barraChakra2) {this.barraChakra2 = barraChakra2;}

	public JLabel getPortrait_p1() {return portrait_p1;}
	public void setPortrait_p1(JLabel portrait_p1) {this.portrait_p1 = portrait_p1;}

	public JLabel getPortrait_p2() {return portrait_p2;}
	public void setPortrait_p2(JLabel portrait_p2) {this.portrait_p2 = portrait_p2;}

	public JLabel getR_1() {return r_1;}
	public void setR_1(JLabel r_1) {this.r_1 = r_1;}

	public JLabel getR_2() {return r_2;}
	public void setR_2(JLabel r_2) {this.r_2 = r_2;}

	public JLabel getR_3() {return r_3;}
	public void setR_3(JLabel r_3) {this.r_3 = r_3;}

	public JLabel[] getRounds() {return rounds;}
	public void setRounds(JLabel[] rounds) {this.rounds = rounds;}

	public JLabel getP1_shuriken() {return p1_shuriken;}
	public void setP1_shuriken(JLabel p1_shuriken) {this.p1_shuriken = p1_shuriken;}

	public JLabel getP2_shuriken() {return p2_shuriken;}
	public void setP2_shuriken(JLabel p2_shuriken) {this.p2_shuriken = p2_shuriken;}

	public JLabel getFight() {return fight;}
	public void setFight(JLabel fight) {this.fight = fight;}

	public Ninja getPlayer_1() {return Player_1;}
	public void setPlayer_1(Ninja player_1) {Player_1 = player_1;}

	public Ninja getPlayer_2() {return Player_2;}
	public void setPlayer_2(Ninja player_2) {Player_2 = player_2;}

	public Thread getP1() {return p1;}
	public void setP1(Thread p1) {this.p1 = p1;}

	public Thread getP2() {return p2;}
	public void setP2(Thread p2) {this.p2 = p2;}

	public Thread getJogo() {return jogo;}
	public void setJogo(Thread jogo) {this.jogo = jogo;}

	public Controlador getPc() {return pc;}
	public void setPc(Controlador pc) {this.pc = pc;}

	public ArrayList<Integer> getAr1() {return ar1;}
	public void setAr1(ArrayList<Integer> ar1) {this.ar1 = ar1;}

	public ArrayList<Integer> getAr2() {return ar2;}
	public void setAr2(ArrayList<Integer> ar2) {this.ar2 = ar2;}

	public int getI() {return i;}
	public void setI(int i) {this.i = i;}

	public boolean isE1() {return e1;}
	public void setE1(boolean e1) {this.e1 = e1;}

	public int getJogador_1() {return jogador_1;}
	public void setJogador_1(int jogador_1) {this.jogador_1 = jogador_1;}

	public int getJogador_2() {return jogador_2;}
	public void setJogador_2(int jogador_2) {this.jogador_2 = jogador_2;}

	public int getRound() {return round;}
	public void setRound(int round) {this.round = round;}

	public int getPlayer01() {return player01;}
	public void setPlayer01(int player01) {this.player01 = player01;}

	public int getPlayer02() {return player02;}
	public void setPlayer02(int player02) {this.player02 = player02;}
 
	public int getModoDeJogo() {return modoDeJogo;}
	public void setModoDeJogo(int modoDeJogo) {this.modoDeJogo = modoDeJogo;}

	public boolean isRodando() {return rodando;}
	public void setRodando(boolean rodando) {this.rodando = rodando;}

	public AudioClip getAux() {return aux;}
	public void setAux(AudioClip aux) {this.aux = aux;}

	public AudioClip getAux_win() {return aux_win;}
	public void setAux_win(AudioClip aux_win) {this.aux_win = aux_win;}

	public AudioClip[] getBattleSong() {return battleSong;}
	public void setBattleSong(AudioClip[] battleSong) {this.battleSong = battleSong;}

	public Random getAudio() {return audio;}
	public void setAudio(Random audio) {this.audio = audio;}

	public AudioClip getAudioRandom() {return audioRandom;}
	public void setAudioRandom(AudioClip audioRandom) {this.audioRandom = audioRandom;}

	public int getGerado() {return gerado;}
	public void setGerado(int gerado) {this.gerado = gerado;}
	
	private void CarregarPersonagens(int player_1, int player_2)
			throws MalformedURLException {

		switch (player_1) {
		case 1:
			Player_1 = new Naruto(20, CHAO, 1, 1);
			port_p1 = new ImageIcon(getClass().getResource(
					"/sprites/Naruto sprites/Naruto Portraits/Naruto_3.png"));

			break;

		case 2:
			Player_1 = new Sasuke(20, CHAO, 1, 1);
			port_p1 = new ImageIcon(getClass().getResource(
					"/sprites/Sasuke sprites/Sasuke Portraits/Sasuke_3.png"));
			break;

		case 3:
			Player_1 = new Kakashi(20, CHAO, 1, 1);
			port_p1 = new ImageIcon(getClass().getResource(
					"/sprites/Kakashi sprites/Kakashi Portraits/Kakashi_3.png"));
			break;

		case 4:
			Player_1 = new Yamato(20, CHAO, 1, 1);
			port_p1 = new ImageIcon(getClass().getResource(
			"/sprites/Yamato sprites/Yamato Portraits/Yamato_3.png"));
			break;

		default:
			break;
		}
		Player_1.setGanhou(false);
		p1 = new Thread(Player_1);
		p1.start();

		switch (player_2) {
		case 1:
			Player_2 = new Naruto(710, CHAO, 2, 2);
			port_p2 = new ImageIcon(getClass().getResource(
					"/sprites/Naruto sprites/Naruto Portraits/Naruto_3.png"));
			break;

		case 2:
			Player_2 = new Sasuke(710, CHAO, 2, 2);
			port_p2 = new ImageIcon(getClass().getResource(
					"/sprites/Sasuke sprites/Sasuke Portraits/Sasuke_3.png"));
			break;

		case 3:
			Player_2 = new Kakashi(710, CHAO, 2, 2);
			port_p2 = new ImageIcon(getClass().getResource(
					"/sprites/Kakashi sprites/Kakashi Portraits/Kakashi_3.png"));

			break;

		case 4:
			Player_2 = new Yamato(710, CHAO, 2, 2);
			port_p2 = new ImageIcon(getClass().getResource(
			"/sprites/Yamato sprites/Yamato Portraits/Yamato_3.png"));
			
			break;
		default:
			break;
		}
		//Player_2.setGanhou(false);
		p2 = new Thread(Player_2);
		p2.start();
	}

	public void run() {

		VerificarRound(jogador_1, jogador_2);
		if (modoDeJogo == 1) {
			pc = new Controlador(Player_2);
			pc.start();
		}

		addKeyListener(Player_1);
		if (modoDeJogo != 1)
			addKeyListener(Player_2);

		contagem.Contar();
		
		while (rodando) {

			Frames(FPS + 30);

			if (modoDeJogo == 1)
				pc.VerificarDistancia(Player_1.getEstado(), Player_1.getSentido(),
						Player_1.getX(), Player_1.getY());

			VerificarTipoDeColisao(Player_1.getBounds(), Player_2.getBounds());
			ColisaoShuriken(Player_1, Player_2);
			ColisaoRasengan(Player_1.getBounds(), Player_2.getBounds());
			ColisaoChidori(Player_1.getBounds(), Player_2.getBounds());
			ColisaoGoukakyuu(Player_1.getBounds(), Player_2.getBounds());
			ColisaoMokuton(Player_1.getBounds(), Player_2.getBounds());
			try {
				VerificarTempo();
				VerificarVida();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			VerificarChakra();
			VerificarShuriken();
			
			

		}
		try {
			NovoRound();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.repaint();
	}
	
	@SuppressWarnings("deprecation")
	private void VerificarTempo() throws InterruptedException{
		
		if(contagem.Terminou()){
			
			if(Player_1.getPontosDeVida() > Player_2.getPontosDeVida()){
				
				Thread.sleep(1300);
				p2.stop();
				if(modoDeJogo == 1) pc.stop();
				jogador_1 += 1;
				Winner(Player_1, p1, e1);
				rodando = false;
			}
			else if(Player_1.getPontosDeVida() < Player_2.getPontosDeVida()){
				
				Thread.sleep(1300);
				p1.stop();
				jogador_2 += 1;
				Winner(Player_2, p2, e1);
				rodando = false;
			}
			else{
				rodando = false;
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void VerificarVida() throws InterruptedException {

		barraLife1.setBounds(45 + 45, 30, (int) (Player_1.getPontosDeVida()*1.65),
				bLife1.getIconHeight());
		barraLife2.setBounds((int) (540 - Player_2.getVidaLabel()*ajustarLabelVida), 30,
				(int) (Player_2.getPontosDeVida()*ajustarLabelVida), bLife2.getIconHeight());

		if (Player_1.getPontosDeVida() <= 0) {
			Thread.sleep(1300);
			Player_1.setPontosDeVida(0);
			p1.stop();
			jogador_2 += 1;
			Winner(Player_2, p2, e1);
			rodando = false;
		} else if (Player_2.getPontosDeVida() <= 0) {
			Thread.sleep(1300);
			Player_2.setPontosDeVida(0);
			p2.stop();
			if(modoDeJogo == 1) pc.stop();
			jogador_1 += 1;
			Winner(Player_1, p1, e1);
			rodando = false;
		}
	}

	private void VerificarChakra() {

		barraChakra1.setBounds(75 + 45, 58, (int) (Player_1.getPontosDeChakra()*1.05),
				bChakra1.getIconHeight());
		barraChakra2.setBounds((int) (570 - Player_2.getChakraLabel()*ajustarLabelChakra), 58,
				(int) (Player_2.getPontosDeChakra()*ajustarLabelChakra), bChakra2.getIconHeight());

	}

	private void VerificarShuriken() {

		switch (Player_1.getShurikens()) {

		case 4:
			p1_shuriken.setIcon(lb_shurikenP1[4]);
			p1_shuriken.setBounds(10, 100, lb_shurikenP1[4].getIconWidth(),
					lb_shurikenP1[4].getIconHeight());
			break;
		case 3:
			p1_shuriken.setIcon(lb_shurikenP1[3]);
			p1_shuriken.setBounds(10, 100, lb_shurikenP1[3].getIconWidth(),
					lb_shurikenP1[3].getIconHeight());
			break;
		case 2:
			p1_shuriken.setIcon(lb_shurikenP1[2]);
			p1_shuriken.setBounds(10, 100, lb_shurikenP1[2].getIconWidth(),
					lb_shurikenP1[2].getIconHeight());
			break;
		case 1:
			p1_shuriken.setIcon(lb_shurikenP1[1]);
			p1_shuriken.setBounds(10, 100, lb_shurikenP1[1].getIconWidth(),
					lb_shurikenP1[1].getIconHeight());
			break;
		case 0:
			p1_shuriken.setIcon(lb_shurikenP1[0]);
			p1_shuriken.setBounds(10, 100, lb_shurikenP1[0].getIconWidth(),
					lb_shurikenP1[0].getIconHeight());
			break;
		}
		switch (Player_2.getShurikens()) {

		case 4:
			p2_shuriken.setIcon(lb_shurikenP2[4]);
			p2_shuriken.setBounds(740, 100, lb_shurikenP2[4].getIconWidth(),
					lb_shurikenP2[4].getIconHeight());
			break;
		case 3:
			p2_shuriken.setIcon(lb_shurikenP2[3]);
			p2_shuriken.setBounds(740, 100, lb_shurikenP2[3].getIconWidth(),
					lb_shurikenP2[3].getIconHeight());
			break;
		case 2:
			p2_shuriken.setIcon(lb_shurikenP2[2]);
			p2_shuriken.setBounds(740, 100, lb_shurikenP2[2].getIconWidth(),
					lb_shurikenP2[2].getIconHeight());
			break;
		case 1:
			p2_shuriken.setIcon(lb_shurikenP2[1]);
			p2_shuriken.setBounds(740, 100, lb_shurikenP2[1].getIconWidth(),
					lb_shurikenP2[1].getIconHeight());
			break;
		case 0:
			p2_shuriken.setIcon(lb_shurikenP2[0]);
			p2_shuriken.setBounds(740, 100, lb_shurikenP2[0].getIconWidth(),
					lb_shurikenP2[0].getIconHeight());
			break;
		}
	}

	void VerificarTipoDeColisao(Rectangle player_1, Rectangle player_2) {

		if (player_1.intersects(player_2)) {

			ColisaoDeAtaque();
			ColisaoDeContato();

		} else {
			ColisaoComParede();

			i = 0;
			ar1.clear();
			ar2.clear();
		}
	}

	private void ColisaoDeAtaque() {

		if (Player_1.getEstado() == 3) {

			if (Player_1.getSentido() != Player_2.getSentido()) {
				Player_2.DiminuirVida(Player_1.Dano(Player_1.getAtaqueBase()));
				Player_2.Recuar("SentidoContrario");
			} else {
				Player_2.DiminuirVida(Player_1.Dano(Player_1.getAtaqueBase()));
				Player_2.Recuar("msmSentido");
			}
		} else if (Player_2.getEstado() == 3) {

			if (Player_2.getSentido() != Player_1.getSentido()) {
				Player_1.DiminuirVida(Player_2.Dano(Player_2.getAtaqueBase()));
				Player_1.Recuar("SentidoContrario");
			} else {
				Player_1.DiminuirVida(Player_2.Dano(Player_2.getAtaqueBase()));
				Player_1.Recuar("msmSentido");
			}

		}
	}

	private void ColisaoDeContato() {

		ar1.add(i, Player_1.getSentido());
		ar2.add(i, Player_2.getSentido());
		Player_1.setVerSentido((Integer) ar1.get(0));
		Player_2.setVerSentido((Integer) ar2.get(0));

		i++;

		Player_1.Colidiu();
		Player_2.Colidiu();

	}

	private void ColisaoComParede() {

		if ((Player_1.getX() <= PAREDE_ESQUERDA && Player_1.getSentido() == 2)
				|| (Player_1.getX() >= PAREDE_DIREITA && Player_1.getSentido() == 1)) {
			Player_1.setObstaculo(1);

		} else
			Player_1.setObstaculo(0);

		if ((Player_2.getX() <= PAREDE_ESQUERDA && Player_2.getSentido() == 2)
				|| (Player_2.getX() >= PAREDE_DIREITA && Player_2.getSentido() == 1)) {
			Player_2.setObstaculo(1);
		} else
			Player_2.setObstaculo(0);

	}

	private void ColisaoShuriken(Ninja player01, Ninja player02) {

		if (player01.getShuriken() != null) {
			if (modoDeJogo == 1)
				pc.VerificarDistanciaProjetil(player01.getShuriken().getSentido(),
						player01.getShuriken().getX(), player01.getShuriken().getY());

			if (Player_1.getShuriken().getBounds().intersects(Player_2.getBounds())) {

				if (Player_1.getSentido() != Player_2.getSentido()) {
					if (!Player_2.isInvuneravel()) {
						Player_2.setVerDano(true);
						Player_2.DiminuirVida(Player_1.Dano(DANO_SHURIKEN));
						Player_2.Recuar("SentidoContrario");

					}

				} else {
					if (!Player_2.isInvuneravel()) {
						Player_2.setVerDano(true);
						Player_2.DiminuirVida(Player_1.Dano(DANO_SHURIKEN));
						Player_2.Recuar("msmSentido");

					}
				}

				Player_1.getShuriken().setObstaculo(true);
				Player_1.setShuriken(null);
				Player_2.setVerDano(false);
			}

		}
		if (player02.getShuriken() != null) {
			if (Player_2.getShuriken().getBounds().intersects(Player_1.getBounds())) {

				if (Player_2.getSentido() != Player_1.getSentido()) {
					if (!Player_1.isInvuneravel()) {
						Player_1.setVerDano(true);
						Player_1.DiminuirVida(Player_2.Dano(DANO_SHURIKEN));
						Player_1.Recuar("SentidoContrario");

					}
				} else {
					if (!Player_1.isInvuneravel()) {
						Player_1.setVerDano(true);
						Player_1.DiminuirVida(Player_2.Dano(DANO_SHURIKEN));
						Player_1.Recuar("msmSentido");

					}
				}

				Player_2.getShuriken().setObstaculo(true);
				Player_2.setShuriken(null);
				Player_1.setVerDano(false);
			}
		}

	}

	private synchronized void ColisaoRasengan(Rectangle Player01,
			Rectangle Player02) {

		if (Player_1 instanceof Naruto) {
			if (Player_1.getRasengan() != null
					&& (((Naruto) Player_1).isUnlock())) {
				if (Player_1.getRasengan().isImpacto()) {
					if (Player_1.getRasengan().getBounds().intersects(Player02)) {

						Player_1.getRasengan().setAcertou(true);
						aux = ((Naruto) Player_1).getRasengan().getRasengan_effect02();
						aux.play();

						if (Player_1.getSentido() != Player_2.getSentido()) {
							Player_2.Recuar("SentidoContrario");
						} else {
							Player_2.Recuar("msmSentido");
						}
						Player_1.setRasengan(null);
						Player_2.DiminuirVida(Player_1
								.Dano(((Naruto) (Player_1)).DANO_RASENGAN));

					} else {
						Player_1.getRasengan().Matar();
						Player_1.setRasengan(null);
						System.gc();
					}

				}

			}
			((Naruto) Player_1).setUnlock(false);
		}

		if (Player_2 instanceof Naruto) {
			if (Player_2.getRasengan() != null
					&& (((Naruto) Player_2).isUnlock())) {
				if (Player_2.getRasengan().isImpacto()) {
					if (Player_2.getRasengan().getBounds().intersects(Player01)) {

						Player_2.getRasengan().setAcertou(true);
						aux = ((Naruto) Player_2).getRasengan().getRasengan_effect02();
						aux.play();

						if (Player_2.getSentido() != Player_1.getSentido()) {
							Player_1.Recuar("SentidoContrario");
						} else {
							Player_1.Recuar("msmSentido");
						}
						Player_2.setRasengan(null);
						Player_1.DiminuirVida(Player_2
								.Dano(((Naruto) (Player_2)).DANO_RASENGAN));

					} else {
						Player_2.getRasengan().Matar();
						Player_2.setRasengan(null);
						System.gc();
					}

				}

			}
			((Naruto) Player_2).setUnlock(false);
		}

	}

	private void ColisaoChidori(Rectangle Player01, Rectangle Player02) {

		if (Player_1 instanceof Kakashi) {
			AudioClip aux;
			if (Player_1.isLancarChidori()) {
				if (Player_1.getBounds().intersects(Player02)) {

					aux = ((Kakashi) Player_1).getRaikiri_04();
					aux.play();

					if (Player_1.getSentido() != Player_2.getSentido()) {
						Player_2.Recuar("SentidoContrario");

					} else {
						Player_2.Recuar("msmSentido");
					}
					Player_2.DiminuirVida(Player_1
							.Dano(((Kakashi) (Player_1)).DANO_CHIDORI));
				}
			}
		}

		if (Player_2 instanceof Kakashi) {
			AudioClip aux;
			if (Player_2.isLancarChidori()) {
				if (Player_2.getBounds().intersects(Player01)) {

					aux = ((Kakashi) Player_2).getRaikiri_04();
					aux.play();

					if (Player_2.getSentido() != Player_1.getSentido()) {
						Player_1.Recuar("SentidoContrario");
					} else {
						Player_1.Recuar("msmSentido");
					}
					Player_1.DiminuirVida(Player_2
							.Dano(((Kakashi) (Player_2)).DANO_CHIDORI));
				}
			}
		}
	}

	private void ColisaoGoukakyuu(Rectangle Player01, Rectangle Player02) {

		if (Player_1 instanceof Sasuke) {
			if (Player_1.getGoukakyuu() != null) {
				
				if (modoDeJogo == 1)
					pc.VerificarDistanciaProjetil(Player_1.getGoukakyuu().getSentido(),
							Player_1.getGoukakyuu().getX(),Player_1.getGoukakyuu().getY());
				
				if (Player_1.getGoukakyuu().getBounds().intersects(Player02)) {
					if (Player_1.getSentido() != Player_2.getSentido()) {
						if (!Player_2.isInvuneravel()) {
							Player_2.setVerDano(true);
							Player_2.Recuar("SentidoContrario");

						}
					} else {
						if (!Player_2.isInvuneravel()) {
							Player_2.setVerDano(true);
							Player_2.Recuar("msmSentido");

						}
					}
					Player_1.getGoukakyuu().impacto = true;
					Player_2.DiminuirVida(Player_1
							.Dano(((Sasuke) (Player_1)).DANO_GOUKAKYUU));
					Player_1.setGoukakyuu(null);
				}
			}
		}
		if (Player_2 instanceof Sasuke) {
			if (Player_2.getGoukakyuu() != null) {
				if (Player_2.getGoukakyuu().getBounds().intersects(Player01)) {
					if (Player_2.getSentido() != Player_1.getSentido()) {
						if (!Player_1.isInvuneravel()) {
							Player_1.Recuar("SentidoContrario");
							Player_1.setVerDano(true);
						}
					} else {
						if (!Player_1.isInvuneravel()) {
							Player_1.Recuar("msmSentido");
							Player_1.setVerDano(true);
						}
					}
					// Player_2.lancarGoukakyuu = false;
					Player_2.getGoukakyuu().impacto = true;
					Player_1.DiminuirVida(Player_2
							.Dano(((Sasuke) (Player_2)).DANO_GOUKAKYUU));
					Player_2.setGoukakyuu(null);
				}
			}
		}
	}
	
	private void ColisaoMokuton(Rectangle player01, Rectangle player02){
		
		if(Player_1 instanceof Yamato){
			if(Player_1.getMokuton() != null){
				if(Player_1.getMokuton().getBounds().intersects(player02)){
					if(Player_1.getSentido() != Player_2.getSentido()){
						if(!Player_2.isInvuneravel()){
							Player_2.Recuar("SentidoContrario");
						}
					}else{
						if(!Player_2.isInvuneravel()){
							Player_2.Recuar("msmSentido");
						}
					}
					Player_2.setVerDano(true);
					Player_2.DiminuirVida(Player_1.Dano(((Yamato)(Player_1)).DANO_MOKUTON));
					Player_1.setMokuton(null);
				}
			}
		}
		if(Player_2 instanceof Yamato){
			if(Player_2.getMokuton() != null){
				if(Player_2.getMokuton().getBounds().intersects(player01)){
					if(Player_2.getSentido() != Player_1.getSentido()){
						if(!Player_1.isInvuneravel()){
							Player_1.Recuar("SentidoContrario");
						}
					}else{
						if(!Player_1.isInvuneravel()){
							Player_1.Recuar("msmSentido");
						}
					}
					Player_1.setVerDano(true);
					Player_1.DiminuirVida(Player_2.Dano(((Yamato)(Player_2)).DANO_MOKUTON));
					Player_2.setMokuton(null);
				}
			}
		}
	}

	private void VerificarRound(int player01, int player02) {

		if (player01 == 1) {
			r_1.setIcon(r1);
			r_1.setBounds(150, 80, r1.getIconWidth(), r1.getIconHeight());
		} else if (player01 == 2) {
			r_1.setIcon(r1);
			r_1.setBounds(150, 80, r1.getIconWidth(), r1.getIconHeight());
			r_3.setIcon(r2);
			r_3.setBounds(170, 80, r2.getIconWidth(), r2.getIconHeight());
		}

		if (player02 == 1) {
			r_2.setIcon(r1);
			r_2.setBounds(620, 80, r1.getIconWidth(), r1.getIconHeight());
		} else if (player02 == 2) {
			r_2.setIcon(r1);
			r_2.setBounds(620, 80, r1.getIconWidth(), r1.getIconHeight());
			r_3.setIcon(r2);
			r_3.setBounds(600, 80, r2.getIconWidth(), r2.getIconHeight());
		}

		if (jogador_1 + jogador_2 == 0) {

			rounds[0].setIcon(icon_r[0]);
			rounds[0].setBounds(330, 150, icon_r[0].getIconWidth(),
					icon_r[0].getIconHeight());
			try {
				Thread.sleep(1500);
				rounds[0].setVisible(false);
				fight.setIcon(icon_fight);
				fight.setBounds(330, 150, icon_fight.getIconWidth(),
						icon_fight.getIconHeight());
				Thread.sleep(1000);
				fight.setVisible(false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		else if (jogador_1 + jogador_2 == 1) {

			rounds[1].setIcon(icon_r[1]);
			rounds[1].setBounds(330, 150, icon_r[1].getIconWidth(),
					icon_r[1].getIconHeight());
			try {
				Thread.sleep(1500);
				rounds[1].setVisible(false);
				fight.setIcon(icon_fight);
				fight.setBounds(330, 150, icon_fight.getIconWidth(),
						icon_fight.getIconHeight());
				Thread.sleep(1000);
				fight.setVisible(false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			rounds[2].setIcon(icon_r[2]);
			rounds[2].setBounds(330, 150, icon_r[2].getIconWidth(),
					icon_r[2].getIconHeight());
			try {
				Thread.sleep(1500);
				rounds[2].setVisible(false);
				fight.setIcon(icon_fight);
				fight.setBounds(330, 150, icon_fight.getIconWidth(),
						icon_fight.getIconHeight());
				Thread.sleep(1000);
				fight.setVisible(false);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void NovoRound() throws MalformedURLException, InterruptedException {

		round += 1;
		if (jogador_1 == 2 || jogador_2 == 2) {
			Thread.sleep(2000);
			audioRandom.stop();
			
			if(modoDeJogo == 1){
				if(player02 < 4 && Player_1.isGanhou()){
				
					new Game(player01, player02+1, round = 0, jogador_1 = 0, jogador_2 = 0,
							modoDeJogo,adversario+1, pontuacao+Player_1.getPontosDeVida());
				}
				else{
					
					pontuacaoJogador.SalvarPontuacao(pontuacao);	
					new SelectPlayer(1);
				}
				
				
				
				
			}
			else new SelectPlayer(2);
			dispose();
	
		} else {
			
			Thread.sleep(2000);
			audioRandom.stop();
			dispose();
			new Game(player01, player02, round, jogador_1, jogador_2,
				modoDeJogo,adversario, pontuacao+Player_1.getPontosDeVida());
		}

	}

	@SuppressWarnings("deprecation")
	private void Winner(Ninja ninja, Thread player, boolean entrou) {

		if (entrou) {
			ninja.getWin().play();
			ninja.setGanhou(true);
			player.stop();
		}
		this.e1 = false;
	}
	
	private void UpgradeAdversario(){
		
		switch(adversario){
			
			case 1:
				Player_2.setPontosDeVida(110);
				Player_2.setPontosDeChakra(110);
				Player_2.setAtaqueMin(3);
				Player_2.setAtaqueBase(7);
				
				ajustarLabelVida = 1.50;
				ajustarLabelChakra = 0.955;
				break;
			case 2:
				Player_2.setPontosDeVida(120);
				Player_2.setPontosDeChakra(120);
				Player_2.setAtaqueMin(5);
				Player_2.setAtaqueBase(9);
				
				ajustarLabelVida = 1.375;
				ajustarLabelChakra = 0.875;
				break;
			case 3:
				Player_2.setPontosDeVida(130);
				Player_2.setPontosDeChakra(130);
				Player_2.setAtaqueMin(7);
				Player_2.setAtaqueBase(11);
				
				ajustarLabelVida = 1.27;
				ajustarLabelChakra = 0.808;
				break;
			case 4:
				Player_2.setPontosDeVida(140);
				Player_2.setPontosDeChakra(140);
				Player_2.setAtaqueMin(9);
				Player_2.setAtaqueBase(13);
				
				ajustarLabelVida = 1.179;
				ajustarLabelChakra = 0.75;
				break;
		}
	}

	private void CarregarImagens_e_Som() throws MalformedURLException {

		image = new ImageIcon[4];
		
		for (int i = 0; i < image.length; i++) {
			image[i] = new ImageIcon(getClass().getResource("/cenarios/background0"+(i+1)+".png"));
		}
		
		
		Player_1life = new ImageIcon(getClass().getResource(
				"/cenarios/life1.png"));
		Player_2life = new ImageIcon(getClass().getResource(
				"/cenarios/life2.png"));
		bLife1 = new ImageIcon(getClass().getResource("/cenarios/barra_hp.png"));
		bLife2 = new ImageIcon(getClass()
				.getResource("/cenarios/barra_hp2.png"));
		bChakra1 = new ImageIcon(getClass().getResource(
				"/cenarios/barra_chakra.png"));
		bChakra2 = new ImageIcon(getClass().getResource(
				"/cenarios/barra_chakra2.png"));
		r1 = new ImageIcon(getClass().getResource("/cenarios/kunai.png"));
		r2 = new ImageIcon(getClass().getResource("/cenarios/kunai.png"));
		r3 = new ImageIcon(getClass().getResource("/cenarios/kunai.png"));
		icon_r = new ImageIcon[3];
		icon_fight = new ImageIcon(getClass()
				.getResource("/cenarios/fight.png"));

		battleSong = new AudioClip[3];

		for (int h = 0; h < battleSong.length; h++) {
			battleSong[h] = Applet.newAudioClip(this.getClass().getResource(
					"/Audio/Frame wav/battle_" + (h + 1) + ".wav"));
		}

		for (int i = 0; i < icon_r.length; i++) {
			icon_r[i] = new ImageIcon(getClass().getResource(
					"/cenarios/Round" + (i + 1) + ".png"));
		}

		lb_shurikenP1 = new ImageIcon[6];
		lb_shurikenP2 = new ImageIcon[6];

		for (int j = 0; j < lb_shurikenP1.length; j++) {
			lb_shurikenP1[j] = new ImageIcon(getClass().getResource(
					"/cenarios/shuriken_" + j + ".png"));
			lb_shurikenP2[j] = new ImageIcon(getClass().getResource(
					"/cenarios/shuriken_" + j + ".png"));
		}

		estagio.setIcon(image[player02-1]);
		estagio.setBounds(0, 0, image[player02-1].getIconWidth(), image[player02-1].getIconHeight());
		PLife_1.setIcon(Player_1life);
		PLife_1.setBounds(0 + 45, 10, Player_1life.getIconWidth(),
				Player_1life.getIconHeight());
		PLife_2.setIcon(Player_2life);
		PLife_2.setBounds(555 - 45, 10, Player_2life.getIconWidth(),
				Player_2life.getIconHeight());
		barraLife1.setIcon(bLife1);// 45
		barraLife1.setBounds(45 + 45, 30, bLife1.getIconWidth(),
				bLife1.getIconHeight());
		barraLife2.setIcon(bLife2);// 585
		barraLife2.setBounds(585 - 45, 30, bLife2.getIconWidth(),
				bLife2.getIconHeight());
		barraChakra1.setIcon(bChakra1);// 75
		barraChakra1.setBounds(75 + 45, 58, bChakra1.getIconWidth(),
				bChakra1.getIconHeight());
		barraChakra2.setIcon(bChakra2);// 615
		barraChakra2.setBounds(615 - 45, 58, bChakra2.getIconWidth(),
				bChakra2.getIconHeight());
		portrait_p1.setIcon(port_p1);
		portrait_p1.setBounds(0, 15, port_p1.getIconWidth(),
				port_p1.getIconHeight());
		portrait_p2.setIcon(port_p2);
		portrait_p2.setBounds(728, 15, port_p2.getIconWidth(),
				port_p2.getIconHeight());
		p1_shuriken.setIcon(lb_shurikenP1[5]);
		p1_shuriken.setBounds(10, 100, lb_shurikenP1[5].getIconWidth(),
				lb_shurikenP1[5].getIconHeight());
		p2_shuriken.setIcon(lb_shurikenP1[5]);
		p2_shuriken.setBounds(740, 100, lb_shurikenP2[5].getIconWidth(),
				lb_shurikenP2[5].getIconHeight());

	}

	private void CriarLabels() {

		estagio = new JLabel();
		PLife_1 = new JLabel();
		PLife_2 = new JLabel();
		barraLife1 = new JLabel();
		barraLife2 = new JLabel();
		barraChakra1 = new JLabel();
		barraChakra2 = new JLabel();
		portrait_p1 = new JLabel();
		portrait_p2 = new JLabel();
		r_1 = new JLabel();
		r_2 = new JLabel();
		r_3 = new JLabel();
		fight = new JLabel();
		p1_shuriken = new JLabel();
		p2_shuriken = new JLabel();

		rounds = new JLabel[3];

		for (int i = 0; i < rounds.length; i++) {
			rounds[i] = new JLabel();
		}

	}

	public void Frames(int segundos) {

		try {
			Thread.sleep(segundos);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

}