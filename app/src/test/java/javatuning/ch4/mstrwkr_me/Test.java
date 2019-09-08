package javatuning.ch4.mstrwkr_me;

public class Test {

    @org.junit.Test
    public void testMasterWorker() {
        Master master = new Master(3);
        for (int i = 1; i < 5; i++) {
            master.addTask(i);
        }
        master.execute();
    }
}
