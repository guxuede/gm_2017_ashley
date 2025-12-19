package com.guxuede.gm.net.client.registry.pack;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.guxuede.gm.gdx.component.TiledMapDataComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.system.render.TiledMapManagerSystem;
import com.guxuede.gm.net.component.PlayerDataComponent;
import com.guxuede.gm.gdx.entityEdit.E;
import com.guxuede.gm.gdx.entityEdit.EntityEditor;
import com.guxuede.gm.gdx.system.render.StageSystem;
import com.guxuede.gm.net.client.pack.utils.PackageUtils;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.samplegame.DesktopLauncher;
import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.StringUtils;


public class ActorLandingPack extends NetPack {
    private String mapName;
    private String userName;
    private int id;
    private float x, y;
    private String character;
    private float directionInDegrees;
    private String client;

    public ActorLandingPack(ByteBuf data) {
        super(data);
        this.mapName = PackageUtils.readString(data);
        this.id = data.readInt();
        this.userName = PackageUtils.readString(data);
        this.character = PackageUtils.readString(data);
        this.x = data.readFloat();
        this.y = data.readFloat();
        this.directionInDegrees = data.readFloat();
        this.client = PackageUtils.readString(data);
    }

    @Override
    public void write(ByteBuf data) {
        PackageUtils.writeString(mapName, data);
        data.writeInt(this.id);
        PackageUtils.writeString(userName, data);
        PackageUtils.writeString(character, data);
        data.writeFloat(this.x);
        data.writeFloat(this.y);
        data.writeFloat(this.directionInDegrees);
        PackageUtils.writeString(client, data);
    }

    private static final Family playerDataComponentFamily = Family.all(PlayerDataComponent.class).get();

    @Override
    public void action(Engine engine, Entity entity) {

        if(isAlreadyLanding(engine)){
            System.out.println("已经登录了,不能再登录");
            return;
        }

        if(StringUtils.equals(DesktopLauncher.currentUserName, userName)){
            //clear map
            engine.getEntitiesFor(playerDataComponentFamily).iterator().forEachRemaining(engine::removeEntity);
            Entity userEntity = buildActor(E.create()).sensor().skill().buildToWorld();
            engine.getSystem(StageSystem.class).setViewActor(userEntity);
            processMap(engine);
        }else if(StringUtils.equals(DesktopLauncher.currentUserName, client)){
            buildActor(E.create()).skill().ai().buildToWorld();
        }else{
            buildActor(E.create()).buildToWorld();
        }
    }

    private boolean isAlreadyLanding(Engine engine){
        for (Entity next : engine.getEntitiesFor(playerDataComponentFamily)) {
            PlayerDataComponent messageComponent = Mappers.netPackCM.get(next);
            if (StringUtils.equals(messageComponent.userName,userName)) {
                return true;
            }
        }
        return false;
    }

    private EntityEditor buildActor(E target){
        return target.with(PlayerDataComponent.class, e -> {
            e.userName = userName;
            e.controlByUserName = client;
            e.setCharacter(character);
            e.setId(id);
            e.position.set(x, y);
        })
                .actorState(directionInDegrees, false, 0,0,0,0)
                .actor(character).pos(x, y)
                .actions().bounds(48, 48).blood(100, 65).team(id);
    }

    private void processMap(Engine engine){
        Entity currentTiledMapEntity = engine.getSystem(TiledMapManagerSystem.class).currentTiledMapEntity;
        if(currentTiledMapEntity!=null){
            engine.removeEntity(currentTiledMapEntity);
        }

        TiledMapDataComponent tiledMapDataComponent = new TiledMapDataComponent(mapName);
        Entity entity1 = new Entity();
        entity1.add(tiledMapDataComponent);
        engine.addEntity(entity1);
    }
}
