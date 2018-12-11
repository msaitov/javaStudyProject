package ru.msaitov.practice.model.mapper.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msaitov.practice.model.employee.Employee;
import ru.msaitov.practice.model.mapper.MapperFacade;
import ru.msaitov.practice.view.EmployeeView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class MapperEmloyeeImpl implements MapperEmployee {

    private final MapperFacade mapperFacade;

    @Autowired
    public MapperEmloyeeImpl(final MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
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


    private EmployeeView mapNullFields(final Employee el, final EmployeeView ev) {

        ev.setOfficeId(el.getOffice().getId());
        ev.setDocCodeNum(el.getDocCode().getCode());
        ev.setDocName(el.getDocCode().getName());
        ev.setCitizenshipCode(el.getCitizenship().getCode());
        ev.setCitizenshipName(el.getCitizenship().getName());
        ev.setPositionName(el.getPosition().getName());

        return ev;
    }
}
