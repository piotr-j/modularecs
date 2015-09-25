package io.piotrjastrzebski.modularecs.plugin.api.components;

import com.artemis.PooledComponent;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class Player extends PooledComponent {
	public float moveSpeed;
	public float rotateSpeed;

	@Override protected void reset () {
		moveSpeed = 0;
		rotateSpeed = 0;
	}

	public Player setMoveSpeed (float moveSpeed) {
		this.moveSpeed = moveSpeed;
		return this;
	}

	public Player setRotateSpeed (float rotateSpeed) {
		this.rotateSpeed = rotateSpeed;
		return this;
	}
}
