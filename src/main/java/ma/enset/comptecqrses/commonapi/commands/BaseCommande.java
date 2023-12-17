package ma.enset.comptecqrses.commonapi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public abstract class BaseCommande<T> {
    @TargetAggregateIdentifier
    @Getter private T id;

    public BaseCommande(T id) {
        this.id = id;
    }
}
