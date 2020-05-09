package com.healthyfoody.mapper;

import com.healthyfoody.dto.MealGroupDto;
import com.healthyfoody.dto.MealModelDto;
import com.healthyfoody.entity.Meal;
import com.healthyfoody.entity.MealGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface MealGroupMapper {

    default List<MealGroupDto> groupsToDtoList(List<MealGroup> entities) {

        Map<String, List<MealGroup>> map = entities.stream()
                .collect(Collectors.groupingBy(MealGroup::getName));

        List<MealGroupDto> list = map.entrySet().stream().map(x -> {

            //flat List<List<Meal>>
            List<Meal> meals = x.getValue().stream()
                    .map(MealGroup::getMeals)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());

            MealGroupDto dto = new MealGroupDto();

            dto.setGroup(x.getKey());
            dto.setMeals(mealsToDtoList(meals));

            return dto;
        }).collect(Collectors.toList());
        return list;
    }

    List<MealModelDto> mealsToDtoList(List<Meal> entities);

}
