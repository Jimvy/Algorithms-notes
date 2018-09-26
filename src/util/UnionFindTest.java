package util;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnionFindTest {
    public static final int N = 1000;
    @Test
    public void testUSPC() {
        test1(new USPCUnionFind(N));
        test2(new USPCUnionFind(N));
    }
    @Test
    public void testURPC() {
        test1(new URPCUnionFind(N));
        test2(new URPCUnionFind(N));
    }
    public void test1(UnionFind uf) {
        for (int i = 1; i < N; i++) {
            uf.union(i-1, i);
            assertTrue(uf.sameSet(i-1, i));
        }
        assertTrue(uf.sameSet(0, N-1));
        assertEquals(1, uf.count());
    }
    public void test2(UnionFind uf) {
        int M = 500;
        Random r = new Random(42);
        for (int i = 0; i < M; i++) {
            int x = ((r.nextInt() % N) + N) % N, y = ((r.nextInt() % N) + N) % N;
            uf.union(x, y);
            assertTrue(uf.sameSet(x, y));
        }
    }
}
