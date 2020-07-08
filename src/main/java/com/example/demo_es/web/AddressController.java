package com.example.demo_es.web;

import com.example.demo_es.entity.Address;
import com.example.demo_es.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public int insert(Address address){
        int insert = service.insert(address);
        return insert;
    }
    @GetMapping("/selectAll")
    @ApiOperation(value = "查询所有",notes = "查询所有")
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

    @GetMapping("/search")
    @ApiOperation(value = "模糊查询",notes = "模糊查询")
    public List<Address> search(String name){
        List<Address> addresses = service.search(name);
        return addresses;
    }
}

