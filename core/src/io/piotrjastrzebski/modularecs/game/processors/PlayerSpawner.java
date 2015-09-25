package io.piotrjastrzebski.modularecs.game.processors;

import com.artemis.*;
import com.badlogic.gdx.Gdx;
import io.piotrjastrzebski.modularecs.game.components.*;
import io.piotrjastrzebski.modularecs.plugin.api.components.Bounds;
import io.piotrjastrzebski.modularecs.plugin.api.components.Health;
import io.piotrjastrzebski.modularecs.plugin.api.components.Player;
import io.piotrjastrzebski.modularecs.plugin.api.components.Transform;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class PlayerSpawner extends BaseSystem {
	public PlayerSpawner () {
		super();
	}
	EntitySubscription players;
	@Override protected void initialize () {
		players = world.getManager(AspectSubscriptionManager.class).get(Aspect.all(Player.class));
	}

	float timer;
	@Override protected void processSystem () {
		if (timer > 0) {
			timer -= world.delta;
		} else if (players.getEntities().size() == 0) {
			if (timer <= 0) {
				timer = 2;
				spawnPlayer();
			}
		}
	}

	private void spawnPlayer () {
		Gdx.app.log("", "Player spawned!");
		EntityEdit edit = world.createEntity().edit();
		edit.create(Player.class).setMoveSpeed(5).setRotateSpeed(90);
		edit.create(Transform.class).set(0, 0, 0);
		edit.create(Health.class).init(10);
		edit.create(Bounds.class).set(1, 2);
		edit.create(DebugTint.class).setColor(1, 0, 0);
		// todo other stuff
	}
}
