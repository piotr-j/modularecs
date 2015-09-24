package io.piotrjastrzebski.modularecs;

import io.piotrjastrzebski.modularecs.game.ModArtScreen;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class ModSelectScreen extends BaseScreen {
	private final static String TAG = ModSelectScreen.class.getSimpleName();

	private GameMods mods;
	public ModSelectScreen (ModularECSGame game) {
		super(game);
		mods = new GameMods();
		mods.load("plugins/");
		// TODO add ui
	}

	@Override public void render (float delta) {
		super.render(delta);
		game.setScreen(new ModArtScreen(game, mods));
	}
}
