package com.plugin.test;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import net.mountainblade.modular.Module;
import net.mountainblade.modular.annotations.Implementation;

/**
 * Created by PiotrJ on 24/09/15.
 */
@Implementation(authors = "PiotrJ", version = "0.1")
public class TestSystem extends EntityProcessingSystem implements Module {
	public TestSystem () {
		super(Aspect.all());
	}

	@Override protected void process (Entity e) {

	}
}
