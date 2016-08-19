package rx;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.mockito.Mockito;

import rx.Observable.OnSubscribe;
import rx.functions.Action1;
import rx.functions.Func2;

public class HDObservableTest {
	@Test(expected = NoSuchElementException.class)
	public void testReduceWithEmptyObservablle() {
		Observable<Integer> observable = Observable.range(1,  0);
		observable.reduce(new Func2<Integer, Integer, Integer>() {
			
			@Override
			public Integer call(Integer t1, Integer t2) {
				return t1 + t2;
			}
		}).toBlocking().forEach(new Action1<Integer>() {

			@Override
			public void call(Integer t) {
				// do nothing
			}
		});
		
		fail("here should be a exception");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSubscribeFail() {
		Observer<String> observer = Mockito.mock(Observer.class);
		final RuntimeException re = new RuntimeException("re");
		Observable<String> o = Observable.create(new OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> t) {
				throw re;
			}
		});
		
		o.subscribe(observer);
		verify(observer, times(0)).onNext(anyString());
		verify(observer, times(0)).onCompleted();
		verify(observer, times(1)).onError(re);
	}
}
