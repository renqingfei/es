package com.example.demo_es;

import com.example.demo_es.repository.AddressRepository;
import com.example.demo_es.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = DemoEsApplication.class)
@RunWith(SpringRunner.class)
public class DemoEsApplicationTests {
    @Autowired
    private AddressRepository redao;
    @Test
    public void contextLoads() {
        Address address = new Address();
        address.setId(3);
        address.setLocal("5555");
        address.setName("巴中");
        address.setPhone("123456789");
        address.setUser_id("1");
        address.setZip_code("636462");
        Address address1 = new Address(0, "333", "自己", "123456132", "1", "323246");
        Address address2 = new Address(1, "111", "飞飞天天都忙", "123456132", "1", "323246");
        Address address3 = new Address(4, "777", "自己", "123456132", "1", "323246");
        Address address4 = new Address(5, "888", "自己", "123456132", "1", "323246");
        Address address5 = new Address(5, "888中", "自中", "123456132", "1", "323246");
        redao.save(address);
        redao.save(address1);
        redao.save(address2);
        redao.save(address3);
        redao.save(address4);
        redao.save(address5);
    }
    @Test
    public void delete(){
        redao.deleteById(0);
    }
    @Test
    public void selectAll() {
        Iterable<Address> all = redao.findAll();
        for (Address address : all) {
            System.out.println("00000000000000" + address);
        }
    }
    @Test
    public void update(){
        Address address1 = new Address(0, "飞飞天天不陪我", "自己", "123456132", "1", "323246");
        redao.save(address1);
    }
    @Test
    public void getone(){
        Optional<Address> byId = redao.findById(4);
        Address address = byId.get();
        System.out.println(address);
    }
    @Test
    public void order(){
        Iterable<Address> zip_code = redao.findAll(Sort.by(Sort.Order.asc("id")));
        for (Address address : zip_code) {
            System.out.println(address);
        }
    }
    @Test
    public void page(){         //0代表第一页
        Page<Address> all = redao.findAll(PageRequest.of(0, 3,Sort.by(Sort.Order.asc("id"))));
        for (Address address : all) {
            System.out.println(address);
        }
    }
    @Test
    public void selectname(){
        Address byName = redao.findByName("我是宝宝");
        System.out.println(byName);
    }
    @Test
    public void nameandphone(){
        Address byNameAndPhone = redao.findByNameAndPhone("我是宝宝", "123456654");
        System.out.println(byNameAndPhone);
    }
    @Test
    public void like(){
        List<Address> byNameLike = redao.findByNameLike("宝");
        for (Address address : byNameLike) {
            System.out.println(address);
        }
    }
    @Test
    public void nameorphone(){
        List<Address> byNameOrPhone = redao.findByNameOrPhone("我是宝宝", "123456654");
        for (Address address : byNameOrPhone) {
            System.out.println(address);
        }
    }
    @Test
    public void IdBetween(){
        List<Address> byIdBetween = redao.findByIdBetween(1, 3);
        for (Address address : byIdBetween) {
            System.out.println(address);
        }
    }
    @Test
    public void namenot(){
        List<Address> byNameNot = redao.findByNameNot("我是宝宝");
        for (Address address : byNameNot) {
            System.out.println(address);
        }
    }
    @Test
    public void end(){
        List<Address> byNameEndingWith = redao.findByNameEndingWith("天");
        for (Address address : byNameEndingWith) {
            System.out.println(address);
        }
    }
}
