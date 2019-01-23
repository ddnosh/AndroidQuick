package la.xiong.androidquick.demo.bean;

import com.unnamed.b.atv.model.TreeNode;

import java.io.Serializable;

public class MenuBean implements Serializable {

	public TreeNode treeNode;
	public int currentId;
	public String name;
	public int upperId;

	public MenuBean() {

	}
	public MenuBean(int currentId, String name, int upperId) {
		this.currentId = currentId;
		this.name = name;
		this.upperId = upperId;
	}
	
}
