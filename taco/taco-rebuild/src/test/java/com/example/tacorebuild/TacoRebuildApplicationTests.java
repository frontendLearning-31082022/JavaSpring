package com.example.tacorebuild;

import com.example.tacorebuild.controllers.DesignTacoController;
import com.example.tacorebuild.controllers.OrderController;
import com.example.tacorebuild.repos.IngredientRepository;
import com.example.tacorebuild.structs.Ingredient;
import com.example.tacorebuild.structs.Taco;
import com.example.tacorebuild.structs.TacoOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Collectors;



@TestPropertySource(properties = { "spring.config.location = classpath:/application.yaml" })
@SpringBootTest
@AutoConfigureMockMvc
class TacoRebuildApplicationTests {
	@Autowired
	private DesignTacoController designTacoController;
	@Autowired
	private OrderController orderController;
	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}


	@Test
	void testFillData_Orders() {
		for (int b = 100; b < 200; b++) {
			Taco taco = new Taco();
			taco.setName("TestTaco");

			List<Ingredient> ingredients = new ArrayList<>();
			List<Ingredient> list = ingredientRepository.findAll();
			Collections.shuffle(list);
			for (int i = 0; i < 4; i++) list.remove(0);
			taco.setIngredients(list);
			taco.setId(Long.valueOf(b));


			TacoOrder tacoOrder=new TacoOrder();
			tacoOrder.setId(Long.valueOf(b));
			designTacoController.processTaco(taco, noErrors(), tacoOrder);
			tacoOrder.setDeliveryName("Test Delevery "+b);

			try {orderController.processOrder(tacoOrder,noErrors(),emptySessionStatus());
			}catch (Exception d){
				d.printStackTrace();
			}

		}
	}

	Errors noErrors() {
		return new Errors() {
			@Override
			public String getObjectName() {
				return null;
			}

			@Override
			public void setNestedPath(String nestedPath) {

			}

			@Override
			public String getNestedPath() {
				return null;
			}

			@Override
			public void pushNestedPath(String subPath) {

			}

			@Override
			public void popNestedPath() throws IllegalStateException {

			}

			@Override
			public void reject(String errorCode) {

			}

			@Override
			public void reject(String errorCode, String defaultMessage) {

			}

			@Override
			public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {

			}

			@Override
			public void rejectValue(String field, String errorCode) {

			}

			@Override
			public void rejectValue(String field, String errorCode, String defaultMessage) {

			}

			@Override
			public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {

			}

			@Override
			public void addAllErrors(Errors errors) {

			}

			@Override
			public boolean hasErrors() {
				return false;
			}

			@Override
			public int getErrorCount() {
				return 0;
			}

			@Override
			public List<ObjectError> getAllErrors() {
				return null;
			}

			@Override
			public boolean hasGlobalErrors() {
				return false;
			}

			@Override
			public int getGlobalErrorCount() {
				return 0;
			}

			@Override
			public List<ObjectError> getGlobalErrors() {
				return null;
			}

			@Override
			public ObjectError getGlobalError() {
				return null;
			}

			@Override
			public boolean hasFieldErrors() {
				return false;
			}

			@Override
			public int getFieldErrorCount() {
				return 0;
			}

			@Override
			public List<FieldError> getFieldErrors() {
				return null;
			}

			@Override
			public FieldError getFieldError() {
				return null;
			}

			@Override
			public boolean hasFieldErrors(String field) {
				return false;
			}

			@Override
			public int getFieldErrorCount(String field) {
				return 0;
			}

			@Override
			public List<FieldError> getFieldErrors(String field) {
				return null;
			}

			@Override
			public FieldError getFieldError(String field) {
				return null;
			}

			@Override
			public Object getFieldValue(String field) {
				return null;
			}

			@Override
			public Class<?> getFieldType(String field) {
				return null;
			}
		};

	}

	SessionStatus emptySessionStatus(){
	  return 	new SessionStatus() {
			@Override
			public void setComplete() {

			}

			@Override
			public boolean isComplete() {
				return false;
			}
		};
	}
}
