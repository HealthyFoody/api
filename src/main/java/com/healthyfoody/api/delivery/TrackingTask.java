package com.healthyfoody.api.delivery;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.healthyfoody.api.common.BaseEntity;
import com.healthyfoody.api.common.TableName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = TableName.TRACKING_TASK, uniqueConstraints = { @UniqueConstraint(columnNames = { "tracking_id", "task_id" }) })
public class TrackingTask extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tracking_id")
	Tracking tracking;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id")
	Task task;
	
	@NotNull
	LocalDateTime startTime;
	
	@NotNull
	LocalTime estimatedTime;
	
	LocalDateTime finishTime;

}
