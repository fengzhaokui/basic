package com.fengzkframework.basic.domain;

import java.io.Serializable;

public class APIEntity implements Serializable{



	public int getApiTimeout() {
		return apiTimeout;
	}

	public void setApiTimeout(int apiTimeout) {
		this.apiTimeout = apiTimeout;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2115479654522615152L;
	
	private String apiAddress;
	private String posapiAddress;
	private String msapiAddress;
	
	private String apiPort;
	
	private String apiId;
	
	private String apiSecret;
	
	private String apiVersion;
	
	private int apiTimeout;
	
	private int apiErrorCount;
	
	private int apiCallTime;

	
	public int getApiErrorCount() {
		return apiErrorCount;
	}

	public void setApiErrorCount(int apiErrorCount) {
		this.apiErrorCount = apiErrorCount;
	}

	public int getApiCallTime() {
		return apiCallTime;
	}

	public void setApiCallTime(int apiCallTime) {
		this.apiCallTime = apiCallTime;
	}

	public String getApiAddress() {
		return apiAddress;
	}

	public void setApiAddress(String apiAddress) {
		this.apiAddress = apiAddress;
	}

	public String getApiPort() {
		return apiPort;
	}

	public void setApiPort(String apiPort) {
		this.apiPort = apiPort;
	}

	public String getPosapiAddress() {
		return posapiAddress;
	}

	public void setPosapiAddress(String posapiAddress) {
		this.posapiAddress = posapiAddress;
	}

	public String getMsapiAddress() {
		return msapiAddress;
	}

	public void setMsapiAddress(String msapiAddress) {
		this.msapiAddress = msapiAddress;
	}
}
