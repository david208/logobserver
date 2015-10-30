package com.snowstore.log.vo;

import java.util.ArrayList;
import java.util.List;

public class TreeData {

	private List<NodeData> nodeDatas = new ArrayList<TreeData.NodeData>();

	public static class NodeData {
		private String text = "Parent 1";
		private String href = "#parent1";
		private String[] tags ;

		private List<NodeData> nodes ;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}

		public String[] getTags() {
			return tags;
		}

		public void setTags(String[] tags) {
			this.tags = tags;
		}

		public List<NodeData> getNodes() {
			return nodes;
		}

		public void setNodes(List<NodeData> nodes) {
			this.nodes = nodes;
		}

	}

	public List<NodeData> getNodeDatas() {
		return nodeDatas;
	}

	public void setNodeDatas(List<NodeData> nodeDatas) {
		this.nodeDatas = nodeDatas;
	}

}
