package io.piotrjastrzebski.modularecs.android;

import io.piotrjastrzebski.modularecs.PlatformBridge;

/**
 * Created by PiotrJ on 04/08/15.
 */
public class AndroidBridge implements PlatformBridge {
	@Override public float getPixelScaleFactor () {
		return 1;
	}
}
