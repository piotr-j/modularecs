package io.piotrjastrzebski.modularecs.game.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class DebugTint extends PooledComponent {
	public Color color = new Color();
	@Override protected void reset () {
		color.set(Color.WHITE);
	}

	public DebugTint setColor (float r, float g, float b) {
		color.set(r, g, b, 1);
		return this;
	}

	public DebugTint setColor (float r, float g, float b, float a) {
		color.set(r, g, b, a);
		return this;
	}
}
