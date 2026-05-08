package com.narxoz.rpg.quest;

import java.util.List;

/**
 * Traverses quests in arrival order.
 */
public class OrderedQuestIterator implements QuestIterator {

    private final List<Quest> snapshot;
    private int cursor;

    public OrderedQuestIterator(QuestLog questLog) {
        this.snapshot = questLog.snapshot();
        this.cursor = 0;
    }

    @Override
    public boolean hasNext() {
        // TODO: return true while the cursor still points at an unread quest.
        return cursor < snapshot.size();
    }

    @Override
    public Quest next() {
        // TODO: return the current quest and advance the cursor.
        return snapshot.get(cursor++);
    }
}
