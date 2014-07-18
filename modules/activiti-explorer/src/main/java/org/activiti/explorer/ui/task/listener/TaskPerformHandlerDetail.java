package org.activiti.explorer.ui.task.listener;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class TaskPerformHandlerDetail {
	private String taskName;
	private Class<? extends VisionaelTaskPerformHandler> handler;
	private Map<String, String> metaData = new HashMap<String, String>();
	
	public TaskPerformHandlerDetail(JSONObject detail) throws ClassNotFoundException{
		taskName = (String) detail.get("taskName");
		handler = (Class<? extends VisionaelTaskPerformHandler>) Class.forName((String)detail.get("HandlerClass"));
		JSONObject metaData = (JSONObject) detail.get("metaData");
		for (Object metaKey : metaData.keySet()) {
			metaData.put(metaKey, metaData.get(metaKey));
		}
	}
	
	public Class<? extends VisionaelTaskPerformHandler> gethandlerClass(){
		return handler;
	}
	
	public Map<String, String> getMetaData() {
		return metaData;
	}

	public String getTaskName() {
		return taskName;
	}
	
	@Override
	public String toString() {
		return "TaskPerformHandlerDetail [taskName=" + taskName + ", handler="
				+ handler + ", metaData=" + metaData + "]";
	}
}
