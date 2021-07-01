package personagens;

import habilidades.Goukakyuu;
import habilidades.Mokuton;
import habilidades.Rasengan;
import habilidades.Shuriken;

import java.applet.AudioClip;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JLabel;


import cenarios.Cenario;

public abstract class Ninja extends JLabel implements Runnable, KeyListener, Cenario{
	
	/*
	 * ESTADOS: 0 -> Parado | 1 -> Correndo | 2 -> Pulando |
	 *  3 -> Atacando | 4 -> Arremessando | 5 -> Ataque Especial | 6 -> Recuperando
	 */
	
//	SENTIDOS: DIREITA = 1 | ESQUERDA = 2
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean arremessar = false;
	private boolean pular = false;
	private boolean lancarShuriken = false;
	private boolean lancarRasengan = false;
	private boolean lancarChidori = false;
	private boolean lancarGoukakyuu = false;
	private boolean invuneravel = false;
	private boolean verDano = false;
	private boolean verChakraKey = false;
	private boolean ganhou = false;
	
	protected int x;
	protected int y;
	private double n;
	
	private int estado; 
	private int estadoAnterior;
	private int obstaculo;
	private int key;
	private int sentido;
	
	private int verSentido = 0;
	private int velocidade = 20;
	private int cenas = 0;
	
	private int PontosDeVida = 100; // 165
	private int PontosDeChakra = 100; // 105
	private int ataqueBase = 5;
	private int ataqueMin = 1;
	private static Random ataque;
	private int Shurikens = 5;
	private int vidaLabel = 0;
	private int chakraLabel = 0;
	private int alturaSprite = 70;
	
	private Icon direita[];
	private Icon esquerda[];
	private Icon atacandoDireita[];
	private Icon atacandoEsquerda[];
	private Icon ataquePulandoDir[];
	private Icon ataquePulandoEsq[];
	private Icon ataqueArremessoDir[];
	private Icon ataqueArremessoEsq[];
	private Icon ataqueArremessoUpDir[];
	private Icon ataqueArremessoUpEsq[];
	private Icon paradoDireita[];
	private Icon paradoEsquerda[];
	private Icon morreuDir[];
	private Icon morreuEsq[];
	private Icon recuperarDir;
	private Icon recuperarEsq;
	private Icon pularDireitaUp;
	private Icon pularEsquerdaUp;
	private Icon pularDireitaDown;
	private Icon pularEsquerdaDown;
	private Icon recebendoDanoDir;
	private Icon recebendoDanoEsq;
	
	private Shuriken shuriken;
	private Rasengan rasengan;
	private Goukakyuu goukakyuu;
	private Mokuton mokuton;
	
	private AudioClip atk;
	private AudioClip atkSpecial;
	private AudioClip damage;
	private AudioClip dead;
	private AudioClip arremessando_shuriken;
	private AudioClip recovery;
	private AudioClip recoveryLoop;
	private AudioClip win;
	
	public Ninja(int x, int y, int sentido,int key) throws MalformedURLException {
		this.x = x;
		this.y = y-alturaSprite;
		this.sentido = sentido;
		this.estado = 0;
		this.n = 0;
		this.key = key;
		ataque = new Random();
		
		CarregarImagens();
		CarregarAudio();
	}
	
	public boolean isGanhou() {return ganhou;}
	public void setGanhou(boolean ganhou) {this.ganhou = ganhou;}

	public Mokuton getMokuton() {return mokuton;}
	public void setMokuton(Mokuton mokuton) {this.mokuton = mokuton;}

	public boolean isArremessar() {return arremessar;}
	public void setArremessar(boolean arremessar) {this.arremessar = arremessar;}

	public boolean isPular() {return pular;}
	public void setPular(boolean pular) {this.pular = pular;}

	public boolean isLancarShuriken() {return lancarShuriken;}
	public void setLancarShuriken(boolean lancarShuriken) {this.lancarShuriken = lancarShuriken;}

	public boolean isLancarRasengan() {return lancarRasengan;}
	public void setLancarRasengan(boolean lancarRasengan) {this.lancarRasengan = lancarRasengan;}

	public boolean isLancarChidori() {return lancarChidori;}
	public void setLancarChidori(boolean lancarChidori) {this.lancarChidori = lancarChidori;}

	public boolean isLancarGoukakyuu() {return lancarGoukakyuu;}
	public void setLancarGoukakyuu(boolean lancarGoukakyuu) {this.lancarGoukakyuu = lancarGoukakyuu;}

	public boolean isInvuneravel() {return invuneravel;}
	public void setInvuneravel(boolean invuneravel) {this.invuneravel = invuneravel;}

	public boolean isVerDano() {return verDano;}
	public void setVerDano(boolean verDano) {this.verDano = verDano;}

	public boolean isVerChakraKey() {return verChakraKey;}
	public void setVerChakraKey(boolean verChakraKey) {this.verChakraKey = verChakraKey;}

	public int getX() {return x;}
	public void setX(int x) {this.x = x;}

	public int getY() {return y;}
	public void setY(int y) {this.y = y;}

	public double getN() {return n;}
	public void setN(double n) {this.n = n;}

	public int getEstado() {return estado;}
	public void setEstado(int estado) {this.estado = estado;}

	public int getEstadoAnterior() {return estadoAnterior;}
	public void setEstadoAnterior(int estadoAnterior) {this.estadoAnterior = estadoAnterior;}

	public int getKey() {return key;}
	public void setKey(int key) {this.key = key;}

	public int getSentido() {return sentido;}
	public void setSentido(int sentido) {this.sentido = sentido;}

	public int getVerSentido() {return verSentido;}
	public void setVerSentido(int verSentido) {this.verSentido = verSentido;}

	public int getVelocidade() {return velocidade;}
	public void setVelocidade(int velocidade) {this.velocidade = velocidade;}

	public int getCenas() {return cenas;}
	public void setCenas(int cenas) {
		if(cenas != 0) this.cenas += cenas;
		else this.cenas = cenas;}

	public int getPontosDeVida() {return PontosDeVida;}
	public void setPontosDeVida(int pontosDeVida) {PontosDeVida = pontosDeVida;}

	public int getPontosDeChakra() {return PontosDeChakra;}
	public void setPontosDeChakra(int pontosDeChakra) {PontosDeChakra = pontosDeChakra;}

	public int getAtaqueBase() {return ataqueBase;}
	public void setAtaqueBase(int ataqueBase) {this.ataqueBase = ataqueBase;}

	public int getAtaqueMin() {return ataqueMin;}
	public void setAtaqueMin(int ataqueMin) {this.ataqueMin = ataqueMin;}

	public static Random getAtaque() {return ataque;}
	public static void setAtaque(Random ataque) {Ninja.ataque = ataque;}

	public int getShurikens() {return Shurikens;}
	public void setShurikens(int shurikens) {Shurikens = shurikens;}

	public int getVidaLabel() {return vidaLabel;}
	public void setVidaLabel(int vidaLabel) {this.vidaLabel = vidaLabel;}

	public int getChakraLabel() {return chakraLabel;}
	public void setChakraLabel(int chakraLabel) {this.chakraLabel = chakraLabel;}

	public int getAlturaSprite() {return alturaSprite;}
	public void setAlturaSprite(int alturaSprite) {this.alturaSprite = alturaSprite;}

	public Icon[] getDireita() {return direita;}
	public void setDireita(Icon[] direita) {this.direita = direita;}

	public Icon[] getEsquerda() {return esquerda;}
	public void setEsquerda(Icon[] esquerda) {this.esquerda = esquerda;}

	public Icon[] getAtacandoDireita() {return atacandoDireita;}
	public void setAtacandoDireita(Icon[] atacandoDireita) {this.atacandoDireita = atacandoDireita;}

	public Icon[] getAtacandoEsquerda() {return atacandoEsquerda;}
	public void setAtacandoEsquerda(Icon[] atacandoEsquerda) {this.atacandoEsquerda = atacandoEsquerda;}

	public Icon[] getAtaquePulandoDir() {return ataquePulandoDir;}
	public void setAtaquePulandoDir(Icon[] ataquePulandoDir) {this.ataquePulandoDir = ataquePulandoDir;}

	public Icon[] getAtaquePulandoEsq() {return ataquePulandoEsq;}
	public void setAtaquePulandoEsq(Icon[] ataquePulandoEsq) {this.ataquePulandoEsq = ataquePulandoEsq;}

	public Icon[] getAtaqueArremessoDir() {return ataqueArremessoDir;}
	public void setAtaqueArremessoDir(Icon[] ataqueArremessoDir) {this.ataqueArremessoDir = ataqueArremessoDir;}

	public Icon[] getAtaqueArremessoEsq() {return ataqueArremessoEsq;}
	public void setAtaqueArremessoEsq(Icon[] ataqueArremessoEsq) {this.ataqueArremessoEsq = ataqueArremessoEsq;}

	public Icon[] getAtaqueArremessoUpDir() {return ataqueArremessoUpDir;}
	public void setAtaqueArremessoUpDir(Icon[] ataqueArremessoUpDir) {this.ataqueArremessoUpDir = ataqueArremessoUpDir;}

	public Icon[] getAtaqueArremessoUpEsq() {return ataqueArremessoUpEsq;}
	public void setAtaqueArremessoUpEsq(Icon[] ataqueArremessoUpEsq) {this.ataqueArremessoUpEsq = ataqueArremessoUpEsq;}

	public Icon[] getParadoDireita() {return paradoDireita;}
	public void setParadoDireita(Icon[] paradoDireita) {this.paradoDireita = paradoDireita;}

	public Icon[] getParadoEsquerda() {return paradoEsquerda;}
	public void setParadoEsquerda(Icon[] paradoEsquerda) {this.paradoEsquerda = paradoEsquerda;}

	public Icon[] getMorreuDir() {return morreuDir;}
	public void setMorreuDir(Icon[] morreuDir) {this.morreuDir = morreuDir;}

	public Icon[] getMorreuEsq() {return morreuEsq;}
	public void setMorreuEsq(Icon[] morreuEsq) {this.morreuEsq = morreuEsq;}

	public Icon getRecuperarDir() {return recuperarDir;}
	public void setRecuperarDir(Icon recuperarDir) {this.recuperarDir = recuperarDir;}

	public Icon getRecuperarEsq() {return recuperarEsq;}
	public void setRecuperarEsq(Icon recuperarEsq) {this.recuperarEsq = recuperarEsq;}

	public Icon getPularDireitaUp() {return pularDireitaUp;}
	public void setPularDireitaUp(Icon pularDireitaUp) {this.pularDireitaUp = pularDireitaUp;}

	public Icon getPularEsquerdaUp() {return pularEsquerdaUp;}
	public void setPularEsquerdaUp(Icon pularEsquerdaUp) {this.pularEsquerdaUp = pularEsquerdaUp;}

	public Icon getPularDireitaDown() {return pularDireitaDown;}
	public void setPularDireitaDown(Icon pularDireitaDown) {this.pularDireitaDown = pularDireitaDown;}

	public Icon getPularEsquerdaDown() {return pularEsquerdaDown;}
	public void setPularEsquerdaDown(Icon pularEsquerdaDown) {this.pularEsquerdaDown = pularEsquerdaDown;}

	public Icon getRecebendoDanoDir() {return recebendoDanoDir;}
	public void setRecebendoDanoDir(Icon recebendoDanoDir) {this.recebendoDanoDir = recebendoDanoDir;}

	public Icon getRecebendoDanoEsq() {return recebendoDanoEsq;}
	public void setRecebendoDanoEsq(Icon recebendoDanoEsq) {this.recebendoDanoEsq = recebendoDanoEsq;}

	public Shuriken getShuriken() {return shuriken;}
	public void setShuriken(Shuriken shuriken) {this.shuriken = shuriken;}

	public Rasengan getRasengan() {return rasengan;}
	public void setRasengan(Rasengan rasengan) {this.rasengan = rasengan;}

	public Goukakyuu getGoukakyuu() {return goukakyuu;}
	public void setGoukakyuu(Goukakyuu goukakyuu) {this.goukakyuu = goukakyuu;}

	public AudioClip getAtk() {return atk;}
	public void setAtk(AudioClip atk) {this.atk = atk;}

	public AudioClip getAtkSpecial() {return atkSpecial;}
	public void setAtkSpecial(AudioClip atkSpecial) {this.atkSpecial = atkSpecial;}

	public AudioClip getDamage() {return damage;}
	public void setDamage(AudioClip damage) {this.damage = damage;}

	public AudioClip getDead() {return dead;}
	public void setDead(AudioClip dead) {this.dead = dead;}

	public AudioClip getArremessando_shuriken() {return arremessando_shuriken;}
	public void setArremessando_shuriken(AudioClip arremessando_shuriken) {this.arremessando_shuriken = arremessando_shuriken;}

	public AudioClip getRecovery() {return recovery;}
	public void setRecovery(AudioClip recovery) {this.recovery = recovery;}

	public AudioClip getRecoveryLoop() {return recoveryLoop;}
	public void setRecoveryLoop(AudioClip recoveryLoop) {this.recoveryLoop = recoveryLoop;}

	public AudioClip getWin() {return win;}
	public void setWin(AudioClip win) {this.win = win;}

	public int getObstaculo() { return this.obstaculo;}
	public void setObstaculo(int obstaculo) { this.obstaculo = obstaculo;}
	
	//   *** MÉTODOS DO PERSONAGEM ***
	
	protected void Acoes() throws MalformedURLException{
		
		switch(this.estado){
			
			case 0:
				Parar(); 	
				break;
				
			case 1:
				Correr();
				break;
						
			case 2:
				//Pular();
				this.pular = true;
				break;
				
			case 3:
				Atacar();
				break;
				
			case 4:
				if(this.Shurikens > 0){
					Atacar_a_Distancia();
					Shurikens--;
				}
				break;
				
			case 5:
				Rasengan();
				Chidori();
				Goukakyuu();
				Mokuton();
				break;
				
			case 6:
				RecuperarChakra();
				break;
				
		}
		
	}
	
	public int Dano(int tipoAtaque){
		return ataque.nextInt(tipoAtaque)+this.ataqueMin;
	}
	
	public void DiminuirVida(int damage){
		this.vidaLabel -= damage;
		this.PontosDeVida -= damage;
	}
	
	protected void DiminuirChakra(int custo){
		
		this.chakraLabel -= custo;
		this.PontosDeChakra -= custo;
	}
	
	protected void RecuperarChakra(){
		
		if(!verChakraKey){
			recovery.play();
			//recoveryLoop.loop();
		}
		
		
		if(this.sentido == 1 && this.y == CHAO - alturaSprite){
	
			setIcon(recuperarDir);
			setBounds(this.x, this.y, recuperarDir.getIconWidth(), recuperarDir.getIconHeight());
			
			if(this.PontosDeChakra <= 105) {
				this.PontosDeChakra++;
				this.chakraLabel++;
			}
			
			Frames(FPS-40);
		}
		if((this.sentido == 2) && (this.y == CHAO - alturaSprite)){

			setIcon(recuperarEsq);
			setBounds(this.x, this.y, recuperarEsq.getIconWidth(), recuperarEsq.getIconHeight());
			
			if(this.PontosDeChakra <= 105){
				this.PontosDeChakra++;
				this.chakraLabel++;
			}

			Frames(FPS-40);
		}
		
		this.estado = 0;
		verChakraKey = true;
	}
	
	protected void Parar(){
		
		if(this.cenas >= paradoDireita.length) this.cenas = 0;
		
		if((this.sentido == 1) && (this.y == CHAO - alturaSprite)){
			setIcon(paradoDireita[this.cenas]);
			setBounds(this.x, this.y, paradoDireita[this.cenas].getIconWidth(), paradoDireita[this.cenas].getIconHeight());
		}
		else if((this.sentido == 2) && (this.y == CHAO - alturaSprite)){
			setIcon(paradoEsquerda[this.cenas]);
			setBounds(this.x, this.y, paradoEsquerda[this.cenas].getIconWidth(), paradoEsquerda[this.cenas].getIconHeight());
		}
		
		this.cenas++;
	
		Frames(20);
	}
	
	protected void Correr(){
		
		if(this.cenas >= direita.length) this.cenas = 0;
		
		if(this.sentido == 1){
			this.verSentido = this.sentido;
			
			if(this. y == CHAO - alturaSprite){
				
				if(getObstaculo() == 0){this.x+=this.velocidade;}
				
				setIcon(direita[this.cenas]);
				setBounds(x, y, direita[this.cenas].getIconWidth(),direita[this.cenas].getIconHeight());
			}
		}
		
		else if(this.sentido == 2){
			this.verSentido = this.sentido;
			
			 if(this. y == CHAO - alturaSprite){
					
					if(getObstaculo() == 0){ this.x-=this.velocidade;}
					
					setIcon(esquerda[this.cenas]);
					setBounds(x, y, esquerda[this.cenas].getIconWidth(),esquerda[this.cenas].getIconHeight());
			}
		}
		
		this.cenas++;
	
	}
	
	protected void Atacar(){
		
		atk.play();
		
		if(this.sentido == 1){				// DIREITA
			
			if(this. y == CHAO - alturaSprite){							// ATAQUE NO CHÃO
					for (int cenasDireita = 0; cenasDireita < atacandoDireita.length; cenasDireita++) {
						
						if (this.x >= PAREDE_ESQUERDA && this.x <= PAREDE_DIREITA) this.x += this.velocidade/10;
	
						setIcon(atacandoDireita[cenasDireita]);
						setBounds(x, y, atacandoDireita[cenasDireita].getIconWidth(),atacandoDireita[cenasDireita].getIconHeight());

						Frames(FPS);
					}
			}	
			else{			// ATAQUE PULANDO
					for (int cenasDireita = 0; cenasDireita < ataquePulandoDir.length; cenasDireita++) {
					
						setIcon(ataquePulandoDir[cenasDireita]);
						setBounds(x, y, ataquePulandoDir[cenasDireita].getIconWidth(),ataquePulandoDir[cenasDireita].getIconHeight());
						
						Frames(FPS);
					}
			}
		}
		if(this.sentido == 2){				//ESQUERDA
			
			if(this. y == CHAO - alturaSprite){							// ATAQUE NO CHÃO
					for (int cenasEsquerda = 0; cenasEsquerda < atacandoEsquerda.length; cenasEsquerda++) {
				
						if (this.x >= PAREDE_ESQUERDA && this.x <= PAREDE_DIREITA) this.x -= this.velocidade/10;
						
						setIcon(atacandoEsquerda[cenasEsquerda]);
						setBounds(x, y, atacandoEsquerda[cenasEsquerda].getIconWidth(),atacandoEsquerda[cenasEsquerda].getIconHeight());
						
						Frames(FPS);
					}
			}
			else{			
					for (int cenasEsquerda = 0; cenasEsquerda < ataquePulandoEsq.length; cenasEsquerda++) {
						
						setIcon(ataquePulandoEsq[cenasEsquerda]);
						setBounds(x, y, ataquePulandoEsq[cenasEsquerda].getIconWidth(),ataquePulandoEsq[cenasEsquerda].getIconHeight());
						
						Frames(FPS);
					}
					
			}
		}
		this.estado = 0;
	}
	
	protected void Atacar_a_Distancia(){
		
		int n = 1;
		
		arremessando_shuriken.play();
		
		if(this.sentido == 1){
			
			if(this. y == CHAO - alturaSprite){
				for (int cenasDireita = 0; cenasDireita < ataqueArremessoDir.length; cenasDireita++) {
				
					setIcon(ataqueArremessoDir[cenasDireita]);
					setBounds(x, y, ataqueArremessoDir[cenasDireita].getIconWidth(),ataqueArremessoDir[cenasDireita].getIconHeight());
			
					Frames(FPS);
				
					if(cenasDireita == n) ArremessarShuriken();
				
				}
			}
			else{
				for (int cenasDireita = 0; cenasDireita < ataqueArremessoUpDir.length; cenasDireita++) {
					
					setIcon(ataqueArremessoUpDir[cenasDireita]);
					setBounds(x, y, ataqueArremessoUpDir[cenasDireita].getIconWidth(),ataqueArremessoUpDir[cenasDireita].getIconHeight());
					
					Frames(FPS);
					
					if(cenasDireita == n) ArremessarShuriken();
				}
			}
		}
		if(this.sentido == 2){
			
			if(this. y == CHAO - alturaSprite){
				for (int cenasEsquerda = 0; cenasEsquerda < ataqueArremessoEsq.length; cenasEsquerda++) {
					
					setIcon(ataqueArremessoEsq[cenasEsquerda]);
					setBounds(x, y, ataqueArremessoEsq[cenasEsquerda].getIconWidth(),ataqueArremessoEsq[cenasEsquerda].getIconHeight());
					
					Frames(FPS);
					
					if(cenasEsquerda == n) ArremessarShuriken();
				}
			}
			else{
				for (int cenasEsquerda = 0; cenasEsquerda < ataqueArremessoUpEsq.length; cenasEsquerda++) {
					
					setIcon(ataqueArremessoUpEsq[cenasEsquerda]);
					setBounds(x, y, ataqueArremessoUpEsq[cenasEsquerda].getIconWidth(),ataqueArremessoUpEsq[cenasEsquerda].getIconHeight());

					Frames(FPS);
					
					if(cenasEsquerda == n) ArremessarShuriken();
				}
			}
		}
		this.estado = 0;
	}
	
	private void Pular(){
			
				
				y = (int)(CHAO - alturaSprite + (-23*n) + n*n);
				n+=2;
				
				if(this.sentido == 1){
					if(this.estadoAnterior == 1 || this.estadoAnterior == 3){
						if(getObstaculo() == 0) x+=20;
					}
					setIcon(pularDireitaUp);
				}
					
				if(this.sentido == 2){
					if(this.estadoAnterior == 1 || this.estadoAnterior == 3){
						if(getObstaculo() == 0) x-=20;
					}
					setIcon(pularEsquerdaUp);
				}
					
				if(y<=CHAO - alturaSprite) setLocation(x,y);
				else{
					this.y = CHAO - alturaSprite;
					setLocation(x,y);
					this.n = 0;
					this.estado = 0;
					this.pular = false;
				}

		}
	
	
	protected void ArremessarShuriken(){ 
	
			shuriken = new Shuriken();
			Container tela = this.getParent(); // retorna a tela
		
			tela.add(shuriken);
			tela.setComponentZOrder(shuriken, 0);
	
			if(shuriken != null){
				shuriken.CapturarPosicao(this.x, this.y, this.sentido);
				shuriken.lancar();
			}
			
			//this.lancarShuriken = true;
	}
	
	public void Recuar(String sentidoGolpe){
		
		
		if(this.PontosDeVida > 0){
	
			damage.play();
			
			if (this.sentido == 1) {
			
				for(int recuarDir = 0; recuarDir < 5; recuarDir++){
		
					if(this.x >= PAREDE_ESQUERDA && this.x <= PAREDE_DIREITA){
						if(sentidoGolpe.equals("SentidoContrario")) x-=recuarDir; // Naruto  <:: Inimigo
						else x+=recuarDir; // Inimigo ::> Naruto
					}	
					setIcon(recebendoDanoDir);
					setBounds(x, y, recebendoDanoDir.getIconWidth(),recebendoDanoDir.getIconHeight());
					this.estado = -1;
					Frames(40);
				}
			}
			if (this.sentido == 2){
				for(int recuarEsq = 0; recuarEsq < 5; recuarEsq++){
				
					if(this.x >= PAREDE_ESQUERDA && this.x <= PAREDE_DIREITA){
						if(sentidoGolpe.equals("SentidoContrario")) x+=recuarEsq; // Inimigo ::> Naruto
						else x-=recuarEsq; // Naruto <:: Inimigo
					}
					setIcon(recebendoDanoEsq);
					setBounds(x, y, recebendoDanoEsq.getIconWidth(),recebendoDanoEsq.getIconHeight());
					this.estado = -1;
					Frames(40);
				}
			
			}
			this.estado = 0;
		}
		else{
			
			dead.play();
			
			if(this.sentido == 1){
				
				int aux = 0;
				
				for(int recuarDir = 0; recuarDir <= 8; recuarDir++){
					
					if(this.x >= PAREDE_ESQUERDA && this.x <= PAREDE_DIREITA){
						if(sentidoGolpe.equals("SentidoContrario")) x-=recuarDir; // Naruto  <:: Inimigo
						else x+=recuarDir; // Inimigo ::> Naruto
					}
					if(recuarDir < 5){
						setIcon(morreuDir[0]);
						setBounds(x, y, morreuDir[0].getIconWidth(),morreuDir[0].getIconHeight());
					}
					else{
						if(aux < morreuDir.length){	
							setIcon(morreuDir[aux]);
							setBounds(x, y, morreuDir[aux].getIconWidth(),morreuDir[aux].getIconHeight());
							aux++;
						}
					}
					this.estado = -1;
					Frames(FPS);
				}
			}
			if(this.sentido == 2){
				
				int aux = 0;
				
				for(int recuarEsq = 0; recuarEsq <= 8; recuarEsq++){
					
					if(this.x >= PAREDE_ESQUERDA && this.x <= PAREDE_DIREITA){
						if(sentidoGolpe.equals("SentidoContrario")) x+=recuarEsq; // Naruto  <:: Inimigo
						else x-=recuarEsq; // Inimigo ::> Naruto
					}
					if(recuarEsq < 5){
						setIcon(morreuEsq[0]);
						setBounds(x, y, morreuEsq[0].getIconWidth(),morreuEsq[0].getIconHeight());
					}
					else{
						if(aux < morreuEsq.length){
							setIcon(morreuEsq[aux]);
							setBounds(x, y, morreuEsq[aux].getIconWidth(),morreuEsq[aux].getIconHeight());
							aux++;
						}
					}
					this.estado = -1;
					Frames(FPS);
				}
			}
			
		}
	}
	
	public void Colidiu(){
		if(this.sentido == this.verSentido){
			setObstaculo(1);
		}
		else if (this.sentido != this.verSentido){
			setObstaculo(0);
		}

	}
	
	// *** HABILIDADES ESPECIAIS *** 
	
	protected abstract void Rasengan() throws MalformedURLException;
	protected abstract void Chidori();
	protected abstract void Goukakyuu() throws MalformedURLException;
	protected abstract void Mokuton();
	
	
	protected abstract void CarregarImagens();
	protected abstract void CarregarAudio()throws MalformedURLException;
	
	//   *** MÉTODO RUNNABLE ***
	
	public void run(){
		while(true){
			
			if(this.pular){
				Pular();
			}
			try { Acoes(); } 
			catch (MalformedURLException e) { e.printStackTrace();}
			Frames(FPS);
		}
	}
	
	// *** MÉTODOS CENARIO ***
	
	
	public void Frames(int segundos){
		
		try {
			Thread.sleep(segundos);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	// *** MÉTODOS KEYLISTENER
	
	public void keyPressed(KeyEvent e) {
		if(this.key == 1){
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				this.estadoAnterior = this.estado;
				this.estado = 1;
				this.sentido = 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT){
				this.estadoAnterior = this.estado;
				this.estado = 1;
				this.sentido = 2;
			}
			else if(e.getKeyCode() == KeyEvent.VK_X){
				this.estadoAnterior = this.estado;
				this.estado = 6;
				
			}
		}
		else{
			if(e.getKeyCode() == KeyEvent.VK_NUMPAD6){
				this.estadoAnterior = this.estado;
				this.estado = 1;
				this.sentido = 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_NUMPAD4){
				this.estadoAnterior = this.estado;
				this.estado = 1;
				this.sentido = 2;
			}
			else if(e.getKeyCode() == KeyEvent.VK_L){
				this.estadoAnterior = this.estado;
				this.estado = 6;
			}
		}
		
		
	}

	public void keyReleased(KeyEvent e) {
		
		if(this.key == 1){
			if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT
					|| e.getKeyCode() == KeyEvent.VK_X){
				this.estado = 0;
				if (verChakraKey) {
					recoveryLoop.stop();
				}
				this.verChakraKey = false;
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP){
				this.estadoAnterior = this.estado;
				this.estado = 2;
			}
			else if(e.getKeyCode() == KeyEvent.VK_A){
				this.estadoAnterior = this.estado;
				this.estado = 3;
			}
			else if(e.getKeyCode() == KeyEvent.VK_S){
				this.estadoAnterior = this.estado;
				this.estado = 4;
				this.arremessar = true;
			}
			else if(e.getKeyCode() == KeyEvent.VK_D){
				this.estadoAnterior = this.estado;
				this.estado = 5;
			}
		}
		else{
			if(e.getKeyCode() == KeyEvent.VK_NUMPAD6 || e.getKeyCode() == KeyEvent.VK_NUMPAD4
					|| e.getKeyCode() == KeyEvent.VK_L){
				this.estado = 0;
				if (verChakraKey) {
					recoveryLoop.stop();
				}
				this.verChakraKey = false;
				
			}
			else if(e.getKeyCode() == KeyEvent.VK_NUMPAD8){
				this.estadoAnterior = this.estado;
				this.estado = 2;
			}
			else if(e.getKeyCode() == KeyEvent.VK_I){
				this.estadoAnterior = this.estado;
				this.estado = 3;
			}
			else if(e.getKeyCode() == KeyEvent.VK_O){
				this.estadoAnterior = this.estado;
				this.estado = 4;
				this.arremessar = true;
			}
			else if(e.getKeyCode() == KeyEvent.VK_P){
				this.estadoAnterior = this.estado;
				this.estado = 5;
			}
		}
	}
	
	public abstract void keyTyped(KeyEvent e);
	
}
