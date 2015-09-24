package io.piotrjastrzebski.modularecs.plugin.api;

import com.artemis.WorldConfiguration;
import net.mountainblade.modular.Module;

/**
 * Created by PiotrJ on 24/09/15.
 */
public interface GameMod extends Module {
	public void initialize (WorldConfiguration config);
}
