package com.plugin2.test;

import com.artemis.WorldConfiguration;
import com.badlogic.gdx.Gdx;
import io.piotrjastrzebski.modularecs.plugin.api.GameMod;
import net.mountainblade.modular.annotations.Implementation;

/**
 * Created by PiotrJ on 24/09/15.
 */
@Implementation(authors = "PiotrJ", version = "0.12")
public class TestMod2 implements GameMod {
	private final static String TAG = TestMod2.class.getSimpleName();

	public TestMod2 () {

	}

	@Override public void initialize (WorldConfiguration config) {
		Gdx.app.log(TAG, "initialize!");
	}
}
