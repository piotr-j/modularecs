package io.piotrjastrzebski.modularecs;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import io.piotrjastrzebski.modularecs.game.ModArtScreen;

/**
 * Created by PiotrJ on 24/09/15.
 */
public class ModSelectScreen extends BaseScreen {
	private final static String TAG = ModSelectScreen.class.getSimpleName();

	private GameMods mods;

	VisTextButton start;

	public ModSelectScreen (final ModularECSGame game) {
		super(game);
		mods = game.getMods();
		mods.load("plugins/");

		VisTable container = new VisTable(true);
		container.add(start = new VisTextButton("Start!"));
		start.addListener(new ClickListener() {
			@Override public void clicked (InputEvent event, float x, float y) {
				game.setScreen(new ModArtScreen(game, mods));
			}
		});
		for (final GameMods.Entry mod : mods) {
			container.row();
			final VisCheckBox ch = new VisCheckBox(mod.mod.getName(), mod.enabled);
			ch.addListener(new ChangeListener() {
				@Override public void changed (ChangeEvent event, Actor actor) {
					mod.enabled = ch.isChecked();
				}
			});
			container.add(ch);
		}
		root.add(container).center();
	}

	@Override public void render (float delta) {
		super.render(delta);
		stage.act(delta);
		stage.draw();
	}
}
