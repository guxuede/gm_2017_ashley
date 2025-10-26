
package com.guxuede.gm.gdx.actions.appearance;

import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.actions.TemporalAction;

public class RotateToAction extends TemporalAction {
	private float start, end;

	public RotateToAction(float duration, float end) {
		super(duration);
		this.end = end;
	}

	protected void begin () {
		start = Mappers.presentableCM.get(actor).rotation;
	}

	protected void update (float percent) {
        Mappers.presentableCM.get(actor).rotation=(start + (end - start) * percent);
	}

	public float getRotation () {
		return end;
	}

	public void setRotation (float rotation) {
		this.end = rotation;
	}
}
