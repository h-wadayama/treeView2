package app.co.jp.model;

import java.util.ArrayList;
import java.util.List;

public class DocModel_Bak {

	private String id;
	private String objectTipe;
	private String objectName;
	private String pathl;
	private String objLevel;
	private String parentId;
	private List<DocModel_Bak> childList = new ArrayList<DocModel_Bak>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObjectTipe() {
		return objectTipe;
	}
	public void setObjectTipe(String objectTipe) {
		this.objectTipe = objectTipe;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getPathl() {
		return pathl;
	}
	public void setPathl(String pathl) {
		this.pathl = pathl;
	}
	public String getObjLevel() {
		return objLevel;
	}
	public void setObjLevel(String objLevel) {
		this.objLevel = objLevel;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public List<DocModel_Bak> getChildList() {
		return childList;
	}
	public void setChildList(List<DocModel_Bak> childList) {
		this.childList = childList;
	}



}
