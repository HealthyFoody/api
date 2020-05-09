package com.healthyfoody;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import com.healthyfoody.entity.Combo;
import com.healthyfoody.entity.Meal;
import com.healthyfoody.entity.MealGroup;
import com.healthyfoody.repository.jpa.ProductRepository;
import com.healthyfoody.service.impl.ProductServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class ProductTests {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @Before
    public void beforeTests() {
        productService = new ProductServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidateCombination(){
        // Inicializamos los datos
        Combo combo = new Combo();

        List<MealGroup> listMealGroups = new ArrayList<>();

        MealGroup mealGroup1 = new MealGroup();
        mealGroup1.setName("Entradas");
        mealGroup1.setOptional(true);
        List<Meal> mealsGroup1 = new ArrayList<>();

        Meal meal1 = new Meal();
        meal1.setId(UUID.fromString("d94b31a5-004b-4de5-8bb6-01f6e882ffed"));

        Meal meal2 = new Meal();
        meal2.setId(UUID.fromString("b4865410-5c40-4349-aeaf-8403a571c607"));

        mealsGroup1.add(meal1);
        mealsGroup1.add(meal2);


        MealGroup mealGroup2 = new MealGroup();
        mealGroup2.setName("Segundos");
        mealGroup2.setOptional(false);
        List<Meal> mealsGroup2 = new ArrayList<>();

        Meal meal3 = new Meal();
        meal3.setId(UUID.fromString("d084584c-acb8-4088-aafe-3193c1aefdc4"));

        Meal meal4 = new Meal();
        meal4.setId(UUID.fromString("3d0a3545-d8b6-4a43-888f-0b4af2429027"));

        mealsGroup2.add(meal3);
        mealsGroup2.add(meal4);

        mealGroup1.setMeals(mealsGroup1);
        mealGroup2.setMeals(mealsGroup2);

        listMealGroups.add(mealGroup1);
        listMealGroups.add(mealGroup2);

        combo.setMealGroups(listMealGroups);
        combo.setId(UUID.fromString("d94b31a5-004b-4de5-8bb6-01f6e882ffed"));
        when(productRepository.findById(UUID.fromString("d94b31a5-004b-4de5-8bb6-01f6e882ffed"))).thenReturn(Optional.of(combo));

        List<UUID> productsId = new ArrayList<>();
        productsId.add(UUID.fromString("d084584c-acb8-4088-aafe-3193c1aefdc4"));
        UUID comboId = UUID.fromString("d94b31a5-004b-4de5-8bb6-01f6e882ffed");

        boolean response = productService.validateCombination(comboId,productsId);
        boolean expected = true;
        assertEquals(expected,response);

    }
    @Test
    public void testValidationReport(){
        //parametros de la función a llamar
        UUID productId = UUID.fromString("d94b31a5-004b-4de5-8bb6-01f6e882ffed");
        boolean checklisted = true;
        UUID storeId = UUID.fromString("b4865410-5c40-4349-aeaf-8403a571c607");
        LocalTime hour = LocalTime.of(13,0,0);

        Meal product = new Meal();
        product.setId(UUID.fromString("d94b31a5-004b-4de5-8bb6-01f6e882ffed"));
        product.setListed(true);
        when(productRepository.findById(UUID.fromString("d94b31a5-004b-4de5-8bb6-01f6e882ffed"))).thenReturn(Optional.of(product));
        when(productRepository.isThereStockAtStore(UUID.fromString("d94b31a5-004b-4de5-8bb6-01f6e882ffed"), UUID.fromString("b4865410-5c40-4349-aeaf-8403a571c607"))).thenReturn(false);
        when(productRepository.isOnSaleSpan(UUID.fromString("d94b31a5-004b-4de5-8bb6-01f6e882ffed"), hour)).thenReturn(false);

        List<String> response = productService.validationReport(productId,checklisted,storeId,hour);

        int result = response.size();
        int expected = 2;
        assertEquals(expected,result);
    }
}
