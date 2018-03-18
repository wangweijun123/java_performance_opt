package javatuning.ch4.future.pattern;

import org.junit.Test;

public class Main {

    @Test
    public void addition_isCorrect() throws Exception {
        Client client = new Client();

        Data data = client.request("a");
        System.out.println("请求完毕");
//        try {
//            //这里可以用一个sleep代替了对其它业务逻辑的处理
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//        }
        //使用真实的数据
        System.out.println("数据 = " + data.getResult());

    }
}
