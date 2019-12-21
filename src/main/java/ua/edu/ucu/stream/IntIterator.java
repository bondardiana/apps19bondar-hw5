package ua.edu.ucu.stream;

public interface IntIterator {
    boolean hasNext();
    void next();
    int getIndex();
    int value();
    void setIndex(int index);


}
