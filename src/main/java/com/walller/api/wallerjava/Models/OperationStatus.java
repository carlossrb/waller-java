package com.walller.api.wallerjava.Models;

public enum OperationStatus {
    DEPOSIT(1), PAYMENT(2), WITHDRAW(3);

    private final int val;

    private OperationStatus(int val) {
        this.val = val;
    }
}
