package edu.unq.springboot.cucumber.userSteps;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import edu.unq.springboot.models.User;
import edu.unq.springboot.service.UserService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RegisterStepDef {
    @Autowired
    private MockMvc mvc;

    ResultActions action;

    @Autowired
    UserService userService;

    @MockBean
    UserService userservice;
    @When("Request to register a new user")
    public void request_to_register_a_new_user() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        User user = new User("Jose123","123456","Jose","Rodrigues","jose@gmial.com");
        String json = mapper.writeValueAsString(user);
        action = mvc.perform(post("/register")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));

    }

    @When("Request to login as user")
    public void request_to_login_as_user() throws Exception {

        User user = new User("Jose123","123","Jose","Rodrigues","jose@gmial.com");
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        String jsonUser = mapper.writeValueAsString(user);
        given(userService.validateUser(user.getUsername(), user.getPassword())).willReturn(true);
        // Inicio sesión
        action = mvc.perform(post("/login")
                .content(jsonUser).contentType(jsonUser)
                .contentType(MediaType.APPLICATION_JSON));

        ResultMatcher result = MockMvcResultMatchers.content().string("OK");
        action.andExpect(result);

    }

    @Then("Response status code of 200")
    public void response_ok() throws Exception {
        action.andExpect(status().isOk());
    }
}