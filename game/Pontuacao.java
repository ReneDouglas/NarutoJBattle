package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Pontuacao {

	private File arquivo;
	private int pontuacaoAtual;

	public Pontuacao() {
		arquivo = new File("C:\\recorde.txt");
		CarregarArquivo();
	}
	
	public int getPontuacaoAtual() {return pontuacaoAtual;}
	public void setPontuacaoAtual(int pontuacaoAtual) {this.pontuacaoAtual = pontuacaoAtual;}
	
	private void CarregarArquivo(){
		int i = 0;
		
		if(arquivo.exists()){
			
			String temp;
			BufferedReader br;
			
			try{
				br = new BufferedReader(new FileReader(arquivo));
				temp = br.readLine();
				while(temp!=null){
					
					try {
						i = Integer.parseInt(temp);
					} catch (Exception e) {
						i = 0;
					}
					temp = br.readLine();
				}
			
				br.close();
			}catch (Exception e) {
				
			}	
		} else
			try {
				arquivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		this.pontuacaoAtual = i;
	}
	
	public void SalvarPontuacao(int pontuacao){
		
		try {
			
			if(pontuacaoAtual < pontuacao){
				
				String salvar = "";
				salvar += pontuacao;
				BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo)); // (arquivo,true) : true nao sobrescreve linha
				bw.write(salvar);
				bw.close();
			}
		} catch (Exception e) {
		}
	}
}
