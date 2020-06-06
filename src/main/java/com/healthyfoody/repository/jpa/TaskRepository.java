package com.healthyfoody.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthyfoody.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByCode(int code);
}
