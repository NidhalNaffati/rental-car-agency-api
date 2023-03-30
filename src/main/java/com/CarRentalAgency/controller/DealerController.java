package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Dealer;
import com.CarRentalAgency.services.DealerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/dealers")
public class DealerController {

    private final DealerServiceImpl dealerService;


    public DealerController(DealerServiceImpl dealerService) {
        this.dealerService = dealerService;
    }

    @PostMapping
    public ResponseEntity<Dealer> saveDealer(@Valid @RequestBody Dealer dealer) {
        Dealer newDealer = dealerService.saveDealer(dealer);
        return new ResponseEntity<>(newDealer, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Dealer> updateDealer(@PathVariable Long id, @RequestBody Dealer newDealer) {
        Dealer updatedDealer = dealerService.updateDealer(id, newDealer);
        return new ResponseEntity<>(updatedDealer, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDealerById(@PathVariable Long id) {
        dealerService.deleteDealerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Dealer> findDealerById(@PathVariable Long id) {
        Dealer dealer = dealerService.findDealerById(id);
        return new ResponseEntity<>(dealer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Dealer>> findAllDealers() {
        List<Dealer> dealers = dealerService.findAllDealers();
        return new ResponseEntity<>(dealers, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<Dealer>> findDealerByName(@RequestParam String name) {
        List<Dealer> dealers = dealerService.findDealerByFirstNameIgnoreCase(name);
        return new ResponseEntity<>(dealers, HttpStatus.OK);
    }

    @GetMapping(params = "email")
    public ResponseEntity<Dealer> findDealerByEmail(@RequestParam String email) {
        Dealer dealer = dealerService.findDealerByEmail(email);
        return new ResponseEntity<>(dealer, HttpStatus.OK);
    }

}