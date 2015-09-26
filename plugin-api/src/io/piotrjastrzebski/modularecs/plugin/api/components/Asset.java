package io.piotrjastrzebski.modularecs.plugin.api.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class Asset extends PooledComponent {
	public Sprite sprite;

	@Override protected void reset () {
		sprite = null;
	}

	public Asset setSprite (Sprite sprite) {
		this.sprite = sprite;
		return this;
	}
}
