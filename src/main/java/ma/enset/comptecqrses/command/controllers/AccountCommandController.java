package ma.enset.comptecqrses.command.controllers;

import lombok.AllArgsConstructor;
import ma.enset.comptecqrses.commonapi.commands.CreateAccountCommand;
import ma.enset.comptecqrses.commonapi.dtos.CreateAccountRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/account")
@AllArgsConstructor
public class AccountCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;
    @PostMapping("/create")
    public  CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO createAccountDTO){
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                createAccountDTO.getInitialBalance(),
                createAccountDTO.getCurrency()
        ));
        return commandResponse;
    }

    @GetMapping("/eventstore/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return  eventStore.readEvents(accountId).asStream();
    }
}