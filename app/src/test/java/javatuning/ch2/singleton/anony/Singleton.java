package javatuning.ch2.singleton.anony;

/**
 * static ���εĳ�Ա�����Լ�static��̬�Ĵ���飬��jvm��������ɾ��Ѿ���ʼ��,
 */
public abstract class Singleton {
	private Singleton(){
		//���������Ĺ��̿��ܻ�Ƚ���
	}

	private static Singleton instance = new Singleton(){};
	public static Singleton getInstance() {
		return instance;
	} 
}