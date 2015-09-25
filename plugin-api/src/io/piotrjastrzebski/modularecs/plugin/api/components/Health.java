package io.piotrjastrzebski.modularecs.plugin.api.components;

import com.artemis.PooledComponent;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class Health extends PooledComponent {
	public float current;
	public float max;

	public Health init (float hp) {
		max = current = hp;
		return this;
	}

	public boolean isDead() {
		return current <= 0;
	}

	@Override protected void reset () {
		current = max = 0;
	}
}
