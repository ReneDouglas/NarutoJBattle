package personagens;

import habilidades.Goukakyuu;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Sasuke extends Ninja{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon goukakyuuDireita[];
	private Icon goukakyuuEsquerda[];
	public int DANO_GOUKAKYUU = 35;
	protected int CUSTO_GOUKAKYUU = 35;
	
	private AudioClip katon;
	private AudioClip goukakyuuJutsu;
	
	public Sasuke(int x, int y, int sentido, int key) throws MalformedURLException {
		super(x, y, sentido, key);
		
		this.setEstado(0);
		
	}
	
	public Icon[] getGoukakyuuDireita() {return goukakyuuDireita;}
	public void setGoukakyuuDireita(Icon[] goukakyuuDireita) {this.goukakyuuDireita = goukakyuuDireita;}

	public Icon[] getGoukakyuuEsquerda() {return goukakyuuEsquerda;}
	public void setGoukakyuuEsquerda(Icon[] goukakyuuEsquerda) {this.goukakyuuEsquerda = goukakyuuEsquerda;}

	public AudioClip getKaton() {return katon;}
	public void setKaton(AudioClip katon) {this.katon = katon;}

	public AudioClip getGoukakyuuJutsu() {return goukakyuuJutsu;}
	public void setGoukakyuuJutsu(AudioClip goukakyuuJutsu) {this.goukakyuuJutsu = goukakyuuJutsu;}

	protected void Goukakyuu() throws MalformedURLException{
		
		//this.cenas = 0;
		
		if (this.getSentido() == 1 && this.y == CHAO - getAlturaSprite() && this.getPontosDeChakra() >= CUSTO_GOUKAKYUU) {
			
				DiminuirChakra(CUSTO_GOUKAKYUU);
				katon.play();
				
			for (int cenasDireita = 0; cenasDireita < goukakyuuDireita.length; cenasDireita++) {
				
			
				setIcon(goukakyuuDireita[cenasDireita]);
				setBounds(x, y, goukakyuuDireita[cenasDireita].getIconWidth(),goukakyuuDireita[cenasDireita].getIconHeight());
				
				if(cenasDireita == 0){
					setGoukakyuu(new Goukakyuu());
					Container tela = this.getParent();
					
					tela.add(getGoukakyuu());
					tela.setComponentZOrder(getGoukakyuu(), 0);
				
					getGoukakyuu().CapturarPosicao(this.x, this.y, this.getSentido());
				}
				if(cenasDireita < 7){
					if (isVerDano()) {
						break;
					}
				}
				
				if(cenasDireita == 7){
					goukakyuuJutsu.play();
					if(getGoukakyuu() != null) getGoukakyuu().start();
					//this.lancarGoukakyuu = true;
				}
				Frames(100);
			}

		}	
		if(this.getSentido() == 2 && this.y == CHAO - getAlturaSprite() && this.getPontosDeChakra() >= CUSTO_GOUKAKYUU){
			
			DiminuirChakra(CUSTO_GOUKAKYUU);
			katon.play();
			
			for (int cenasEsquerda = 0; cenasEsquerda < goukakyuuEsquerda.length; cenasEsquerda++) {
				
				setIcon(goukakyuuEsquerda[cenasEsquerda]);
				setBounds(x, y, goukakyuuEsquerda[cenasEsquerda].getIconWidth(),goukakyuuEsquerda[cenasEsquerda].getIconHeight());
				
				if(cenasEsquerda == 0){
					setGoukakyuu(new Goukakyuu());
					Container tela = this.getParent();
					
					tela.add(getGoukakyuu());
					tela.setComponentZOrder(getGoukakyuu(), 0);
				
					getGoukakyuu().CapturarPosicao(this.x, this.y, this.getSentido());
				}
				if(cenasEsquerda < 7){
					if (isVerDano()) {
						break;
					}
				}
				
				if(cenasEsquerda == 7){
					goukakyuuJutsu.play();
					getGoukakyuu().start();
					//this.lancarGoukakyuu = true;
				}
				Frames(100);
			}
			
		}
		this.setVerDano(false);
		this.setCenas(0);
		this.setEstado(0);
		
	}
	
	
	protected void CarregarImagens(){
	
		setPularDireitaUp(new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Pulando/Sasuke_pulando_UpDir.png")));
		setPularEsquerdaUp(new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Pulando/pulando esquerda/Sasuke_pulando_UpEsq.png")));
		
		setPularDireitaDown(new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Pulando/Sasuke_pulando_DownDir.png")));
		setPularEsquerdaDown(new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Pulando/pulando esquerda/Sasuke_pulando_DownEsq.png")));
		
		setRecebendoDanoDir(new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Recebendo Dano/Sasuke_dmg.png")));
		setRecebendoDanoEsq(new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Recebendo Dano/esquerda/Sasuke_dmg.png")));
		
		setParadoDireita(new ImageIcon[6]);
		setParadoEsquerda(new ImageIcon[6]);
		
		for (int a = 0; a < getParadoDireita().length; a++) {
			getParadoDireita()[a] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Parado/Sasuke_parado_"+(a+1)+".png"));
			getParadoEsquerda()[a] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Parado/parado esquerda/Sasuke_parado_"+(a+1)+".png"));
		}
		
		setDireita(new ImageIcon[6]);
		setEsquerda(new ImageIcon[6]);
		
		for (int b = 0; b < getDireita().length; b++) {
			getDireita()[b] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Correndo/Sasuke_run_"+(b+1)+".png"));
			getEsquerda()[b] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Correndo/correndo esquerda/Sasuke_run_"+(b+1)+".png"));
		}
		
		setAtacandoDireita(new ImageIcon[5]);
		setAtacandoEsquerda(new ImageIcon[5]);
		
		for (int c = 0; c < getAtacandoDireita().length; c++) {
			getAtacandoDireita()[c] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Atacando/Sasuke_atk_"+(c+1)+".png"));
			getAtacandoEsquerda()[c] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Atacando/atacando esquerda/Sasuke_atk_"+(c+1)+".png"));
		}
		
		setAtaquePulandoDir(new ImageIcon[5]);
		setAtaquePulandoEsq(new ImageIcon[5]);
		
		for (int d = 0; d < getAtaquePulandoDir().length; d++) {
			getAtaquePulandoDir()[d] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Ataque Pulando/Sasuke_atkJump_"+(d+1)+".png"));
			getAtaquePulandoEsq()[d] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Ataque Pulando/esquerda/Sasuke_atkJump_"+(d+1)+".png"));
		}
		
		setAtaqueArremessoDir(new ImageIcon[4]);
		setAtaqueArremessoEsq(new ImageIcon[4]);
		
		for (int e = 0; e < getAtaqueArremessoDir().length; e++) {
			getAtaqueArremessoDir()[e] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Arremessando/Sasuke_arremesso_"+(e+1)+".png"));
			getAtaqueArremessoEsq()[e] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Arremessando/esquerda/Sasuke_arremesso_"+(e+1)+".png"));
		}
		
		setAtaqueArremessoUpDir(new ImageIcon[4]);
		setAtaqueArremessoUpEsq(new ImageIcon[4]);
		
		for (int f = 0; f < getAtaqueArremessoUpDir().length; f++) {
			getAtaqueArremessoUpDir()[f] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Arremessar Pulando/Sasuke_arremessoUp_"+(f+1)+".png"));
			getAtaqueArremessoUpEsq()[f] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Arremessar Pulando/esquerda/Sasuke_arremessoUp_"+(f+1)+".png"));
		}
		
		goukakyuuDireita = new ImageIcon[10];
		goukakyuuEsquerda = new ImageIcon[10];
		
		for (int g = 0; g < goukakyuuDireita.length; g++) {
			goukakyuuDireita[g] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Goukakyuu no Jutsu/Sasuke_goukakyuu_"+(g+1)+".png"));
			goukakyuuEsquerda[g] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Goukakyuu no Jutsu/esquerda/Sasuke_goukakyuu_"+(g+1)+".png"));
		}
		
		setMorreuDir(new ImageIcon[4]);
		setMorreuEsq(new ImageIcon[4]);
		
		for (int h = 0; h < getMorreuDir().length; h++) {
			getMorreuDir()[h] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke K.O/Sasuke_ko_"+(h+1)+".png"));
			getMorreuEsq()[h] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke K.O/K.O esquerda/Sasuke_ko_"+(h+1)+".png"));
		}
		
		setRecuperarDir(new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Recovery/Sasuke_rec_1.png")));
		setRecuperarEsq(new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Recovery/esquerda/Sasuke_rec_1.png")));
		
	}
	
	@Override
	protected void CarregarAudio() throws MalformedURLException {
		
		setAtk(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Sasuke wav/ataque.wav")));
		setArremessando_shuriken(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Sasuke wav/shuriken.wav")));
		katon = Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Sasuke wav/katon.wav"));
		goukakyuuJutsu = Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Sasuke wav/goukakyuu.wav"));
		setDamage(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Sasuke wav/damage.wav")));
		setDead(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Sasuke wav/dead.wav")));
		setRecovery(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Sasuke wav/chakra_rec.wav")));
		setRecoveryLoop(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Recovery.wav")));
		setWin(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Sasuke wav/win.wav")));
	}
	
	protected void Rasengan() {}

	@Override
	protected void Chidori() {
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
