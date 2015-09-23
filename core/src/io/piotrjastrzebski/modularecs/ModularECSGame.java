package io.piotrjastrzebski.modularecs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kotcrab.vis.ui.VisUI;
import io.piotrjastrzebski.modularecs.game.ModArtScreen;

public class ModularECSGame extends Game {
	private final PlatformBridge bridge;
	SpriteBatch batch;
	ShapeRenderer renderer;

	public ModularECSGame (PlatformBridge bridge) {
		this.bridge = bridge;
	}

	@Override
	public void create () {
		boolean highRes = Math.max(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()) > 1980;
		if (highRes || bridge.getPixelScaleFactor() > 1.5f) {
			VisUI.load(VisUI.SkinScale.X2);
		} else {
			VisUI.load(VisUI.SkinScale.X1);
		}
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		setScreen(new ModArtScreen(this));
	}

	public SpriteBatch getBatch () {
		return batch;
	}

	public ShapeRenderer getRenderer () {
		return renderer;
	}

	@Override public void dispose () {
		super.dispose();
		batch.dispose();
		renderer.dispose();
		VisUI.dispose();
	}
}
