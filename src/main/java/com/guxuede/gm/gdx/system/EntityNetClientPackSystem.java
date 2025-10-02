package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.guxuede.gm.gdx.component.ActorStateComponent;
import com.guxuede.gm.gdx.component.NetClientComponent;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.component.SensorComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.pack.PlayerMovePack;
import com.guxuede.gm.net.client.registry.pack.PlayerPositionPack;


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
        Engine engine = this.getEngine();
        //process inbound entity message
        NetClientComponent netClientComponent = Mappers.netPackCM.get(entity);

        //process controller
        processSensorAndMove(entity, netClientComponent);

        //process package
        netClientComponent.inboundNetPacks.consumerAll(e-> processNetPack(engine, entity, e));
//
//        GlobalNetPackSystem netPackSystem = getEngine().getSystem(GlobalNetPackSystem.class);
//        netClientComponent.outboundNetPacks.consumerAll(netPackSystem::outboundNetPack);
    }

    private void processNetPack(Engine engine, Entity entity, NetPack pack){
        pack.action(engine , entity);
    }


    private void processSensorAndMove(Entity entity, NetClientComponent netClientComponent){
        Entity viewActor = getEngine().getSystem(StageSystem.class).getViewActor();

        //如果是本地操作角色,那就本地移动.定时汇报给服务器速度和位置
        if(viewActor == entity){
            SensorComponent sensorComponent = Mappers.sensorCM.get(entity);
            ActorStateComponent stateComponent = Mappers.actorStateCM.get(entity);
            stateComponent.acceleration.set(sensorComponent.acceleration);

            if(!sensorComponent.acceleration.epsilonEquals(sensorComponent.lastAcceleration)){
                PlayerMovePack playerMovePack = new PlayerMovePack(netClientComponent.getId(),sensorComponent.acceleration);
                netClientComponent.outBoundPack(playerMovePack);
            }

            netClientComponent.acceleration.set(stateComponent.acceleration);
            netClientComponent.direction = stateComponent.direction;

            PositionComponent positionComponent = Mappers.positionCM.get(entity);
            if (positionComponent != null && !positionComponent.position.epsilonEquals(netClientComponent.position)
                    && (System.currentTimeMillis() - netClientComponent.lastTimePositionReported) > 1000) {
                netClientComponent.position.set(positionComponent.position);
                netClientComponent.lastTimePositionReported = System.currentTimeMillis();
                PlayerPositionPack playerPositionPack = new PlayerPositionPack(netClientComponent.getId(), netClientComponent.direction , netClientComponent.position);
                netClientComponent.outBoundPack(playerPositionPack);
            }
        }else{
            //不是本地角色, 接收服务器数据
            ActorStateComponent stateComponent = Mappers.actorStateCM.get(entity);
            stateComponent.acceleration.set(netClientComponent.acceleration);

            PositionComponent positionComponent = Mappers.positionCM.get(entity);
            if (positionComponent != null && !positionComponent.position.epsilonEquals(netClientComponent.position)
                    && (System.currentTimeMillis() - netClientComponent.lastTimePositionReported) > 1000) {
                positionComponent.position.set(netClientComponent.position);
                netClientComponent.lastTimePositionReported = System.currentTimeMillis();
            }
        }
    }
}
