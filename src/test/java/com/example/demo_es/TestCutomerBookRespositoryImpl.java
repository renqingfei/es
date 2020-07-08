package com.example.demo_es;

import com.example.demo_es.repository.CutomerAddressRespository;
import com.example.demo_es.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoEsApplication.class)
public class TestCutomerBookRespositoryImpl {
    @Autowired
    private CutomerAddressRespository cutomerBookRespository;

    //Term查询
    @Test
    public void test1() {
        List<Address> addresses = cutomerBookRespository.TermByName("我是宝宝");
        for (Address aa : addresses) {
            System.out.println(aa);
        }
    }

    //Term查询指定返回值
    @Test
    public void test2() {
        List<Address> books = cutomerBookRespository.TermByNameBySources("333");
        for (Address book : books) {
            System.out.println(book);
        }
    }


    //Term查询指定返回值 排序
    @Test
    public void test3() {
        List<Address> books = cutomerBookRespository.TermByNameBySourcesByOrder("user_id", "大飞的春天");
        for (Address book : books) {
            System.out.println(book);
        }
    }

    //    ////Term查询指定返回值 排序  分页
//    @Test
//    public  void   test4(){
//        List<Address> books = cutomerBookRespository.TermByNameBySourcesByOrderByPage(2,2,"price","西游记");
//        for (Address book : books) {
//            System.out.println(book);
//        }
//    }
//    ////Term查询指定返回值 排序  分页
//    @Test
//    public  void   test5(){
//        List<Address> books = cutomerBookRespository.TermByNameBySourcesByOrderByPageByFilter("不错",0,2,"price","西游记");
//        for (Address book : books) {
//            System.out.println(book);
//        }
//    }
//
//    ////Term查询  分页  高亮
    @Test
    public void test6() {
        List<Address> addresses = cutomerBookRespository.TermByHighLightByPage(0, 1, "大飞的春天");
        for (Address book : addresses) {
            System.out.println("--------------------"+book);
        }
    }

}
