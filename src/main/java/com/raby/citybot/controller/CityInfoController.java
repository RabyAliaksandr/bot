package com.raby.citybot.controller;

import com.raby.citybot.entity.City;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityInfoController {

    @GetMapping("")
    String hello() {
        return "Hello world";
    }
    @GetMapping("/find")
    String find() {
        return "find";
    }

    @PostMapping("")
    public void addCity(@RequestBody City city) {

    }

    @PutMapping("")
    public void update(@RequestBody City city) {

    }

    @DeleteMapping("")
    public void delete(@RequestBody long id) {

    }
}
