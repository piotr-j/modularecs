package io.piotrjastrzebski.modularecs.desktop;

import io.piotrjastrzebski.modularecs.PlatformBridge;
import org.lwjgl.opengl.Display;

/**
 * Created by PiotrJ on 04/08/15.
 */
public class DesktopBridge implements PlatformBridge {
	@Override public float getPixelScaleFactor () {
		return Display.getPixelScaleFactor();
	}
}
