package habilidades;

import java.applet.Applet;
import java.applet.AudioClip;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import cenarios.Cenario;

public class Mokuton extends JLabel implements Runnable, Cenario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int x;
	protected int y;
	private int sentido;
	private int cenas = 0;
	private boolean rodar = true;
	private int VELOCIDADE = 20;
	private Icon mokutonDir[];
	private Icon mokutonEsq[];
	
	private AudioClip effect;
	private AudioClip effect2;
	
	private Thread subir;
	

	public Mokuton() {
		
		subir = new Thread(this);
		
		Carregar_Imagens_e_Som();
		
	}
	
	public boolean isRodar() {return rodar;}
	public void setRodar(boolean rodar) {this.rodar = rodar;}

	public Icon[] getMokutonDir() {return mokutonDir;}
	public void setMokutonDir(Icon[] mokutonDir) {this.mokutonDir = mokutonDir;}

	public Icon[] getMokutonEsq() {return mokutonEsq;}
	public void setMokutonEsq(Icon[] mokutonEsq) {this.mokutonEsq = mokutonEsq;}

	public AudioClip getEffect() {return effect;}
	public void setEffect(AudioClip effect) {this.effect = effect;}

	public Thread getSubir() {return subir;}
	public void setSubir(Thread subir) {this.subir = subir;}

	public int getSentido() {return sentido;}
	public void setSentido(int sentido) {this.sentido = sentido;}
	
	public int getVELOCIDADE() {return VELOCIDADE;}
	public void setVELOCIDADE(int vELOCIDADE) {VELOCIDADE = vELOCIDADE;}

	private void Efeito01() throws InterruptedException{
		
		if(this.sentido == 1){
			setIcon(mokutonDir[cenas]);
			setBounds(x+50, y, mokutonDir[cenas].getIconWidth(), mokutonDir[cenas].getIconHeight());
			
			cenas++;
			if (cenas >= mokutonDir.length){
				Thread.sleep(80);
				this.rodar = false;
			}
		}
		else if(this.sentido == 2){
			setIcon(mokutonEsq[cenas]);
			setBounds(x-250, y, mokutonEsq[cenas].getIconWidth(), mokutonEsq[cenas].getIconHeight());
			
			cenas++;
			if (cenas >= mokutonEsq.length){
				Thread.sleep(80);
				this.rodar = false;
			}
		}
	}
	
	private void Efeito02() throws InterruptedException{
		
		effect2.play();
		
		if(this.sentido == 1){
			for(int cenasDireita = mokutonDir.length-1; cenasDireita >= 0; cenasDireita--){	
				setIcon(mokutonDir[cenasDireita]);
				setBounds(x+50, y, mokutonDir[cenasDireita].getIconWidth(), mokutonDir[cenasDireita].getIconHeight());
			
				Frames(FPS);
			}
		}
		else if(this.sentido == 2){
			for(int cenasEsquerda = mokutonEsq.length-1; cenasEsquerda >= 0; cenasEsquerda--){	
				setIcon(mokutonEsq[cenasEsquerda]);
				setBounds(x-250, y, mokutonEsq[cenasEsquerda].getIconWidth(), mokutonEsq[cenasEsquerda].getIconHeight());
			
				Frames(FPS);
			}
		}
	}
	
	public void start() {
		subir.start();	
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		
		effect.play();
		while (rodar) {
			
			try {
				Efeito01();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Frames(FPS);
		}
		
		try {
			Efeito02();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.setVisible(false);
		subir.stop();
		subir.destroy();
		
	}
	
	private void Carregar_Imagens_e_Som(){
		
		mokutonDir = new ImageIcon[6];
		mokutonEsq = new ImageIcon[6];
		
		for (int i = 0; i < mokutonDir.length; i++) {
			mokutonDir[i] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Mokuton/efeito/Mokuton_"+(i+1)+".png"));
			mokutonEsq[i] = new ImageIcon(getClass().getResource("/sprites/Yamato sprites/Yamato Mokuton/efeito/esquerda/Mokuton_"+(i+1)+".png"));
		}
		
		effect = Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Yamato wav/effect.wav"));
		effect2 = Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Yamato wav/effect02.wav"));
	}
	
	public void CapturarPosicao(int x, int y, int sentido){
		this.x = x;
		this.y = y-60;
		this.sentido = sentido;
	}


	public void Frames(int segundos) {

		try {
			Thread.sleep(segundos);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

}
