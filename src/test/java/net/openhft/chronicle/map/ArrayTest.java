package net.openhft.chronicle.map;

import net.openhft.chronicle.core.OS;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ArrayTest {
    // don't use Double[] as it uses ~3.5x the memory of a double[]
    @Test
    public void testDoubleArray() throws IOException {
        File file = new File(OS.getTarget() + "/testDoubleArray-" + System.nanoTime());
        ChronicleMap<Long, double[]> writeMap = ChronicleMapBuilder
                .of(Long.class, double[].class)
                .entries(1_000)
                .averageValue(new double[150])
                .createPersistedTo(file);
        double a[] = {2D};
        writeMap.put(1L, a);

        //read
        ChronicleMap<Long, double[]> readMap =
                ChronicleMapBuilder.of(Long.class, double[].class)
                        .averageValue(new double[150])
                        .createPersistedTo(file);
        double b[] = readMap.get(1L);
    }

    // don't use Long[] as it uses ~3.5x the memory of a long[]
    @Test
    public void testLongArray() throws IOException {
        File file = new File(OS.getTarget() + "/testLongArray-" + System.nanoTime());
        ChronicleMap<Long, long[]> writeMap = ChronicleMapBuilder
                .of(Long.class, long[].class)
                .entries(1_000)
                .averageValue(new long[150])
                .createPersistedTo(file);
        long a[] = {2};
        writeMap.put(1L, a);

        //read
        ChronicleMap<Long, long[]> readMap =
                ChronicleMapBuilder.of(Long.class, long[].class)
                        .averageValue(new long[150])
                        .createPersistedTo(file);
        long b[] = readMap.get(1L);
    }
}