package edu.todobot.bot.data;

import edu.todobot.bot.config.Configurations;
import edu.todobot.bot.factory.interfaces.ActionsFactory;
import edu.todobot.bot.factory.interfaces.RequestActionsFactory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Component
public class ActionsData implements Configurations {

    private final List<ActionsFactory> actionsFactories;
    private final List<RequestActionsFactory> requestActionsFactories;

    private final Map<String, ActionsFactory> actionsFactoryMap = new ConcurrentHashMap<>();
    private final Map<String, RequestActionsFactory> requestActionsFactoryMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void initAct() {
        actionsFactories.forEach(f -> f.getCommand().forEach(c -> actionsFactoryMap.put(c, f)));
    }
    @PostConstruct
    public void initReq() {
        requestActionsFactories.forEach(f -> f.getCommand().forEach(c -> requestActionsFactoryMap.put(c, f)));
    }

    public RequestActionsFactory getRequestActionsFactory(String command) {
        return requestActionsFactoryMap.get(command);
    }

    public ActionsFactory getActionsFactory(String command) {
        return actionsFactoryMap.get(command);
    }
}