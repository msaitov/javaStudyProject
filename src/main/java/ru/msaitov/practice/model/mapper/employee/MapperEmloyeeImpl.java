package ru.msaitov.practice.model.mapper.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msaitov.practice.controller.handleException.CustomNotFoundException;
import ru.msaitov.practice.model.Office;
import ru.msaitov.practice.model.employee.Citizenship;
import ru.msaitov.practice.model.employee.Citizenship_;
import ru.msaitov.practice.model.employee.DocCode;
import ru.msaitov.practice.model.employee.DocCode_;
import ru.msaitov.practice.model.employee.Employee;
import ru.msaitov.practice.model.employee.Position;
import ru.msaitov.practice.model.employee.Position_;
import ru.msaitov.practice.model.mapper.MapperFacade;
import ru.msaitov.practice.view.EmployeeView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class MapperEmloyeeImpl implements MapperEmployee {

    private final MapperFacade mapperFacade;
    private final EntityManager em;

    @Autowired
    public MapperEmloyeeImpl(MapperFacade mapperFacade, EntityManager em) {
        this.mapperFacade = mapperFacade;
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EmployeeView> mapAsList(final List<Employee> employeeList) {


        List<EmployeeView> employeeViewList = mapperFacade.mapAsList(employeeList, EmployeeView.class);

        for (int i = 0; i < employeeViewList.size(); i++) {

            EmployeeView ev = employeeViewList.get(i);
            Employee el = employeeList.get(i);

            mapNullFields(el, ev);

            employeeViewList.set(i, ev);
        }
        return employeeViewList;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeView map(final Employee employee) {
        EmployeeView employeeView = mapperFacade.map(employee, EmployeeView.class);
        return mapNullFields(employee, employeeView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee map(final EmployeeView employeeView) {
        Employee employee = mapperFacade.map(employeeView, Employee.class);
        return mapNullFields(employeeView, employee);
    }

    private EmployeeView mapNullFields(final Employee el, final EmployeeView ev) {

        if (el == null) {
            return null;
        }
        ev.setOfficeId(el.getOffice().getId());
        ev.setDocCodeNum(el.getDocCode().getCode());
        ev.setDocName(el.getDocCode().getName());
        ev.setCitizenshipCode(el.getCitizenship().getCode());
        ev.setCitizenshipName(el.getCitizenship().getName());
        ev.setPositionName(el.getPosition().getName());

        return ev;
    }

    private Employee mapNullFields(final EmployeeView ev, final Employee el) {

        if (ev == null) {
            return null;
        }
        el.setOffice(mapOffice(ev));
        el.setDocCode(mapDocCode(ev));
        el.setCitizenship(mapCitizenship(ev));
        el.setPosition(mapPosition(ev));

        return el;
    }

    private Office mapOffice(final EmployeeView ev) {

        Office office = null;
        if (ev.getOfficeId() != null) {
            office = em.find(Office.class, ev.getOfficeId());
        }
        return office;
    }

    private DocCode mapDocCode(final EmployeeView ev) {

        if (ev.getDocName() == null && ev.getDocCodeNum() == null) {
            return null;
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<DocCode> cqDocCode = cb.createQuery(DocCode.class);

        Root<DocCode> docCodeRoot = cqDocCode.from(DocCode.class);

        if (ev.getDocName() != null) {
            cqDocCode.where(cb.equal(docCodeRoot.get(DocCode_.name), ev.getDocName()));
        } else if (ev.getDocCodeNum() != null) {
            cqDocCode.where(cb.equal(docCodeRoot.get(DocCode_.code), ev.getDocCodeNum()));
        }

        TypedQuery<DocCode> query = em.createQuery(cqDocCode);

        return query.getSingleResult();
    }


    private Citizenship mapCitizenship(final EmployeeView ev) {

        if (ev.getCitizenshipName() == null && ev.getCitizenshipCode() == null) {
            return null;
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Citizenship> cq = cb.createQuery(Citizenship.class);

        Root<Citizenship> docCitizenship = cq.from(Citizenship.class);

        if (ev.getCitizenshipName() != null) {
            cq.where(cb.equal(docCitizenship.get(Citizenship_.name), ev.getCitizenshipName()));
        } else if (ev.getCitizenshipCode() != null) {
            cq.where(cb.equal(docCitizenship.get(Citizenship_.code), ev.getCitizenshipCode()));
        }

        TypedQuery<Citizenship> query = em.createQuery(cq);

        return query.getSingleResult();
    }


    private Position mapPosition(final EmployeeView ev) {

        if (ev.getPositionName() == null) {
            return null;
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Position> cq = cb.createQuery(Position.class);

        Root<Position> docPosition = cq.from(Position.class);

        if (ev.getPositionName() != null) {
            cq.where(cb.equal(docPosition.get(Position_.name), ev.getPositionName()));
        }

        TypedQuery<Position> query = em.createQuery(cq);
        Position positionQuery;
        try {
            positionQuery = query.getSingleResult();
        } catch (Exception e) {
            throw new CustomNotFoundException("неверное значение поля position");
        }
        return positionQuery;
    }
}
