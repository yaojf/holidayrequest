package org.flowable;

import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.task.Task;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yaojiafeng
 * @create 2017-07-27 下午5:29
 */
public class ManagementServiceTest {

    ProcessEngine processEngine = null;

    @Before
    public void before() {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/flowable")
                .setJdbcUsername("root")
                .setJdbcPassword("root")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        processEngine = cfg.buildProcessEngine();
    }


    @Test
    public void getTableName(){
        ManagementService managementService = processEngine.getManagementService();

        System.out.println(managementService.getTableName(Task.class));
    }


}
