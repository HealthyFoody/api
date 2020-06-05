package com.healthyfoody.entity;

import java.util.UUID;

import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

import lombok.*;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderProductComponent {
    @Type(type = "uuid-char")
    UUID mealId;
}
