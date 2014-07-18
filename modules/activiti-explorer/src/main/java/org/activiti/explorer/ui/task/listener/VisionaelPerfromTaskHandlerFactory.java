package org.activiti.explorer.ui.task.listener;



import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class VisionaelPerfromTaskHandlerFactory {
	private static VisionaelPerfromTaskHandlerFactory factory = null;
	private Map<String, TaskPerformHandlerDetail> taskPerformHandlerDetails = new HashMap<String,TaskPerformHandlerDetail>();
	
	private VisionaelPerfromTaskHandlerFactory() throws ClassNotFoundException, IOException, ParseException{
		 FileReader inputFile = new FileReader("/etc/visionael/activiti_handler.json");
		 JSONParser parser = new JSONParser();
		 JSONObject json = (JSONObject) parser.parse(inputFile);
		System.out.println("create jsons"+json);
		for(Object  taskDefId : json.keySet()){
			JSONObject value = (JSONObject) json.get(taskDefId);
			TaskPerformHandlerDetail handlerDetail = new TaskPerformHandlerDetail(value);
			taskPerformHandlerDetails.put((String)taskDefId, handlerDetail);
			System.out.println(taskDefId+"------->"+handlerDetail.toString());
		}
	}
	public VisionaelTaskPerformHandler getHandler(
			String taskDefinationKey) throws InstantiationException, IllegalAccessException { 
		 TaskPerformHandlerDetail handlerDetail = taskPerformHandlerDetails.get(taskDefinationKey);
		 if(handlerDetail == null){
			 handlerDetail = taskPerformHandlerDetails.get("default");
		 }
		 Class<? extends VisionaelTaskPerformHandler> handlerClass = handlerDetail.gethandlerClass();
		 VisionaelTaskPerformHandler handler = handlerClass.newInstance();
		 handler.setDetails(handlerDetail);
		 return handler;
	}

	public static synchronized VisionaelPerfromTaskHandlerFactory getInstance() throws ClassNotFoundException, IOException, ParseException {
		if(factory == null){
			factory = new VisionaelPerfromTaskHandlerFactory();
		}
		return factory;
	}

}
