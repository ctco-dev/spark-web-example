package com.home.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

@Slf4j
public class Stats {

    public static void printMemoryStats() {
        // Get current size of heap in bytes
        Runtime runtime = Runtime.getRuntime();
        long totalHeapSize = runtime.totalMemory();
        log.info("Heap total: {}", totalHeapSize / 1024 / 1024 + " Mb");

        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
        log.info("Heap max size: {}", runtime.maxMemory() / 1024 / 1024 + " Mb");

        // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
        long heapFreeSize = runtime.freeMemory();
        log.info("Heap used: {}", (totalHeapSize - heapFreeSize) / 1024 / 1024 + " Mb");

        MemoryUsage metaspaceUsage = ManagementFactory.getMemoryPoolMXBeans().stream()
                .filter(b -> b.getName().equals("Metaspace"))
                .findFirst().get().getUsage();
        log.info("Metaspace: {}", metaspaceUsage.getUsed() / 1024 / 1024 + " Mb / "
                + metaspaceUsage.getMax() / 1024 / 1024 + " Mb");
    }
}
