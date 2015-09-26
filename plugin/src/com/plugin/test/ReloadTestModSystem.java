package com.plugin.test;

import com.artemis.BaseSystem;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.Gdx;
import io.piotrjastrzebski.modularecs.plugin.api.GameMod;
import net.mountainblade.modular.annotations.Implementation;

/**
 * Created by PiotrJ on 24/09/15.
 */
@Implementation(authors = "PiotrJ", version = "0.1")
public class ReloadTestModSystem extends BaseSystem implements GameMod {
	private final static String TAG = ReloadTestModSystem.class.getSimpleName();

	public ReloadTestModSystem () {

	}

	float timer;
	@Override protected void processSystem () {
		timer += world.delta;
		if (timer >= 1) {
			timer -= 1;
			Gdx.app.log("", "welp");
		}
	}

	@Override public void initialize (WorldConfiguration config) {
		Gdx.app.log(TAG, "initialize!");
		config.setSystem(this);
	}

	@Override public String getName () {
		return "Test mod 1 - test base thing?!!";
	}

	@Override public String getDescription () {
		return "This is first test mod, it adds a turret to player";
	}

	@Override public String getVersionString () {
		return "0.1";
	}

	@Override public int getVersion () {
		return 1;
	}
}
