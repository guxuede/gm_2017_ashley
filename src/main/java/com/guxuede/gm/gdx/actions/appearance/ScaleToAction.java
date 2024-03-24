
package com.guxuede.gm.gdx.actions.appearance;

import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.actions.TemporalAction;
import com.guxuede.gm.gdx.component.PresentableComponent;

public class ScaleToAction extends TemporalAction {
	private float startX, startY;
	private float endX, endY;

	protected void begin () {
        PresentableComponent presentableComponent = Mappers.presentableCM.get(actor);
        startX = presentableComponent.scaleX;
		startY = presentableComponent.scaleY;
	}

	protected void update (float percent) {
        PresentableComponent presentableComponent = Mappers.presentableCM.get(actor);
        presentableComponent.scaleX = startX + (endX - startX) * percent;
        presentableComponent.scaleY = startY + (endY - startY) * percent;
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
