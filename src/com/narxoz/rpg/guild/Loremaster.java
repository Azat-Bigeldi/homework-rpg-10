package com.narxoz.rpg.guild;

public class Loremaster extends GuildMember {

    public Loremaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void shareLore(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Loremaster " + getName() + "] studies " + topic
                + " from " + from.getName() + ": " + payload);
    }
}
