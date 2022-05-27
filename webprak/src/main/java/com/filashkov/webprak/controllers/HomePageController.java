package com.filashkov.webprak.controllers;

import com.filashkov.webprak.DAO.*;
import com.filashkov.webprak.DAO.implemetation.*;
import com.filashkov.webprak.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.testng.internal.collections.Pair;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public int mode = 3;
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

    @GetMapping("signin_client")
    public String get_signin(Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "signin_client";
    }

    @GetMapping("lk_client")
    public String get_lk_client(Model model) {
        List<Contracts> contracts_list = (List<Contracts>) contractsDAO.getAllContractsByColValue("client_id", String.valueOf(user_id));
        model.addAttribute("contracts_list", contracts_list);
        model.addAttribute("contractsDAO", contractsDAO);
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "lk_client";
    }

    public Boolean participating_in_contract(Long employee_id, Long contract_id) {
        EmployeeRegisteredService employeeRegisteredService = employeeRegisteredServiceDAO.getById(new ComplexId(employee_id, contract_id));
        return employeeRegisteredService != null;
    }

    @GetMapping("lk_staff")
    public String get_lk_staff(Model model) {
        List<Contracts> contracts_list_old = (List<Contracts>) contractsDAO.getAll();
        List<Contracts> contracts_list = new ArrayList<Contracts>();
        List<EmployeeRegisteredService> ers_list = new ArrayList<>();
        if (user_id != null) {
            ers_list = employeeRegisteredServiceDAO.getEmployeeRegisteredServiceByValue("employee_id", user_id);
        }
        for (int i = 0; i < contracts_list_old.size(); i++) {
            Long contract_id = contracts_list_old.get(i).getId();
            boolean finded = false;
            for (int j = 0; j < ers_list.size(); j++) {
                Long cid = ers_list.get(j).getId().getRegistered_service_id();
                if (Objects.equals(cid, contract_id)) {
                    finded = true;
                    break;
                }
            }
            if (finded) {
                contracts_list.add(contracts_list_old.get(i));
            }
        }
        model.addAttribute("contracts_list", contracts_list);
        model.addAttribute("contractsDAO", contractsDAO);
        model.addAttribute("mode", mode);
        model.addAttribute("user_id", user_id);
        return "lk_staff";
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

    @PostMapping("/save_client")
    public String save_client(@RequestParam(name = "client_id", required = false) Long clientId,
                              @RequestParam(name = "client_fullname") String clientFullname,
                              @RequestParam(name = "client_contact_phone") Long clientContactPhone,
                              @RequestParam(name = "client_email", required = false) String clientEmail,
                              @RequestParam(name = "client_login") String clientLogin,
                              @RequestParam(name = "client_password") String clientPassword,
                              Model model) {

        Clients client = null;
        if (clientId != null) {
            client = clientsDAO.getById(clientId);
        }

        if (client != null) {
            client.setClient_fullname(clientFullname);
            client.setClient_contact_phone(clientContactPhone);
            client.setClient_email(clientEmail);
            client.setClient_login(clientLogin);
            client.setClient_password(clientPassword);
            clientsDAO.update(client);
        } else {
            client = new Clients(clientId, clientFullname, clientContactPhone, clientEmail, clientLogin, clientPassword);
            clientsDAO.save(client);
        }

        return "redirect:/clients";
    }

    @PostMapping("/save_staff")
    public String save_staff(@RequestParam(name = "employee_id", required = false) Long employeeId,
                             @RequestParam(name = "employee_fullname") String employeeFullname,
                             @RequestParam(name = "employee_address") String employeeAddress,
                             @RequestParam(name = "employee_phone_number", required = false) Long employeePhoneNumber,
                             @RequestParam(name = "employee_email") String employeeEmail,
                             @RequestParam(name = "employee_login") String employeeLogin,
                             @RequestParam(name = "employee_password", required = false) String employeePassword,
                             @RequestParam(name = "employee_post") String employeePost,
                             @RequestParam(name = "employee_is_admin") Long employeeIsAdmin,
                             Model model) {

        Staff staff = null;
        if (employeeId != null) {
            staff = staffDAO.getById(employeeId);
        }

        if (staff != null) {
            staff.setEmployee_fullname(employeeFullname);
            staff.setEmployee_address(employeeAddress);
            staff.setEmployee_phone_number(employeePhoneNumber);
            staff.setEmployee_email(employeeEmail);
            staff.setEmployee_login(employeeLogin);
            staff.setEmployee_password(employeePassword);
            staff.setEmployee_post(employeePost);
            staff.setEmployee_is_admin(employeeIsAdmin);
            staffDAO.update(staff);
        } else {
            staff = new Staff(employeeId, employeeFullname, employeeAddress, employeePhoneNumber, employeeEmail, employeeLogin, employeePassword, employeePost, employeeIsAdmin);
            staffDAO.save(staff);
        }

        return "redirect:/staff";
    }

    Date str2Date(String s) {
        return s.isBlank() ? null : Date.valueOf(s);
    }

    @PostMapping("/save_contract")
    public String save_contract(@RequestParam(name = "contract_id", required = false) Long contract_id,
                                @RequestParam(name = "client_id") Long client_id,
                                @RequestParam(name = "service_type_id") Long service_type_id,
                                @RequestParam(name = "beginning_date", required = false) String beginning_date,
                                @RequestParam(name = "date_of_completion", required = false) String date_of_completion,
                                @RequestParam(name = "contract_description") String contract_description,
                                @RequestParam(name = "real_cost") Long real_cost,
                                Model model) {

        Contracts contract = null;
        if (contract_id != null) {
            contract = contractsDAO.getById(contract_id);
        }

        if (contract != null) {
            contract.setClient_id(client_id);
            contract.setService_type_id(service_type_id);
            contract.setBeginning_date(str2Date(beginning_date));
            contract.setDate_of_completion(str2Date(date_of_completion));
            contract.setContract_description(contract_description);
            contract.setReal_cost(real_cost);
            contractsDAO.update(contract);
        } else {
            contract = new Contracts(contract_id, client_id, service_type_id, str2Date(beginning_date), str2Date(date_of_completion), contract_description, real_cost);
            contractsDAO.save(contract);
        }

        return "redirect:/contracts";
    }

    @PostMapping("/save_service")
    public String save_services(@RequestParam(name = "service_id", required = false) Long service_id,
                                @RequestParam(name = "service_type_name") String service_type_name,
                                @RequestParam(name = "service_description") String service_description,
                                @RequestParam(name = "service_approximate_cost", required = false) Long service_approximate_cost,
                                Model model) {

        Services service = null;
        if (service_id != null) {
            service = servicesDAO.getById(service_id);
        }

        if (service != null) {
            service.setService_type_name(service_type_name);
            service.setService_description(service_description);
            service.setService_approximate_cost(service_approximate_cost);
            servicesDAO.update(service);
        } else {
            service = new Services(service_id, service_type_name, service_description, service_approximate_cost);
            servicesDAO.save(service);
        }

        return "redirect:/services";
    }

    @PostMapping("/delete_client")
    public String removeClient(@RequestParam(name = "client_id") Long clientId) {
        clientsDAO.deleteById(clientId);
        return "redirect:/clients";
    }

    @PostMapping("/delete_contract")
    public String removeContract(@RequestParam(name = "contract_id") Long contractId) {
        contractsDAO.deleteById(contractId);
        return "redirect:/contracts";
    }

    @PostMapping("/delete_staff")
    public String removeStaff(@RequestParam(name = "staff_id") Long staffId) {
        staffDAO.deleteById(staffId);
        return "redirect:/staff";
    }

    @PostMapping("/delete_service")
    public String removeService(@RequestParam(name = "service_id") Long serviceId) {
        servicesDAO.deleteById(serviceId);
        return "redirect:/services";
    }

    @PostMapping("/signin_as_guest")
    public String signin_as_guest() {
        mode = 3;
        user_id = null;
        return "redirect:/services";
    }

    @PostMapping("/signin_as_client")
    public String signin_as_client(@RequestParam(name = "user_login") String user_login,
                                   @RequestParam(name = "user_password") String user_password) {
        List<Clients> cl = clientsDAO.getAllClientsByValue("client_login", user_login);
        if (cl.size() != 1) {
            return "redirect:/signin_client";
        }
        if (!cl.get(0).getClient_password().equals(user_password)) {
            return "redirect:/signin_client";
        }
        mode = 2;
        user_id = cl.get(0).getId();
        return "redirect:/services";
    }

    @PostMapping("/signin_as_employee")
    public String signin_as_employee(@RequestParam(name = "employee_login") String user_login,
                                     @RequestParam(name = "employee_password") String user_password) {
        List<Staff> sl = staffDAO.getAllStaffByValue("employee_login", user_login);
        if (sl.size() != 1) {
            return "redirect:/signin_client";
        }
        if (!sl.get(0).getEmployee_password().equals(user_password)) {
            return "redirect:/signin_client";
        }
        if (sl.get(0).getEmployee_is_admin() == 1L) {
            mode = 0;
        } else {
            mode = 1;
        }
        user_id = sl.get(0).getId();
        return "redirect:/services";
    }
}
