package io.piotrjastrzebski.modularecs.game.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.MathUtils;
import io.piotrjastrzebski.modularecs.plugin.api.components.Player;
import io.piotrjastrzebski.modularecs.plugin.api.components.Transform;
import io.piotrjastrzebski.modularecs.plugin.api.Input;

/**
 * Created by PiotrJ on 24/09/15.
 */
@Wire
public class PlayerController extends EntityProcessingSystem implements Input, InputProcessor {
	protected ComponentMapper<Transform> mTransform;
	protected ComponentMapper<Player> mPlayer;
	public PlayerController () {
		super(Aspect.all(Player.class, Transform.class));
	}

	int rotate;
	int move;
	@Override protected void process (Entity e) {
		Player player = mPlayer.get(e);
		Transform transform = mTransform.get(e);
		transform.angle += rotate * player.rotateSpeed * world.delta;
		if (move != 0) {
			transform.pos.add(
				MathUtils.sin(transform.angle * MathUtils.degRad) * player.moveSpeed * world.delta * move,
				-MathUtils.cos(transform.angle * MathUtils.degRad) * player.moveSpeed * world.delta * move
			);
		}
	}

	@Override public int priority () {
		return 0;
	}

	@Override public InputProcessor get () {
		return this;
	}

	@Override public boolean keyDown (int keycode) {
		switch (keycode) {
		case com.badlogic.gdx.Input.Keys.W:
			move--;
			return true;
		case com.badlogic.gdx.Input.Keys.S:
			move++;
			return true;
		case com.badlogic.gdx.Input.Keys.A:
			rotate++;
			return true;
		case com.badlogic.gdx.Input.Keys.D:
			rotate--;
			return true;
		}
		return false;
	}

	@Override public boolean keyUp (int keycode) {
		switch (keycode) {
		case com.badlogic.gdx.Input.Keys.W:
			move++;
			return true;
		case com.badlogic.gdx.Input.Keys.S:
			move--;
			return true;
		case com.badlogic.gdx.Input.Keys.A:
			rotate--;
			return true;
		case com.badlogic.gdx.Input.Keys.D:
			rotate++;
			return true;
		}
		return false;
	}

	@Override public boolean keyTyped (char character) {
		return false;
	}

	@Override public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override public boolean touchDragged (int screenX, int screenY, int pointer) {
		return false;
	}

	@Override public boolean mouseMoved (int screenX, int screenY) {
		return false;
	}

	@Override public boolean scrolled (int amount) {
		return false;
	}
}
