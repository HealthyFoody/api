package com.healthyfoody.entity.redis;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CartCombo {

    @NonNull
    @EqualsAndHashCode.Include
    UUID comboId;

    @NonNull
    @EqualsAndHashCode.Include
    Integer instance;

    List<UUID> bundleComponents;
}
