package com.fse.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fse.taskmanager.model.ParentTask;

public interface ParentTaskRepository extends JpaRepository<ParentTask, Integer> {

}
