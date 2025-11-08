
package com.guxuede.gm.gdx.actions.damage;

import com.guxuede.gm.gdx.actions.Action;
import com.guxuede.gm.gdx.entityEdit.Mappers;

public class DamageAreAction extends Action {
	public boolean damage;

	public boolean act (float delta) {
		Mappers.bloodCM.get(actor).modify(-10);
		return true;
	}


}
