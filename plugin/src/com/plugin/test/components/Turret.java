package com.plugin.test.components;

import com.artemis.PooledComponent;
import com.artemis.annotations.EntityId;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by PiotrJ on 25/09/15.
 */
public class Turret extends PooledComponent {
	@EntityId
	public int parent = -1;
	public Vector2 offset = new Vector2();

	@Override protected void reset () {
		parent = -1;
		offset.setZero();
	}

	public Turret parent (int id) {
		parent = id;
		return this;
	}

	public Turret offset (float x, float y) {
		offset.set(x, y);
		return this;
	}
}
