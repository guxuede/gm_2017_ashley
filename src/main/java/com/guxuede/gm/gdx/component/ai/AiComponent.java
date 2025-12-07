package com.guxuede.gm.gdx.component.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.utils.Pool;

public class AiComponent implements Component , Pool.Poolable{

    public BehaviorTree<Entity> behaviorTree;

    @Override
    public void reset() {

    }
}
