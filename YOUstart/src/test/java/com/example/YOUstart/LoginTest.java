package com.example.YOUstart;

import com.example.YOUstart.controllers.GreetingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private GreetingController controller;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void init() throws Exception{
        assertThat(controller).isNotNull();
    }
    @Test
    public void getRootWeb() throws Exception{
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Messages page")));
    }

    @Test
    public void loginRedirectTest() throws Exception{
        this.mockMvc.perform(get("/messages")).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void loginTest() throws Exception{
        this.mockMvc.perform(formLogin().user("p").password("p"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void loginIncorrectUserTest() throws Exception{
        this.mockMvc.perform(post("/login").param("incorrect","incorrect"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }




}
