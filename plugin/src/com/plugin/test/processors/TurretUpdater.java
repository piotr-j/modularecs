package com.plugin.test.processors;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.plugin.test.components.Turret;
import io.piotrjastrzebski.modularecs.plugin.api.components.Bounds;
import io.piotrjastrzebski.modularecs.plugin.api.components.Player;
import io.piotrjastrzebski.modularecs.plugin.api.components.Transform;

/**
 * Created by PiotrJ on 25/09/15.
 */
@Wire
public class TurretUpdater extends IteratingSystem {
	protected ComponentMapper<Transform> mTransform;
	protected ComponentMapper<Turret> mTurret;

	public TurretUpdater () {
		super(Aspect.all(Turret.class, Transform.class));
	}

	@Override protected void inserted (int entityId) {

	}

	@Override protected void process (int eid) {
		Turret turret = mTurret.get(eid);
		if (turret.parent < 0) return;
		Entity parent = world.getEntity(turret.parent);
		if (parent == null) return;
		Transform tTrans = mTransform.get(eid);
		Transform pTrans = mTransform.get(parent);
		Vector2 pPos = pTrans.pos;

		Vector2 off = turret.offset;
		float sin = MathUtils.sinDeg(pTrans.angle);
		float cos = MathUtils.cosDeg(pTrans.angle);
		tTrans.pos.set(
			pPos.x + cos * off.x - sin * off.y,
			pPos.y + sin * off.x + cos * off.y
		);
	}

	@Override protected void removed (int entityId) {

	}
}
