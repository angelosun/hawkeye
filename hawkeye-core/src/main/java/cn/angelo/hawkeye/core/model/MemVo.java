package cn.angelo.hawkeye.core.model;

/**
 * Author: angelosun
 * Date: 2021/7/6 15:11
 * Description:
 */
public class MemVo {

    /**
     * 内存总量
     */
    private long total;

    /**
     * 已用内存
     */
    private long used;


    /**
     * 剩余内存
     */
    private long free;


    @Override
    public String toString() {
        return this.total + "|" + this.used + "|" + this.free;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public long getFree() {
        return free;
    }

    public void setFree(long free) {
        this.free = free;
    }
}
