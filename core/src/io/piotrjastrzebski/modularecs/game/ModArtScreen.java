package io.piotrjastrzebski.modularecs.game;

import com.artemis.BaseSystem;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.Gdx;
import io.piotrjastrzebski.modularecs.GameScreen;
import io.piotrjastrzebski.modularecs.ModularECSGame;
import net.mountainblade.modular.Module;
import net.mountainblade.modular.impl.DefaultModuleManager;

import java.net.URI;
import java.util.Collection;

/**
 * What do we want from plugins?
 * - custom components
 * - custom systems
 * - injection of existing stuff
 * - hotish reloading
 * nice to have
 * - override existing systems? a lot o problems with that
 * - sand box plugins, not net access, file write etc
 *
 * Created by EvilEntity on 23/09/2015.
 */
public class ModArtScreen extends GameScreen {
	private final static String TAG = ModArtScreen.class.getSimpleName();

	public static final String MODULE_MANAGER = "module-manager";

	public ModArtScreen (ModularECSGame game) {
		super(game);
	}

	DefaultModuleManager moduleManager;
	@Override protected void preInit (WorldConfiguration config) {
		// add our stuff + stuff from plugins

		moduleManager = new DefaultModuleManager();
		config.register(MODULE_MANAGER, moduleManager);
		// TODO why loading raw .class doesnt work? :( fails with shading exception
//		loadInternal(config, "classes/");
		loadInternal(config, "plugins/");

	}

	private void loadInternal(WorldConfiguration config, String path) {
		// Note: this feels like a bad idea, will probably break in dist
		load(config, Gdx.files.internal(path).file().toURI());
	}

	private void load(WorldConfiguration config, URI uri) {
		Collection<Module> loaded = moduleManager.loadModules(uri);
		Gdx.app.log(TAG, "loaded " + loaded.size() + " module(s) from " + uri);
		for (Module module : loaded) {
			if (module instanceof BaseSystem) {
				config.setSystem((BaseSystem)module);
			}
		}
	}

	@Override protected void postInit () {

	}
}
