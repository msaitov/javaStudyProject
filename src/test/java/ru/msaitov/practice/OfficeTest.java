package ru.msaitov.practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import ru.msaitov.practice.view.DataViewList;
import ru.msaitov.practice.view.DataViewSingle;
import ru.msaitov.practice.view.OfficeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Тесты Office
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OfficeTest {

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Тест вывод Office по id
     */
    @Test
    public void testOfficeId1() {
        Long testId = 2L;
        String url = "http://localhost:8888/api/office/" + testId;

        ParameterizedTypeReference<DataViewSingle<OfficeView>> clazz = new ParameterizedTypeReference<DataViewSingle<OfficeView>>() {
        };
        OfficeView OfficeView = new GetDataByUrl<OfficeView>().getSingle(url, clazz);

        OfficeView OfficeViewExp = new OfficeView();
        OfficeViewExp.setId(2L);
        OfficeViewExp.setName("Техника");
        OfficeViewExp.setAddress("г. Москва, ул. Митчинская, 3");
        OfficeViewExp.setPhone("+79268453453");
        OfficeViewExp.setIsActive(true);

        assertThat(OfficeView, samePropertyValuesAs(OfficeViewExp));
    }

    /**
     * Тест вывод Office в случае неверного id
     */
    @Test
    public void testOfficeId2() {
        String url = "http://localhost:8888/api/office/1000000000";
        String responseError = restTemplate.getForObject(url, String.class);
        assertEquals(responseError, "{\"error\":\"id не найдено\"}");
    }

    /**
     * Тест вывод списка Office по фильтру
     */
    @Test
    public void testOfficeList1() {
        String url = "http://localhost:8888/api/office/list";

        OfficeView filter = new OfficeView();
        filter.setOrganizationId(4L);

        ParameterizedTypeReference<DataViewList<OfficeView>> clazz = new ParameterizedTypeReference<DataViewList<OfficeView>>() {
        };
        List<OfficeView> OfficeViewListActual = new GetDataByUrl<OfficeView>().getList(url, filter, clazz);

        Long id = 5L;
        String name = "Газоны на заказ";
        Boolean isActive = true;
        List<OfficeView> OfficeViewListExpect = new ArrayList<>();
        OfficeView OfficeViewExpect = new OfficeView();
        OfficeViewExpect.setId(id);
        OfficeViewExpect.setName(name);
        OfficeViewExpect.setIsActive(isActive);
        OfficeViewListExpect.add(OfficeViewExpect);

        for (int i = 0; i < OfficeViewListExpect.size(); i++) {
            OfficeView OfficeView = OfficeViewListActual.get(i);
            OfficeViewExpect = OfficeViewListExpect.get(i);
            assertThat(OfficeView, samePropertyValuesAs(OfficeViewExpect));
        }
    }

    /**
     * Тест вывод списка Office по фильтру
     */
    @Test
    public void testOfficeList2() {

        String url = "http://localhost:8888/api/office/list";

        OfficeView filter = new OfficeView();
        filter.setOrganizationId(1L);
        filter.setName("Шубы");
        filter.setPhone("+74954365465");
        filter.setIsActive(true);

        ParameterizedTypeReference<DataViewList<OfficeView>> clazz = new ParameterizedTypeReference<DataViewList<OfficeView>>() {
        };
        List<OfficeView> OfficeViewListActual = new GetDataByUrl<OfficeView>().getList(url, filter, clazz);

        Long id = 1L;
        String name = "Шубы";
        Boolean isActive = true;
        List<OfficeView> OfficeViewListExpect = new ArrayList<>();
        OfficeView OfficeViewExpect = new OfficeView();
        OfficeViewExpect.setId(id);
        OfficeViewExpect.setName(name);
        OfficeViewExpect.setIsActive(isActive);
        OfficeViewListExpect.add(OfficeViewExpect);

        for (int i = 0; i < OfficeViewListExpect.size(); i++) {
            OfficeView OfficeView = OfficeViewListActual.get(i);
            OfficeViewExpect = OfficeViewListExpect.get(i);
            assertThat(OfficeView, samePropertyValuesAs(OfficeViewExpect));
        }
    }

    /**
     * Тест обновление Office
     */
    @Test
    public void testOfficeUpdate1() {
        String url = "http://localhost:8888/api/office/update";

        OfficeView officeView = new OfficeView();
        officeView.setId(4L);
        officeView.setName("Свет на заказ");
        officeView.setAddress("г. Москва, Виницы 5");
        officeView.setPhone("+33333333333");
        officeView.setIsActive(true);

        Map statusUpdateActual = new GetDataByUrl<OfficeView>().getStatus(url, officeView);
        assertEquals("success", statusUpdateActual.get("result"));
    }

    /**
     * Тест обновление Office в случае неверного id
     */
    @Test
    public void testOfficeUpdate2() {
        String url = "http://localhost:8888/api/office/update";

        OfficeView officeView = new OfficeView();
        officeView.setId(100000000000L);
        officeView.setName("");
        officeView.setAddress("");

        Map statusUpdateActual = new GetDataByUrl<OfficeView>().getStatus(url, officeView);
        assertEquals("failure", statusUpdateActual.get("result"));
    }


    /**
     * Сохранение Employee
     */
    @Test
    public void testOfficeSave() {
        String url = "http://localhost:8888/api/office/save";

        OfficeView officeView = new OfficeView();
        officeView.setName("Крутые сапоги");
        officeView.setOrganizationId(2L);
        officeView.setAddress("г.Москва ул. Петрова, 11");
        officeView.setPhone("+55555555555");
        officeView.setIsActive(true);

        Map statusSaveActual = new GetDataByUrl<OfficeView>().getStatus(url, officeView);
        assertEquals("success", statusSaveActual.get("result"));
    }
}
