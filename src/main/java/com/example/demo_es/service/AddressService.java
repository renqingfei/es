package com.example.demo_es.service;

import com.example.demo_es.dao.AddressMapper;
import com.example.demo_es.repository.AddressRepository;
import com.example.demo_es.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
  @Autowired
    private AddressRepository addressdao;

  @Autowired
  private AddressMapper mapper;
    public int insert(Address address){
        int insert = mapper.insert(address);
        Address save = addressdao.save(address);
        if(save==null&&insert==1){
            throw new RuntimeException("添加失败");
        }else {
            return insert;
        }
    }
    public List<Address> selectAll(){
        List<Address> addresses = mapper.selectAll();
        return addresses;
    }
    public  boolean selectAlles(){
        List<Address> addresses = mapper.selectAll();
        for (Address address : addresses) {
            addressdao.save(address);
        }
        return true;
    }
    public List<Address> search(String name){
        List<Address> byNameLike = addressdao.findByNameLike(name);
        return byNameLike;
    }

    //

}
