package habilidades;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import cenarios.Cenario;


public class Rasengan extends JLabel implements Runnable, Cenario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int x;
	protected int y = 21;
	
	private boolean acertou = false;
	private boolean impacto = false;
	private boolean ativar = true;
	private int sentido;
	private int cenas = 0;
	private int count = 0;
	
	private Icon efeito_01[];
	private Icon efeitoDir_02[];
	private Icon efeitoEsq_02[];
	
	private AudioClip rasengan_effect;
	private AudioClip rasengan_effect02;
	
	private Thread rodar;
	
	public Rasengan() throws MalformedURLException {
		
		rodar = new Thread(this);
		
		CarregarImagens_e_Som();
		
	}
	
	public boolean isAcertou() {return acertou;}
	public void setAcertou(boolean acertou) {this.acertou = acertou;}

	public boolean isImpacto() {return impacto;}
	public void setImpacto(boolean impacto) {this.impacto = impacto;}

	public boolean isAtivar() {return ativar;}
	public void setAtivar(boolean ativar) {this.ativar = ativar;}

	public int getSentido() {return sentido;}
	public void setSentido(int sentido) {this.sentido = sentido;}

	public int getCenas() {return cenas;}
	public void setCenas(int cenas) {this.cenas = cenas;}

	public int getCount() {return count;}
	public void setCount(int count) {this.count = count;}

	public Icon[] getEfeito_01() {return efeito_01;}
	public void setEfeito_01(Icon[] efeito_01) {this.efeito_01 = efeito_01;}

	public Icon[] getEfeitoDir_02() {return efeitoDir_02;}
	public void setEfeitoDir_02(Icon[] efeitoDir_02) {this.efeitoDir_02 = efeitoDir_02;}

	public Icon[] getEfeitoEsq_02() {return efeitoEsq_02;}
	public void setEfeitoEsq_02(Icon[] efeitoEsq_02) {this.efeitoEsq_02 = efeitoEsq_02;}

	public AudioClip getRasengan_effect() {return rasengan_effect;}
	public void setRasengan_effect(AudioClip rasengan_effect) {this.rasengan_effect = rasengan_effect;}

	public AudioClip getRasengan_effect02() {return rasengan_effect02;}
	public void setRasengan_effect02(AudioClip rasengan_effect02) {this.rasengan_effect02 = rasengan_effect02;}

	public Thread getRodar() {return rodar;}
	public void setRodar(Thread rodar) {this.rodar = rodar;}

	private void efeito_01(){
	
		if (this.sentido == 1) {
			
			if(count<5){
				count++;
				this.x+=8;
				if(count>3){
					this.x+=10;
				}
			}
			if(count == 4){
				setIcon(efeito_01[cenas]);
				setBounds(this.x, this.y, efeito_01[cenas].getIconWidth()+30, efeito_01[cenas].getIconHeight());
				this.impacto = true;
			}
			else{
				setIcon(efeito_01[cenas]);
				setBounds(this.x, this.y, efeito_01[cenas].getIconWidth(), efeito_01[cenas].getIconHeight());
			}
		}
		if(this.sentido == 2){
			
			if(count<5){
				count++;
				this.x-=8;
				if(count>3){
					this.x-=10;
				}
				
			}
			if(count == 4){
				setIcon(efeito_01[cenas]);
				setBounds(this.x+40, this.y, efeito_01[cenas].getIconWidth()+30, efeito_01[cenas].getIconHeight());
				this.impacto = true;
			}
			else{
				setIcon(efeito_01[cenas]);
				setBounds(this.x+40, this.y, efeito_01[cenas].getIconWidth(), efeito_01[cenas].getIconHeight());
			}
		}
		cenas++;

		if(cenas>=efeito_01.length) cenas = 0;
		
	}
	
	private void efeito_02(){
		
		if(this.sentido == 1){
			setIcon(efeitoDir_02[cenas]);
			
				setBounds(this.x-60, this.y-37, efeitoDir_02[cenas].getIconWidth(), efeitoDir_02[cenas].getIconHeight());

		}
		if(this.sentido == 2){
			setIcon(efeitoEsq_02[cenas]);
			setBounds(this.x-50, this.y-40, efeitoEsq_02[cenas].getIconWidth(), efeitoEsq_02[cenas].getIconHeight());
		}
		cenas++;
		if(cenas == efeitoDir_02.length) this.ativar = false;
	}
	
	public void start(){
		rodar.start();
	}
	
	public void CapturarPosicao(int x, int y, int sentido){
		this.x = x;
		this.y += y;
		this.sentido = sentido;
	}
	
	
	@SuppressWarnings("deprecation")
	public void run() {
		
		rasengan_effect.play();
		
		while(ativar){
			if(acertou) efeito_02();
			else efeito_01();
			
			Frames(FPS);
		}
		Matar();
		rodar.destroy();
	}

	
	@SuppressWarnings("deprecation")
	public void Matar(){
		this.setVisible(false);
		//Container tela = this.getParent();
		//tela.remove(this);
		//tela.repaint();
		rodar.stop();
		
	}
	
	public void Frames(int segundos){
		
		try {
			Thread.sleep(segundos);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	private void CarregarImagens_e_Som(){
		
		efeito_01 = new ImageIcon[4];
		
		for (int a = 0; a < efeito_01.length; a++) {
			efeito_01[a] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Rasengan/Efeito 1/Naruto_rasengan_efeito01_"+(a+1)+".png"));
		}
		
		efeitoDir_02 = new ImageIcon[9];
		efeitoEsq_02 = new ImageIcon[9];
		
		for (int b = 0; b < efeitoDir_02.length; b++) {
			efeitoDir_02[b] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Rasengan/Efeito 2/Naruto_rasengan_efeito03_"+(b+1)+".png"));
			efeitoEsq_02[b] = new ImageIcon(getClass().getResource("/sprites/Naruto sprites/Naruto Rasengan/Efeito 2/esquerda/Naruto_rasengan_efeito03_"+(b+1)+".png"));
		}
		
		rasengan_effect = Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Naruto wav/rasengan_effect.wav"));
		rasengan_effect02 = Applet.newAudioClip(this.getClass().getResource("/Audio/Sprites wav/Naruto wav/rasengan_effect02.wav"));
	}

	
}
