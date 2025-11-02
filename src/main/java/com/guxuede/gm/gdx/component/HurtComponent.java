package com.guxuede.gm.gdx.component;

import com.badlogic.ashley.core.Component;

//携带此Component的entity 可以对别的 entity 的BloodComponent 造成伤害2
public class HurtComponent implements Component {

    public float hurtDamage;
    public float hurtRadius;

}
