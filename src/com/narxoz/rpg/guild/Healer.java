package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for wounds, potions, and recovery plans.
 */
public class Healer extends GuildMember {

    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void prepareAid(String topic, String payload) {
        // TODO: send a healing message through the mediator.
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        // TODO: react to a guild-hall message without calling another colleague directly.
        System.out.println("[Healer " + getName() + "] received " + topic + " from " + from.getName() + ": " + payload);
    }
}
