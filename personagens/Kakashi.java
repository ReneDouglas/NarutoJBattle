package personagens;


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Kakashi extends Ninja{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon chidoriDireita[];
	private Icon chidoriEsquerda[];
	public int DANO_CHIDORI = 25;
	protected int CUSTO_CHIDORI = 40;
	
	private AudioClip raikiri_01;
	private AudioClip raikiri_02;
	private AudioClip raikiri_03;
	private AudioClip raikiri_04;
	
	public Kakashi(int x, int y, int sentido, int key) throws MalformedURLException {
		super(x, y, sentido, key);
		
		this.setEstado(0);
		
	}
	
	public Icon[] getChidoriDireita() {return chidoriDireita;}
	public void setChidoriDireita(Icon[] chidoriDireita) {this.chidoriDireita = chidoriDireita;}

	public Icon[] getChidoriEsquerda() {return chidoriEsquerda;}
	public void setChidoriEsquerda(Icon[] chidoriEsquerda) {this.chidoriEsquerda = chidoriEsquerda;}

	public AudioClip getRaikiri_01() {return raikiri_01;}
	public void setRaikiri_01(AudioClip raikiri_01) {this.raikiri_01 = raikiri_01;}

	public AudioClip getRaikiri_02() {return raikiri_02;}
	public void setRaikiri_02(AudioClip raikiri_02) {this.raikiri_02 = raikiri_02;}

	public AudioClip getRaikiri_03() {return raikiri_03;}
	public void setRaikiri_03(AudioClip raikiri_03) {this.raikiri_03 = raikiri_03;}

	public AudioClip getRaikiri_04() {return raikiri_04;}
	public void setRaikiri_04(AudioClip raikiri_04) {this.raikiri_04 = raikiri_04;}

	protected void Chidori() {
		
		this.setCenas(0);
		
		if(this. y == CHAO - getAlturaSprite() && this.getPontosDeChakra() >= CUSTO_CHIDORI){
			
			DiminuirChakra(CUSTO_CHIDORI);
			raikiri_01.play();
			raikiri_02.loop();
			//raikiri_03.play();
			
			if (this.getSentido() == 1) {
				for (int cenasDireita = 0; cenasDireita < chidoriDireita.length; cenasDireita++) {
					setIcon(chidoriDireita[cenasDireita]);
					setBounds(x-25, y, chidoriDireita[cenasDireita].getIconWidth(), chidoriDireita[cenasDireita].getIconHeight());
					
					if(cenasDireita > 8){
						this.setLancarChidori(true);
						if (isVerDano()) break;
						if(cenasDireita == 9) raikiri_03.play();
					}
					
					if (cenasDireita > 8 && cenasDireita <= 15) {
						if(this.x > PAREDE_ESQUERDA && this.x < PAREDE_DIREITA) this.x += getVelocidade()*2;
						
						this.setInvuneravel(true);
					}

					Frames(FPS);
				}

			}
			if(this.getSentido() == 2){
				for (int cenasEsquerda = 0; cenasEsquerda < chidoriEsquerda.length; cenasEsquerda++) {
					setIcon(chidoriEsquerda[cenasEsquerda]);
					setBounds(x-20, y, chidoriEsquerda[cenasEsquerda].getIconWidth(), chidoriEsquerda[cenasEsquerda].getIconHeight());
					
					if(cenasEsquerda > 8){
						this.setLancarChidori(true);
						if (isVerDano()) break;
						if(cenasEsquerda == 9) raikiri_03.play();
					}
					
					if (cenasEsquerda > 8 && cenasEsquerda <= 15) {
						if(this.x > PAREDE_ESQUERDA && this.x < PAREDE_DIREITA) this.x -= getVelocidade()*2;
						
						this.setInvuneravel(true);
					}
					
					Frames(FPS);
				}
				
			}
			raikiri_02.stop();
			this.setLancarChidori(false);
			this.setEstado(0);
			this.setCenas(0);
			this.setInvuneravel(false);
			this.setVerDano(false);
		}
	}

	protected void CarregarImagens(){
		
		setPularDireitaUp(new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Pulando/Kakashi_pulandoUp.png")));
		setPularEsquerdaUp(new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Pulando/esquerda/Kakashi_pulandoUp.png")));
		
		setPularDireitaDown(new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Pulando/Kakashi_pulandoDown.png")));
		setPularEsquerdaDown(new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Pulando/esquerda/Kakashi_pulandoDown.png")));
		
		setRecebendoDanoDir(new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Recebendo Dano/Kakashi_dmg.png")));
		setRecebendoDanoEsq(new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Recebendo Dano/esquerda/Kakashi_dmg.png")));
		
		setDireita(new ImageIcon[6]);
		setEsquerda(new ImageIcon[6]);
		
		setParadoDireita(new ImageIcon[6]);
		setParadoEsquerda(new ImageIcon[6]);
		
		for (int a = 0; a < getParadoDireita().length; a++) {
			getParadoDireita()[a] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Parado/Kakashi_parado_"+(a+1)+".png"));
			getParadoEsquerda()[a] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Parado/parado esquerda/Kakashi_parado_"+(a+1)+".png"));
		}
		
		for (int b = 0; b < getDireita().length; b++) {
			getDireita()[b] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Correndo/Kakashi_run_"+(b+1)+".png"));
			getEsquerda()[b] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Correndo/esquerda/Kakashi_run_"+(b+1)+".png"));
		}
		
		setAtacandoDireita(new ImageIcon[4]);
		setAtacandoEsquerda(new ImageIcon[4]);
		
		for (int c = 0; c < getAtacandoDireita().length; c++) {
			getAtacandoDireita()[c] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Atacando/Kakashi_atk_"+(c+1)+".png"));
			getAtacandoEsquerda()[c] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Atacando/esquerda/Kakashi_atk_"+(c+1)+".png"));
		}
		
		setAtaquePulandoDir(new ImageIcon[5]);
		setAtaquePulandoEsq(new ImageIcon[5]);
		
		for (int d = 0; d < getAtaquePulandoDir().length; d++) {
			getAtaquePulandoDir()[d] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Ataque Pulando/Kakashi_atkUp_"+(d+1)+".png"));
			getAtaquePulandoEsq()[d] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Ataque Pulando/esquerda/Kakashi_atkUp_"+(d+1)+".png"));
		}
		
		setAtaqueArremessoDir(new ImageIcon[3]);
		setAtaqueArremessoEsq(new ImageIcon[3]);
		
		for (int e = 0; e < getAtaqueArremessoDir().length; e++) {
			getAtaqueArremessoDir()[e] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Arremessando/Kakashi_arremessando_"+(e+1)+".png"));
			getAtaqueArremessoEsq()[e] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Arremessando/esquerda/Kakashi_arremessando_"+(e+1)+".png"));
		}
		
		setAtaqueArremessoUpDir(new ImageIcon[5]);
		setAtaqueArremessoUpEsq(new ImageIcon[5]);
		
		for (int f = 0; f < getAtaqueArremessoUpDir().length; f++) {
			getAtaqueArremessoUpDir()[f] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Arremessar Pulando/Kakashi_arremessarUp_"+(f+1)+".png"));
			getAtaqueArremessoUpEsq()[f] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Arremessar Pulando/esquerda/Kakashi_arremessarUp_"+(f+1)+".png"));
		}
		
		chidoriDireita = new ImageIcon[23];
		chidoriEsquerda = new ImageIcon[23];
		
		for (int g = 0; g < chidoriDireita.length; g++) {
			chidoriDireita[g] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Chidori/Kakashi_chidori_"+(g+1)+".png"));
			chidoriEsquerda[g] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Chidori/esquerda/Kakashi_chidori_"+(g+1)+".png"));
		}
		
		setMorreuDir(new ImageIcon[3]);
		setMorreuEsq(new ImageIcon[3]);
		
		for (int h = 0; h < getMorreuDir().length; h++) {
			getMorreuDir()[h] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi K.O/Kakashi_ko_"+(h+1)+".png"));
			getMorreuEsq()[h] = new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi K.O/esquerda/Kakashi_ko_"+(h+1)+".png"));
		}
		
		setRecuperarDir(new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Recovery/Kakashi_rec_1.png")));
		setRecuperarEsq(new ImageIcon(getClass().getResource("/sprites/Kakashi sprites/Kakashi Recovery/esquerda/Kakashi_rec_1.png")));
		
	}
	
	@Override
	protected void CarregarAudio() throws MalformedURLException {

		setAtk(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Kakashi wav/ataque.wav")));
		setArremessando_shuriken(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Kakashi wav/shuriken.wav")));
		raikiri_01 = Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Kakashi wav/raikiri_1.wav"));
		raikiri_02 = Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Kakashi wav/raikiri_2.wav"));
		raikiri_03 = Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Kakashi wav/raikiri_3.wav"));
		raikiri_04 = Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Kakashi wav/raikiri_4.wav"));
		setDamage(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Kakashi wav/damage.wav")));
		setDead(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Kakashi wav/dead.wav")));
		setRecovery(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Kakashi wav/chakra_rec.wav")));
		setRecoveryLoop(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Recovery.wav")));
		setWin(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Kakashi wav/win.wav")));
		
	}
	
	protected void Rasengan() {}

	@Override
	protected void Goukakyuu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void Mokuton() {
		// TODO Auto-generated method stub
		
	}

	


}
