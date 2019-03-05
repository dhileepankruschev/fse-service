package com.fse.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fse.taskmanager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query(value = "SELECT parent_id FROM parent_task WHERE parent_task = ?1", nativeQuery = true)
	Integer findParentTask(String taskName);

	@Modifying
	@Query(value = "update task set delete_flag='Y' where task_id=:taskId", nativeQuery = true)
	void endTask(@Param("taskId") Integer taskId);

}
