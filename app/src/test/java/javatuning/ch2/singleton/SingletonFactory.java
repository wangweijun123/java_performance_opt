package javatuning.ch2.singleton;

public class SingletonFactory {
	private SingletonFactory() {
		System.out.println("Singleton is create");
	}

	private static SingletonFactory instance = new SingletonFactory();
	public static SingletonFactory getInstance() {
		return instance;
	} 
}