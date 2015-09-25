package io.piotrjastrzebski.modularecs.plugin.api.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class Bounds extends PooledComponent {
	public Rectangle rect = new Rectangle();
	@Override protected void reset () {
		rect.set(0, 0, 0 ,0);
	}

	public Bounds set (float width, float height) {
		rect.setSize(width, height);
		return this;
	}
}
