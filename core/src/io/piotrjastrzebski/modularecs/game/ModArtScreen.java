package io.piotrjastrzebski.modularecs.game;

import com.artemis.WorldConfiguration;
import io.piotrjastrzebski.modularecs.GameScreen;
import io.piotrjastrzebski.modularecs.ModularECSGame;

/**
 * What do we want from plugins?
 * - custom components
 * - custom systems
 * - injection of existing stuff
 * nice to have
 * - override existing systems? a lot o problems with that
 * - sand box plugins, not net access, file write etc
 *
 * Created by EvilEntity on 23/09/2015.
 */
public class ModArtScreen extends GameScreen {
	public ModArtScreen (ModularECSGame game) {
		super(game);
	}

	@Override protected void preInit (WorldConfiguration config) {
		// add out stuff + stuff from plugins
	}

	@Override protected void postInit () {

	}
}
