package activity4.exercise1;

/**
 * Classe responsável por consumir recursos.
 * 
 * @author daniel
 */
public class Consumer implements Runnable {

	private QueueMonitor product;
	private int time;
	
	public Consumer (QueueMonitor product) {
		this(product, 0);
	}
	
	public Consumer (QueueMonitor product, int time) {
		this.product = product;
		this.time = time;
	}
	
	public void consume(Long value) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException " + e.getMessage());
		}
		System.out.println("Consumindo " + value);
	}
	
	@Override
	public void run() {
		while (true) {
			Long value = this.product.take();
			this.consume(value);
		}
	}
	
}
