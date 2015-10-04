package io.piotrjastrzebski.modularecs.game.processors;

import com.artemis.*;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StreamUtils;
import io.piotrjastrzebski.modularecs.plugin.api.components.Asset;
import io.piotrjastrzebski.modularecs.plugin.api.components.AssetRef;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by EvilEntity on 26/09/2015.
 */
@Wire
public class Assets extends BaseEntitySystem {
	private ComponentMapper<AssetRef> mAssetRef;
	private ComponentMapper<Asset> mAsset;
	AssetManager manager;
	ObjectMap<String, Texture> textures;
	public Assets () {
		super(Aspect.all(AssetRef.class));
		// todo make dist work
		manager = new AssetManager(new JarHandleResolver("plugins/"));
		textures = new ObjectMap<>();
	}

	@Override protected void inserted (int entityId) {
		AssetRef ref = mAssetRef.get(entityId);
		Texture texture = textures.get(ref.path, null);
		if (texture == null) {
			manager.load(ref.path, Texture.class);
			manager.finishLoading();
			texture = manager.get(ref.path, Texture.class);
			textures.put(ref.path, texture);
		}
		EntityEdit edit = world.getEntity(entityId).edit();
		Asset asset = edit.create(Asset.class);
		// TODO pool?
		asset.sprite =  new Sprite(texture);
	}

	@Override protected void removed (int entityId) {
		// TODO free sprite from pool
	}

	@Override protected void processSystem () {}

	@Override protected void dispose () {
		manager.dispose();
		textures.clear();
	}

	private static class JarHandleResolver implements FileHandleResolver {
		private InternalFileHandleResolver internal;
		FileHandle jars;
		public JarHandleResolver (String dir) {
			jars = Gdx.files.internal(dir);
			internal = new InternalFileHandleResolver();
		}

		@Override public FileHandle resolve (String fileName) {
			FileHandle iHandle = internal.resolve(fileName);
			if (iHandle.exists()) {
				return iHandle;
			}

			// look in jars
			// TODO cache this
			for (FileHandle handle:jars.list()) {
				if (!handle.extension().equals("jar")) continue;
				FileHandle found = find(fileName, handle);
				if (found != null) return found;
			}
			return null;
		}

		private FileHandle find (String fileName, FileHandle jar) {
			try {
				ZipInputStream zin = new ZipInputStream(jar.read());
				ZipEntry entry;
				String name;
				while ((entry = zin.getNextEntry()) != null) {
					name = entry.getName();
					if (!name.endsWith(fileName)) continue;

					BufferedOutputStream os = null;
					try {
						File tmp = File.createTempFile(fileName, null);
						os = new BufferedOutputStream(new FileOutputStream(tmp));
						StreamUtils.copyStream(zin, os);
						return Gdx.files.absolute(tmp.getAbsolutePath());
					} catch (FileNotFoundException ex) {
						Gdx.app.error("Oops", "Unpack failed " + name);
					} finally {
						if (os != null) {
							os.close();
						}
					}
				}
				zin.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return null;
		}
	}
}
