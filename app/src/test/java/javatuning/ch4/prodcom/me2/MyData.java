package javatuning.ch4.prodcom.me2;

public class MyData {
    private  final int intData;
    public MyData(int d){
        intData=d;
    }
    public MyData(String d){
        intData=Integer.valueOf(d);
    }
    public int getData(){
        return intData;
    }
    @Override
    public String toString(){
        return "data:"+intData;
    }
}
