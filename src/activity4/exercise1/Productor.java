package activity4.exercise1;

import java.util.Random;

/**
 * Classe responsável por produzir recursos.
 * 
 * @author daniel
 */
public class Productor implements Runnable {

	private QueueMonitor product;
	
	private Random random = new Random();

	private int time;
	
	public Productor(QueueMonitor product) {
		this(product, 0);
	}
	
	public Productor(QueueMonitor product, int time) {
		this.product = product;
		this.time = time;
	}
	
	private Long makeProduct() {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException " + e.getMessage());
		}
		return random.nextLong();
	}
	
	@Override
	public void run() {
		while (true) {
			this.product.put(this.makeProduct());
		}
	}
	
}
