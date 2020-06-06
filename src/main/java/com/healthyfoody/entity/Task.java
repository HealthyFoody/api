package com.healthyfoody.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableName.TASK)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Task {

    @Id
    Long id;

    @NotNull
    @NaturalId
    @EqualsAndHashCode.Include
    Integer code;

    @NotNull
    @Column(unique = true)
    String name;
    
    public static enum DeliveryTask {
        PREPARING(1, "Preparando"),
        SENDING(2, "Enviando"),
        AWAITING_PAYMENT(3, "Esperando Pago");
    	
    	public final String taskName;
        public final int code;

        DeliveryTask(int code, String taskName) {
            this.code = code;
            this.taskName = taskName;
        }

        public static DeliveryTask valueOfCode(int code) {
            for (DeliveryTask e : values()) {
                if (e.code == code)
                    return e;
            }
            return null;
        }
        
        public static DeliveryTask valueOfTaskName(String taskName) {
            for (DeliveryTask e : values()) {
                if (e.taskName == taskName)
                    return e;
            }
            return null;
        }
    }
}
