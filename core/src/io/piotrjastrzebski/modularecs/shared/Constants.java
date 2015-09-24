package io.piotrjastrzebski.modularecs.shared;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class Constants {
	public static final float SCALE = 32f;
	public static final float INV_SCALE = 1f/SCALE;
	public static final float VP_WIDTH = 1280 * INV_SCALE;
	public static final float VP_HEIGHT = 720 * INV_SCALE;

	public static final String WIRE_GUI_CAM = "gui-cam";
	public static final String WIRE_GUI_VP = "gui-vp";
	public static final String WIRE_GAME_CAM = "game-cam";
	public static final String WIRE_GAME_VP = "game-vp";

}
