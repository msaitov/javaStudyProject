package ru.msaitov.practice.service.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    public OrganizationServiceImpl(final OrganizationDao organizationDao, final MapperFacade mapperFacade) {
        this.organizationDao = organizationDao;
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

        List<Organization> organizationList = organizationDao.getItems(organizationView);
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
}
