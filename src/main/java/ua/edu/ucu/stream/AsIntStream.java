package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

public class AsIntStream implements IntStream {
    private int[] values;
    private int length;
    private IntIterator iterator;
    private AsIntStream() {
        // To Do
    }

    public static IntStream of(int... values) {
        AsIntStream object = new AsIntStream();
        object.iterator = object.new DefaultIterator();
        return object;
    }

    @Override
    public Double average() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer max() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer min() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer sum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void forEach(IntConsumer action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class DefaultIterator implements IntIterator {
        private int currentInd = -1;
        private int length;
        DefaultIterator(){

        }
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public void next() {
            ++currentInd;
        }

        @Override
        public int value() {
            return values[currentInd];
        }

        @Override
        public int getIndex() {
            return currentInd;
        }

        @Override
        public void setIndex(int currentIndex) {
            this.currentInd = currentIndex;
        }
    }
    public class FilterIterator extends DefaultIterator{
        IntIterator oldIterator;
        IntPredicate predicate;
        FilterIterator(IntIterator old, IntPredicate predicate){
            this.oldIterator = old;
            this.predicate = predicate;
        }
        @Override
        public void next() {
            while (oldIterator.hasNext() && !predicate.test(oldIterator.value())) {
                }
            }
    }
    public class MapIterator extends DefaultIterator{
        IntIterator oldIterator;
        IntUnaryOperator operator;
        MapIterator(IntIterator old, IntUnaryOperator operator){
            this.operator = operator;
            this.oldIterator = old;
        }
        @Override
        public int value() {
            return operator.apply(oldIterator.value());
        }
    }
}
