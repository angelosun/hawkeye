package cn.angelo.hawkeye.spring.boot;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Author: angelo
 * Date: 2021/5/31 15:28
 * Description:
 */
@ConfigurationProperties(prefix = "hawkeye.cluster")
public class ClusterConfig {

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
