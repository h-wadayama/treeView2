package app.co.jp.model;

import java.util.ArrayList;
import java.util.List;

public class JsonModel {

	private String id;
	private String parentId;
	private String text;
	private String icon;
	private String pathl;
	private String objLevel;

    public class SubJsonModel {
        boolean opened;
        boolean disabled;
        boolean selected;
		public boolean isOpened() {
			return opened;
		}
		public void setOpened(boolean opened) {
			this.opened = opened;
		}
		public boolean isDisabled() {
			return disabled;
		}
		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}
		public boolean isSelected() {
			return selected;
		}
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
    }

    private SubJsonModel state ;

	private String li_attr;
	private String a_attr;
	private List<JsonModel> children = new ArrayList<JsonModel>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public SubJsonModel getState() {
		return state;
	}
	public void setState(SubJsonModel state) {
		this.state = state;
	}
	public String getLi_attr() {
		return li_attr;
	}
	public void setLi_attr(String li_attr) {
		this.li_attr = li_attr;
	}
	public String getA_attr() {
		return a_attr;
	}
	public void setA_attr(String a_attr) {
		this.a_attr = a_attr;
	}
	public List<JsonModel> getChildren() {
		return children;
	}
	public void setChildren(List<JsonModel> children) {
		this.children = children;
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


}
