package com.walller.api.wallerjava.controllers;

import com.walller.api.wallerjava.Models.TransactionEntity;
import com.walller.api.wallerjava.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/transactions")
public class Transactions {

    @Autowired
    private TransactionService transService;

    @PostMapping("/deposit")
    @ResponseStatus(code = HttpStatus.CREATED)
    public TransactionEntity MakeDeposit(@RequestParam(name = "id") Integer id, @RequestBody DepositBody body) throws Exception {
        return transService.makeDeposit(id, body.getAmount());
    }

    @PostMapping("/withdrawal")
    @ResponseStatus(code = HttpStatus.CREATED)
    public TransactionEntity MakeWithdrawal(@RequestParam(name = "id") Integer id, @RequestBody WithdrawalBody body) throws Exception {
        return transService.makeWithdrawal(id, body.getAmount());
    }

    @PostMapping("/payment")
    @ResponseStatus(code = HttpStatus.CREATED)
    public TransactionEntity MakePayment(@RequestParam(name = "id") Integer id, @RequestBody PaymentBody body) throws Exception {
        return transService.makePayment(id, body.getAmount(), body.getDestinationAccount());
    }

}

class DepositBody {
    private float amount;

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(){};
}

class WithdrawalBody {
    private float amount;

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(){};
}

class PaymentBody {
    private float amount;
    private String destinationAccount;

    public float getAmount() {
        return this.amount;
    }

    public String getDestinationAccount() {
        return this.destinationAccount;
    }

    public void setAmount(){};
}