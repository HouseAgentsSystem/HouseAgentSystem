package com.houseAgent.activiti.domain;

public class ProcessDefinitionDTO {
	private String id;
	private String category;
	private String name;
	private String key;
	private String description;
	private int    version;
	private String resourceName;
	private String deploymentId;
	private String diagramResourceName;
	private String tenantId;
	private boolean startFormKey;
	private boolean graphicalNotation;
	private boolean suspended;
	public String getId() {
		return id;
	}
	public String getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
	public String getKey() {
		return key;
	}
	public String getDescription() {
		return description;
	}
	public int getVersion() {
		return version;
	}
	public String getResourceName() {
		return resourceName;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public String getDiagramResourceName() {
		return diagramResourceName;
	}
	public String getTenantId() {
		return tenantId;
	}
	public boolean isStartFormKey() {
		return startFormKey;
	}
	public boolean isGraphicalNotation() {
		return graphicalNotation;
	}
	public boolean isSuspended() {
		return suspended;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public void setDiagramResourceName(String diagramResourceName) {
		this.diagramResourceName = diagramResourceName;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public void setStartFormKey(boolean startFormKey) {
		this.startFormKey = startFormKey;
	}
	public void setGraphicalNotation(boolean graphicalNotation) {
		this.graphicalNotation = graphicalNotation;
	}
	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}
	
}
