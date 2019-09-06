package com.androidwind.androidquick.demo.bean;

import com.unnamed.b.atv.model.TreeNode;

import java.io.Serializable;

public class MenuBean implements Serializable {

	public TreeNode treeNode;
	public long currentId;
	public String name;
	public long upperId;

	public MenuBean() {

	}
	public MenuBean(long currentId, String name, long upperId) {
		this.currentId = currentId;
		this.name = name;
		this.upperId = upperId;
	}
	
}
