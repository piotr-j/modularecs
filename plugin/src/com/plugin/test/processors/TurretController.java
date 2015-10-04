package com.plugin.test.processors;

import com.artemis.*;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.plugin.test.components.Turret;
import io.piotrjastrzebski.modularecs.plugin.api.Constants;
import io.piotrjastrzebski.modularecs.plugin.api.Input;
import io.piotrjastrzebski.modularecs.plugin.api.components.Transform;

/**
 * Created by PiotrJ on 25/09/15.
 */
@Wire
public class TurretController extends IteratingSystem implements Input, InputProcessor {
	private final static String TAG = TurretController.class.getSimpleName();

	@Wire(name = Constants.WIRE_GAME_CAM)
	OrthographicCamera cam;
	protected ComponentMapper<Turret> mTurret;
	protected ComponentMapper<Transform> mTransform;

	public TurretController () {
		super(Aspect.all(Turret.class, Transform.class));
	}

	@Override protected void initialize () {
		cam.unproject(tp.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0));
	}

	Vector2 angle = new Vector2();
	@Override protected void process (int eid) {
		Transform transform = mTransform.get(eid);
		transform.angle = angle.set(transform.pos).sub(tp.x, tp.y).angle() + 90;
	}

	@Override public int priority () {
		return 1;
	}

	@Override public InputProcessor get () {
		return this;
	}

	@Override public boolean keyDown (int keycode) {
		return false;
	}

	@Override public boolean keyUp (int keycode) {
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
	Vector3 tp = new Vector3();
	@Override public boolean mouseMoved (int screenX, int screenY) {
		cam.unproject(tp.set(screenX, screenY, 0));
		return false;
	}

	@Override public boolean scrolled (int amount) {
		return false;
	}
}
