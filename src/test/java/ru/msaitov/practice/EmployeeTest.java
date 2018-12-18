package ru.msaitov.practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import ru.msaitov.practice.view.DataViewList;
import ru.msaitov.practice.view.DataViewSingle;
import ru.msaitov.practice.view.EmployeeView;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Тесты Employee
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeTest {

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Тест вывод Employee по id
     */
    @Test
    public void testEmployeeId1() {
        Long testId = 4L;
        String url = "http://localhost:8888/api/user/" + testId;

        ParameterizedTypeReference<DataViewSingle<EmployeeView>> clazz = new ParameterizedTypeReference<DataViewSingle<EmployeeView>>() {
        };
        EmployeeView EmployeeView = new GetDataByUrl<EmployeeView>().getSingle(url, clazz);

        EmployeeView EmployeeViewExp = new EmployeeView();
        EmployeeViewExp.setId(4L);
        EmployeeViewExp.setFirstName("Никита");
        EmployeeViewExp.setMiddleName("Александрович");
        EmployeeViewExp.setLastName("Кузнецов");
        EmployeeViewExp.setPhone("+75439458524");
        EmployeeViewExp.setDocNumber("9634543905");
        EmployeeViewExp.setDocDate(LocalDate.of(1990, Month.AUGUST, 30));
        EmployeeViewExp.setDocCodeNum(12L);
        EmployeeViewExp.setDocName("Вид на жительство в Российской Федерации");
        EmployeeViewExp.setCitizenshipCode(826L);
        EmployeeViewExp.setCitizenshipName("Великобритания");
        EmployeeViewExp.setPositionName("бухгалтер");
        EmployeeViewExp.setIsIdentified(true);

        assertThat(EmployeeView, samePropertyValuesAs(EmployeeViewExp));
    }

    /**
     * Тест вывод Employee в случае неверного id
     */
    @Test
    public void testEmployeeId2() {
        String url = "http://localhost:8888/api/user/1000000000";
        String responseError = restTemplate.getForObject(url, String.class);
        assertEquals(responseError, "{\"error\":\"id не найдено\"}");
    }

    /**
     * Тест вывод списка Employee по фильтру
     */
    @Test
    public void testEmployeeList1() {
        String url = "http://localhost:8888/api/user/list";

        EmployeeView filter = new EmployeeView();
        filter.setOfficeId(1L);

        ParameterizedTypeReference<DataViewList<EmployeeView>> clazz = new ParameterizedTypeReference<DataViewList<EmployeeView>>() {
        };
        List<EmployeeView> EmployeeViewListActual = new GetDataByUrl<EmployeeView>().getList(url, filter, clazz);

        List<EmployeeView> EmployeeViewListExpect = new ArrayList<>();
        EmployeeView EmployeeViewExpect = new EmployeeView();
        EmployeeViewExpect.setId(1L);
        EmployeeViewExpect.setFirstName("Василий");
        EmployeeViewExpect.setMiddleName("Петров");
        EmployeeViewExpect.setLastName("Кудинов");
        EmployeeViewExpect.setPositionName("программист");
        EmployeeViewListExpect.add(EmployeeViewExpect);

        for (int i = 0; i < EmployeeViewListExpect.size(); i++) {
            EmployeeView EmployeeView = EmployeeViewListActual.get(i);
            EmployeeViewExpect = EmployeeViewListExpect.get(i);
            assertThat(EmployeeView, samePropertyValuesAs(EmployeeViewExpect));
        }
    }

    /**
     * Тест вывод списка Employee по фильтру
     */
    @Test
    public void testEmployeeList2() {
        String url = "http://localhost:8888/api/user/list";

        EmployeeView filter = new EmployeeView();
        filter.setOfficeId(5L);

        ParameterizedTypeReference<DataViewList<EmployeeView>> clazz = new ParameterizedTypeReference<DataViewList<EmployeeView>>() {
        };
        List<EmployeeView> EmployeeViewListActual = new GetDataByUrl<EmployeeView>().getList(url, filter, clazz);

        List<EmployeeView> EmployeeViewListExpect = new ArrayList<>();
        EmployeeView EmployeeViewExpect = new EmployeeView();
        EmployeeViewExpect.setId(2L);
        EmployeeViewExpect.setFirstName("Максим");
        EmployeeViewExpect.setMiddleName("Иванов");
        EmployeeViewExpect.setLastName("Воробьев");
        EmployeeViewExpect.setPositionName("бухгалтер");
        EmployeeViewListExpect.add(EmployeeViewExpect);

        EmployeeViewExpect = new EmployeeView();
        EmployeeViewExpect.setId(9L);
        EmployeeViewExpect.setFirstName("Матвей");
        EmployeeViewExpect.setMiddleName("Олегович");
        EmployeeViewExpect.setLastName("Белов");
        EmployeeViewExpect.setPositionName("юрист");
        EmployeeViewListExpect.add(EmployeeViewExpect);

        for (int i = 0; i < EmployeeViewListExpect.size(); i++) {
            EmployeeView EmployeeView = EmployeeViewListActual.get(i);
            EmployeeViewExpect = EmployeeViewListExpect.get(i);
            assertThat(EmployeeView, samePropertyValuesAs(EmployeeViewExpect));
        }
    }

    /**
     * Тест вывод списка Employee по фильтру
     */
    @Test
    public void testEmployeeList3() {
        String url = "http://localhost:8888/api/user/list";

        EmployeeView filter = new EmployeeView();
        filter.setOfficeId(2L);
        filter.setPositionName("бухгалтер");

        ParameterizedTypeReference<DataViewList<EmployeeView>> clazz = new ParameterizedTypeReference<DataViewList<EmployeeView>>() {
        };
        List<EmployeeView> EmployeeViewListActual = new GetDataByUrl<EmployeeView>().getList(url, filter, clazz);

        List<EmployeeView> EmployeeViewListExpect = new ArrayList<>();
        EmployeeView EmployeeViewExpect = new EmployeeView();
        EmployeeViewExpect.setId(11L);
        EmployeeViewExpect.setFirstName("Леонид");
        EmployeeViewExpect.setMiddleName("Михайлович");
        EmployeeViewExpect.setLastName("Соколов");
        EmployeeViewExpect.setPositionName("бухгалтер");
        EmployeeViewListExpect.add(EmployeeViewExpect);

        for (int i = 0; i < EmployeeViewListExpect.size(); i++) {
            EmployeeView EmployeeView = EmployeeViewListActual.get(i);
            EmployeeViewExpect = EmployeeViewListExpect.get(i);
            assertThat(EmployeeView, samePropertyValuesAs(EmployeeViewExpect));
        }
    }

    /**
     * Тест вывод списка Employee по фильтру
     */
    @Test
    public void testEmployeeList4() {
        String url = "http://localhost:8888/api/user/list";

        EmployeeView filter = new EmployeeView();
        filter.setOfficeId(3L);
        filter.setDocCodeNum(21L);

        ParameterizedTypeReference<DataViewList<EmployeeView>> clazz = new ParameterizedTypeReference<DataViewList<EmployeeView>>() {
        };
        List<EmployeeView> EmployeeViewListActual = new GetDataByUrl<EmployeeView>().getList(url, filter, clazz);

        List<EmployeeView> EmployeeViewListExpect = new ArrayList<>();
        EmployeeView EmployeeViewExpect = new EmployeeView();
        EmployeeViewExpect.setId(10L);
        EmployeeViewExpect.setFirstName("Ярослав");
        EmployeeViewExpect.setMiddleName("Тимофеевич");
        EmployeeViewExpect.setLastName("Кузьмин");
        EmployeeViewExpect.setPositionName("программист");
        EmployeeViewListExpect.add(EmployeeViewExpect);

        for (int i = 0; i < EmployeeViewListExpect.size(); i++) {
            EmployeeView EmployeeView = EmployeeViewListActual.get(i);
            EmployeeViewExpect = EmployeeViewListExpect.get(i);
            assertThat(EmployeeView, samePropertyValuesAs(EmployeeViewExpect));
        }
    }

    /**
     * Тест обновление Employee
     */
    @Test
    public void testEmployeeUpdate1() {
        String url = "http://localhost:8888/api/user/update";
        EmployeeView EmployeeView = new EmployeeView();
        EmployeeView.setId(7L);
        EmployeeView.setFirstName("AAAAAAAAAAA");
        EmployeeView.setPositionName("юрист");

        Map statusUpdateActual = new GetDataByUrl<EmployeeView>().getStatus(url, EmployeeView);
        assertEquals("success", statusUpdateActual.get("result"));
    }

    /**
     * Тест обновление Employee в случае неверного id
     */
    @Test
    public void testEmployeeUpdate2() {
        String url = "http://localhost:8888/api/user/update";
        EmployeeView EmployeeView = new EmployeeView();
        EmployeeView.setId(1000000L);
        EmployeeView.setFirstName("aaa");
        EmployeeView.setPositionName("юрист");


        Map statusUpdateActual = new GetDataByUrl<EmployeeView>().getStatus(url, EmployeeView);
        assertEquals("failure", statusUpdateActual.get("result"));
    }

    /**
     * Сохранение Employee
     */
    @Test
    public void testEmployeeSave() {
        String url = "http://localhost:8888/api/user/save";

        EmployeeView EmployeeView = new EmployeeView();
        EmployeeView.setOfficeId(2L);
        EmployeeView.setFirstName("Валера");
        EmployeeView.setPositionName("бухгалтер");
        EmployeeView.setDocCodeNum(12L);
        EmployeeView.setCitizenshipCode(276L);

        Map statusSaveActual = new GetDataByUrl<EmployeeView>().getStatus(url, EmployeeView);
        assertEquals("success", statusSaveActual.get("result"));
    }
}
