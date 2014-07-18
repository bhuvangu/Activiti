package org.activiti.explorer.ui.task.listener;

public interface VisionaelTaskPerformHandler {
	
	void handle(String taskId);
	
	void setDetails(TaskPerformHandlerDetail taskPerformHandlerDetail);
	
	TaskPerformHandlerDetail getDetails();

	void complete(String taskId);
}
