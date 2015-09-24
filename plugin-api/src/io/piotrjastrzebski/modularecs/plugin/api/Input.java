package io.piotrjastrzebski.modularecs.plugin.api;

import com.badlogic.gdx.InputProcessor;

/**
 * Created by EvilEntity on 15/08/2015.
 */
public interface Input {
	int priority ();
	InputProcessor get ();

}
