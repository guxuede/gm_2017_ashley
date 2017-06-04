
package com.guxuede.gm.gdx.actions.appearance;

import com.guxuede.gm.gdx.Mappers;
import com.guxuede.gm.gdx.actions.Action;

public class VisibleAction extends Action {
	private boolean visible;

	public boolean act (float delta) {
        Mappers.presentableCM.get(actor).visible = visible;
		return true;
	}

	public boolean isVisible () {
		return visible;
	}

	public void setVisible (boolean visible) {
		this.visible = visible;
	}
}
