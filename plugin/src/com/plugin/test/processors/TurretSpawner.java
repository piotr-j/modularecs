package com.plugin.test.processors;

import com.artemis.*;
import com.artemis.annotations.Wire;
import com.plugin.test.components.Turret;
import com.plugin.test.components.TurretRef;
import io.piotrjastrzebski.modularecs.plugin.api.components.*;

/**
 * Created by PiotrJ on 25/09/15.
 */
@Wire
public class TurretSpawner extends EntitySystem {
	protected ComponentMapper<TurretRef> mTurretRef;
	protected ComponentMapper<Transform> mTransform;

	public TurretSpawner () {
		super(Aspect.all(Player.class, Transform.class, Bounds.class));
		setPassive(true);
	}

	@Override protected void inserted (int entityId) {
		Transform transform = mTransform.get(entityId);
		Entity turret = world.createEntity();
		EntityEdit edit = turret.edit();
		edit.create(Transform.class).set(transform);
		edit.create(Bounds.class).set(0.5f, 3f);
		edit.create(Turret.class).parent(entityId).offset(0, 1.5f);
		edit.create(DebugTint.class).setColor(0, 1, 0);

		world.getEntity(entityId).edit().create(TurretRef.class).ref(turret.id);
	}

	@Override protected void processSystem () {
		// passive, do nothing
	}

	@Override protected void removed (int entityId) {
		TurretRef turretRef = mTurretRef.getSafe(entityId);
		if (turretRef == null) return;
		if (turretRef.turret < 0) return;
		world.deleteEntity(turretRef.turret);
	}
}
