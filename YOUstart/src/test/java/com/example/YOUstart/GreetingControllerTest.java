package com.example.YOUstart;

import com.example.YOUstart.controllers.GreetingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("p")
@TestPropertySource(properties = { "spring.config.location = classpath:/application-test.yaml" })
@Sql(value = {"/sql/create-user-atStart.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/addMessagesBefore.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/addMessagesAfter.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"/sql/create-user-atEND.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GreetingControllerTest {
    @Autowired
    private GreetingController controller;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPageMessages() throws Exception{
        this.mockMvc.perform(get("/messages")).andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect( xpath("//*[@id='navbarSupportedContent']/div/div")
                        .string("Logined as: p"));
    }
    @Test
    public void listMessagesTest() throws Exception{
        this.mockMvc.perform(get("/messages")).andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(xpath("//div[@id='one_message']").nodeCount(4));
    }
    @Test
    public void FilterlistMessagesTest() throws Exception{
        this.mockMvc.perform(get("/messages").param("filter","t1"))
                . andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(xpath("//div[@id='one_message']").nodeCount(4));
    }

}
