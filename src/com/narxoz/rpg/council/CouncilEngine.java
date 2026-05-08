package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.QuestLog;
import java.util.List;

/**
 * Orchestrates a planning session that uses both Iterator and Mediator.
 */
public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        // TODO: walk questLog with at least 2 different iterators,
        //       dispatch coordinating messages through hall for each quest,
        //       and return counters (questsTraversed, messagesRouted, membersNotified).
        int traversed = 0;
        int messages = 0;

        System.out.println("\nParty members:");
        for (Hero hero : party) {
            System.out.println(hero);
        }

        System.out.println("\n=== Ordered quests ===");
        var ordered = questLog.ordered();
        while (ordered.hasNext()) {
            var quest = ordered.next();
            traversed++;
            System.out.println(quest);
            hall.dispatch("command", null, "Prepare for " + quest.getTitle());
            hall.dispatch("supply", null, "Pack supplies for " + quest.getTitle());
            messages += 2;
        }

        System.out.println("\n=== Reverse quests ===");
        var reverse = questLog.reverse();
        while (reverse.hasNext()) {
            System.out.println(reverse.next());
            traversed++;
        }

        hall.dispatch("lore", null, "Ancient curse found in ruins");
        messages++;
        return new CouncilRunResult(0, 0, 0);
    }
}
