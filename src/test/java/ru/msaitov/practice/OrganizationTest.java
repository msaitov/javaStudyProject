package ru.msaitov.practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import ru.msaitov.practice.view.DataViewList;
import ru.msaitov.practice.view.DataViewSingle;
import ru.msaitov.practice.view.OrganizationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Тесты Organization
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrganizationTest {

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Тест вывод Organization по id
     */
    @Test
    public void testOrganizationId1() {
        Long testId = 1L;
        String url = "http://localhost:8888/api/organization/" + testId;

        ParameterizedTypeReference<DataViewSingle<OrganizationView>> clazz = new ParameterizedTypeReference<DataViewSingle<OrganizationView>>() {
        };
        OrganizationView organizationView = new GetDataByUrl<OrganizationView>().getSingle(url, clazz);

        OrganizationView organizationViewExp = new OrganizationView();
        organizationViewExp.setId(1L);
        organizationViewExp.setName("Xerox");
        organizationViewExp.setNameFull("Xerox Corporation");
        organizationViewExp.setAddress("г. Москва, ул.Цюрупы, 16");
        organizationViewExp.setInn("769340695065");
        organizationViewExp.setKpp("4829548543");
        organizationViewExp.setPhone("+79251235464");
        organizationViewExp.setIsActive(true);

        assertThat(organizationView, samePropertyValuesAs(organizationViewExp));
    }

    /**
     * Тест вывод Organization в случае неверного id
     */
    @Test
    public void testOrganizationId2() {
        String url = "http://localhost:8888/api/organization/1000000000";
        String responseError = restTemplate.getForObject(url, String.class);
        assertEquals(responseError, "{\"error\":\"id не найдено\"}");
    }

    /**
     * Тест вывод списка Office по фильтру
     */
    @Test
    public void testOrganizationList1() {
        String url = "http://localhost:8888/api/organization/list";

        OrganizationView filter = new OrganizationView();
        filter.setName("Xerox");

        ParameterizedTypeReference<DataViewList<OrganizationView>> clazz = new ParameterizedTypeReference<DataViewList<OrganizationView>>() {
        };
        List<OrganizationView> organizationViewListActual = new GetDataByUrl<OrganizationView>().getList(url, filter, clazz);

        Long id = 1L;
        String name = "Xerox";
        Boolean isActive = true;
        List<OrganizationView> organizationViewListExpect = new ArrayList<>();
        OrganizationView organizationViewExpect = new OrganizationView();
        organizationViewExpect.setId(id);
        organizationViewExpect.setName(name);
        organizationViewExpect.setIsActive(isActive);
        organizationViewListExpect.add(organizationViewExpect);

        for (int i = 0; i < organizationViewListExpect.size(); i++) {
            OrganizationView organizationView = organizationViewListActual.get(i);
            organizationViewExpect = organizationViewListExpect.get(i);
            assertThat(organizationView, samePropertyValuesAs(organizationViewExpect));
        }
    }

    /**
     * Тест вывод списка Office по фильтру
     */
    @Test
    public void testOrganizationList2() {

        String url = "http://localhost:8888/api/organization/list";

        OrganizationView filter = new OrganizationView();
        filter.setName("Toshiba");
        filter.setInn("830438493403");
        filter.setIsActive(false);

        ParameterizedTypeReference<DataViewList<OrganizationView>> clazz = new ParameterizedTypeReference<DataViewList<OrganizationView>>() {
        };
        List<OrganizationView> organizationViewListActual = new GetDataByUrl<OrganizationView>().getList(url, filter, clazz);

        Long id = 3L;
        String name = "Toshiba";
        Boolean isActive = false;
        List<OrganizationView> organizationViewListExpect = new ArrayList<>();
        OrganizationView organizationViewExpect = new OrganizationView();
        organizationViewExpect.setId(id);
        organizationViewExpect.setName(name);
        organizationViewExpect.setIsActive(isActive);
        organizationViewListExpect.add(organizationViewExpect);

        for (int i = 0; i < organizationViewListExpect.size(); i++) {
            OrganizationView organizationView = organizationViewListActual.get(i);
            organizationViewExpect = organizationViewListExpect.get(i);
            assertThat(organizationView, samePropertyValuesAs(organizationViewExpect));
        }
    }

    /**
     * Тест обновление Organization
     */
    @Test
    public void testOrganizationUpdate1() {
        String url = "http://localhost:8888/api/organization/update";
        OrganizationView organizationView = new OrganizationView();
        organizationView.setId(2L);
        organizationView.setName("Sony Japan");
        organizationView.setNameFull("Sony Corporation of Japan");
        organizationView.setInn("111111111111");
        organizationView.setKpp("222222222222");
        organizationView.setAddress("г.Токио, ул. Ибрагима, 1");
        organizationView.setPhone("+33333333333");
        organizationView.setIsActive(false);

        Map statusUpdateActual = new GetDataByUrl<OrganizationView>().getStatus(url, organizationView);
        assertEquals("success", statusUpdateActual.get("result"));
    }

    /**
     * Тест обновление Office в случае неверного id
     */
    @Test
    public void testOrganizationUpdate2() {
        String url = "http://localhost:8888/api/organization/update";
        OrganizationView organizationView = new OrganizationView();
        organizationView.setId(100000000000L);
        organizationView.setName("Sony Japan");
        organizationView.setNameFull("Sony Corporation of Japan");
        organizationView.setInn("111111111111");
        organizationView.setKpp("222222222222");
        organizationView.setAddress("г.Токио, ул. Ибрагима, 1");

        Map statusUpdateActual = new GetDataByUrl<OrganizationView>().getStatus(url, organizationView);
        assertEquals("failure", statusUpdateActual.get("result"));
    }


    /**
     * Сохранение Organization
     */
    @Test
    public void testOrganizationSave() {
        String url = "http://localhost:8888/api/organization/save";
        Random random = new Random();
        Integer randomInn = random.nextInt(1000000000);
        Integer randomKpp = random.nextInt(1000000000);

        OrganizationView organizationView = new OrganizationView();
        organizationView.setName("Philips");
        organizationView.setNameFull("Philips Corporation");
        organizationView.setInn(String.valueOf(randomInn));
        organizationView.setKpp(String.valueOf(randomKpp));
        organizationView.setAddress("г.Будапешт, ул. Веселая, 10");
        organizationView.setPhone("+33333333333");
        organizationView.setIsActive(true);

        Map statusSaveActual = new GetDataByUrl<OrganizationView>().getStatus(url, organizationView);
        assertEquals("success", statusSaveActual.get("result"));
    }
}
