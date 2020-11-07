package app.co.jp.entity;

public class DocEntity {

	private Number id;
	private String objectTipe;
	private String objectName;
	private String parentId;
//	private String extension;
//	private Number fileid;

	public Number getId() {
		return id;
	}
	public void setId(Number id) {
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

	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


}
