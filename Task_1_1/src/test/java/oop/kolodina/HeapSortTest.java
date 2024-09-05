package oop.kolodina;
import oop.kolodina.HeapSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import java.util.Arrays;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.MAX_VALUE;
public class HeapSortTest {
    /*
    testing with random arrays
     */
    @Test
    public void testArraysWithRandom() {
        for (int i=0; i<10000; i++) {
            Random random = new Random();
            int rand_len = random.nextInt(100000);
            int[] array1 = random.ints(rand_len, 10, 100000).toArray();
            int[] array2 = new int[rand_len];
            System.arraycopy(array1, 0, array2, 0, rand_len);
            HeapSort object = new HeapSort();
            object.heapsort(array1, rand_len);
            Arrays.sort(array2);
            assertArrayEquals(array1, array2);
        }
    }
    /*
    testing edge cases with max and min values
     */
    @Test
    public void testEdges(){
        int[] array1 = {MIN_VALUE, MIN_VALUE, MAX_VALUE, MAX_VALUE, 0, -1000000, 321534, 0, -132456876, 777};
        int[] array2 = new int[array1.length];
        System.arraycopy(array1, 0, array2, 0, array1.length);
        HeapSort object = new HeapSort();
        object.heapsort(array1, array1.length);
        Arrays.sort(array2);
        assertArrayEquals(array1, array2);
    }
    /*
    testing array with equal elements
     */
    @Test
    public void checkEquals(){
        int[] array1 = {124, 124, 124, 124, 124, 124, 124, 124,124};
        int[] array2 = new int[array1.length];
        System.arraycopy(array1, 0, array2, 0, array1.length);
        HeapSort object = new HeapSort();
        object.heapsort(array1, array1.length);
        Arrays.sort(array2);
        assertArrayEquals(array1,array2);
    }
    /*
    testing array with one element
     */
    @Test
    public void checkOneElem(){
        int[] array1 = {100};
        int[] array2 = {100};
        HeapSort object = new HeapSort();
        object.heapsort(array1,array1.length);
        Arrays.sort(array2);
        assertArrayEquals(array1,array2);
    }
}