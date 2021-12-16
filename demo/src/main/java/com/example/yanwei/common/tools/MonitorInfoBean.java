package com.example.yanwei.common.tools;

import lombok.Data;

/**
 * 监视信息的JavaBean类.
 */
@Data
public class MonitorInfoBean {

    /** *//** 可使用内存. */
    private long totalMemory;

    /** *//** 剩余内存. */
    private long freeMemory;

    /** *//** 最大可使用内存. */
    private long maxMemory;

    /** *//** 操作系统. */
    private String osName;

    /** *//** 总的物理内存. */
    private long totalMemorySize;

    /** *//** 剩余的物理内存. */
    private long freePhysicalMemorySize;

    /** *//** 已使用的物理内存. */
    private long usedMemory;

    /** *//** 线程总数. */
    private int totalThread;

    /** *//** cpu使用率. */
    private double cpuRatio;

}
