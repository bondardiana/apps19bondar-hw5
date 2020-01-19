package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AsIntStream implements IntStream {
    private IntIterator iterator;
    private int[] values;
    private int length;
    private AsIntStream() {
        // To Do
    }

    public static IntStream of(int... values) {

        AsIntStream intStream = new AsIntStream();
        intStream.iterator = intStream.new DefaultIterator();
        intStream.length = values.length;
        intStream.values =values;
        return intStream;
    }

    @Override
    public Double average() {
        double sum = 0;
        int count = 0;
        while (iterator.moveToNext()) {
            sum += iterator.getValue();
            ++count;
        }

        System.out.println(sum/count);
        if (count > 0)
            return sum/count;

        throw new IllegalArgumentException();
    }

    @Override
    public Integer max() {
        int max = Integer.MIN_VALUE;
        if (!iterator.moveToNext()){
            throw new IllegalArgumentException();
        }

        while (iterator.moveToNext()) {
            if (iterator.getValue() > max) {
                max = iterator.getValue();
            }
        }

        return max;
    }

    @Override
    public Integer min() {
        int min = Integer.MAX_VALUE;
        if (!iterator.moveToNext()){
            throw new IllegalArgumentException();
        }
        while (iterator.moveToNext()) {
            if (iterator.getValue() < min) {
                min = iterator.getValue();
            }
        }

        return min;
    }

    @Override
    public long count() {
        long count = 0;
        while (iterator.moveToNext()) {
            count++;
        }
        return count;
    }

    @Override
    public Integer sum() {
        int sum = 0;
        if (!iterator.moveToNext()){
            throw new IllegalArgumentException();
        }
        while (iterator.moveToNext()) {
            sum += iterator.getValue();
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        this.iterator = new FilterIterator(this.iterator, predicate);
        return this;
    }

    @Override
    public void forEach(IntConsumer action) {

        while (iterator.moveToNext()) {
            action.accept(iterator.getValue());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        this.iterator = new MapIterator(this.iterator, mapper);
        return this;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        List<Integer> resultList = new LinkedList<>();
        while (iterator.moveToNext()) {
            IntStream intStream = func.applyAsIntStream(iterator.getValue());
            for(int elem:intStream.toArray()){
                resultList.add(elem);
            }

        }
        int[] resultArray = new int[resultList.size()];
        int index = -1;
        for (Integer resultInt : resultList) {
            resultArray[++index] = resultInt;
        }
        return AsIntStream.of(resultArray);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        while (iterator.moveToNext()) {
            result = op.apply(result, iterator.getValue());
        }
        return result;
    }

    @Override
    public int[] toArray() {
        List<Integer> resultList = new ArrayList<>();
        while (iterator.moveToNext()) {
            resultList.add(iterator.getValue());
        }
        int[] resultArray = new int[resultList.size()];
        int index = -1;
        for (Integer resultInteger : resultList) {
            resultArray[++index] = resultInteger;
        }
        return resultArray;
    }

    private class DefaultIterator implements IntIterator {
        private int currentIndex = -1;
        private boolean isEnd = true;

        @Override
        public boolean moveToNext() {
            ++currentIndex;
            this.isEnd = currentIndex >= length;
            return !this.isEnd;
        }

        @Override
        public int getValue() {
            return values[currentIndex];
        }

        @Override
        public boolean isEnd() {
            return isEnd;
        }


    }
}


class FilterIterator implements IntIterator {

    private IntIterator oldIterator;
    private IntPredicate predicate;

    public FilterIterator (IntIterator oldIterator, IntPredicate predicate) {
        this.oldIterator = oldIterator;
        this.predicate = predicate;
    }

    @Override
    public boolean moveToNext() {
        while (oldIterator.moveToNext() && !predicate.test(oldIterator.getValue())) {

        }
        return !this.isEnd();
    }
    @Override
    public int getValue() {
        return oldIterator.getValue();
    }

    @Override
    public boolean isEnd() {
        return oldIterator.isEnd();
    }

}

class MapIterator implements IntIterator{

    private IntIterator oldIterator;
    private IntUnaryOperator mapOperator;
    public MapIterator (IntIterator oldIterator, IntUnaryOperator mapOperator) {
        this.oldIterator = oldIterator;
        this.mapOperator = mapOperator;
    }

    @Override
    public boolean moveToNext() {
        return oldIterator.moveToNext();
    }

    @Override
    public int getValue() {
        return mapOperator.apply(oldIterator.getValue());
    }

    @Override
    public boolean isEnd() {
        return oldIterator.isEnd();
    }
}
