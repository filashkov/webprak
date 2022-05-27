package com.filashkov.webprak.controllers;

import com.filashkov.webprak.DAO.*;
import com.filashkov.webprak.DAO.implemetation.*;
import com.filashkov.webprak.models.Clients;
import com.filashkov.webprak.models.Contracts;
import com.filashkov.webprak.models.Services;
import com.filashkov.webprak.models.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    private final ContractsDAO contractsDAO = new ContractsDAOImplementation();

    @Autowired
    private final ServicesDAO servicesDAO = new ServicesDAOImplementation();

    @Autowired
    private final StaffDAO staffDAO = new StaffDAOImplementation();

    @Autowired
    private final EmployeeRegisteredServiceDAO employeeRegisteredServiceDAO = new EmployeeRegisteredServiceDAOImplementation();

    @Autowired
    private final ClientsDAO clientsDAO = new ClientsDAOImplementation();

    @RequestMapping(value = { "/","/services"})
    public String services() {
        return "services";
    }

    public int mode = 0;
    public Long user_id = null;

    public String fullname = "";

    @RequestMapping(value = "about_company_page")
    public String about() {
        return "about_company_page";
    }

    @RequestMapping(value = "admin_panel")
    public String admin_panel() {
        return "admin_panel";
    }

    @RequestMapping(value = "contracts")
    public String contracts() {
        return "contracts";
    }

    @RequestMapping(value = "clients")
    public String clients() {
        return "clients";
    }

    @RequestMapping(value = "staff")
    public String staff() {
        return "staff";
    }

    @RequestMapping(value = "signin_client")
    public String sign_client() {
        return "signin_client";
    }

//    @RequestMapping(value = "edit_client")
//    public String edit_client() {
//        return "edit_client";
//    }
//
//    @RequestMapping(value = "edit_contract")
//    public String edit_contract() {
//        return "edit_contract";
//    }
//
//    @RequestMapping(value = "edit_staff")
//    public String edit_staff() {
//        return "edit_staff";
//    }

    @GetMapping("/edit_client")
    public String edit_client_page(@RequestParam(name = "client_id", required = false) Long clientId, Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        if (clientId == null) {
            model.addAttribute("client", new Clients());
            model.addAttribute("clientsDAO", clientsDAO);
            return "edit_client";
        }

        Clients client = clientsDAO.getById(clientId);

        if (client == null) {
            return "services";
        }

        model.addAttribute("client", client);
        model.addAttribute("clientsDAO", clientsDAO);
        return "edit_client";
    }

    @GetMapping("/edit_contract")
    public String edit_contract_page(@RequestParam(name = "contract_id", required = false) Long contractId, Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        if (contractId == null) {
            model.addAttribute("contract", new Contracts());
            model.addAttribute("contractsDAO", contractsDAO);
            return "edit_contract";
        }

        Contracts contract = contractsDAO.getById(contractId);

        if (contract == null) {
            return "services";
        }

        model.addAttribute("contract", contract);
        model.addAttribute("contractsDAO", contractsDAO);
        return "edit_contract";
    }

    @GetMapping("/edit_staff")
    public String edit_staff_page(@RequestParam(name = "staff_id", required = false) Long staffId, Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        if (staffId == null) {
            model.addAttribute("staff", new Staff());
            model.addAttribute("staffDAO", staffDAO);
            return "edit_staff";
            // return "error_page";
        }

        Staff staff = staffDAO.getById(staffId);

        if (staff == null) {
            return "services";
        }

        model.addAttribute("staff", staff);
        model.addAttribute("staffDAO", staffDAO);
        // return "error_page";
        return "edit_staff";
    }

    @GetMapping("/edit_service")
    public String edit_service_page(@RequestParam(name = "service_id", required = false) Long serviceId, Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        if (serviceId == null) {
            model.addAttribute("service", new Services());
            model.addAttribute("servicesDAO", servicesDAO);
            return "edit_service";
        }

        Services service = servicesDAO.getById(serviceId);

        if (service == null) {
            return "services";
        }

        model.addAttribute("service", service);
        model.addAttribute("servicesDAO", servicesDAO);
        // return "error_page";
        return "edit_service";
    }

    @GetMapping({"/", "/services"})
    public String servicesList(Model model) {
        List<Services> services_list = (List<Services>) servicesDAO.getAll();
        model.addAttribute("people", services_list);
        model.addAttribute("services_list", services_list);
        model.addAttribute("servicesDAO", servicesDAO);
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "services";
    }

    @GetMapping("contracts")
    public String contractsListPage(Model model) {
        List<Contracts> contracts_list = (List<Contracts>) contractsDAO.getAll();
        model.addAttribute("contracts_list", contracts_list);
        model.addAttribute("contractsDAO", contractsDAO);
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "contracts";
    }

    @GetMapping("about_company_page")
    public String get_about(Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "about_company_page";
    }

    @GetMapping("admin_panel")
    public String get_admin_panel(Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "admin_panel";
    }

    @GetMapping("clients")
    public String get_clients(Model model) {
        List<Clients> clients_list = (List<Clients>) clientsDAO.getAll();
        model.addAttribute("clients_list", clients_list);
        model.addAttribute("clientsDAO", clientsDAO);
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "clients";
    }

    @GetMapping("staff")
    public String get_staff(Model model) {
        List<Staff> staff_list = (List<Staff>) staffDAO.getAll();
        model.addAttribute("staff_list", staff_list);
        model.addAttribute("staffDAO", staffDAO);
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "staff";
    }
}
