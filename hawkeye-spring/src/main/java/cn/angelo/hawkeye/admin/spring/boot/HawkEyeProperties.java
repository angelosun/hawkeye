package cn.angelo.hawkeye.admin.spring.boot;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Author: angelo
 * Date: 2021/5/31 15:28
 * Description:
 */
@Configuration
@ConfigurationProperties(prefix = "hwakeye")
public class HawkEyeProperties {

    private String clusterName;

    private String registryAddress;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }
}
