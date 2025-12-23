
package com.guxuede.gm.gdx.actions.appearance;


import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.actions.RelativeTemporalAction;
import com.guxuede.gm.gdx.component.PresentableComponent;

/** Scales an actor's scale to a relative size.
 * @author Nathan Sweet */
public class ScaleByAction extends RelativeTemporalAction {

    private float amountX, amountY;

	public ScaleByAction() {
	}

	public ScaleByAction(float amountX, float amountY, float duration){
        this.amountX = amountX;
        this.amountY = amountY;
        setDuration(duration);
    }

	protected void updateRelative (float percentDelta) {
        PresentableComponent presentableComponent = Mappers.presentableCM.get(actor);
        presentableComponent.scaleX += amountX * percentDelta;
        presentableComponent.scaleY += amountY * percentDelta;
	}

	public void setAmount (float x, float y) {
		amountX = x;
		amountY = y;
	}

	public void setAmount (float scale) {
		amountX = scale;
		amountY = scale;
	}

	public float getAmountX () {
		return amountX;
	}

	public void setAmountX (float x) {
		this.amountX = x;
	}

	public float getAmountY () {
		return amountY;
	}

	public void setAmountY (float y) {
		this.amountY = y;
	}

}
