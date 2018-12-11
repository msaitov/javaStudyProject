package ru.msaitov.practice.service.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.msaitov.practice.dao.office.OfficeDao;
import ru.msaitov.practice.model.Office;
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
    public OfficeServiceImpl(final OfficeDao officeDao, final MapperFacade mapperFacade) {
        this.officeDao = officeDao;
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

        List<Office> officeList = officeDao.getItems(officeView);
        for (Office office : officeList) {
            office.setOrganization(null);
            office.setAddress(null);
            office.setPhone(null);
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
}
