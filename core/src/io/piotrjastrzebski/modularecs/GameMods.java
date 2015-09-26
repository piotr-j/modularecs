package io.piotrjastrzebski.modularecs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import io.piotrjastrzebski.modularecs.plugin.api.GameMod;
import net.mountainblade.modular.Module;
import net.mountainblade.modular.ModuleManager;
import net.mountainblade.modular.impl.DefaultModuleManager;

import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class GameMods implements Iterable<GameMods.Entry> {
	private final static String TAG = GameMods.class.getSimpleName();

	private Array<Entry> entries;
	private ModuleManager modules;
	public GameMods () {
		entries = new Array<>();
		modules = new DefaultModuleManager();

		// TODO why loading raw .class doesnt work? :( fails with shading exception
		// TODO try with manager.getLoader().setLoadingStrategy(ParentFirstStrategy.class); sometime
//		loadInternal(mods, "classes/");
	}

	public void load (String path) {
		load(Gdx.files.internal(path).file().toURI());
	}

	private void load(URI uri) {
		// TODO handle errors properly
		reload();
		Collection<Module> loaded = modules.loadModules(uri);
		Gdx.app.log(TAG, "loaded " + loaded.size() + " module(s) from " + uri);
		for (Module module : loaded) {
			if (module instanceof GameMod) {
				Entry entry = new Entry();
				entry.mod = (GameMod)module;
				entry.order = entries.size;
				entries.add(entry);
			}
		}
	}

	Array<Class<? extends Module>> loaded = new Array<>();
 	private void reload () {
		loaded.clear();
		for (Module module : modules.getRegistry().getModules()) {
			loaded.add(module.getClass());
		}
		if (loaded.size == 0) {
			Gdx.app.log(TAG, "nothing to reload");
			return;
		}
		modules.shutdown();
		for (Class<? extends Module> cls : loaded) {
			modules.loadModule(cls);
		}
		Gdx.app.log(TAG, "reloaded " + loaded.size + " module(s)");
	}

	@Override public Iterator<Entry> iterator () {
		return entries.iterator();
	}

	@Override public void forEach (Consumer<? super Entry> action) {
		throw new RuntimeException("Not implemented");
	}

	@Override public Spliterator<Entry> spliterator () {
		throw new RuntimeException("Not implemented");
	}

	public static class Entry {
		public GameMod mod;
		public int order;
		public boolean enabled;
	}
}
