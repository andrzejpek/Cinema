package com.patand.Cinema.model;

import lombok.Getter;

@Getter
public enum Ticket {
        Normal(25),Reduced(20),Wednesday(15);

        private int price;

    private Ticket(int price) {
        this.price = price;
    }
}
