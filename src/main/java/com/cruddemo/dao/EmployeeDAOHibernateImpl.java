package com.cruddemo.dao;

import com.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.util.Calendar.*;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    @Transactional
    public List<Employee> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
//
        return employees;
    }

    @Override
    @Transactional
    public String findById(int id, int bill, int groceries) {
        int discount = 0;
        int affected = 0;

        Session currentSession = entityManager.unwrap(Session.class);

        Employee theEmployee = currentSession.get(Employee.class, id);
        /**
         * bill must be higher or equal to groceries
         */
        if (bill < groceries) {
            return "The bill must be higher than the groceries";
        }
        /**
         * if employee then discount 30% on the bill, and for each 100 remove 5
         */
        if (theEmployee != null && theEmployee.getEmployee().equals("true")) {
            discount = (int) (bill - ((bill - groceries) * 0.3));
            affected = bill - groceries;
            return returnResult(discount, affected, theEmployee.getFirstName(), theEmployee.getLastName(), 30);
        }
        /**
         * if affliate then discount 10% on the bill, and for each 100$ remove 5
         */
        if (theEmployee != null && theEmployee.getAffliate().equals("true")) {
            discount = (int) (bill - ((bill - groceries) * 0.1));
            affected = bill - groceries;
            return returnResult(discount, affected, theEmployee.getFirstName(), theEmployee.getLastName(), 10);
        }
        /**
         * if customer for more than 2 years then discount 5% on the bill, and for each 100 $
         */
        if (theEmployee != null && getDiffYears(theEmployee.getDate(), new Date()) >= 2) {
            discount = (int) (bill - ((bill - groceries) * 0.05));
            affected = bill - groceries;
            return returnResult(discount, affected, theEmployee.getFirstName(), theEmployee.getLastName(), 5);
        }
        /**
         * if none of the above, then see if the bill is more than 100 and remove 5$ on every 100 $
         */
        discount = bill - (bill / 100 * 5);
        if (discount < bill) {
            String message;
            if (theEmployee != null) {
                message = "Hello " + theEmployee.getFirstName() + " " + theEmployee.getLastName();
            } else {
                message = "Hello ";
            }
            message = message + "\nFor every 100$ we will remove 5$";
            message = message + "\nYour discount is : " + (bill / 100 * 5) + "$";
            message = message + "\nYour bill is : " + discount + "$";
            return message;
        } else {
            return "There is no discount on your bill";
        }
    }

    private static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);

        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

    private int discountedBill(int bill) {
        return (bill - (bill / 100 * 5));
    }

    private String returnResult(int discount, int affected, String firstName, String lastName, int percentage) {
        String message = "Hello " + firstName + " " + lastName;
        if (percentage == 30) {
            message += "\nYou are an employee here\n";
        } else if (percentage == 5) {
            message += "\nYou have been our customer for more than 2 years";
        }
        message += "\nYour discount is " + percentage + "%";
        message += "\nOnly " + affected + "$ will be affected by the discount, because discounts is not applied on groceries.";
        discount = discountedBill(discount);
        message += "\nYour bill after the discount is " + discount + "$";
        return message;
    }

    @Test
    public void testTheCode() {
        findById( 1, 100, 0);
        assert true;
    }
}







