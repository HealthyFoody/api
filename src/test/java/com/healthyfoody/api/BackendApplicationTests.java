package com.healthyfoody.api;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class BackendApplicationTests {

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext,
					  RestDocumentationContextProvider restDocumentation) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation))
				.alwaysDo(document("{method-name}",
						preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
				.build();
	}

}
