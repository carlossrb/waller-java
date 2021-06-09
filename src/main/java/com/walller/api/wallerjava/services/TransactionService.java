package com.walller.api.wallerjava.services;

import com.walller.api.wallerjava.Models.TransactionEntity;
import com.walller.api.wallerjava.Repositories.TransactionRepository;
import com.walller.api.wallerjava.Models.AccountEntity;
import com.walller.api.wallerjava.Models.OperationStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Boas práticas para implementação dos serviços
 */
interface TransactionServiceInterface {

    /**
     * Realiza um depósito na sua própria conta
     * 
     * @param id
     * @param amount valor a depositar
     * @return
     * @throws Exception
     */
    TransactionEntity makeDeposit(Integer id, float amount) throws Exception;

    /**
     * Realiza uma retirada da própria conta
     * 
     * @param id
     * @param amount
     * @return
     * @throws Exception
     */
    TransactionEntity makeWithdrawal(Integer id, float amount) throws Exception;

    /**
     * Realiza um pagamento
     * 
     * @param id
     * @param amount
     * @param destinationAccount
     * @return
     * @throws Exception
     */
    TransactionEntity makePayment(Integer id, float amount, String destinationAccount) throws Exception;

}

@Service
public class TransactionService implements TransactionServiceInterface {
    private final float yieldRate = (float) 0.003; // taxa de rendimento

    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private AccountService accountService;

    public TransactionEntity makeDeposit(Integer id, float amount) throws Exception {
        AccountEntity acc = accountService.getAccountBalance(id);

        // add um depósito
        TransactionEntity entity = new TransactionEntity();
        entity.setAccount(acc);
        entity.setAmount(amount);
        entity.setStatus(OperationStatus.DEPOSIT);
        entity.setYieldRate(this.yieldRate);

        return transRepo.save(entity);
    }

    public TransactionEntity makeWithdrawal(Integer id, float amount) throws Exception {
        AccountEntity acc = accountService.getAccountBalance(id);

        if (acc.getAccountTotal() < amount) {
            throw new Exception("Insufficient funds");
        }

        // add um depósito
        TransactionEntity entity = new TransactionEntity();
        entity.setAccount(acc);
        entity.setAmount(amount);
        entity.setStatus(OperationStatus.WITHDRAW);
        entity.setYieldRate(this.yieldRate);

        return transRepo.save(entity);

    }

    public TransactionEntity makePayment(Integer id, float amount, String destinationAccount) throws Exception {
        AccountEntity acc = accountService.getAccountBalance(id);

        if (acc.getAccountTotal() < amount) {
            throw new Exception("Insufficient funds");
        }

        // add um depósito
        TransactionEntity entity = new TransactionEntity();
        entity.setAccount(acc);
        entity.setAmount(amount);
        entity.setDestinationAccount(destinationAccount);
        entity.setStatus(OperationStatus.PAYMENT);
        entity.setYieldRate(this.yieldRate);

        return transRepo.save(entity);
    }

}
