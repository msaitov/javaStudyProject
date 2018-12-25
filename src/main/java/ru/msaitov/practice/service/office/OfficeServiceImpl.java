package ru.msaitov.practice.service.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msaitov.practice.dao.Switcher;
import ru.msaitov.practice.dao.office.OfficeDao;
import ru.msaitov.practice.model.Office;
import ru.msaitov.practice.model.Organization;
import ru.msaitov.practice.model.mapper.MapperFacade;
import ru.msaitov.practice.view.OfficeView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(final Switcher switcher, final MapperFacade mapperFacade) {
        this.officeDao = switcher.getDaoFactory().getOfficeDao();
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeView> filter(final OfficeView officeView) {

        if (officeView.getOrganizationId() == null) {
            return null;
        }

        Office office = mapperFacade.map(officeView, Office.class);

        Organization organization = new Organization();
        organization.setId(officeView.getOrganizationId());

        office.setOrganization(organization);

        List<Office> officeList = officeDao.getItems(office);
        for (Office officeDoFieldsNull : officeList) {
            officeDoFieldsNull.setOrganization(null);
            officeDoFieldsNull.setAddress(null);
            officeDoFieldsNull.setPhone(null);
        }
        return mapperFacade.mapAsList(officeList, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeView loadById(final Long id) {
        Office office = officeDao.getItemById(id);
        return mapperFacade.map(office, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public String update(final OfficeView officeView) {
        Office office = mapperFacade.map(officeView, Office.class);
        Organization organization = new Organization();
        organization.setId(officeView.getOrganizationId());
        office.setOrganization(organization);
        return officeDao.updateItem(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public String save(final OfficeView officeView) {
        Office office = mapperFacade.map(officeView, Office.class);
        Organization organization = new Organization();
        organization.setId(officeView.getOrganizationId());
        office.setOrganization(organization);
        return officeDao.add(office);
    }
}
