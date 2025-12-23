
package com.guxuede.gm.gdx.actions.appearance;

import com.guxuede.gm.gdx.actions.TemporalAction;
import com.guxuede.gm.gdx.component.PresentableComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;

public class ScaleFromToAction extends TemporalAction {
	private float startX, startY;
	private float endX, endY;


	protected void update (float percent) {
        PresentableComponent presentableComponent = Mappers.presentableCM.get(actor);
        presentableComponent.scaleX = startX + (endX - startX) * percent;
        presentableComponent.scaleY = startY + (endY - startY) * percent;
	}

	public void setFrom (float from) {
		endX = from;
		endY = from;
	}

	public void setScale (float x, float y) {
		endX = x;
		endY = y;
	}

	public void setScale (float scale) {
		endX = scale;
		endY = scale;
	}

	public float getX () {
		return endX;
	}

	public void setX (float x) {
		this.endX = x;
	}

	public float getY () {
		return endY;
	}

	public void setY (float y) {
		this.endY = y;
	}
}
