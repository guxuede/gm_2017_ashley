package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.component.NetClientComponent;
import com.guxuede.gm.gdx.component.SensorComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.pack.PlayerMovePack;


/**
 * Created by guxuede on 2017/6/3 .
 */
public class EntityNetClientPackSystem extends IteratingSystem {

    private static final Family family = Family.all(NetClientComponent.class).get();

    public EntityNetClientPackSystem(){
        super(family);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //process inbound entity message
        NetClientComponent netClientComponent = Mappers.netPackCM.get(entity);

        //process contoller
        processSensor(entity, netClientComponent);

        //process movement
        syncNetPosition(entity, netClientComponent);

        //process package
        netClientComponent.inboundNetPacks.consumerAll(e-> processNetPack(entity, e));

        GlobalNetPackSystem netPackSystem = getEngine().getSystem(GlobalNetPackSystem.class);
        netClientComponent.outboundNetPacks.consumerAll(netPackSystem::outboundNetPack);
    }

    private void processNetPack(Entity entity, NetPack pack){
        pack.action(entity);
    }

    private void syncNetPosition(Entity entity, NetClientComponent netClientComponent){
        ActorStateComponent stateComponent = Mappers.actorStateCM.get(entity);
        stateComponent.acceleration.set(netClientComponent.acceleration);
//        PositionComponent positionComponent = Mappers.positionCM.get(entity);
//        if(positionComponent.position.dst(netClientComponent.position) > 10){
//            positionComponent.position.set(netClientComponent.position);
//        }
    }

    private void processSensor(Entity entity, NetClientComponent netClientComponent){
        SensorComponent sensorComponent = Mappers.sensorCM.get(entity);
        if(sensorComponent!=null && !sensorComponent.acceleration.epsilonEquals(sensorComponent.lastAcceleration)){
            PlayerMovePack playerMovePack = new PlayerMovePack(netClientComponent.getId(),sensorComponent.acceleration);
            netClientComponent.outBoundPack(playerMovePack);
        }
    }
}
