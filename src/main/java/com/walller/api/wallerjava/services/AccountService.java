package com.walller.api.wallerjava.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.walller.api.wallerjava.Models.AccountEntity;
import com.walller.api.wallerjava.Models.OperationStatus;
import com.walller.api.wallerjava.Models.TransactionEntity;
import com.walller.api.wallerjava.Repositories.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Boas práticas para implementação dos serviços
 */
interface AccountServiceInterface {

    /**
     * Obtem os valores de entrada e saída da conta e retorna a conta com esse
     * balanço
     * 
     * @param id id da conta
     * @throws Exception
     * @returns
     */
    AccountEntity getAccountBalance(Integer id) throws Exception;

    /**
     * Obtém a conta do usuário (caso exista, senão lança uma exception)
     * 
     * @param id
     * @return
     */
    AccountEntity getUserAccount(Integer id) throws Exception;

    /**
     * Add conta do usuário na database
     * 
     * @param account
     * @return
     */
    AccountEntity addUserAccount(AccountEntity account);

    /**
     * Método chamado caso seja usado o construtor passando o id
     * 
     * @return account
     * @throws Exception
     */
    AccountEntity getUserAccount() throws Exception;

    /**
     * Atualiza account repo
     * 
     * @param accountTotal
     * @param totalWithdrawal
     * @param accountTotalNoYieldRate
     * @return
     */
    AccountEntity updateAccount(float accountTotal, float totalWithdrawal, float accountTotalNoYieldRate, Integer id);

}

@Service
public class AccountService implements AccountServiceInterface {
    @Autowired
    private AccountRepository accountRepo;
    Integer id;

    public AccountService(Integer id) {
        this.id = id;
    }

    public AccountService() {
    }

    @Override
    public AccountEntity getAccountBalance(Integer id) throws Exception {
        AccountEntity acc = this.getUserAccount(id);

        List<TransactionEntity> transactions = acc.getTransactions();

        // Faz a soma das transações
        float accountTotal = 0, totalWithdrawal = 0, accountTotalNoYieldRate = 0;
        for (TransactionEntity trans : transactions) {
            if (trans.getStatus().equals(OperationStatus.DEPOSIT)) {
                // total é rendimento - taxas inerentes à conta
                accountTotal += trans.getAmount() + trans.getAmount() * trans.getYieldRate()
                        - acc.getAccountTotal() * acc.getFees();
                accountTotalNoYieldRate += trans.getAmount();
            } else {
                totalWithdrawal += trans.getAmount();
                accountTotal -= trans.getAmount();
                accountTotalNoYieldRate -= trans.getAmount();
            }
        }
        // Faz um update de valores na conta e retorna valores atualizados
        return this.updateAccount(accountTotal, totalWithdrawal, accountTotalNoYieldRate, id);
    }

    @Override
    public AccountEntity updateAccount(float accountTotal, float totalWithdrawal, float accountTotalNoYieldRate,
            Integer id) {
        AccountEntity acc = accountRepo.findById(id).get();
        acc.setAccountTotal(accountTotal);
        acc.setTotalWithdrawal(totalWithdrawal);
        acc.setAccountTotalNoYieldRate(accountTotalNoYieldRate);
        return accountRepo.save(acc);
    }

    @Override
    public AccountEntity addUserAccount(AccountEntity account) {
        return accountRepo.save(account);
    }

    @Override
    public AccountEntity getUserAccount(Integer id) throws Exception {
        return accountRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found!"));
    }

    @Override
    public AccountEntity getUserAccount() throws Exception {
        return accountRepo.findById(this.id).orElseThrow(() -> new EntityNotFoundException("Account not found!"));
    }

}
