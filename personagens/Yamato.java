package personagens;

import habilidades.Mokuton;

import java.applet.Applet;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Yamato extends Ninja{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon mokutonDir[];
	private Icon mokutonEsq[];
	private int CUSTO_MOKUTON = 40;
	public int DANO_MOKUTON = 45;
	
	public Yamato(int x, int y, int sentido, int key)
			throws MalformedURLException {
		super(x, y, sentido, key);
		
		this.setEstado(0);
		
	}
	
	protected void Mokuton(){
		
		if (this.getSentido() == 1 && this.y == CHAO - getAlturaSprite() && this.getPontosDeChakra() >= CUSTO_MOKUTON) {
			
			DiminuirChakra(CUSTO_MOKUTON);
			getAtkSpecial().play();
			
			for (int cenasDireita = 0; cenasDireita < mokutonDir.length; cenasDireita++) {
				
				setIcon(mokutonDir[cenasDireita]);
				setBounds(x, y, mokutonDir[cenasDireita].getIconWidth(),mokutonDir[cenasDireita].getIconHeight());
				
				Frames(FPS);
				if(cenasDireita == mokutonDir.length-1){
					CriarMokuton();
				}
			}		
		}
		else if (this.getSentido() == 2 && this.y == CHAO - getAlturaSprite() && this.getPontosDeChakra() >= CUSTO_MOKUTON) {
			
			DiminuirChakra(CUSTO_MOKUTON);
			getAtkSpecial().play();
			
			for (int cenasEsquerda = 0; cenasEsquerda < mokutonEsq.length; cenasEsquerda++) {
				
				setIcon(mokutonEsq[cenasEsquerda]);
				setBounds(x, y, mokutonEsq[cenasEsquerda].getIconWidth(),mokutonEsq[cenasEsquerda].getIconHeight());
				
				Frames(FPS);
				if(cenasEsquerda == mokutonEsq.length-1){
					CriarMokuton();
				}
			}
		}
		this.setCenas(0);
		this.setEstado(0);
	}
	
	private void CriarMokuton(){
		setMokuton(new Mokuton());
		Container tela = this.getParent();
		
		tela.add(getMokuton());
		tela.setComponentZOrder(getMokuton(), 0);
	
		getMokuton().CapturarPosicao(this.x, this.y, this.getSentido());
		getMokuton().start();
	}

	public Icon[] getMokutonDir() {return mokutonDir;}
	public void setMokutonDir(Icon[] mokutonDir) {this.mokutonDir = mokutonDir;}

	public Icon[] getMokutonEsq() {return mokutonEsq;}
	public void setMokutonEsq(Icon[] mokutonEsq) {this.mokutonEsq = mokutonEsq;}

	protected void CarregarImagens() {	
		
		setParadoDireita(new ImageIcon[5]);
		setParadoEsquerda(new ImageIcon[5]);
		
		for (int a = 0; a < getParadoDireita().length; a++) {
			getParadoDireita()[a] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Parado/Yamato_parado_"+(a+1)+".png"));
			getParadoEsquerda()[a] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Parado/esquerda/Yamato_parado_"+(a+1)+".png"));
		}
		
		setPularDireitaUp(new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Pulando/Yamato_pulandoUp.png")));
		setPularEsquerdaUp(new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Pulando/esquerda/Yamato_pulandoUp.png")));
		
		setPularDireitaDown(new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Pulando/Yamato_pulandoDown.png")));
		setPularEsquerdaDown(new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Pulando/esquerda/Yamato_pulandoDown.png")));
		
		setRecebendoDanoDir(new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Recebendo Dano/Yamato_dmg.png")));
		setRecebendoDanoEsq(new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Recebendo Dano/esquerda/Yamato_dmg.png")));
		
		setRecuperarDir(new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Recovery/Yamato_recovery.png")));
		setRecuperarEsq(new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Recovery/esquerda/Yamato_recovery.png")));
		
		setDireita(new ImageIcon[6]);
		setEsquerda(new ImageIcon[6]);
		
		for (int b = 0; b < getDireita().length; b++) {
			getDireita()[b] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Correndo/Yamato_run_"+(b+1)+".png"));
			getEsquerda()[b] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Correndo/esquerda/Yamato_run_"+(b+1)+".png"));
		}
		
		setAtacandoDireita(new ImageIcon[6]);
		setAtacandoEsquerda(new ImageIcon[6]);
		
		for (int c = 0; c < getAtacandoDireita().length; c++) {
			getAtacandoDireita()[c] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Atacando/Yamato_atk_"+(c+1)+".png"));
			getAtacandoEsquerda()[c] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Atacando/esquerda/Yamato_atk_"+(c+1)+".png"));
		}
		
		setAtaquePulandoDir(new ImageIcon[5]);
		setAtaquePulandoEsq(new ImageIcon[5]);
		
		for (int d = 0; d < getAtaquePulandoDir().length; d++) {
			getAtaquePulandoDir()[d] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Ataque Pulando/Yamato_atkUp_"+(d+1)+".png"));
			getAtaquePulandoEsq()[d] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Ataque Pulando/esquerda/Yamato_atkUp_"+(d+1)+".png"));
		}
		
		setAtaqueArremessoDir(new ImageIcon[5]);
		setAtaqueArremessoEsq(new ImageIcon[5]);
		
		for (int e = 0; e < getAtaqueArremessoDir().length; e++) {
			getAtaqueArremessoDir()[e] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Arremessando/Yamato_arremessando_"+(e+1)+".png"));
			getAtaqueArremessoEsq()[e] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Arremessando/esquerda/Yamato_arremessando_"+(e+1)+".png"));
		}
		
		setAtaqueArremessoUpDir(new ImageIcon[3]);
		setAtaqueArremessoUpEsq(new ImageIcon[3]);
		
		for (int f = 0; f < getAtaqueArremessoUpDir().length; f++) {
			getAtaqueArremessoUpDir()[f] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Arremessar Pulando/Yamato_arremessarUp_"+(f+1)+".png"));
			getAtaqueArremessoUpEsq()[f] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Arremessar Pulando/esquerda/Yamato_arremessarUp_"+(f+1)+".png"));
		}
		
		setMokutonDir(new ImageIcon[8]);
		setMokutonEsq(new ImageIcon[8]);
		
		for (int g = 0; g < getMokutonDir().length; g++) {
			getMokutonDir()[g] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Mokuton/Yamato_mokuton_"+(g+1)+".png"));
			getMokutonEsq()[g] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Mokuton/esquerda/Yamato_mokuton_"+(g+1)+".png"));
		}
		
		setMorreuDir(new ImageIcon[3]);
		setMorreuEsq(new ImageIcon[3]);
		
		for (int h = 0; h < getMorreuDir().length; h++) {
			getMorreuDir()[h] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato K.O/Yamato_ko_"+(h+1)+".png"));
			getMorreuEsq()[h] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato K.O/esquerda/Yamato_ko_"+(h+1)+".png"));
		}
		
	}

	protected void CarregarAudio() throws MalformedURLException {
		
		setAtk(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Yamato wav/atk.wav")));
		setArremessando_shuriken(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Yamato wav/shuriken.wav")));
		setAtkSpecial(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Yamato wav/mokuton.wav")));
		setDamage(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Yamato wav/dmg.wav")));
		setDead(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Yamato wav/dead.wav")));
		setRecovery(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Yamato wav/chakra_rec.wav")));
		setRecoveryLoop(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Recovery.wav")));
		setWin(Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Yamato wav/win.wav")));
		
	}

	@Override
	protected void Rasengan() throws MalformedURLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void Chidori() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void Goukakyuu() throws MalformedURLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
