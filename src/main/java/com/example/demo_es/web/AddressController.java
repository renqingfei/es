package com.example.demo_es.web;

import com.example.demo_es.common.enums.ExceptionEnums;
import com.example.demo_es.common.vo.Result;
import com.example.demo_es.entity.Address;
import com.example.demo_es.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
@Api(value = "地址|AddressController")
public class AddressController {
   @Autowired
    private AddressService service;
    @GetMapping("/insert")
    @ApiOperation(value = "添加地址",notes = "添加地址")
    @CacheEvict(cacheNames = "address",key = "'address'")
    public Result insert(Address address){
        int insert = service.insert(address);
        return new Result(ExceptionEnums.ADD_ADDRESS_OK.getCode(),ExceptionEnums.ADD_ADDRESS_OK.getMsg(),insert);
    }


    @GetMapping("/selectAll")
    @ApiOperation(value = "查询所有添加缓存",notes = "查询所有添加缓存")
    @Cacheable(cacheNames = "address",key = "'address'")
    public List<Address> selectAll(){
        List<Address> addresses = service.selectAll();
        return addresses;
    }
    @GetMapping("/selectAlles")
    @ApiOperation(value = "查询所有入es",notes = "查询所有入es")
    public boolean selectAlles(){
        boolean b = service.selectAlles();
        return b;
    }
    @GetMapping("/findByNameOrPhoneOrLocalOrZip_code")
    @ApiOperation(value = "多条件查询",notes = "多条件查询")
    public List<Address> findByNameOrPhoneOrLocalOrZip_code(String name,String phone,String local,String zip_code){
        List<Address> byNameOrPhoneOrLocalOrZip_code = service.findByNameOrPhoneOrLocalOrZip_code(name, phone, local, zip_code);
        return byNameOrPhoneOrLocalOrZip_code;
    }
    @GetMapping("/search")
    @ApiOperation(value = "模糊查询",notes = "模糊查询")
    public List<Address> search(String name){
        List<Address> addresses = service.search(name);
        return addresses;
    }
    @GetMapping("/update")
    @ApiOperation(value = "修改地址",notes = "修改地址")
    @CacheEvict(cacheNames = "address",key = "'address'")
    public int update(Address address){
        int i = service.updateAddress(address);
        return i;
    }
    @GetMapping("/delete")
    @ApiOperation(value = "删除地址",notes = "删除地址")
    @CacheEvict(cacheNames = "address",key = "'address'")
    public int delete(int id){
        int i = service.deleteAddress(id);
        return i;
    }
}

