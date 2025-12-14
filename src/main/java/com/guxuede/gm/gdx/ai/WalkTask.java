/*******************************************************************************
 * Copyright 2014 See AUTHORS file.
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

package com.guxuede.gm.gdx.ai;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.actions.movement.bysensor.ActorMoveToAction;
import com.guxuede.gm.gdx.entityEdit.Mappers;

/** @author implicit-invocation
 * @author davebaol */
public class WalkTask extends LeafTask<Entity> {

	ActorMoveToAction moveToAction;

	@Override
	public void start () {
		System.out.println("WalkTask");
		Entity dog = getObject();
		Vector2 position = Mappers.positionCM.get(dog).position;
		moveToAction = new ActorMoveToAction((position.x+ MathUtils.random(-200f,200f)), (position.y+ MathUtils.random(-200f,200f)));
		Mappers.actionCM.get(dog).addAction(dog, moveToAction);
	}

	@Override
	public Status execute () {
		if(!moveToAction.isComplete()){
			return Status.RUNNING;
		}else{
			return Status.SUCCEEDED;
		}
	}

	@Override
	public void end () {
//		getObject().stopWalking();
	}

	@Override
	protected Task<Entity> copyTo (Task<Entity> task) {
		return task;
	}

}
