package com.example.demo_es.repository;

import com.example.demo_es.entity.Address;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends ElasticsearchRepository<Address,Integer> {
     Address findByName(String name);
     Address findByNameAndPhone(String name,String phone);
     List<Address> findByNameLike(String name);
     List<Address> findByNameOrPhone(String name,String phone);
     List<Address> findByIdBetween(Integer min,Integer max);
     List<Address> findByNameNot(String name);
     List<Address> findByNameEndingWith(String name);
     List<Address> findByNameOrPhoneOrLocalOrZip_code(String name,String phone,String local,String zip_code);


}
