package cn.angelo.hawkeye.core.util;

/**
 * Author: angelosun
 * Date: 2021/7/6 19:23
 * Description:
 */
public class CommonUtil {

    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    public static String capacityConversion(double size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%f B", size);
        }
    }

}
