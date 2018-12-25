package ru.msaitov.practice.service.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msaitov.practice.dao.Switcher;
import ru.msaitov.practice.dao.organization.OrganizationDao;
import ru.msaitov.practice.model.Organization;
import ru.msaitov.practice.model.mapper.MapperFacade;
import ru.msaitov.practice.view.OrganizationView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(final Switcher switcher, final MapperFacade mapperFacade) {
        this.organizationDao = switcher.getDaoFactory().getOrganizationDao();
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> filter(final OrganizationView organizationView) {

        if (organizationView.getName() == null) {
            return null;
        }

        Organization organization = mapperFacade.map(organizationView, Organization.class);

        List<Organization> organizationList = organizationDao.getItems(organization);
        for (Organization org : organizationList) {
            org.setNameFull(null);
            org.setAddress(null);
            org.setInn(null);
            org.setKpp(null);
            org.setPhone(null);
        }
        return mapperFacade.mapAsList(organizationList, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationView loadById(final Long id) {
        Organization organization = organizationDao.getItemById(id);
        return mapperFacade.map(organization, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public String update(final OrganizationView organizationView) {
        Organization organization = mapperFacade.map(organizationView, Organization.class);
        return organizationDao.updateItem(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public String save(final OrganizationView organizationView) {
        Organization organization = mapperFacade.map(organizationView, Organization.class);
        return organizationDao.add(organization);
    }
}
