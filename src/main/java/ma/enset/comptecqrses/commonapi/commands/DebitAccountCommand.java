package ma.enset.comptecqrses.commonapi.commands;

public class DebitAccountCommand extends BaseCommande<String>{
    private double amount;
    private String currency;
    public DebitAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
