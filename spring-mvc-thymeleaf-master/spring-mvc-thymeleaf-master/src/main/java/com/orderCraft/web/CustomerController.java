package com.orderCraft.web;

import java.util.List;

import com.orderCraft.service.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orderCraft.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping("list")
    public String listCustomers(Model theModel) {
        List<Customer> customers = customerService.getAllCustomers();
        theModel.addAttribute("customers", customers);
        return "client/list-customers";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        LOG.debug("inside show customer-form handler method");
        Customer theCustomer = new Customer();
        theModel.addAttribute("customer", theCustomer);
        return "client/customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }

    /*@GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("customerId") int theId,
                                    Model theModel) throws NullPointerException {
        Customer theCustomer = customerService.getCustomer(theId);
        theModel.addAttribute("customer", theCustomer);
        return "customer-form";
    }*/

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) throws NullPointerException {
        customerService.deleteCustomer((long)theId);
        return "redirect:/customer/list";
    }
}