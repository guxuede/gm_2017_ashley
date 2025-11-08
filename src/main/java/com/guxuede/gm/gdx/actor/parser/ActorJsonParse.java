package com.guxuede.gm.gdx.actor.parser;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.jsoup.helper.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guxuede on 2016/5/27 .
 */
public class ActorJsonParse {

    public static final String WALK_DOWN_ANIMATION = "walkDownAnimation";
    public static final String WALK_LEFT_ANIMATION = "walkLeftAnimation";
    public static final String WALK_RIGHT_ANIMATION = "walkRightAnimation";
    public static final String WALK_UP_ANIMATION = "walkUpAnimation";
    public static final String STOP_DOWN_ANIMATION = "stopDownAnimation";
    public static final String STOP_LEFT_ANIMATION = "stopLeftAnimation";
    public static final String STOP_RIGHT_ANIMATION = "stopRightAnimation";
    public static final String STOP_UP_ANIMATION = "stopUpAnimation";
    public static final String DEATH_ANIMATION = "deathAnimation";

    public List<ActorHolder> parse(FileHandle fileHandle){
        JsonReader jsonReader = new JsonReader();
        JsonValue jsonValue = jsonReader.parse(fileHandle);

        List<ActorHolder> animationHolders= new ArrayList<ActorHolder>();
        for(JsonValue.JsonIterator it = jsonValue.iterator(); it.hasNext();){
            JsonValue jsonValue1 = it.next();
            ActorHolder animationHolder=getParser(jsonValue1).parseActor(jsonValue1);
            animationHolders.add(animationHolder);
        }
        return animationHolders;
    }

    private AbstractSpriteParser getParser(JsonValue jsonValue){
        String p = jsonValue.getString("parser", null);
        if(StringUtil.isBlank(p)){
            return new DefaultSpriteParser();
        }else {
            try {
                return (AbstractSpriteParser) Class.forName(p).newInstance();
            } catch (Exception e) {
                throw new RuntimeException("failed to load parser " + p, e);
            }
        }
    }


    public static void main(String[] args) {
        int hn = 5;//横向几帧
        int vn = 3;//竖向几帧
        int pw = 960;//图片宽度
        int ph = 576;//图片高度

        int fw = pw/hn;//单帧宽度
        int fh = ph/vn;//单帧高度

        String json = "{\n" +
                "  \"name\" : \"Wagon01\",\n" +
                "  \"width\":"+fw+",\n" +
                "  \"height\":"+fh+",\n" +
                "  \"texture\":\"188-Wagon01\",\n" +
                "  \"animations\":[\n" +
                "     {\n" +
                "       \"name\":\"walkDownAnimation\",\n" +
                "       \"frameDuration\":0.1,\n" +
                "       \"frames\":[\n" +
                "         [0,0,"+fw+","+fh+"],\n" +
                "         ["+fw+",0,"+fw+","+fh+"],\n" +
                "         ["+fw*2+",0,"+fw+","+fh+"],\n" +
                "         ["+fw*3+",0,"+fw+","+fh+"]\n" +
                "       ]\n" +
                "     },\n" +
                "     {\n" +
                "       \"name\":\"walkLeftAnimation\",\n" +
                "       \"frameDuration\":0.1,\n" +
                "       \"frames\":[\n" +
                "         [0,"+fh+","+fw+","+fh+"],\n" +
                "         ["+fw+","+fh+","+fw+","+fh+"],\n" +
                "         ["+fw*2+","+fh+","+fw+","+fh+"],\n" +
                "         ["+fw*3+","+fh+","+fw+","+fh+"]\n" +
                "       ]\n" +
                "     },\n" +
                "     {\n" +
                "       \"name\":\"walkRightAnimation\",\n" +
                "       \"frameDuration\":0.1,\n" +
                "       \"frames\":[\n" +
                "         [0,"+fh*2+","+fw+","+fh+"],\n" +
                "         ["+fw+","+fh*2+","+fw+","+fh+"],\n" +
                "         ["+fw*2+","+fh*2+","+fw+","+fh+"],\n" +
                "         ["+fw*3+","+fh*2+","+fw+","+fh+"]\n" +
                "       ]\n" +
                "     },\n" +
                "     {\n" +
                "       \"name\":\"walkUpAnimation\",\n" +
                "       \"frameDuration\":0.1,\n" +
                "       \"frames\":[\n" +
                "         [0,"+fh*3+","+fw+","+fh+"],\n" +
                "         ["+fw+","+fh*3+","+fw+","+fh+"],\n" +
                "         ["+fw*2+","+fh*3+","+fw+","+fh+"],\n" +
                "         ["+fw*3+","+fh*3+","+fw+","+fh+"]\n" +
                "       ]\n" +
                "     },\n" +
                "     {\n" +
                "       \"name\":\"stopDownAnimation\",\n" +
                "       \"frameDuration\":0.5,\n" +
                "       \"frames\":[\n" +
                "         [0,0,"+fw+","+fh+"],\n" +
                "         ["+fw*2+",0,"+fw+","+fh+"]\n" +
                "       ]\n" +
                "     },\n" +
                "     {\n" +
                "       \"name\":\"stopLeftAnimation\",\n" +
                "       \"frameDuration\":0.1,\n" +
                "       \"frames\":[\n" +
                "         [0,"+fh+","+fw+","+fh+"],\n" +
                "         ["+fw*2+","+fh+","+fw+","+fh+"]\n" +
                "       ]\n" +
                "     },\n" +
                "     {\n" +
                "       \"name\":\"stopRightAnimation\",\n" +
                "       \"frameDuration\":0.1,\n" +
                "       \"frames\":[\n" +
                "         [0,"+fh*2+","+fw+","+fh+"],\n" +
                "         ["+fw*2+","+fh*2+","+fw+","+fh+"]\n" +
                "       ]\n" +
                "     },\n" +
                "     {\n" +
                "       \"name\":\"stopUpAnimation\",\n" +
                "       \"frameDuration\":0.1,\n" +
                "       \"frames\":[\n" +
                "         [0,"+fh*3+","+fw+","+fh+"],\n" +
                "         ["+fw*2+","+fh*3+","+fw+","+fh+"]\n" +
                "       ]\n" +
                "     },\n" +
                "     {\n" +
                "       \"name\":\"deathAnimation\",\n" +
                "       \"frameDuration\":0.1,\n" +
                "       \"frames\":[\n" +
                "         [0,"+fh+","+fw+","+fh+"],\n" +
                "         [0,"+fh+","+fw+","+fh+"],\n" +
                "         [0,"+fh*2+","+fw+","+fh+"],\n" +
                "         [0,256,"+fw+","+fh+"]\n" +
                "       ]\n" +
                "     }\n" +
                "  ]\n" +
                "}";


        System.out.println(json);


//        JsonWriter rootWriter = new JsonWriter(new PrintWriter(System.out));
//        try {
//            JsonWriter firstChild = rootWriter.object();
//            firstChild.set("name", "xxxx");
//            firstChild.set("width", pw / hn);
//            firstChild.set("height", ph / vn);
//            firstChild.set("texture", "");
//
//            JsonWriter animationChild = rootWriter.array("animations");
//            {
//                JsonWriter animationJ = animationChild.object();
//                animationJ.set("name",WALK_DOWN_ANIMATION);
//                animationJ.set("frameDuration",0.1f);
//                JsonWriter frameJ = animationJ.array("frames");
//            }
//            rootWriter.close();
//            rootWriter.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//

    }
}
