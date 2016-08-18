package rx.hd;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class CountDownLatchTest {
	@Test
	public void test1() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(10);
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 12; i++) {
					latch.countDown();
					System.out.println(i);

				}
			}
		}).start();
		latch.await();
		System.out.println("latch state->" + latch.toString());
	}
}
