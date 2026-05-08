package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Topic-based mediator for the Adventurers' Guild war council.
 */
public class GuildHall implements GuildMediator {

    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();

    @Override
    public void register(GuildMember member) {
        // TODO: add the member to the topic lists it should receive.
        if (member instanceof Quartermaster) {
            addSubscriber("supply", member);
            addSubscriber("reward", member);
        } else if (member instanceof Scout) {
            addSubscriber("scout", member);
            addSubscriber("danger", member);
        } else if (member instanceof Healer) {
            addSubscriber("heal", member);
            addSubscriber("danger", member);
        } else if (member instanceof Captain) {
            addSubscriber("command", member);
            addSubscriber("reward", member);
        } else if (member instanceof Loremaster) {
            addSubscriber("lore", member);
            addSubscriber("curse", member);
        }
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        // TODO: notify registered members for the topic without direct colleague calls.
        String sender = from == null ? "System" : from.getName();
        System.out.println("\n[GuildHall] topic=" + topic + ", from=" + sender);
        for (GuildMember member : subscribersFor(topic)) {
            if (member != from) {
                member.receive(topic, from == null ? member : from, payload);
            }
        }
    }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
}
