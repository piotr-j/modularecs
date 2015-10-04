package io.piotrjastrzebski.modularecs.game;

import com.artemis.BaseSystem;
import com.artemis.Manager;
import com.artemis.World;
import com.artemis.injection.*;
import com.artemis.utils.ImmutableBag;
import com.artemis.utils.reflect.Field;

import java.util.Map;

/**
 * Created by PiotrJ on 26/09/15.
 */
public class InterfaceInjector implements Injector {
	CachedInjector injector;

	public InterfaceInjector () {
		injector = new CachedInjector();
	}

	@Override public void initialize (World world, Map<String, Object> injectables) {
		FieldHandler handler = new FieldHandler(new InjectionCache(), injectables);
		handler.addFieldResolver(new InterfaceFieldResolver(injectables));
		injector.setFieldHandler(handler);
		injector.initialize(world, injectables);
	}

	@Override public void inject (Object target) throws RuntimeException {
		injector.inject(target);
	}

	@Override public boolean isInjectable (Object target) {
		return injector.isInjectable(target);
	}

	@Override public Injector setFieldHandler (FieldHandler fieldHandler) {
		return injector.setFieldHandler(fieldHandler);
	}

	private class InterfaceFieldResolver implements FieldResolver, UseInjectionCache {
		private InjectionCache cache;

		private Map<String, Object> pojos;

		public InterfaceFieldResolver (Map<String, Object> pojos) {
			this.pojos = pojos;
		}

		ImmutableBag<BaseSystem> systems;
		@Override
		public void initialize(World world) {
			systems = world.getSystems();
		}

		@Override
		public Object resolve(Class<?> fieldType, Field field) {
			ClassType injectionType = cache.getFieldClassType(fieldType);
			CachedField cachedField = cache.getCachedField(field);

			if (injectionType == ClassType.CUSTOM) {
				if (cachedField.wireType == WireType.WIRE) {
					String key = cachedField.name;
					if ("".equals(key)) {
						key = field.getType().getName();
					}

					Object value = pojos.get(key);
					if (value == null) {
						Class<?> cType = cachedField.field.getType();
						for (BaseSystem system : systems) {
							if (cType.isAssignableFrom(system.getClass())) {
								pojos.put(key, system);
								return system;
							}
						}
					}
					return value;
				}
			}
			return null;
		}

		@Override
		public void setCache(InjectionCache cache) {
			this.cache = cache;
		}
	}
}
