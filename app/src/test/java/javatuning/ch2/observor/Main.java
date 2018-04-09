package javatuning.ch2.observor;

import org.junit.Test;

public class Main {
	@Test
	public void main() {
		ConcreteSubject sub=new ConcreteSubject();
		sub.attach(new ConcreteObserver());
		sub.inform();
	}
}
