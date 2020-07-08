package com.example.demo_es.repository;

import com.example.demo_es.entity.Address;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AddressRepository extends ElasticsearchRepository<Address,Integer> {
    public Address findByName(String name);
    public Address findByNameAndPhone(String name,String phone);
    public List<Address> findByNameLike(String name);
    public List<Address> findByNameOrPhone(String name,String phone);
    public List<Address> findByIdBetween(Integer min,Integer max);
    public List<Address> findByNameNot(String name);
    public List<Address> findByNameEndingWith(String name);


}
