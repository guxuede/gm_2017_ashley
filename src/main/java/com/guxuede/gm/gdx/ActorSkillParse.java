package com.guxuede.gm.gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.guxuede.gm.gdx.component.skill.ScriptSkill;
import com.guxuede.gm.gdx.component.skill.Skill;
import com.guxuede.gm.gdx.component.skill.SkillTargetTypeEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by guxuede on 2017/4/3 .
 */
public class ActorSkillParse {

    public static final Map<String,Skill> parseSkill(FileHandle fileHandle){
        Map<String,Skill> skills = new HashMap<String, Skill>();
        try {
            Document document = Jsoup.parse(fileHandle.read(),"UTF-8","");
            Elements elements = document.getElementsByTag("skill");
            ListIterator<Element> elementListIterator = elements.listIterator();
            while(elementListIterator.hasNext()){
                Element node = elementListIterator.next();
                String skillId = node.attr("id");
                String name = node.attr("name");
                String icon = node.attr("icon");
                float skillCooldownTime = Float.parseFloat(node.attr("skillCooldownTime"));
                int hotKey = Input.Keys.valueOf(node.attr("hotKey"));
                SkillTargetTypeEnum targetType = SkillTargetTypeEnum.valueOf(node.attr("targetType"));
                String script = node.getElementsByTag("script").html();
                String description = node.getElementsByTag("description").html();
                ScriptSkill skill = new ScriptSkill();
                skill.setName(name);
                skill.setDescription(description);
                skill.setHotKey(hotKey);
                skill.setScript(script);
                skill.setTargetType(targetType);
                skill.setSkillCooldownTime(skillCooldownTime);
                //skill.setIcon(new TextureRegionDrawable(ResourceManager.getTextureRegion(icon)));
                skills.put(skillId,skill);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skills;
    }
}
