package io.piotrjastrzebski.modularecs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import io.piotrjastrzebski.modularecs.game.ModArtScreen;
import io.piotrjastrzebski.modularecs.plugin.api.GameMod;
import net.mountainblade.modular.Module;
import net.mountainblade.modular.ModuleManager;
import net.mountainblade.modular.impl.DefaultModuleManager;

import java.net.URI;
import java.util.Collection;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class ModSelectScreen extends BaseScreen {
	private final static String TAG = ModSelectScreen.class.getSimpleName();

	private ModuleManager moduleManager;
	private Array<GameMod> mods;
	public ModSelectScreen (ModularECSGame game) {
		super(game);
		mods = new Array<>();
		moduleManager = new DefaultModuleManager();

		// TODO why loading raw .class doesnt work? :( fails with shading exception
//		loadInternal(mods, "classes/");
		loadInternal(mods, "plugins/");
		game.setScreen(new ModArtScreen(game, mods));
	}

	private void loadInternal(Array<GameMod> mods, String path) {
		// Note: this feels like a bad idea, breaks in dist
		load(mods, Gdx.files.internal(path).file().toURI());
	}

	private void load(Array<GameMod> mods, URI uri) {
		Collection<Module> loaded = moduleManager.loadModules(uri);
		Gdx.app.log(TAG, "loaded " + loaded.size() + " module(s) from " + uri);
		// TODO wrap GameMod in something that allows disabling, order etc
		for (Module module : loaded) {
			if (module instanceof GameMod) {
				mods.add((GameMod)module);
			}
		}
	}
}
