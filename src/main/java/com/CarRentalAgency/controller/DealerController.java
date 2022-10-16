package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Dealer;
import com.CarRentalAgency.exception.NoSuchElementException;
import com.CarRentalAgency.exception.AlreadyExistsException;
import com.CarRentalAgency.services.DealerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/dealer")
public class DealerController {

    private final DealerServiceImpl dealerServiceService;

    // done
    @GetMapping(value = "/list")
    public List<Dealer> dealerList() {
        List<Dealer> dealerList = dealerServiceService.findAllDealer();
        if (dealerList.isEmpty())
            throw new NoSuchElementException("THERE IS NO DEALERS IN THE DATA BASE.");
        return dealerList;
    }

    @PostMapping(value = "/save")
    public Dealer addDealer(@RequestBody Dealer dealer) throws AlreadyExistsException {
        return dealerServiceService.saveDealer(dealer);
    }

    @GetMapping(value = "/get/{id}")
    public Dealer findDealerByID(@PathVariable Long id) {
        return dealerServiceService.findDealerById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String DeleteDealerByID(@PathVariable Long id) throws NoSuchElementException {
        dealerServiceService.deleteDealerById(id);
        return "Deleted Successfully ;) ";
    }

    @PutMapping(value = "/update/{id}")
    public Dealer updateDealer(@PathVariable Long id, @RequestBody @Valid Dealer dealer)
            throws NoSuchElementException, AlreadyExistsException {
        return dealerServiceService.updateDealer(id, dealer);
    }

}
