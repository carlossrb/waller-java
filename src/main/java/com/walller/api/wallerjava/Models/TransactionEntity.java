package com.walller.api.wallerjava.Models;

import java.sql.Date;
import java.util.Objects;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;

/**
 * Criação do model principal para cada transação executada nos controllers ou
 * services
 */
@Entity
@Table(name = "Transactions")
public class TransactionEntity {
    // Primary key da tabela sql
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private OperationStatus status;

    @Column(nullable = true, length = 10)
    private String destinationAccount;

    @Column()
    private float amount;

    @Column()
    private float yieldRate;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    // Relacionamento com a tabela de conta em que várias trasactions -> uma conta
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "Account_id")
    private AccountEntity account;

    // Getters e Setters (boilerplate) - Atributos privados hão de ter getters e
    // setters
    // Métodos poderiam ser substituidos por lib chamada lombock para gerá-los
    // automáticamente

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountEntity getAccount() {
        return this.account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public OperationStatus getStatus() {
        return this.status;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }

    public String getDestinationAccount() {
        return this.destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getYieldRate() {
        return this.yieldRate;
    }

    public void setYieldRate(float yieldRate) {
        this.yieldRate = yieldRate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TransactionEntity)) {
            return false;
        }
        TransactionEntity transactionEntity = (TransactionEntity) o;
        return this.id == transactionEntity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

}
