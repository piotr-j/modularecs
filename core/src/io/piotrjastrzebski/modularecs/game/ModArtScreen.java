package io.piotrjastrzebski.modularecs.game;

import com.artemis.BaseSystem;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import io.piotrjastrzebski.modularecs.GameScreen;
import io.piotrjastrzebski.modularecs.ModularECSGame;
import io.piotrjastrzebski.modularecs.plugin.api.GameMod;
import net.mountainblade.modular.Module;
import net.mountainblade.modular.impl.DefaultModuleManager;

import java.net.URI;
import java.util.Collection;

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

	private Array<GameMod> mods;

	public ModArtScreen (ModularECSGame game, Array<GameMod> mods) {
		super(game, mods);
	}

	@Override protected void preInit (WorldConfiguration config, Object... extra) {
		// this is mods that was passed in constructor
		mods = (Array<GameMod>)extra[0];
		for (GameMod mod : mods) {
			mod.initialize(config);
		}
	}

	@Override protected void postInit () {

	}
}
