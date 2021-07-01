package habilidades;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import cenarios.Cenario;

public class Goukakyuu extends JLabel implements Runnable, Cenario{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int x;
	protected int y;
	
	
	private int sentido;
	private int VELOCIDADE = 15;
	private int cenas = 0;
	private boolean inicio = true;
	
	private Icon goukakyuuDir[];
	private Icon goukakyuuEsq[];
	private Icon goukakyuuDir02[];
	private Icon goukakyuuEsq02[];
	private Icon efeito01Dir[];
	private Icon efeito01Esq[];
	
	public boolean impacto = false;
	
	private AudioClip effect;
	
	private Thread rodar;
	
	public Goukakyuu() throws MalformedURLException {
		
		rodar = new Thread(this);
		
		CarregarImagens();
		
		effect = Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Sasuke wav/katon_effect.wav"));
	}
	
	public int getSentido() {return sentido;}
	public void setSentido(int sentido) {this.sentido = sentido;}

	public int getVELOCIDADE() {return VELOCIDADE;}
	public void setVELOCIDADE(int vELOCIDADE) {VELOCIDADE = vELOCIDADE;}

	public int getCenas() {return cenas;}
	public void setCenas(int cenas) {this.cenas = cenas;}

	public boolean isInicio() {return inicio;}
	public void setInicio(boolean inicio) {this.inicio = inicio;}

	public Icon[] getGoukakyuuDir() {return goukakyuuDir;}
	public void setGoukakyuuDir(Icon[] goukakyuuDir) {this.goukakyuuDir = goukakyuuDir;}

	public Icon[] getGoukakyuuEsq() {return goukakyuuEsq;}
	public void setGoukakyuuEsq(Icon[] goukakyuuEsq) {this.goukakyuuEsq = goukakyuuEsq;}

	public Icon[] getGoukakyuuDir02() {return goukakyuuDir02;}
	public void setGoukakyuuDir02(Icon[] goukakyuuDir02) {this.goukakyuuDir02 = goukakyuuDir02;}

	public Icon[] getGoukakyuuEsq02() {return goukakyuuEsq02;}
	public void setGoukakyuuEsq02(Icon[] goukakyuuEsq02) {this.goukakyuuEsq02 = goukakyuuEsq02;}

	public Icon[] getEfeito01Dir() {return efeito01Dir;}
	public void setEfeito01Dir(Icon[] efeito01Dir) {this.efeito01Dir = efeito01Dir;}

	public Icon[] getEfeito01Esq() {return efeito01Esq;}
	public void setEfeito01Esq(Icon[] efeito01Esq) {this.efeito01Esq = efeito01Esq;}

	public boolean isImpacto() {return impacto;}
	public void setImpacto(boolean impacto) {this.impacto = impacto;}

	public AudioClip getEffect() {return effect;}
	public void setEffect(AudioClip effect) {this.effect = effect;}

	public Thread getRodar() {return rodar;}
	public void setRodar(Thread rodar) {this.rodar = rodar;}

	private void goukakyuuInicio(){
		
		if(this.sentido == 1) {
	
			this.x+=this.VELOCIDADE - 5;
			setIcon(goukakyuuDir[cenas]);
			setBounds(x+10, y, goukakyuuDir[cenas].getIconWidth(), goukakyuuDir[cenas].getIconHeight());
			cenas++;
		
		}
		
		if (this.sentido == 2) {
				
			this.x-=this.VELOCIDADE - 5;
			setIcon(goukakyuuEsq[cenas]);
			setBounds(x-30, y, goukakyuuEsq[cenas].getIconWidth(), goukakyuuEsq[cenas].getIconHeight());
			cenas++;
	
		}
		
		if(cenas == 4) inicio = false;
	}
	
	private void goukakyuuLoop(){
		
		if(this.sentido == 1) {
			
			this.x+=this.VELOCIDADE;
			setIcon(goukakyuuDir02[cenas]);
			setBounds(x-40, y, goukakyuuDir02[cenas].getIconWidth(), goukakyuuDir02[cenas].getIconHeight());
			cenas++;
			
			if (cenas>=goukakyuuDir02.length) cenas = 0;
		}
		
		if(this.sentido == 2){
			
			this.x-=this.VELOCIDADE;
			setIcon(goukakyuuEsq02[cenas]);
			setBounds(x-80, y, goukakyuuEsq02[cenas].getIconWidth(), goukakyuuEsq02[cenas].getIconHeight());
			cenas++;
			
			if (cenas>=goukakyuuEsq02.length) cenas = 0;
		}
			
	}
	
	protected void Impacto(){
	
		if(this.sentido == 1){
			for (int cenasDireira = 0; cenasDireira < efeito01Dir.length; cenasDireira++) {
				setIcon(efeito01Dir[cenasDireira]);
				setBounds(x, y, efeito01Dir[cenasDireira].getIconWidth(), efeito01Dir[cenasDireira].getIconHeight());
				Frames(FPS);
			}
		}
		if(this.sentido == 2){
			for (int cenasEsquerda = 0; cenasEsquerda < efeito01Esq.length; cenasEsquerda++) {
				setIcon(efeito01Esq[cenasEsquerda]);
				setBounds(x-120, y, efeito01Esq[cenasEsquerda].getIconWidth(), efeito01Esq[cenasEsquerda].getIconHeight());
				Frames(FPS);
			}
		}
		
	}
	
	public void CapturarPosicao(int x, int y, int sentido){
		this.x = x;
		this.y = y;
		this.sentido = sentido;
	}
	
	public void start(){
		rodar.start();
	}
	
	@SuppressWarnings("deprecation")
	protected void Matar(){
		setVisible(false);
		rodar.stop();
		rodar.destroy();
	}
	
	public void run() {
		
		effect.loop();
		
		while (!impacto) {
			
			Frames(FPS);
			
			if(impacto) break;
			else{
				if(inicio) goukakyuuInicio();
				else goukakyuuLoop();
			}
			
			if(this.x <= PAREDE_ESQUERDA || this.x >= PAREDE_DIREITA) break;

		}
		effect.stop();
		Impacto();
		Matar();
	}
	
	
	public void Frames(int segundos){
		
		try {
			Thread.sleep(segundos);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	private void CarregarImagens(){
		
		goukakyuuDir = new ImageIcon[5];
		goukakyuuEsq = new ImageIcon[5];
		
		for (int a = 0; a < goukakyuuDir.length; a++) {
			goukakyuuDir[a] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Goukakyuu no Jutsu/efeitos/goukakyuu_"+(a+1)+".png"));
			goukakyuuEsq[a] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Goukakyuu no Jutsu/esquerda/efeitos/goukakyuu_"+(a+1)+".png"));
		}
		
		efeito01Dir = new ImageIcon[4];
		efeito01Esq = new ImageIcon[4];
		
		for (int b = 0; b < efeito01Dir.length; b++) {
			efeito01Dir[b] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Goukakyuu no Jutsu/efeitos/efeito01_"+(b+1)+".png"));
			efeito01Esq[b] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Goukakyuu no Jutsu/esquerda/efeitos/efeito01_"+(b+1)+".png"));
		}
		
		goukakyuuDir02 = new ImageIcon[6];
		goukakyuuEsq02 = new ImageIcon[6];
		
		for (int c = 0; c < goukakyuuDir02.length; c++) {
			goukakyuuDir02[c] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Goukakyuu no Jutsu/efeitos/goukakyuu02_"+(c+1)+".png"));
			goukakyuuEsq02[c] = new ImageIcon(getClass().getResource("/sprites/Sasuke sprites/Sasuke Goukakyuu no Jutsu/esquerda/efeitos/goukakyuu02_"+(c+1)+".png"));
		}
		
	}

}
