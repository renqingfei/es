package com.example.demo_es.repository;

import com.example.demo_es.entity.Address;

import java.util.List;

public interface CutomerAddressRespository {
    //自定义Term查询
    public List<Address>  TermByName(String name);
   //自定义Term查询 并且指定返回字段                   //可变长参数
    public List<Address> TermByNameBySources(String name, String... sources);
   //自定义Term查询 并且指定返回字段 并排序      //根据哪一个属性进行排序
    public List<Address> TermByNameBySourcesByOrder(String filed, String name, String... sources);
//    //自定义Term查询 并且指定返回字段 并排序  分页           页号          每页展示的条数
//    public List<Address> TermByNameBySourcesByOrderByPage(Integer page, Integer size, String filed, String name, String... sources);
//    //自定义Term查询 并且指定返回字段 并分页  过滤器              //过滤需要的参数
//    public List<Address> TermByNameBySourcesByOrderByPageByFilterer(String user_id, Integer page, Integer size, String filed, String name, String... sources);
       //  Term查询   高亮   分页
   public List<Address>  TermByHighLightByPage(Integer page, Integer size, String name);
}
