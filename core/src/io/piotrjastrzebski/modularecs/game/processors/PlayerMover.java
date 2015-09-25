package io.piotrjastrzebski.modularecs.game.processors;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import io.piotrjastrzebski.modularecs.plugin.api.components.Bounds;
import io.piotrjastrzebski.modularecs.plugin.api.components.Player;
import io.piotrjastrzebski.modularecs.plugin.api.components.Transform;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class PlayerMover extends EntityProcessingSystem {
	public PlayerMover () {
		super(Aspect.all(Player.class, Transform.class, Bounds.class));
	}

	@Override protected void process (Entity e) {

	}
}
