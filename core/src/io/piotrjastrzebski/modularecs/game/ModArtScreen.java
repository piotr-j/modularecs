package io.piotrjastrzebski.modularecs.game;

import com.artemis.WorldConfiguration;
import io.piotrjastrzebski.modularecs.GameMods;
import io.piotrjastrzebski.modularecs.GameScreen;
import io.piotrjastrzebski.modularecs.ModularECSGame;
import io.piotrjastrzebski.modularecs.game.processors.DebugRenderer;
import io.piotrjastrzebski.modularecs.game.processors.PlayerController;
import io.piotrjastrzebski.modularecs.game.processors.PlayerMover;
import io.piotrjastrzebski.modularecs.game.processors.PlayerSpawner;

/**
 * TODO
 * What do we want from plugins?
 * - custom components
 * - custom systems
 * - injection of existing stuff
 * - hotish reloading
 * - assets from jar
 * nice to have
 * - override existing systems? a lot o problems with that
 * - sand box plugins, not net access, file write etc
 *
 * Created by EvilEntity on 23/09/2015.
 */
public class ModArtScreen extends GameScreen {
	private final static String TAG = ModArtScreen.class.getSimpleName();

	public ModArtScreen (ModularECSGame game, GameMods mods) {
		super(game, mods);
	}

	@Override protected void preInit (WorldConfiguration config, Object... extra) {
		config.setSystem(new DebugRenderer());
		config.setSystem(new PlayerSpawner());
		config.setSystem(new PlayerController());
		config.setSystem(new PlayerMover());

		// this is mods that was passed in constructor
		GameMods mods = (GameMods)extra[0];
		for (GameMods.Entry entry : mods) {
			if (entry.enabled) {
				entry.mod.initialize(config);
			}
		}
	}

	@Override protected void postInit () {

	}

	@Override public void render (float delta) {
		super.render(delta);
	}
}
