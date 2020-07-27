package com.example.demo_es.service;

import com.example.demo_es.common.enums.ExceptionEnums;
import com.example.demo_es.common.exception.EsException;
import com.example.demo_es.dao.AddressMapper;
import com.example.demo_es.repository.AddressRepository;
import com.example.demo_es.entity.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
        if(save==null&&insert==0){
            throw new EsException(ExceptionEnums.ADD_ADDRESS_ER);
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

    public List<Address> findByNameOrPhoneOrLocalOrZip_code(String name,String phone,String local,String zip_code){
        List<Address> byNameOrPhoneOrLocalOrZip_code = mapper.findByNameOrPhoneOrLocalOrZip_code(name, phone, local, zip_code);
        return byNameOrPhoneOrLocalOrZip_code;
    }

    public int updateAddress(Address address){
        int i = mapper.updateAddress(address);
        Address save = addressdao.save(address);
        if(save==null&&i!=1){
            throw new RuntimeException("添加失败");
        }else {
            return i;
        }
    }
    public int deleteAddress(int id){
        int i = mapper.deleteAddress(id);
        addressdao.deleteById(id);
        if(i==0){
            throw new RuntimeException("修改失败");
        }else {
            return i;
        }
    }


}
