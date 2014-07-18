

/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.explorer.ui.task.listener;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.explorer.ExplorerApp;
import org.activiti.explorer.I18nManager;
import org.activiti.explorer.Messages;
import org.activiti.explorer.NotificationManager;
import org.activiti.explorer.ViewManager;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PerformTaskClickListener implements ClickListener {

  private static final long serialVersionUID = 6322369324898641234L;
  
  protected String taskId;

  protected transient TaskService taskService;
  protected I18nManager i18nManager;
  protected ViewManager viewManager;
  protected NotificationManager notificationManager;
  
  
  public PerformTaskClickListener(String taskId, TaskService taskService) {
    this.taskId = taskId;
    this.taskService = taskService;
    this.viewManager = ExplorerApp.get().getViewManager();
    this.i18nManager = ExplorerApp.get().getI18nManager();
    this.notificationManager = ExplorerApp.get().getNotificationManager();
  }

  public void buttonClick(ClickEvent event) {
	  try{
	  TaskEntity taskEntity = (TaskEntity) taskService.createTaskQuery().taskId(taskId).list().iterator().next();
	  String taskDefinationKey = taskEntity.getTaskDefinitionKey();
	  VisionaelTaskPerformHandler visHandler = VisionaelPerfromTaskHandlerFactory.getInstance().getHandler(taskDefinationKey);
	  visHandler.handle(taskId); 
	  }catch(Exception e){
		  e.printStackTrace();
		  notificationManager.showErrorNotification(Messages.TASK_CLAIM_FAILED, e.getMessage());
	  }
  }

}

