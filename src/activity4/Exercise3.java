package activity4;

import java.util.Random;

import activity4.core.ObjectMonitor;

/**
 * Escreva um monitor BoundedCounter que possui um valor
 * mı́nimo e máximo. A classe possui dois métodos: increment()
 * e decrement(). Ao alcançar os limites mı́nimo ou máximo, a
 * thread que alcançou deve ser bloqueada.
 * 
 * @author daniel
 */
public class Exercise3 {
	public static void main(String[] args) {
		BoundedCounter counter = new BoundedCounter(0, 100);
		ObjectMonitor<Integer> monitor = new ObjectMonitor<Integer>(counter);
		
		Runnable incrementer = () -> {
			String name = Thread.currentThread().getName();
			Random random = new Random();
			while (true) {
				try {
					Thread.sleep(random.nextInt(1000) + 500);
					for (int i = 1; Math.random() > 0.5; i++) {
						System.out.println(name + ": " + i);
						Thread.sleep(random.nextInt(1000) + 500);
					}
				} catch (InterruptedException e) {
					// ...
				}
				Integer value = monitor.produce(null);
				System.out.println(name + ": Increment to " + value);
			}
		};
		
		Runnable decrementer = () -> {
			String name = Thread.currentThread().getName();
			Random random = new Random();
			while (true) {
				try {
					for (int i = 0; Math.random() > 0.5; i++) {
						System.out.println(name + ": " + i);
						Thread.sleep(random.nextInt(1000) + 500);
					}
				} catch (InterruptedException e) {
					// ...
				}
				Integer value = monitor.consume();
				System.out.println(Thread.currentThread().getName() + ": Decrement to " + value);
			}
		};
		
		new Thread(incrementer, "Incrementer").start();
		new Thread(decrementer, "Decrementer").start();
	}
}
