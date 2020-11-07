package app.co.jp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import app.co.jp.model.jsTree.A_attr;
import app.co.jp.model.jsTree.Data;
import app.co.jp.model.jsTree.Li_attr;
import app.co.jp.model.jsTree.State;

public class DocModel {

	@JsonTypeInfo(use = Id.CLASS)
	private String id;
	private String text;
	private String icon;

	private State state;
	//private String pathl;
	//private String objLevel;
	private String parentId;

	private Li_attr li_attr;
	private A_attr a_attr;
	private Data data;
	private String extension;
	private List<DocModel> children = new ArrayList<DocModel>();

	private String fileid;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public List<DocModel> getChildren() {
		return children;
	}
	public void setChildren(List<DocModel> children) {
		this.children = children;
	}

	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Li_attr getLi_attr() {
		return li_attr;
	}
	public void setLi_attr(Li_attr li_attr) {
		this.li_attr = li_attr;
	}
	public A_attr getA_attr() {
		return a_attr;
	}
	public void setA_attr(A_attr a_attr) {
		this.a_attr = a_attr;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}


}
