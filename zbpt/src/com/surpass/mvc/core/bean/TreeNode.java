package com.surpass.mvc.core.bean;

import java.io.Serializable;
import java.util.List;

public class TreeNode implements  Serializable {
	
	/**
	 * TreeNode.java
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String text;
	private TreeNodeState state=null;
	private List<TreeNode> nodes=null;
	/**  
	 * 获取 nodes  
	 * @return nodes nodes  
	 */
	public List<TreeNode> getNodes() {
		return nodes;
	}

	/**  
	 * 设置 nodes  
	 * @param nodes nodes  
	 */
	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

	private Boolean leaf;
	private Boolean singleClickExpand = true;
	/**
	 * 设置为false将不允许此节点有孩子节点 (默认值为 true)
	 */
	private Boolean allowChildren = false;
	/**
	 * 如果draggable = true (默认值为true)，设置此项为false将使得此节点不可拖拽。
	 */
	private Boolean allowDrag;

	/**
	 * 如果不可以把孩子节点拖动到此节点上，设置为false (默认值为 true)
	 */
	private Boolean allowDrop;

	/**
	 * 设置为true将为此节点渲染一个选中状态复选框， false将渲染一个未选中的复选框...
	 */
	private Boolean checked;
	/***
	 * 添加到节点的CSS类
	 */
	private String cls;

	/** 路径 */
	private String path;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean getSingleClickExpand() {
		return singleClickExpand;
	}

	public void setSingleClickExpand(Boolean singleClickExpand) {
		this.singleClickExpand = singleClickExpand;
	}

	public Boolean getAllowChildren() {
		return allowChildren;
	}

	public void setAllowChildren(Boolean allowChildren) {
		this.allowChildren = allowChildren;
	}

	public Boolean getAllowDrag() {
		return allowDrag;
	}

	public void setAllowDrag(Boolean allowDrag) {
		this.allowDrag = allowDrag;
	}

	public Boolean getAllowDrop() {
		return allowDrop;
	}

	public void setAllowDrop(Boolean allowDrop) {
		this.allowDrop = allowDrop;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**  
	 * 获取 state  
	 * @return state state  
	 */
	public TreeNodeState getState() {
		return state;
	}

	/**  
	 * 设置 state  
	 * @param state state  
	 */
	public void setState(TreeNodeState state) {
		this.state = state;
	}
}
