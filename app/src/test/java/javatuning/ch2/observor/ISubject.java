package javatuning.ch2.observor;

public interface ISubject{  
    void attach(IObserver observer);
    void detach(IObserver observer);
    void inform();
}  
