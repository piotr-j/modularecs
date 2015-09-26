package io.piotrjastrzebski.modularecs.plugin.api.components;

import com.artemis.PooledComponent;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class AssetRef extends PooledComponent {
	public String path;

	@Override protected void reset () {
		path = null;
	}

	public AssetRef setPath (String path) {
		this.path = path;
		return this;
	}
}
