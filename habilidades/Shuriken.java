package habilidades;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import cenarios.Cenario;

public class Shuriken extends JLabel implements Runnable, Cenario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int x;
	protected int y = 20;

	private int sentido;
	private int cenas = 0;
	private int VELOCIDADE = 35;
	private boolean obstaculo = false;

	private Icon shurikenDir[];
	private Icon shurikenEsq[];

	private Thread lancar;

	public Shuriken() {

		lancar = new Thread(this);

		shurikenDir = new ImageIcon[2];
		shurikenEsq = new ImageIcon[2];

		for (int a = 0; a < shurikenDir.length; a++) {
			shurikenDir[a] = new ImageIcon(getClass().getResource(
					"/sprites/sprites diversos/Shuriken e efeito/Shuriken_"
							+ (a + 1) + ".png"));
			shurikenEsq[a] = new ImageIcon(getClass().getResource(
					"/sprites/sprites diversos/Shuriken e efeito/esquerda/Shuriken_"
							+ (a + 1) + ".png"));
		}

	}

	public void lancar() {
		lancar.start();

	}

	public void girar() {

		if (this.sentido == 1) {

			this.x += this.VELOCIDADE;

			setIcon(shurikenDir[cenas]);
			setBounds(x, y, shurikenDir[cenas].getIconWidth(),
					shurikenDir[cenas].getIconHeight());
			cenas++;

			if (cenas >= shurikenDir.length)
				cenas = 0;

		}
		if (this.sentido == 2) {

			this.x -= this.VELOCIDADE;

			setIcon(shurikenEsq[cenas]);
			setBounds(x, y, shurikenEsq[cenas].getIconWidth(),
					shurikenEsq[cenas].getIconHeight());
			cenas++;

			if (cenas >= shurikenEsq.length)
				cenas = 0;

		}

	}

	public void CapturarPosicao(int x, int y, int sentido) {
		this.x = x += sentido == 1 ? 30 : -5;
		this.y += y;
		this.sentido = sentido;
	}

	@SuppressWarnings("deprecation")
	public void run() {

		while (true) {

			if (obstaculo) {
				this.setVisible(false);
				break;
			}
			if (this.x <= PAREDE_ESQUERDA || this.x >= PAREDE_DIREITA)
				break;

			girar();
			Frames(FPS);

		}
		this.setVisible(false);
		lancar.stop();
		lancar.destroy();

	}

	public void Frames(int segundos) {

		try {
			Thread.sleep(segundos);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public int getSentido() {
		return this.sentido;
	}

	public void setSentido(int sentido) {
		this.sentido = sentido;
	}

	public boolean isObstaculo() {
		return this.obstaculo;
	}

	public void setObstaculo(boolean obstaculo) {
		this.obstaculo = obstaculo;
	}

}
