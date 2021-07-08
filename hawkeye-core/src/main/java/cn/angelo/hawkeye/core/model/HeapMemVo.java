package cn.angelo.hawkeye.core.model;

/**
 * Author: angelo
 * Date: 2021/7/7 19:05
 * Description:
 */
public class HeapMemVo {


	@Metric(name = "S0C", capacityConversionFlag = true)
	private Double survior0Capacity;

	@Metric(name = "S0U", capacityConversionFlag = true)
	private Double survior0Used;

	@Metric(name = "S1C", capacityConversionFlag = true)
	private Double survior1Capacity;

	@Metric(name = "S1U", capacityConversionFlag = true)
	private Double survior1Used;

	@Metric(name = "EC", capacityConversionFlag = true)
	private Double edenCapacity;

	@Metric(name = "EU", capacityConversionFlag = true)
	private Double edenUsed;

	@Metric(name = "OC", capacityConversionFlag = true)
	private Double oldCapacity;

	@Metric(name = "OU", capacityConversionFlag = true)
	private Double oldUsed;

	@Metric(name = "YGC")
	private Integer youngGcTimes;

	@Metric(name = "YGCT")
	private Double youngGcCostTime;

	@Metric(name = "FGC")
	private Integer fullGcTimes;

	@Metric(name = "FGCT")
	private Double fullGcCostTime;

	@Metric(name = "GCT")
	private Double gcCostTimes;

	@Override
	public String toString() {
		return this.survior0Capacity + "|" + this.survior0Used + "|" + this.survior1Capacity + "|" + this.survior1Used + "|" +
				this.edenCapacity + "|" + this.edenUsed + "|" + this.oldCapacity + "|" +  this.oldUsed + "|" +
				this.youngGcTimes + "|" + this.youngGcCostTime + "|" + this.fullGcTimes + "|" + this.fullGcCostTime + "|" + gcCostTimes;
	}

	public Double getSurvior0Capacity() {
		return survior0Capacity;
	}

	public void setSurvior0Capacity(Double survior0Capacity) {
		this.survior0Capacity = survior0Capacity;
	}

	public Double getSurvior1Capacity() {
		return survior1Capacity;
	}

	public void setSurvior1Capacity(Double survior1Capacity) {
		this.survior1Capacity = survior1Capacity;
	}

	public Double getSurvior0Used() {
		return survior0Used;
	}

	public void setSurvior0Used(Double survior0Used) {
		this.survior0Used = survior0Used;
	}

	public Double getSurvior1Used() {
		return survior1Used;
	}

	public void setSurvior1Used(Double survior1Used) {
		this.survior1Used = survior1Used;
	}

	public Double getEdenCapacity() {
		return edenCapacity;
	}

	public void setEdenCapacity(Double edenCapacity) {
		this.edenCapacity = edenCapacity;
	}

	public Double getEdenUsed() {
		return edenUsed;
	}

	public void setEdenUsed(Double edenUsed) {
		this.edenUsed = edenUsed;
	}

	public Double getOldCapacity() {
		return oldCapacity;
	}

	public void setOldCapacity(Double oldCapacity) {
		this.oldCapacity = oldCapacity;
	}

	public Double getOldUsed() {
		return oldUsed;
	}

	public void setOldUsed(Double oldUsed) {
		this.oldUsed = oldUsed;
	}

	public Integer getYoungGcTimes() {
		return youngGcTimes;
	}

	public void setYoungGcTimes(Integer youngGcTimes) {
		this.youngGcTimes = youngGcTimes;
	}

	public Double getYoungGcCostTime() {
		return youngGcCostTime;
	}

	public void setYoungGcCostTime(Double youngGcCostTime) {
		this.youngGcCostTime = youngGcCostTime;
	}

	public Integer getFullGcTimes() {
		return fullGcTimes;
	}

	public void setFullGcTimes(Integer fullGcTimes) {
		this.fullGcTimes = fullGcTimes;
	}

	public Double getFullGcCostTime() {
		return fullGcCostTime;
	}

	public void setFullGcCostTime(Double fullGcCostTime) {
		this.fullGcCostTime = fullGcCostTime;
	}

	public Double getGcCostTimes() {
		return gcCostTimes;
	}

	public void setGcCostTimes(Double gcCostTimes) {
		this.gcCostTimes = gcCostTimes;
	}
}
