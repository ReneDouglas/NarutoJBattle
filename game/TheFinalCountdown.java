package game;

import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;


public class TheFinalCountdown {

	private Timer t;
	private TimerTask count;
	private int contador = 60;
	
	private Font fonte;
	private JLabel numero;
	
	
	public TheFinalCountdown() {
		
		this.t = new Timer();
		this.numero = new JLabel();
		
	}
	
	public JLabel getNumero() {return numero;}
	public void setNumero(JLabel numero) {this.numero = numero;}
	
	public void Contar(){
		
		this.count = new TimerTask() {
	
			public void run() {
				contador--;
				Escrever(contador);
				Terminou();
			}
		}; 
		t.scheduleAtFixedRate(count, 0, 1000);
	}
	
	private void Escrever(int n){
		
		fonte = new Font("Serif", Font.BOLD, 60);
		numero.setText(""+n);
		numero.setFont(fonte);
		numero.setBounds(380, 15, 60, 60);
		numero.setForeground(Color.WHITE);
		
	}
	
	public boolean Terminou(){
		
		if(contador == 0){
			t.cancel();
			return true;
		}
		else return false;
		
	}
}
