package com.master.controller;

import com.master.models.CustomersEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller// I am the controler. I map where thee program points to
public class HomeController { // class called HomController

    @RequestMapping("/")// mapping for "/" i.e. the home page

    public ModelAndView helloWorld()
    {
        return new
                ModelAndView("welcome","message","Hello World");// returns the ViewName called welcome. Inserted in the page is the ModelName called message. The variable stored in message is called Hello World

    }

    public ArrayList<CustomersEntity> getAllCustomers()// method that runs on the home page that returns an Array List with the customers
    {
        // configuration is a hibernate function
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        //session factory
        SessionFactory sessionFact = cfg.buildSessionFactory();

        //Session: where the magic happens.
        Session selectCustomers = sessionFact.openSession();

        //starting our session
        selectCustomers.beginTransaction();

        //takes in all of the variables from the CustomerEntity class
        Criteria c = selectCustomers.createCriteria(CustomersEntity.class);

        //.list prints out all of the classes in the customer entity
        ArrayList<CustomersEntity> customerList=(ArrayList<CustomersEntity>)c.list();

        return  customerList;


    }

    @RequestMapping("welcome")

    public ModelAndView helloWorld2()
    {

        ArrayList<CustomersEntity> customerList = getAllCustomers();


        return new
                ModelAndView("welcompg4","cList",customerList);

    }


    @RequestMapping("delete")
    public ModelAndView deleteCustomer(@RequestParam("id") String id)
    {
        // temp will store info for the object that we want to delete
        CustomersEntity temp = new CustomersEntity();
        temp.setCustomerId(id);


        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory fact = cfg.buildSessionFactory();

        Session customers = fact.openSession();
        customers.beginTransaction();

        customers.delete(temp);// delete the object from the list

        customers.getTransaction().commit();// delete the row from the database

        ArrayList<CustomersEntity> customerList = getAllCustomers();

        return new
                ModelAndView("welcome2","cList",customerList);

    }

    @RequestMapping("searchByCity")
    public ModelAndView searchByCity(@RequestParam ("city") String cityName){
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");


        SessionFactory sessionFact = cfg.buildSessionFactory();

        Session selectCustomers = sessionFact.openSession();

        selectCustomers.beginTransaction();

        Criteria c = selectCustomers.createCriteria(CustomersEntity.class);

        c.add(Restrictions.like("city","%"+cityName+"%"));

        ArrayList<CustomersEntity> CustomerList = (ArrayList<CustomersEntity>)c.list();






        return new
                ModelAndView("welcompg4","cList",CustomerList);
    }


}