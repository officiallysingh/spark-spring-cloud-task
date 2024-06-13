//package com.telos.sparkspringcloudtask;
//
//import org.springframework.cloud.task.repository.TaskExplorer;
//import org.springframework.cloud.task.repository.TaskRepository;
//import org.springframework.cloud.task.repository.support.SimpleTaskExplorer;
//import org.springframework.cloud.task.repository.support.SimpleTaskRepository;
//import org.springframework.cloud.task.repository.support.TaskExecutionDaoFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TaskRepositoryConfig {
//
//    @Bean
//    public TaskRepository taskRepository() {
//        return new SimpleTaskRepository(new TaskExecutionDaoFactoryBean(null, "TASK_"));
//    }
//
//    @Bean
//    public TaskExplorer taskExplorer(TaskRepository taskRepository) {
//        return new SimpleTaskExplorer(taskRepository);
//    }
//}
