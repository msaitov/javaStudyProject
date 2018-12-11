package ru.msaitov.practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrganizationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOrganizationId() throws Exception {
        this.mockMvc
                //.perform(get("/api/organization/1"))
                .perform(get("/api/employee/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("{\"id\":1,\"firstName\":\"Василий\",\"middleName\":\"Петров\",\"lastName\":\"Кудинов\",\"phone\":\"+79265854654\",\"docNumber\":\"4565543543\",\"docDate\":\"1995-04-01\",\"docCodeNum\":21,\"docName\":\"Паспорт гражданина Российской Федерации\",\"citizenshipCode\":643,\"citizenshipName\":\"Россия\",\"positionName\":\"программист\",\"isIdentified\":true}")));

    }
}
