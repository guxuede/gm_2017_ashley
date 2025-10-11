package com.guxuede.gm.net.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.net.component.PlayerDataComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.component.SensorComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.system.render.StageSystem;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.pack.ActorMovePack;
import com.guxuede.gm.net.client.registry.pack.ActorPositionPack;


/**
 * Created by guxuede on 2017/6/3 .
 */
public class EntityNetClientPackSystem extends IteratingSystem {

    private static final Family family = Family.all(PlayerDataComponent.class).get();

    public EntityNetClientPackSystem(){
        super(family);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Engine engine = this.getEngine();
        //process inbound entity message
        PlayerDataComponent playerDataComponent = Mappers.netPackCM.get(entity);

        //process controller
        processSensorAndMove(entity, playerDataComponent);

        //process package
        playerDataComponent.inboundNetPacks.consumerAll(e-> processNetPack(engine, entity, e));
//
//        GlobalNetPackSystem netPackSystem = getEngine().getSystem(GlobalNetPackSystem.class);
//        netClientComponent.outboundNetPacks.consumerAll(netPackSystem::outboundNetPack);
    }

    private void processNetPack(Engine engine, Entity entity, NetPack pack){
        pack.action(engine , entity);
    }


    private void processSensorAndMove(Entity entity, PlayerDataComponent playerDataComponent){
        Entity viewActor = getEngine().getSystem(StageSystem.class).getViewActor();

        //如果是本地操作角色,那就本地移动.定时汇报给服务器速度和位置
        if(viewActor == entity){
            SensorComponent sensorComponent = Mappers.sensorCM.get(entity);
            ActorStateComponent stateComponent = Mappers.actorStateCM.get(entity);
            stateComponent.acceleration.set(sensorComponent.acceleration);

            if(!sensorComponent.acceleration.epsilonEquals(sensorComponent.lastAcceleration)){
                ActorMovePack actorMovePack = new ActorMovePack(playerDataComponent.getId(),sensorComponent.acceleration);
                playerDataComponent.outBoundPack(actorMovePack);
            }

            playerDataComponent.acceleration.set(stateComponent.acceleration);
            playerDataComponent.direction = stateComponent.direction;

            PositionComponent positionComponent = Mappers.positionCM.get(entity);
            if (positionComponent != null && !positionComponent.position.epsilonEquals(playerDataComponent.position)
                    && (System.currentTimeMillis() - playerDataComponent.lastTimePositionReported) > 1000) {
                playerDataComponent.position.set(positionComponent.position);
                playerDataComponent.lastTimePositionReported = System.currentTimeMillis();
                ActorPositionPack actorPositionPack = new ActorPositionPack(playerDataComponent.getId(), playerDataComponent.direction , playerDataComponent.position);
                playerDataComponent.outBoundPack(actorPositionPack);
            }
        }else{
            //不是本地角色, 接收服务器数据
            ActorStateComponent stateComponent = Mappers.actorStateCM.get(entity);
            stateComponent.acceleration.set(playerDataComponent.acceleration);

            PositionComponent positionComponent = Mappers.positionCM.get(entity);
            if (positionComponent != null && !positionComponent.position.epsilonEquals(playerDataComponent.position)
                    && (System.currentTimeMillis() - playerDataComponent.lastTimePositionReported) > 1000) {
                positionComponent.position.set(playerDataComponent.position);
                playerDataComponent.lastTimePositionReported = System.currentTimeMillis();
            }
        }
    }
}
