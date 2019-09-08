package javatuning.ch4.mstrwkr_me2;

public class Test {

    @org.junit.Test
    public void testMasterWorker() throws InterruptedException {
        Master master = new Master(new Master.CallBack() {
            @Override
            public void onComplete(int result) {
                System.out.println(result);
            }
        });
        for (int i = 1; i < 500; i++) {
            master.addTask(i);
        }
        master.execute();

        Thread.sleep(5*1000);
    }
}
