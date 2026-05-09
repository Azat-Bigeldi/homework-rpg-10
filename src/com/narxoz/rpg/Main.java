package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import com.narxoz.rpg.quest.RewardSortedQuestIterator;

import java.util.List;

/**
 * Entry point for Homework 10 — The Adventurers' Guild: Iterator + Mediator.
 *
 * The scaffold prints the banner only; students fill in the guild demo.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");

        // 1. Create at least 2 heroes.
        // 2. Build a QuestLog with at least 5 quests of mixed priority.
        // 3. Register at least 4 GuildMembers (Quartermaster, Scout, Healer, Captain) on the GuildHall.
        // 4. Iterate the quest log with at least 2 different QuestIterator implementations.
        // 5. Dispatch coordinating messages through the mediator during quest planning.
        // 6. Run the CouncilEngine and print a final CouncilRunResult.

        Hero knight = new Hero("Arthas", 120, 20, 15);
        Hero mage = new Hero("Merlin", 80, 100, 30, 5, 200);

        List<Hero> party = List.of(knight, mage);

        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Goblin Camp", QuestPriority.LOW, 50, false));
        questLog.add(new Quest("Lost Caravan", QuestPriority.NORMAL, 120, false));
        questLog.add(new Quest("Dragon Cave", QuestPriority.URGENT, 500, true));
        questLog.add(new Quest("Haunted Ruins", QuestPriority.HIGH, 300, true));
        questLog.add(new Quest("Bandit Hunt", QuestPriority.NORMAL, 170, false));

        GuildHall hall = new GuildHall();

        Quartermaster quartermaster = new Quartermaster("Borin", hall);
        Scout scout = new Scout("Lia", hall);
        Healer healer = new Healer("Selena", hall);
        Captain captain = new Captain("Ragnar", hall);
        Loremaster loremaster = new Loremaster("Eldon", hall);

        captain.issueOrder("command", "All members gather in the guild hall");
        scout.reportRoute("danger", "Road to the ruins is full of monsters");
        healer.prepareAid("heal", "Prepare healing potions");
        quartermaster.requestSupplies("supply", "Bring extra arrows and food");
        loremaster.shareLore("lore", "The ruins hide an ancient curse");

        System.out.println("\n=== Reward sorted iterator ===");
        QuestIterator rewardIterator = questLog.rewardSorted();
        while (rewardIterator.hasNext()) {
            System.out.println(rewardIterator.next());
        }

        CouncilEngine engine = new CouncilEngine();
        System.out.println("\n" + engine.runCouncil(party, questLog, hall));
    }
}
