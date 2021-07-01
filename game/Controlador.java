package game;

import java.net.MalformedURLException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import personagens.Naruto;
import personagens.Ninja;

public class Controlador extends Thread{
	
	private Ninja personagem;
	private static Random random = new Random();
	private int decisao;
	private int estadoInimigo;
	private int sentidoInimigo;
	private int sentido_projetil;
	private int localizacaoX, localizacaoY;
	private int localizacaoX_2, localizacaoY_2;
	private int distancia, distanciaAnterior, distancia_projetil;
	private int decisaoTomada;
	private int count = 0;
	private boolean matar;
	
	private Timer cronometro;
	private int segundos;
	
	public Controlador(Ninja ninja) {
		this.personagem = ninja;
		cronometro = new Timer();
	}
	
	public int getCount() {return count;}
	public void setCount(int count) {this.count = count;}

	public boolean isMatar() {return matar;}
	public void setMatar(boolean matar) {this.matar = matar;}

	public Ninja getPersonagem() {return personagem;}
	public void setPersonagem(Ninja personagem) {this.personagem = personagem;}

	public static Random getRandom() {return random;}
	public static void setRandom(Random random) {Controlador.random = random;}

	public int getDecisao() {return decisao;}
	public void setDecisao(int decisao) {this.decisao = decisao;}

	public int getEstadoInimigo() {return estadoInimigo;}
	public void setEstadoInimigo(int estadoInimigo) {this.estadoInimigo = estadoInimigo;}

	public int getSentidoInimigo() {return sentidoInimigo;}
	public void setSentidoInimigo(int sentidoInimigo) {this.sentidoInimigo = sentidoInimigo;}

	public int getSentido_projetil() {return sentido_projetil;}
	public void setSentido_projetil(int sentido_projetil) {this.sentido_projetil = sentido_projetil;}

	public int getLocalizacaoX() {return localizacaoX;}
	public void setLocalizacaoX(int localizacaoX) {this.localizacaoX = localizacaoX;}

	public int getLocalizacaoY() {return localizacaoY;}
	public void setLocalizacaoY(int localizacaoY) {this.localizacaoY = localizacaoY;}

	public int getLocalizacaoX_2() {return localizacaoX_2;}
	public void setLocalizacaoX_2(int localizacaoX_2) {this.localizacaoX_2 = localizacaoX_2;}

	public int getLocalizacaoY_2() {return localizacaoY_2;}
	public void setLocalizacaoY_2(int localizacaoY_2) {this.localizacaoY_2 = localizacaoY_2;}

	public int getDistancia() {return distancia;}
	public void setDistancia(int distancia) {this.distancia = distancia;}

	public int getDistanciaAnterior() {return distanciaAnterior;}
	public void setDistanciaAnterior(int distanciaAnterior) {this.distanciaAnterior = distanciaAnterior;}

	public int getDistancia_shuriken() {return distancia_projetil;}
	public void setDistancia_shuriken(int distancia_shuriken) {this.distancia_projetil = distancia_shuriken;}

	public int getDecisaoTomada() {return decisaoTomada;}
	public void setDecisaoTomada(int decisaoTomada) {this.decisaoTomada = decisaoTomada;}

	public Timer getCronometro() {return cronometro;}
	public void setCronometro(Timer cronometro) {this.cronometro = cronometro;}

	public int getSegundos() {return segundos;}
	public void setSegundos(int segundos) {this.segundos = segundos;}

	protected int Decisao(int i){
		return random.nextInt(i);
	}
	
	protected void MoverPlayer() throws MalformedURLException, InterruptedException{
		
		VerificarDistancia(estadoInimigo,sentidoInimigo,localizacaoX,localizacaoY);

		
		if(personagem.getPontosDeVida() <= 0) this.matar = true;
		if(personagem.isGanhou()) this.matar = true;
		
		if(distancia <= 65){
			
			this.decisaoTomada = Decisao(4);
			if(decisaoTomada == 0)personagem.setEstado(3);
			if(decisaoTomada == 1)personagem.setEstado(3);
			if(decisaoTomada == 2)personagem.setEstado(5);
			if(decisaoTomada == 3)personagem.setEstado(5);
			
			if(decisaoTomada == 3){
				if(sentidoInimigo == 2 && personagem.getSentido() == 2){
					personagem.setSentido(1);
					personagem.setEstado(3);
				}
				if(sentidoInimigo == 1 && personagem.getSentido() == 1){
					personagem.setSentido(2);
					personagem.setEstado(3);
				}
			}
			else if(decisaoTomada == 5){
				if(personagem instanceof Naruto){
					if(sentidoInimigo == 2 && personagem.getSentido() == 2){
						personagem.setSentido(1);
						personagem.setEstado(5);
					}
					if(sentidoInimigo == 1 && personagem.getSentido() == 1){
						personagem.setSentido(2);
						personagem.setEstado(5);
					}
				}
				
			}
		}

		else if(distancia > 65){
			
			if(distanciaAnterior == 0){
				personagem.setSentido(2);
				personagem.setEstado(1);
			}
			
			else if(distanciaAnterior > distancia){
				if((localizacaoX <= 400 || localizacaoX <= 300)){
					personagem.setSentido(2);
					personagem.setEstado(1);
				}
				else if((localizacaoX >= 400 || localizacaoX >= 300)){
					personagem.setSentido(1);
					personagem.setEstado(1);
				}
					
				
			}
			else if(distanciaAnterior < distancia){
				if((localizacaoX <= 400 || localizacaoX <= 300 || localizacaoX <= 200
						|| localizacaoX <= 100)){	
					personagem.setSentido(2);
					personagem.setEstado(1);
				}
				else if ((localizacaoX >= 400 || localizacaoX >= 300)){
					if(personagem.getBounds().x >= 300 || personagem.getBounds().x >= 400){
						personagem.setSentido(2);
					}
					else personagem.setSentido(1);
					personagem.setEstado(1);
				}
			}
			
			else if(distanciaAnterior == distancia){
				if ((localizacaoX <= 400 || localizacaoX <= 300)&&(personagem.getBounds().x > 400)) {
					personagem.setSentido(2);
					personagem.setEstado(1);
				}
				else if((localizacaoX <= 400 || localizacaoX <= 300)&&(personagem.getBounds().x < 400)){
					personagem.setSentido(1);
					personagem.setEstado(1);
				}
				else if((localizacaoX >= 400 || localizacaoX >= 300)&&(personagem.getBounds().x < 400)){
						personagem.setSentido(1);
						personagem.setEstado(1);
				}
				else{
					personagem.setSentido(2);
					personagem.setEstado(1);
				}
			}

			
			
		}
		
		if(Decisao(5) == 2){
			
			if(personagem.getPontosDeChakra() <= 35){
				while(personagem.getPontosDeChakra() < 25){	
					System.out.println(personagem.getPontosDeChakra());
					personagem.setEstado(6);
					
				}
				
			}
			else{
				personagem.setEstado(4);
			}
			
		}
		
		if(Decisao(8) == 6){
			if(!(personagem instanceof Naruto)) personagem.setEstado(5);
			while(personagem.getPontosDeChakra() < 25){	
				System.out.println(personagem.getPontosDeChakra());
				personagem.setEstado(6);
			
			}
		
		}
		
		if(Decisao(4) == 2){
			while(count < 150){
				count++;
				personagem.setEstado(6);
			}
			count = 0;
		}
	
		if(distancia_projetil != 0){
			if(distancia_projetil < 250){
				if(personagem.getEstadoAnterior() != 2 || personagem.getEstadoAnterior() != 0){
					personagem.setEstado(2);
					distancia_projetil = 0;
				}
				else{
					this.decisaoTomada = Decisao(3);
					if(decisaoTomada == 0)personagem.setEstado(1);
					if(decisaoTomada == 1)personagem.setEstado(3);
					if(decisaoTomada == 2)personagem.setEstado(5);
					
				}
			}
		}
		distancia_projetil = 0;
		
	}
	
	protected void VerificarDistancia(int estado,int sentido ,int localizacaoX, int localizacaoY){
		
		this.estadoInimigo = estado;
		this.sentidoInimigo = sentido;
		this.localizacaoX = localizacaoX;
		this.localizacaoY = localizacaoY;
		
		distancia = (int) Math.sqrt((localizacaoX - personagem.getBounds().x)
					*(localizacaoX - personagem.getBounds().x)
					+(localizacaoY - personagem.getBounds().y)
					*(localizacaoY - personagem.getBounds().y)); 
		
		
	}
	
	protected void VerificarDistanciaProjetil(int sentido, int localizacaoX, int localizacaoY){
		
			distancia_projetil = (int) Math.sqrt((localizacaoX - personagem.getBounds().x)
					*(localizacaoX - personagem.getBounds().x)
					+(localizacaoY - personagem.getBounds().y)
					*(localizacaoY - personagem.getBounds().y));
			
		
	}
	
	public void Temporizador() {  
		
        TimerTask tarefa = new TimerTask() {  
            private int contagem;
           
			public void run() {  
               segundos = this.contagem++;   
            }  
        };  
        cronometro.scheduleAtFixedRate(tarefa, 0, 1000);  
    } 
	
	public void run() {
		super.run();
		Temporizador();
		while(!matar){
			
			if(segundos % 2 == 0){
					distanciaAnterior = distancia;
			}
		try {
			MoverPlayer();
			Thread.sleep(300);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

	}

}
