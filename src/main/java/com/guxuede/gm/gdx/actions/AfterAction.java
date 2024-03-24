/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.guxuede.gm.gdx.actions;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.guxuede.gm.gdx.entityEdit.Mappers;

/** Executes an action only after all other actions on the actor at the time this action's target was set have finished.
 * @author Nathan Sweet */
public class AfterAction extends DelegateAction {
	private Array<Action> waitForActions = new Array(false, 4);

	public void setTarget (Entity target) {
		if (target != null) waitForActions.addAll(Mappers.actionCM.get(target).actions);
		super.setTarget(target);
	}

	public void restart () {
		super.restart();
		waitForActions.clear();
	}

	protected boolean delegate (float delta) {
		Array<Action> currentActions = Mappers.actionCM.get(target).actions;
		if (currentActions.size == 1) waitForActions.clear();
		for (int i = waitForActions.size - 1; i >= 0; i--) {
			Action action = waitForActions.get(i);
			int index = currentActions.indexOf(action, true);
			if (index == -1) waitForActions.removeIndex(i);
		}
		if (waitForActions.size > 0) return false;
		return action.act(delta);
	}
}
