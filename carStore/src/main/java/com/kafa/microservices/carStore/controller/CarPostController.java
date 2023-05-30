package com.kafa.microservices.carStore.controller;

import com.kafa.microservices.carStore.dto.CarPostDto;
import com.kafa.microservices.carStore.service.CarPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sales")
public class CarPostController {

    @Autowired
    private CarPostService carPostService;

    @GetMapping
    public ResponseEntity<List<CarPostDto >> getCarSales(){
        return ResponseEntity.status(HttpStatus.FOUND).body(carPostService.getCarSales());
    }
    @PutMapping("/car/{id}")
    public ResponseEntity changeCarSales(@RequestBody CarPostDto carPostDto, @PathVariable String id){
        carPostService.changeCarSale(carPostDto, Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @DeleteMapping("/car/{id}")
    public ResponseEntity deleteCarSale(@PathVariable String id){
        carPostService.deleteCarSale(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
