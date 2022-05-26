package com.filashkov.webprak.DAO;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.filashkov.webprak.models.Contracts;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class ContractsDAOTest {

    @Autowired
    private ContractsDAO contractsDAO;
    @Autowired
    private SessionFactory sessionFactory;

    // Выбор из таблицы контрактов.
    // Проверяется работа двух методов: выбор по значению и выбор по принадлежности промежутку (например, по дате или по цене)
    // Проводятся на уже заполненный базе данных
    @Test
    void selections_test() {
        List<Contracts> gt = new ArrayList<>();
        gt.add(new Contracts(1L, 1L, 1L, Date.valueOf("2022-01-16"), Date.valueOf("2022-01-17"), "fkjn", 200L));
        gt.add(new Contracts(3L, 1L, 2L, Date.valueOf("2022-01-16"), Date.valueOf("2022-01-17"), "jlghgj", 250L));

        List<Contracts> should_be_gt0 = contractsDAO.getAllContractsByColRange("contract_description", "a", "h");
        assertEquals(1, should_be_gt0.size());
        assertEquals(gt.get(0), should_be_gt0.get(0));

        List<Contracts> should_be_gt1 = contractsDAO.getAllContractsByColRange("real_cost", "249", "251");
        assertEquals(1, should_be_gt1.size());
        assertEquals(gt.get(1), should_be_gt1.get(0));

        List<Contracts> first_answer = contractsDAO.getAllContractsByColValue("client_id", "1");
        List<Contracts> second_answer = contractsDAO.getAllContractsByColRange("date_of_completion", "2022-01-16", "2022-01-18");
        assertTrue(first_answer.size() == gt.size() && first_answer.containsAll(gt) && gt.containsAll(first_answer));
        assertTrue(second_answer.size() == gt.size() && second_answer.containsAll(gt) && gt.containsAll(second_answer));
    }

    @Test
    void insertion_and_deleting_test() {
        Contracts new_contract_a = new Contracts(null, 1L, 2L, Date.valueOf("2020-01-14"), Date.valueOf("2022-01-19"), "jlghgj", 250L);
        Contracts new_contract_b = new Contracts(null, 1L, 2L, Date.valueOf("2022-01-13"), Date.valueOf("2022-01-20"), "fifth", 500L);
        Contracts new_contract_c = new Contracts(null, 1L, 2L, Date.valueOf("2022-01-12"), Date.valueOf("2022-01-21"), "six", 600L);
        List<Contracts> contracts_list_new = new ArrayList<Contracts>();
        contracts_list_new.add(new_contract_a);
        contracts_list_new.add(new_contract_b);
        contracts_list_new.add(new_contract_c);
        contractsDAO.saveCollection(contracts_list_new);

        List<Contracts> check_a = contractsDAO.getAllContractsByColValue("beginning_date", "2020-01-14");
        List<Contracts> check_b = contractsDAO.getAllContractsByColValue("contract_description", "fifth");
        List<Contracts> check_c = contractsDAO.getAllContractsByColValue("real_cost", "600");

        assertEquals(1, check_a.size());
        assertEquals(1, check_b.size());
        assertEquals(1, check_c.size());

        assertEquals(check_a.get(0), new_contract_a);
        assertEquals(check_b.get(0), new_contract_b);
        assertEquals(check_c.get(0), new_contract_c);

        contractsDAO.delete(new_contract_a);
        contractsDAO.delete(new_contract_b);
        contractsDAO.deleteById(new_contract_c.getId());

        List<Contracts> deleted_a = contractsDAO.getAllContractsByColValue("beginning_date", "2020-01-14");
        List<Contracts> deleted_b = contractsDAO.getAllContractsByColValue("real_cost", "500");
        List<Contracts> deleted_c = contractsDAO.getAllContractsByColValue("contract_description", "six");

        assertEquals(deleted_a.size(), 0);
        assertEquals(deleted_b.size(), 0);
        assertEquals(deleted_c.size(), 0);
    }

    @Test
    void updating_test() {
        List<Contracts> would_be_updated_list = contractsDAO.getAllContractsByColValue("client_id", "2");
        assertEquals(would_be_updated_list.size(), 1);
        Contracts would_be_updated = would_be_updated_list.get(0);
        Contracts original = would_be_updated;
        would_be_updated.setDate_of_completion(Date.valueOf("2022-02-26"));
        contractsDAO.update(would_be_updated);
        List<Contracts> updated_list = contractsDAO.getAllContractsByColValue("client_id", "2");
        Contracts updated = updated_list.get(0);
        assertEquals(updated, would_be_updated);
        updated.setDate_of_completion(Date.valueOf("2022-02-25"));
        contractsDAO.update(updated);
        List<Contracts> should_be_original_list = contractsDAO.getAllContractsByColValue("client_id", "2");
        Contracts should_be_original = should_be_original_list.get(0);
        assertEquals(original, should_be_original);
    }

    @Test
    void simple_test() {
        // List<Contracts> would_be_updated_list = contractsDAO.getAllContractsByColValue("real_cost", "200");
        // assertEquals(would_be_updated_list.size(), 2);
        Contracts contracts = contractsDAO.getById(2L);
        assertEquals(2L, contracts.getClient_id());
    }
}
