package com.plugin.test;

import com.artemis.WorldConfiguration;
import com.badlogic.gdx.Gdx;
import com.plugin.test.processors.TurretController;
import com.plugin.test.processors.TurretSpawner;
import com.plugin.test.processors.TurretUpdater;
import io.piotrjastrzebski.modularecs.plugin.api.GameMod;
import net.mountainblade.modular.annotations.Implementation;

/**
 * Created by PiotrJ on 24/09/15.
 */
@Implementation(authors = "PiotrJ", version = "0.1")
public class TurretMod implements GameMod {
	private final static String TAG = TurretMod.class.getSimpleName();

	public TurretMod () {

	}

	@Override public void initialize (WorldConfiguration config) {
		Gdx.app.log(TAG, "initialize!");
		config.setSystem(new TurretSpawner());
		config.setSystem(new TurretController());
		config.setSystem(new TurretUpdater());
	}

	@Override public String getName () {
		return "Test mod 1 - turret!";
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

	public static void main (String[] arg) {
		System.out.println("Dummy main!");
	}
}
