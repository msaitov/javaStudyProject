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
public class OfficeTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOfficeId() throws Exception {
        this.mockMvc
                .perform(get("/api/office/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("{\"id\":1,\"name\":\"Шубы\",\"address\":\"г. Москва, ул. Зеленодольская, 20\",\"phone\":\"+74954365465\"}")));

    }
}
