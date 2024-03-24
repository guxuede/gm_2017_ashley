
package com.guxuede.gm.gdx.actions.appearance;


import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.actions.RelativeTemporalAction;

public class RotateByAction extends RelativeTemporalAction {
	private float amount;

	protected void updateRelative (float percentDelta) {
        float amountInDegrees = amount * percentDelta;
        if (amountInDegrees != 0) {
            Mappers.presentableCM.get(actor).rotation += amountInDegrees;
        }
	}

	public float getAmount () {
		return amount;
	}

	public void setAmount (float rotationAmount) {
		amount = rotationAmount;
	}
}
