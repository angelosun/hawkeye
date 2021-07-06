package cn.angelo.hawkeye.core.model;

/**
 * Author: junyingcao
 * Date: 2021/7/6 15:11
 * Description:
 */
public class MemVo {

    /**
     * 内存总量
     */
    private double total;

    /**
     * 已用内存
     */
    private double used;

    /**
     * 剩余内存
     */
    private double free;



    @Override
    public String toString() {
        return this.total + "|" + this.used + "|" + this.free;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getUsed() {
        return used;
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public double getFree() {
        return free;
    }

    public void setFree(double free) {
        this.free = free;
    }
}
