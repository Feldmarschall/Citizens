package com.citizens.npctypes.questers.questtypes;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.event.entity.EntityDeathEvent;

import com.citizens.npctypes.questers.objectives.Objectives.ObjectiveCycler;
import com.citizens.npctypes.questers.quests.QuestIncrementer;
import com.citizens.resources.npclib.HumanNPC;

public class HuntQuest extends QuestIncrementer {
	public HuntQuest(HumanNPC npc, Player player, String questName,
			ObjectiveCycler objectives) {
		super(npc, player, questName, objectives);
	}

	@Override
	public void updateProgress(Event event) {
		if (event instanceof EntityDeathEvent) {
			EntityDeathEvent ev = (EntityDeathEvent) event;
			if (ev.getEntity() instanceof Monster
					|| ev.getEntity() instanceof Creature) {
				this.getProgress().incrementCompleted(1);
			}
		}
	}

	@Override
	public boolean isCompleted() {
		return this.getProgress().getAmount() >= this.objective.getAmount();
	}

	@Override
	public Type[] getEventTypes() {
		return new Type[] { Type.ENTITY_DEATH };
	}
}