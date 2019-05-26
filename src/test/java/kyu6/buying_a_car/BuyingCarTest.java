package kyu6.buying_a_car;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuyingCarTest {
    @Test
    public void test1() {
        int[] r = new int[] { 6, 766 };
        assertArrayEquals(r, BuyingCar.nbMonths(2000, 8000, 1000, 1.5));
    }

    @Test
    public void test2() {
        int[] r = new int[] { 0, 4000 };
        assertArrayEquals(r, BuyingCar.nbMonths(12000, 8000, 1000, 1.5));
    }

    @Test
    public void test3() {
        int[] r = new int[] { 9, 749 };
        assertArrayEquals(r, BuyingCar.nbMonths(2260, 12260, 1000, 1));
    }

    @Test
    public void test4() {
        int[] r = new int[] { 8, 332 };
        assertArrayEquals(r, BuyingCar.nbMonths(18000, 32000, 1500, 1.25));
    }

    @Test
    public void test5() {
        int[] r = new int[] { 25, 122 };
        assertArrayEquals(r, BuyingCar.nbMonths(7500, 32000, 300, 1.55));
    }

    @Test
    public void test_buying_by_the_same_price() {
        int[] r = new int[] { 0, 0 };
        assertArrayEquals(r, BuyingCar.nbMonths(12000, 12000, 1000, 1.5));
    }

    @Test
    public void test_cla() {
        int[] r = new int[] { 17, 227};
        assertArrayEquals(r, BuyingCar.nbMonths(3500, 19000, 500, 1.5));
    }
}