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
import com.guxuede.gm.gdx.actions.SequenceAction;
import com.guxuede.gm.gdx.component.SkillComponent;
import com.guxuede.gm.gdx.component.skill.Skill;
import com.guxuede.gm.gdx.entityEdit.Mappers;

public class PlayTask extends LeafTask<Entity> {

	long startTime;

	SequenceAction sequenceAction;

	public void start () {
		System.out.println("PlayTask");
		startTime = System.currentTimeMillis();
		Entity dog = getObject();
//		dog.brainLog("WOW - Lets play!");
		SkillComponent playerDataComponent = Mappers.skillCM.get(dog);
		if(playerDataComponent!=null){
			Vector2 position = Mappers.positionCM.get(dog).position;

//			Skill skill = playerDataComponent.getSkill("flyFireWithMe");
			Skill skill = playerDataComponent.getRandomSkill();
			skill.targetEntry = dog;
			skill.owner = dog;
			skill.targetPos = new Vector2((position.x+ MathUtils.random(-200f,200f)), (position.y+ MathUtils.random(-200f,200f)));
			sequenceAction = (SequenceAction) skill.play();
		}
	}

	@Override
	public Status execute () {
		if(System.currentTimeMillis() - startTime <  3000){
			return Status.RUNNING;
		}else{
			return Status.SUCCEEDED;
		}
	}

	@Override
	public void end () {
		Entity dog = getObject();
		sequenceAction =null;
//		dog.brainLog("SIC - No time to play :(");
	}

	@Override
	protected Task<Entity> copyTo (Task<Entity> task) {
		return task;
	}
}
