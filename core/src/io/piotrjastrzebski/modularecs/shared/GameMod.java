package io.piotrjastrzebski.modularecs.shared;

import com.artemis.WorldConfiguration;

/**
 * Main entry point for game mod
 *
 * Created by PiotrJ on 24/09/15.
 */
public interface GameMod {
	// TODO do we want to pass in some cut down version of config?
	public void initialize (WorldConfiguration config);
}
