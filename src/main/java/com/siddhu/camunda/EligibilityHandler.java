package com.siddhu.camunda;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.context.annotation.Configuration;


@Configuration
@ExternalTaskSubscription("changevalue") 
public class EligibilityHandler implements ExternalTaskHandler {

	@Override
	public void execute(ExternalTask extTask, ExternalTaskService extTaskService) {

		String name = extTask.getVariable("name");
		long age = extTask.getVariable("age");
		String status="value changed";

		name = name.concat(status);
		System.out.println("name"+name);
		System.out.println("age"+age);

		VariableMap variables = Variables.createVariables();
		variables.put("name", name);
		variables.put("age", age);
		variables.put("status", status);		

		extTaskService.complete(extTask,variables);
	}

}
