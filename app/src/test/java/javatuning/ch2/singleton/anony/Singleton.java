package javatuning.ch2.singleton.anony;

/**
 * static 修饰的成员变量以及static静态的代码块，在jvm加载类完成就已经初始化,
 */
public abstract class Singleton {
	private Singleton(){
		//创建单例的过程可能会比较慢
	}

	private static Singleton instance = new Singleton(){};
	public static Singleton getInstance() {
		return instance;
	} 
}