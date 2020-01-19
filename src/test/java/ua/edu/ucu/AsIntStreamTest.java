package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class AsIntStreamTest {

    private IntStream intStr;
    private IntStream emptyIntStr;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 3, 4};
        int[] emptyArr = {};
        intStr = AsIntStream.of(intArr);
        emptyIntStr = AsIntStream.of(emptyArr);
    }

    @Test
    public void testAverage() {
        double expResult = 1.4;
        double result = intStr.average();
        assertEquals(expResult, result, 0.01);
    }

    @Test
    public void testMin() {
        int expResult = -1;
        int result = intStr.min();
        assertEquals(expResult, result);
    }

    @Test
    public void testMax() {
        int expResult = 4;
        int result = intStr.max();
        assertEquals(expResult, result);
    }

    @Test
    public void testCount() {
        long expResult = 5;
        long result = intStr.count();
        assertEquals(expResult, result);
    }

    @Test
    public void testSum() {
        int expResult = 8;
        int result = intStr.sum();
        assertEquals(expResult, result);
    }

    @Test
    public void testFilter() {
        int[] expResult = new int[]{1, 3, 4};
        int[] res = intStr
                .filter(x -> x > 0).toArray();
        assertArrayEquals(expResult, res);
    }

    @Test
    public void testMap() {
        int[] expResult = new int[]{1, 0, 1, 9, 16};
        int[] res = intStr
                .map(x -> x * x).toArray();
        assertArrayEquals(expResult, res);
    }

    @Test
    public void testFlatMap() {
        int[] expResult = new int[]{-2, -1, 0, -1, 0, 1, 0, 1, 2, 2, 3, 4, 3, 4, 5};
        int[] res = intStr
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1)).toArray();
        assertArrayEquals(expResult, res);
    }

    @Test
    public void testReduce() {
        int expResult = 7;
        int res = intStr
                .reduce(0, (sum, x) -> sum += x);
        assertEquals(expResult, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageEmpty() {
        double result = emptyIntStr.average();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxEmpty() {
        double result = emptyIntStr.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinEmpty() {
        int result = emptyIntStr.min();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumEmpty() {
        int result = emptyIntStr.sum();
    }

    @Test
    public void testForEach() {
        String expResult = "-10134";
        String result = StreamApp.streamForEach(intStr);
        assertEquals(expResult, result);
    }

}