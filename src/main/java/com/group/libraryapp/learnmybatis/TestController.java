package com.group.libraryapp.learnmybatis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    private final FruitMapper fruitMapper;

    public TestController(FruitMapper fruitMapper) {
        this.fruitMapper = fruitMapper;
    }

    @GetMapping("test/mybatis")
    public void test() {
        List<Fruit> fruitList = fruitMapper.findAll();
        System.out.println(fruitList.toString());
    }
}
