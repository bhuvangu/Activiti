package org.activiti.engine.impl.event.logger.handler;

import java.util.Map;

import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.EventLogEntryEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;

/**
 * @author Joram Barrez
 */
public class TaskCompletedEventHandler extends AbstractTaskEventHandler {
	
	@Override
	public EventLogEntryEntity generateEventLogEntry(CommandContext commandContext) {

		TaskEntity task = (TaskEntity) ((ActivitiEntityEvent) event).getEntity();
		
		Map<String, Object> data = handleCommonTaskFields(task);
		long duration = timeStamp.getTime() - task.getCreateTime().getTime();
		putInMapIfNotNull(data, Fields.DURATION, duration);
		
    return createEventLogEntry(task.getProcessDefinitionId(), task.getProcessInstanceId(), task.getExecutionId(), task.getId(), data);
	}

}
