package org.flowable;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.impl.persistence.entity.VariableInstanceEntity;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.task.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * @author yaojiafeng
 * @create 2017-07-27 下午5:29
 */
public class ManagementServiceTest {

    ProcessEngine processEngine = null;

    @Before
    public void before() {
        processEngine = ProcessEngines.getDefaultProcessEngine();
    }


    @Test
    public void testManagementService() {
        ManagementService managementService = processEngine.getManagementService();

        System.out.println(managementService.getTableName(HistoricActivityInstance.class));
    }

    @Test
    public void testTaskService() {
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        System.out.println(task);

        taskService.claim(task.getId(), "fozzie");
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();

        taskService.complete(tasks.get(0).getId());
    }


    @Test
    public void testRepositoryService() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("oneTaskProcess")
                .singleResult();
        System.out.println(processDefinition);
    }

    @Test
    public void testHistoryService() {
        HistoryService historyService = processEngine.getHistoryService();
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId("7501").singleResult();
        System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
    }

    @Test
    public void testRuntimeService() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.startProcessInstanceByKey("oneTaskProcess");
    }
}
