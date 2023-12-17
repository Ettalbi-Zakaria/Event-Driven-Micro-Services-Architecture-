package ma.enset.comptecqrses.commonapi.commands;

import lombok.Getter;

public class CreateAccountCommand extends BaseCommande<String>{
    @Getter
    private double initialBalance;
    @Getter
    private String curreny;
    public CreateAccountCommand(String id, double initialBalance, String curreny) {
        super(id);
        this.initialBalance = initialBalance;
        this.curreny = curreny;
    }
}
