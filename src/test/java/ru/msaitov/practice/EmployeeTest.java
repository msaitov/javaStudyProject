package ru.msaitov.practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import ru.msaitov.practice.view.Countries;
import ru.msaitov.practice.view.DataViewList;
import ru.msaitov.practice.view.DataViewSingle;
import ru.msaitov.practice.view.Docs;
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
        EmployeeView employeeView = new GetDataByUrl<EmployeeView>().getSingle(url, clazz);

        EmployeeView employeeViewExp = new EmployeeView();
        employeeViewExp.setId(4L);
        employeeViewExp.setFirstName("Никита");
        employeeViewExp.setMiddleName("Александрович");
        employeeViewExp.setLastName("Кузнецов");
        employeeViewExp.setPhone("+75439458524");
        employeeViewExp.setDocNumber("9634543905");
        employeeViewExp.setDocDate(LocalDate.of(1990, Month.AUGUST, 30));
        employeeViewExp.setDocCodeNum(12L);
        employeeViewExp.setDocName("Вид на жительство в Российской Федерации");
        employeeViewExp.setCitizenshipCode(826L);
        employeeViewExp.setCitizenshipName("Великобритания");
        employeeViewExp.setPositionName("бухгалтер");
        employeeViewExp.setIsIdentified(true);

        assertThat(employeeView, samePropertyValuesAs(employeeViewExp));
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
        List<EmployeeView> employeeViewListActual = new GetDataByUrl<EmployeeView>().getList(url, filter, clazz);

        List<EmployeeView> employeeViewListExpect = new ArrayList<>();
        EmployeeView employeeViewExpect = new EmployeeView();
        employeeViewExpect.setId(1L);
        employeeViewExpect.setFirstName("Василий");
        employeeViewExpect.setMiddleName("Петров");
        employeeViewExpect.setLastName("Кудинов");
        employeeViewExpect.setPositionName("программист");
        employeeViewListExpect.add(employeeViewExpect);

        for (int i = 0; i < employeeViewListExpect.size(); i++) {
            EmployeeView employeeView = employeeViewListActual.get(i);
            employeeViewExpect = employeeViewListExpect.get(i);
            assertThat(employeeView, samePropertyValuesAs(employeeViewExpect));
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
        List<EmployeeView> employeeViewListActual = new GetDataByUrl<EmployeeView>().getList(url, filter, clazz);

        List<EmployeeView> employeeViewListExpect = new ArrayList<>();
        EmployeeView employeeViewExpect = new EmployeeView();
        employeeViewExpect.setId(2L);
        employeeViewExpect.setFirstName("Максим");
        employeeViewExpect.setMiddleName("Иванов");
        employeeViewExpect.setLastName("Воробьев");
        employeeViewExpect.setPositionName("бухгалтер");
        employeeViewListExpect.add(employeeViewExpect);

        employeeViewExpect = new EmployeeView();
        employeeViewExpect.setId(9L);
        employeeViewExpect.setFirstName("Матвей");
        employeeViewExpect.setMiddleName("Олегович");
        employeeViewExpect.setLastName("Белов");
        employeeViewExpect.setPositionName("юрист");
        employeeViewListExpect.add(employeeViewExpect);

        for (int i = 0; i < employeeViewListExpect.size(); i++) {
            EmployeeView employeeView = employeeViewListActual.get(i);
            employeeViewExpect = employeeViewListExpect.get(i);
            assertThat(employeeView, samePropertyValuesAs(employeeViewExpect));
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
        List<EmployeeView> employeeViewListActual = new GetDataByUrl<EmployeeView>().getList(url, filter, clazz);

        List<EmployeeView> employeeViewListExpect = new ArrayList<>();
        EmployeeView employeeViewExpect = new EmployeeView();
        employeeViewExpect.setId(11L);
        employeeViewExpect.setFirstName("Леонид");
        employeeViewExpect.setMiddleName("Михайлович");
        employeeViewExpect.setLastName("Соколов");
        employeeViewExpect.setPositionName("бухгалтер");
        employeeViewListExpect.add(employeeViewExpect);

        for (int i = 0; i < employeeViewListExpect.size(); i++) {
            EmployeeView employeeView = employeeViewListActual.get(i);
            employeeViewExpect = employeeViewListExpect.get(i);
            assertThat(employeeView, samePropertyValuesAs(employeeViewExpect));
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
        List<EmployeeView> employeeViewListActual = new GetDataByUrl<EmployeeView>().getList(url, filter, clazz);

        List<EmployeeView> employeeViewListExpect = new ArrayList<>();
        EmployeeView employeeViewExpect = new EmployeeView();
        employeeViewExpect.setId(10L);
        employeeViewExpect.setFirstName("Ярослав");
        employeeViewExpect.setMiddleName("Тимофеевич");
        employeeViewExpect.setLastName("Кузьмин");
        employeeViewExpect.setPositionName("программист");
        employeeViewListExpect.add(employeeViewExpect);

        for (int i = 0; i < employeeViewListExpect.size(); i++) {
            EmployeeView employeeView = employeeViewListActual.get(i);
            employeeViewExpect = employeeViewListExpect.get(i);
            assertThat(employeeView, samePropertyValuesAs(employeeViewExpect));
        }
    }

    /**
     * Тест обновление Employee
     */
    @Test
    public void testEmployeeUpdate1() {
        String url = "http://localhost:8888/api/user/update";
        EmployeeView employeeView = new EmployeeView();
        employeeView.setId(7L);
        employeeView.setFirstName("AAAAAAAAAAA");
        employeeView.setPositionName("юрист");

        Map statusUpdateActual = new GetDataByUrl<EmployeeView>().getStatus(url, employeeView);
        assertEquals("success", statusUpdateActual.get("result"));
    }

    /**
     * Тест обновление Employee в случае неверного id
     */
    @Test
    public void testEmployeeUpdate2() {
        String url = "http://localhost:8888/api/user/update";
        EmployeeView employeeView = new EmployeeView();
        employeeView.setId(1000000L);
        employeeView.setFirstName("aaa");
        employeeView.setPositionName("юрист");


        Map statusUpdateActual = new GetDataByUrl<EmployeeView>().getStatus(url, employeeView);
        assertEquals("failure", statusUpdateActual.get("result"));
    }

    /**
     * Сохранение Employee
     */
    @Test
    public void testEmployeeSave() {
        String url = "http://localhost:8888/api/user/save";

        EmployeeView employeeView = new EmployeeView();
        employeeView.setOfficeId(2L);
        employeeView.setFirstName("Валера");
        employeeView.setPositionName("бухгалтер");
        employeeView.setDocCodeNum(12L);
        employeeView.setCitizenshipCode(276L);

        Map statusSaveActual = new GetDataByUrl<EmployeeView>().getStatus(url, employeeView);
        assertEquals("success", statusSaveActual.get("result"));
    }

    /**
     * Тест Справочник Тип документов
     */
    @Test
    public void testEmployeeDocs() {
        String url = "http://localhost:8888/api/docs";
        ParameterizedTypeReference<DataViewList<Docs>> clazz = new ParameterizedTypeReference<DataViewList<Docs>>() {
        };
        List<Docs> docsList = new GetDataByUrl<Docs>().getListQueryGet(url, clazz);

        List<Docs> docsListExpect = new ArrayList<>();

        Docs docsExpect = new Docs();
        docsExpect.setName("Свидетельство о рождении");
        docsExpect.setCode(3L);
        docsListExpect.add(docsExpect);

        docsExpect = new Docs();
        docsExpect.setName("Паспорт гражданина Российской Федерации");
        docsExpect.setCode(21L);
        docsListExpect.add(docsExpect);

        docsExpect = new Docs();
        docsExpect.setName("Вид на жительство в Российской Федерации");
        docsExpect.setCode(12L);
        docsListExpect.add(docsExpect);

        docsExpect = new Docs();
        docsExpect.setName("Паспорт иностранного гражданина");
        docsExpect.setCode(10L);
        docsListExpect.add(docsExpect);

        for (int i = 0; i < docsListExpect.size(); i++) {
            Docs docs = docsList.get(i);
            docsExpect = docsListExpect.get(i);
            assertThat(docs, samePropertyValuesAs(docsExpect));
        }
    }

    /**
     * Тест Справочник Гражданство
     */
    @Test
    public void testEmployeeCountries() {
        String url = "http://localhost:8888/api/countries";
        ParameterizedTypeReference<DataViewList<Countries>> clazz = new ParameterizedTypeReference<DataViewList<Countries>>() {
        };
        List<Countries> countriesList = new GetDataByUrl<Countries>().getListQueryGet(url, clazz);

        List<Countries> countriesListExpect = new ArrayList<>();

        Countries countriesExpect = new Countries();
        countriesExpect.setName("Россия");
        countriesExpect.setCode(643L);
        countriesListExpect.add(countriesExpect);

        countriesExpect = new Countries();
        countriesExpect.setName("Великобритания");
        countriesExpect.setCode(826L);
        countriesListExpect.add(countriesExpect);

        countriesExpect = new Countries();
        countriesExpect.setName("Германия");
        countriesExpect.setCode(276L);
        countriesListExpect.add(countriesExpect);

        countriesExpect = new Countries();
        countriesExpect.setName("Франция");
        countriesExpect.setCode(250L);
        countriesListExpect.add(countriesExpect);

        for (int i = 0; i < countriesListExpect.size(); i++) {
            Countries countries = countriesList.get(i);
            countriesExpect = countriesListExpect.get(i);
            assertThat(countries, samePropertyValuesAs(countriesExpect));
        }
    }

}
