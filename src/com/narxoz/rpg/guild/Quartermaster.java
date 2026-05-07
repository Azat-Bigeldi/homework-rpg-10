package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for gear, supplies, and rewards.
 */
public class Quartermaster extends GuildMember {

    public Quartermaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void requestSupplies(String topic, String payload) {
        // TODO: send a supply-related message through the mediator.
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        // TODO: react to a guild-hall message without calling another colleague directly.
        System.out.println("[Quartermaster " + getName() + "] received " + topic + " from " + from.getName() + ": " + payload);
    }
}
