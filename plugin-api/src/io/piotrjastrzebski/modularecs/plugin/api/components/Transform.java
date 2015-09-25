package io.piotrjastrzebski.modularecs.plugin.api.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class Transform extends PooledComponent {
	public Vector2 pos = new Vector2();
	public float angle;

	@Override protected void reset () {
		pos.setZero();
		angle = 0;
	}

	public Transform set (float x, float y, float angle) {
		pos.set(x, y);
		this.angle = angle;
		return this;
	}

	public Transform set (Transform other) {
		pos.set(other.pos);
		angle = other.angle;
		return this;
	}
}
