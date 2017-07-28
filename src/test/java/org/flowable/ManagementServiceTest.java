package org.flowable;

import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.impl.persistence.entity.VariableInstanceEntity;
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

        System.out.println(managementService.getTableName(VariableInstanceEntity.class));
    }

    @Test
    public void testTaskService() {
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee("尼玛")
                .processVariableValueEquals("description", "尼玛")
                .list();
        System.out.println(tasks);

        taskService.complete(tasks.get(0).getId());

    }


}
