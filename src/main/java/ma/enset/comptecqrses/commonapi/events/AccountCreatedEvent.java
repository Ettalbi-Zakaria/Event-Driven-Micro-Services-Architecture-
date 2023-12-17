package ma.enset.comptecqrses.commonapi.events;

import lombok.Getter;

public class AccountCreatedEvent extends BaseEvent<String>{
    @Getter
    private double initialBalance;
    @Getter
    private String curreny;

    public AccountCreatedEvent(String id, double initialBalance, String curreny) {
        super(id);
        this.initialBalance = initialBalance;
        this.curreny = curreny;
    }
}
