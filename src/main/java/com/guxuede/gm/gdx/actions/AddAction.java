
package com.guxuede.gm.gdx.actions;

import com.guxuede.gm.gdx.entityEdit.Mappers;

/** Adds an action to an actor.
 * @author Nathan Sweet */
public class AddAction extends Action {
	private Action action;

	public boolean act (float delta) {
        Mappers.actionCM.get(target).addAction(target,action);
		return true;
	}

	public Action getAction () {
		return action;
	}

	public void setAction (Action action) {
		this.action = action;
	}

	public void restart () {
		if (action != null) action.restart();
	}

	public void reset () {
		super.reset();
		action = null;
	}
}
