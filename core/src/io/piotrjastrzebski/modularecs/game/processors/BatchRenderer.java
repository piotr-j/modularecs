package io.piotrjastrzebski.modularecs.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.piotrjastrzebski.modularecs.plugin.api.Constants;
import io.piotrjastrzebski.modularecs.plugin.api.components.Asset;
import io.piotrjastrzebski.modularecs.plugin.api.components.Bounds;
import io.piotrjastrzebski.modularecs.plugin.api.components.DebugTint;
import io.piotrjastrzebski.modularecs.plugin.api.components.Transform;

/**
 * Created by PiotrJ on 25/09/15.
 */
@Wire
public class BatchRenderer extends EntityProcessingSystem {
	protected ComponentMapper<Transform> mTransform;
	protected ComponentMapper<Bounds> mBounds;
	protected ComponentMapper<Asset> mAsset;

	@Wire(name = Constants.WIRE_GAME_CAM)
	OrthographicCamera cam;
	@Wire
	SpriteBatch batch;

	public BatchRenderer () {
		super(Aspect.all(Transform.class, Bounds.class, Asset.class));
	}

	@Override protected void begin () {
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
	}

	@Override protected void process (Entity e) {
		Transform transform = mTransform.get(e);
		Vector2 pos = transform.pos;
		float angle = transform.angle;
		Bounds bounds = mBounds.get(e);
		Rectangle rect = bounds.rect;
		Asset asset = mAsset.get(e);
		Sprite sprite = asset.sprite;
		// TODO center? yay/nay?
		sprite.setPosition(pos.x - rect.width / 2, pos.y - rect.height / 2);
		sprite.setRotation(angle);
		// TODO do whe bother to not do this each update?
		sprite.setSize(rect.width, rect.height);
		sprite.setOrigin(rect.width / 2 , rect.height / 2);

		sprite.draw(batch);
	}

	@Override protected void end () {
		batch.end();
	}
}
