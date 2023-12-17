package ma.enset.comptecqrses.command.aggregates;


import ma.enset.comptecqrses.commonapi.commands.CreateAccountCommand;
import ma.enset.comptecqrses.commonapi.enums.AccountStatus;
import ma.enset.comptecqrses.commonapi.events.AccountActivatedEvent;
import ma.enset.comptecqrses.commonapi.events.AccountCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus accountStatus;

    //required
    public AccountAggregate(){
    }

    //pour recevoir la commande
    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand){
        if(createAccountCommand.getInitialBalance()<0) throw new RuntimeException("Balance ......");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getInitialBalance(),
                createAccountCommand.getCurreny()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.accountId = event.getId();
        this.balance = event.getInitialBalance();
        this.currency = event.getCurreny();;
        this.accountStatus = AccountStatus.CREATED;
        //une fois l'etat de l'app et mutte on peut ajouter un autre evenment
        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                AccountStatus.ACTIVATED
        ));
    }

    @EventHandler
    public void on(AccountActivatedEvent event){
        this.accountStatus = event.getAccountStatus();
    }
}
