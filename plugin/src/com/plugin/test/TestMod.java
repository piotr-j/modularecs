package com.plugin.test;

import com.artemis.WorldConfiguration;
import com.badlogic.gdx.Gdx;
import io.piotrjastrzebski.modularecs.plugin.api.GameMod;
import net.mountainblade.modular.annotations.Implementation;

/**
 * Created by PiotrJ on 24/09/15.
 */
@Implementation(authors = "PiotrJ", version = "0.1")
public class TestMod implements GameMod {
	private final static String TAG = TestMod.class.getSimpleName();

	public TestMod () {

	}

	@Override public void initialize (WorldConfiguration config) {
		Gdx.app.log(TAG, "initialize!");
	}

	@Override public String getName () {
		return "Test mod 1!";
	}

	@Override public String getDescription () {
		return "This is first test mod";
	}

	@Override public String getVersionString () {
		return "0.1";
	}

	@Override public int getVersion () {
		return 1;
	}
}
