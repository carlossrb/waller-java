package com.walller.api.wallerjava.Models;
import java.sql.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;
import java.util.Objects;

@Table(name = "Account")
@Entity
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    private String accountNumber;

    @Column()
    private String userEmail;

    @Column()
    private String userName;

    @Column()
    private float accountTotal;

    @Column()
    private float totalWithdrawal;

    @Column()
    private float accountTotalNoYieldRate;

    @Column()
    private float fees;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    // Relacionamento com a tabela de transactions em um conta -> várias transaçoes
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<TransactionEntity> transactions;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AccountEntity)) {
            return false;
        }
        AccountEntity accountEntity = (AccountEntity) o;
        return Objects.equals(id, accountEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getAccountTotal() {
        return this.accountTotal;
    }

    public void setAccountTotal(float accountTotal) {
        this.accountTotal = accountTotal;
    }

    public float getTotalWithdrawal() {
        return this.totalWithdrawal;
    }

    public void setTotalWithdrawal(float totalWithdrawal) {
        this.totalWithdrawal = totalWithdrawal;
    }

    public float getAccountTotalNoYieldRate() {
        return this.accountTotalNoYieldRate;
    }

    public void setAccountTotalNoYieldRate(float accountTotalNoYieldRate) {
        this.accountTotalNoYieldRate = accountTotalNoYieldRate;
    }

    public float getFees() {
        return this.fees;
    }

    public void setFees(float fees) {
        this.fees = fees;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<TransactionEntity> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }

}
