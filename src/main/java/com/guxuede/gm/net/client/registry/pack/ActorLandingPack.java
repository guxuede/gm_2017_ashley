package com.guxuede.gm.net.client.registry.pack;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.guxuede.gm.gdx.ResourceManager;
import com.guxuede.gm.gdx.component.SkillComponent;
import com.guxuede.gm.gdx.component.TiledMapDataComponent;
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

import java.util.function.Consumer;


public class ActorLandingPack extends NetPack {
    private String mapName;
    private String userName;
    private int id;
    private float x, y;
    private String character;
    private int direction;

    public ActorLandingPack(ByteBuf data) {
        super(data);
        this.mapName = PackageUtils.readString(data);
        this.id = data.readInt();
        this.userName = PackageUtils.readString(data);
        this.character = PackageUtils.readString(data);
        this.x = data.readFloat();
        this.y = data.readFloat();
        this.direction = data.readInt();
    }

    @Override
    public void write(ByteBuf data) {
        PackageUtils.writeString(mapName, data);
        data.writeInt(this.id);
        PackageUtils.writeString(userName, data);
        PackageUtils.writeString(character, data);
        data.writeFloat(this.x);
        data.writeFloat(this.y);
        data.writeInt(this.direction);
    }

    @Override
    public void action(Engine engine, Entity entity) {
        if(StringUtils.equals(DesktopLauncher.currentUserName, userName)){
            //clear map
            engine.getEntitiesFor(Family.all(PlayerDataComponent.class).get()).iterator().forEachRemaining(engine::removeEntity);
            Entity userEntity = buildActor(E.create()).sensor().with(SkillComponent.class, (Consumer<SkillComponent>) o -> {
                o.skills.add(ResourceManager.getSkillById("burstFire"));
                o.skills.add(ResourceManager.getSkillById("huijian"));
                o.skills.add(ResourceManager.getSkillById("bow"));
                o.skills.add(ResourceManager.getSkillById("meteorite"));
                o.skills.add(ResourceManager.getSkillById("fireBall"));
                o.skills.add(ResourceManager.getSkillById("blink"));
            }).buildToWorld();
            engine.getSystem(StageSystem.class).setViewActor(userEntity);
            processMap(engine);
        }else{
            buildActor(E.create()).buildToWorld();
        }
    }

    private EntityEditor buildActor(E target){
        return target.with(PlayerDataComponent.class, e -> {
            e.userName = userName;
            e.setCharacter(character);
            e.setId(id);
            e.position.set(x, y);
        })
                .actorState(direction, false, 0,0,0,0)
                .actorAnimation(character).pos(x, y)
                .actions().bounds(48, 48).blood(100, 65);
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
