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

    @Test
    void selections_test() {
        List<Contracts> gt = new ArrayList<>();
        gt.add(new Contracts(1L, 1L, 1L, Date.valueOf("2022-01-16"), Date.valueOf("2022-01-17"), "fkjn", 200L));
        gt.add(new Contracts(3L, 1L, 2L, Date.valueOf("2022-01-16"), Date.valueOf("2022-01-17"), "jlghgj", 250L));

        List<Contracts> first_answer = contractsDAO.getAllContractsByColValue("client_id", "1");
        List<Contracts> second_answer = contractsDAO.getAllContractsByColRange("date_of_completition", "2022-01-16", "2022-01-18");
        assertTrue(first_answer.size() == gt.size() && first_answer.containsAll(gt) && gt.containsAll(first_answer));
        assertTrue(second_answer.size() == gt.size() && second_answer.containsAll(gt) && gt.containsAll(second_answer));
    }

    @Test
    void insertion_and_deleting_test() {
        Contracts new_contract_a = new Contracts(33L, 1L, 2L, Date.valueOf("2022-01-14"), Date.valueOf("2022-01-19"), "jlghgj", 250L);
        Contracts new_contract_b = new Contracts(34L, 1L, 2L, Date.valueOf("2022-01-13"), Date.valueOf("2022-01-20"), "jlghgj", 250L);
        Contracts new_contract_c = new Contracts(35L, 1L, 2L, Date.valueOf("2022-01-12"), Date.valueOf("2022-01-21"), "jlghgj", 250L);
        List<Contracts> contracts_list_new = new ArrayList<Contracts>();
        contracts_list_new.add(new_contract_a);
        contracts_list_new.add(new_contract_b);
        contracts_list_new.add(new_contract_c);
        contractsDAO.saveCollection(contracts_list_new);

        List<Contracts> check_a = contractsDAO.getAllContractsByColValue("id", "33");
        List<Contracts> check_b = contractsDAO.getAllContractsByColValue("id", "34");
        List<Contracts> check_c = contractsDAO.getAllContractsByColValue("id", "35");

        assertEquals(check_a.size(), 1);
        assertEquals(check_b.size(), 1);
        assertEquals(check_c.size(), 1);

        assertEquals(check_a.get(0), new_contract_a);
        assertEquals(check_b.get(0), new_contract_b);
        assertEquals(check_c.get(0), new_contract_c);

        contractsDAO.delete(new_contract_a);
        contractsDAO.delete(new_contract_b);
        contractsDAO.delete(new_contract_c);

        List<Contracts> deleted_a = contractsDAO.getAllContractsByColValue("id", "33");
        List<Contracts> deleted_b = contractsDAO.getAllContractsByColValue("id", "34");
        List<Contracts> deleted_c = contractsDAO.getAllContractsByColValue("id", "35");

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
