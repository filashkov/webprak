package com.filashkov.webprak.DAO;


import ch.qos.logback.core.net.server.Client;
import com.filashkov.webprak.models.Clients;
import com.filashkov.webprak.models.Contracts;
import javassist.expr.NewArray;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

/*
Заполненные значения таблицы Clients
1	"Иванов Николай Петрович"	        89156785678	  "ivnipe@gmail.com"	"ivnipe"	"12345"
2	"Жуков Иван Сергеевич"	            89167390579	  "jukov@mail.ru"	    "jukov"	    "qwerty"
3	"Масленников Пётр Александрович"	89267489380	   "erbb@yandex.ru"	    "okcff"	    "qwerty"
*/

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class ClientsDAOTest {
    @Autowired
    private ClientsDAO clientsDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void test_getall_getbyid_update() {
        Clients gt1 = new Clients(1L, "Иванов Николай Петрович", 89156785678L, "ivnipe@gmail.com", "ivnipe", "12345");
        Clients gt2 = new Clients(2L, "Жуков Иван Сергеевич", 89167390579L, "jukov@mail.ru", "jukov", "qwerty");
        Clients gt3 = new Clients(3L, "Масленников Пётр Александрович", 89267489380L, "erbb@yandex.ru", "okcff", "qwerty");

        List<Clients> gt_all = new ArrayList<>();
        gt_all.add(gt1);
        gt_all.add(gt2);
        gt_all.add(gt3);

        List<Clients> answer_all = new ArrayList<>(clientsDAO.getAll());
        assertTrue(answer_all.size() == gt_all.size() && answer_all.containsAll(gt_all) && gt_all.containsAll(answer_all));


        Clients PetrMaslennikov = clientsDAO.getById(3L);
        assertEquals(PetrMaslennikov, gt3);


        Clients gt3_modified = new Clients(3L, "Масленников Иван Александрович", 89267489381L, "erbb@yandex.ru", "okcff", "qwerty");
        List<Clients> gt_all_modified = new ArrayList<>();
        gt_all_modified.add(gt1);
        gt_all_modified.add(gt2);
        gt_all_modified.add(gt3_modified);
        Clients IvanMaslennikov = PetrMaslennikov;
        IvanMaslennikov.setClient_fullname("Масленников Иван Александрович");
        IvanMaslennikov.setClient_contact_phone(89267489381L);
        clientsDAO.update(IvanMaslennikov);
        List<Clients> answer_all_modified = new ArrayList<>(clientsDAO.getAll());
        clientsDAO.update(gt3);
        assertTrue(answer_all_modified.size() == gt_all_modified.size()
                && answer_all_modified.containsAll(gt_all_modified)
                && gt_all_modified.containsAll(answer_all_modified));
    }

    @Test
    void test2_delete_and_save() {
        Clients gt1 = new Clients(1L, "Иванов Николай Петрович", 89156785678L, "ivnipe@gmail.com", "ivnipe", "12345");
        Clients gt2 = new Clients(2L, "Жуков Иван Сергеевич", 89167390579L, "jukov@mail.ru", "jukov", "qwerty");
        Clients gt3 = new Clients(3L, "Масленников Пётр Александрович", 89267489380L, "erbb@yandex.ru", "okcff", "qwerty");

        List<Clients> gt_all = new ArrayList<>();
        gt_all.add(gt1);
        gt_all.add(gt2);
        gt_all.add(gt3);

        List<Clients> clients_list = new ArrayList<>(clientsDAO.getAll());
        assertTrue(clients_list.size() == gt_all.size() && clients_list.containsAll(gt_all) && gt_all.containsAll(clients_list));

        clientsDAO.delete(gt2);
        clientsDAO.delete(gt3);

        List<Clients> should_be_gt1 = new ArrayList<>(clientsDAO.getAll());
        assertEquals(should_be_gt1.size(), 1);
        assertEquals(should_be_gt1.get(0), gt1);

        clientsDAO.deleteById(1L);
        List<Clients> should_be_empty = new ArrayList<>(clientsDAO.getAll());
        assertEquals(should_be_empty.size(), 0);

        clientsDAO.save(gt3);
        List<Clients> should_be_gt3 = new ArrayList<>(clientsDAO.getAll());
        assertEquals(should_be_gt3.size(), 1);
        assertEquals(should_be_gt3.get(0), gt3);
        clientsDAO.deleteById(3L);

        clientsDAO.saveCollection(gt_all);
        List<Clients> answer_all = new ArrayList<>(clientsDAO.getAll());
        assertTrue(answer_all.size() == gt_all.size() && answer_all.containsAll(gt_all) && gt_all.containsAll(answer_all));
    }

    // Тест - список всех клиентов по определённому имени и по номеру телефона
    @Test
    void get_by_name() {
        Clients gt = new Clients(1L, "Иванов Николай Петрович", 89156785678L, "ivnipe@gmail.com", "ivnipe", "12345");
        List<Clients> answer = clientsDAO.getAllClientsByValue("client_fullname", "Иванов Николай Петрович");
        assertEquals(1, answer.size());
        assertEquals(gt, answer.get(0));

        answer = clientsDAO.getAllClientsByValue("client_contact_phone", "89156785678");
        assertEquals(1, answer.size());
        assertEquals(gt, answer.get(0));
    }

    @Test
    void get_wrong_id() {
        Clients nobody = clientsDAO.getById(300000000L);
        assertEquals(null, nobody);
    }
}
