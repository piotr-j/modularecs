package io.piotrjastrzebski.modularecs.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.piotrjastrzebski.modularecs.plugin.api.components.Bounds;
import io.piotrjastrzebski.modularecs.plugin.api.components.DebugTint;
import io.piotrjastrzebski.modularecs.plugin.api.components.Transform;
import io.piotrjastrzebski.modularecs.plugin.api.Constants;

/**
 * Created by PiotrJ on 25/09/15.
 */
@Wire
public class DebugRenderer extends EntityProcessingSystem {
	protected ComponentMapper<Transform> mTransform;
	protected ComponentMapper<Bounds> mBounds;
	protected ComponentMapper<DebugTint> mDebugTint;

	@Wire(name = Constants.WIRE_GAME_CAM)
	OrthographicCamera cam;
	@Wire
	ShapeRenderer renderer;

	public DebugRenderer () {
		super(Aspect.all(Transform.class, Bounds.class).one(DebugTint.class));
	}

	@Override protected void begin () {
		renderer.setProjectionMatrix(cam.combined);
		renderer.begin(ShapeRenderer.ShapeType.Line);
	}

	@Override protected void process (Entity e) {
		if (mDebugTint.has(e)) {
			DebugTint debugTint = mDebugTint.get(e);
			renderer.setColor(debugTint.color);
		} else {
			renderer.setColor(Color.WHITE);
		}

		Transform transform = mTransform.get(e);
		Vector2 pos = transform.pos;
		float angle = transform.angle;
		Bounds bounds = mBounds.get(e);
		Rectangle rect = bounds.rect;
		renderer.rect(pos.x - rect.width / 2, pos.y - rect.height /2,
			rect.width  / 2, rect.height / 2, rect.width, rect.height, 1, 1, angle);
	}

	@Override protected void end () {
		renderer.end();
	}
}
