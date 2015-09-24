package io.piotrjastrzebski.modularecs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import io.piotrjastrzebski.modularecs.plugin.api.GameMod;
import net.mountainblade.modular.Module;
import net.mountainblade.modular.ModuleManager;
import net.mountainblade.modular.impl.DefaultModuleManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
//		loadInternal(mods, "classes/");
	}

	public void load (String path) {
		load(Gdx.files.internal(path).file().toURI());
	}

	private void load(URI uri) {
		// TODO handle errors properly
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

	@Override public Iterator<Entry> iterator () {
		return entries.iterator();
	}

	@Override public void forEach (Consumer<? super Entry> action) {
		throw new NotImplementedException();
	}

	@Override public Spliterator<Entry> spliterator () {
		throw new NotImplementedException();
	}

	public static class Entry {
		public GameMod mod;
		public int order;
		public boolean enabled;
	}
}
