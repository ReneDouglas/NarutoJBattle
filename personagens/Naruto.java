package personagens;

import habilidades.Rasengan;

import java.applet.Applet;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Naruto extends Ninja{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int DANO_RASENGAN = 40;
	protected int CUSTO_RASENGAN = 26;
	private Icon rasenganDir[];
	private Icon rasenganEsq[];
	private boolean Unlock = false;
	
	public Naruto(int x, int y, int sentido, int key) throws MalformedURLException {
		super(x, y, sentido, key);
		
		this.setEstado(0);
		
	}
	
	public Icon[] getRasenganDir() {return rasenganDir;}
	public void setRasenganDir(Icon[] rasenganDir) {this.rasenganDir = rasenganDir;}

	public Icon[] getRasenganEsq() {return rasenganEsq;}
	public void setRasenganEsq(Icon[] rasenganEsq) {this.rasenganEsq = rasenganEsq;}

	public boolean isUnlock() {return Unlock;}
	public void setUnlock(boolean unlock) {Unlock = unlock;}

	protected void Rasengan() throws MalformedURLException{
		
		//this.cenas = 0;
		
		if (this.getSentido() == 1 && this.y == CHAO - getAlturaSprite() && this.getPontosDeChakra() >= CUSTO_RASENGAN) {
			
			DiminuirChakra(CUSTO_RASENGAN);
			
			getAtkSpecial().play();
			
			for (int cenasDireita = 0; cenasDireita < rasenganDir.length; cenasDireita++) {
				
			
				setIcon(rasenganDir[cenasDireita]);
				setBounds(x, y, rasenganDir[cenasDireita].getIconWidth(),rasenganDir[cenasDireita].getIconHeight());
				
				if(cenasDireita == 0){
					CriarRasengan();
				}
				Frames(40);
			}

		}	
		if(this.getSentido() == 2 && this.y == CHAO - getAlturaSprite() && this.getPontosDeChakra() >= CUSTO_RASENGAN){
			
			DiminuirChakra(CUSTO_RASENGAN);
			
			getAtkSpecial().play();
			
			for (int cenasEsquerda = 0; cenasEsquerda < rasenganDir.length; cenasEsquerda++) {
				
				setIcon(rasenganEsq[cenasEsquerda]);
				setBounds(x, y, rasenganEsq[cenasEsquerda].getIconWidth(),rasenganEsq[cenasEsquerda].getIconHeight());
				
				if(cenasEsquerda == 0){
					CriarRasengan();
				}
				Frames(40);
			}
			
		}
		this.setCenas(0);
		this.setEstado(0);
		this.setUnlock(true);
	}
	
	private void CriarRasengan() throws MalformedURLException{
		
		setRasengan(new Rasengan());
		Container tela = this.getParent();
		
		tela.add(getRasengan());
		tela.setComponentZOrder(getRasengan(), 0);
	
		getRasengan().CapturarPosicao(this.x, this.y, this.getSentido());
		getRasengan().start();
		//this.lancarRasengan = true;
		
	}
	
	
	protected void CarregarImagens(){
		
		setParadoDireita(new ImageIcon[6]);
		setParadoEsquerda(new ImageIcon[6]);
		
		for (int i = 0; i < getParadoDireita().length; i++) {
			getParadoDireita()[i] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Parado/Naruto_st_"+(i+1)+".png"));
			getParadoEsquerda()[i] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Parado/parado esquerda/Naruto_st_"+(i+1)+".png"));
		}
				
		setPularDireitaUp(new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Pulando/Naruto_jump_1.png")));
		setPularEsquerdaUp(new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Pulando/pulando esquerda/Naruto_jump_1.png")));
		
		setPularDireitaDown(new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Pulando/Naruto_jump_4.png")));
		setPularEsquerdaDown(new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Pulando/pulando esquerda/Naruto_jump_4.png")));
		
		setRecebendoDanoDir(new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Recebendo Dano/Naruto_dmg_1.png")));
		setRecebendoDanoEsq(new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Recebendo Dano/esquerda/Naruto_dmg_1.png")));
		
		setDireita(new ImageIcon[6]);
		setEsquerda(new ImageIcon[6]);
		
		for (int a = 0; a < getDireita().length; a++) {
			getDireita()[a] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Correndo/Naruto_run_"+(a+1)+".png"));
			getEsquerda()[a] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Correndo/correndo esquerda/Naruto_run_"+(a+1)+".png"));
		}
		
		setAtacandoDireita(new ImageIcon[3]);
		setAtacandoEsquerda(new ImageIcon[3]);
		
		for (int b = 0; b < getAtacandoDireita().length; b++) {
			getAtacandoDireita()[b] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Atacando/Naruto_atk_"+(b+1)+".png"));
			getAtacandoEsquerda()[b] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Atacando/Atacando Esquerda/Naruto_atk_"+(b+1)+".png"));
		}
		
		setAtaquePulandoDir(new ImageIcon[4]);
		setAtaquePulandoEsq(new ImageIcon[4]);
		
		for (int c = 0; c < getAtaquePulandoDir().length; c++) {
			getAtaquePulandoDir()[c] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Ataque Pulando/Naruto_atkJump_"+(c+1)+".png"));
			getAtaquePulandoEsq()[c] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Ataque Pulando/esquerda/Naruto_atkJump_"+(c+1)+".png"));
		}
		
		setAtaqueArremessoDir(new ImageIcon[5]);
		setAtaqueArremessoEsq(new ImageIcon[5]);
		
		for (int d = 0; d < getAtaqueArremessoDir().length; d++) {
			getAtaqueArremessoDir()[d] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Arremessando/Naruto_arremesso_"+(d+1)+".png"));
			getAtaqueArremessoEsq()[d] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Arremessando/esquerda/Naruto_arremesso_"+(d+1)+".png"));
		}
		
		setAtaqueArremessoUpDir(new ImageIcon[3]);
		setAtaqueArremessoUpEsq(new ImageIcon[3]);
		
		for (int e = 0; e < getAtaqueArremessoUpDir().length; e++) {
			getAtaqueArremessoUpDir()[e] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Arremessar Pulando/Naruto_arremessoUp_"+(e+1)+".png"));
			getAtaqueArremessoUpEsq()[e] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Arremessar Pulando/esquerda/Naruto_arremessoUp_"+(e+1)+".png"));
		}
		
		rasenganDir = new ImageIcon[10];
		rasenganEsq = new ImageIcon[10];
		
		for (int f = 0; f < rasenganDir.length; f++) {
			rasenganDir[f] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Rasengan/Naruto_rg_final_"+(f+1)+".png"));
			rasenganEsq[f] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Rasengan/esquerda/Naruto_rg_final_"+(f+1)+".png"));
		}
		
		setMorreuDir(new ImageIcon[3]);
		setMorreuEsq(new ImageIcon[3]);
		
		for (int g = 0; g < getMorreuDir().length; g++) {
			getMorreuDir()[g] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto K.O/Naruto_ko_"+(g+1)+".png"));
			getMorreuEsq()[g] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto K.O/esquerda/Naruto_ko_"+(g+1)+".png"));
		}
		
		setRecuperarDir(new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Recovery/Naruto_rec_2.png")));
		setRecuperarEsq(new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Recovery/esquerda/Naruto_rec_2.png")));
		
	}
	
	protected void CarregarAudio() throws MalformedURLException{
		
		setAtk(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Naruto wav/ataque.wav")));
		setArremessando_shuriken(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Naruto wav/shuriken.wav")));
		setAtkSpecial(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Naruto wav/rasengan.wav")));
		setDamage(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Naruto wav/damage.wav")));
		setDead(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Naruto wav/dead.wav")));
		setRecovery(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Naruto wav/chakra_rec.wav")));
		setRecoveryLoop(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Recovery.wav")));
		setWin(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Naruto wav/win.wav")));
	}

	@Override
	protected void Chidori() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void Goukakyuu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void Mokuton() {
		// TODO Auto-generated method stub
		
	}

	
	

}
