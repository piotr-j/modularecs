package com.plugin.test.components;

import com.artemis.PooledComponent;
import com.artemis.annotations.EntityId;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by PiotrJ on 25/09/15.
 */
public class TurretRef extends PooledComponent {
	@EntityId
	public int turret = -1;

	@Override protected void reset () {
		turret = -1;
	}

	public TurretRef ref (int id) {
		turret = id;
		return this;
	}
}
