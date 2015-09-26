package io.piotrjastrzebski.modularecs.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntityEdit;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ObjectMap;
import io.piotrjastrzebski.modularecs.plugin.api.Constants;
import io.piotrjastrzebski.modularecs.plugin.api.components.Asset;
import io.piotrjastrzebski.modularecs.plugin.api.components.AssetRef;

/**
 * Created by EvilEntity on 26/09/2015.
 */
@Wire
public class Assets extends EntitySystem {
	private ComponentMapper<AssetRef> mAssetRef;
	private ComponentMapper<Asset> mAsset;
	AssetManager manager;
	ObjectMap<String, Texture> textures;
	public Assets () {
		super(Aspect.all(AssetRef.class));
		setPassive(true);
		manager = new AssetManager();
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
}
