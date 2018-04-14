package javatuning.ch2.timespace;

import org.junit.Test;

public class SwapValue {

	/**
	 * 时间换空间
	 */
	@Test
	public void main(){
		int a=55;
		int b=66;
		a=a+b;
		b=a-b;
		a=a-b;
		System.out.println("a="+a+" b="+b);
	}

	/**
	 * 空间换时间
	 */
	@Test
	public void main2(){
		int a=55;
		int b=66;
		int temp = a;
		a = b;
		b = temp;
		System.out.println("a="+a+" b="+b);
	}
}
