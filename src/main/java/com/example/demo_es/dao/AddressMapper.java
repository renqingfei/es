package com.example.demo_es.dao;

import com.example.demo_es.entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    public int insert(Address address);
    public List<Address> selectAll();
    public int updateAddress(Address address);
    public int deleteAddress(int id);
    public List<Address> findByNameOrPhoneOrLocalOrZip_code(String name,String phone,String local,String zip_code);
    Address selectName(String name);
}
