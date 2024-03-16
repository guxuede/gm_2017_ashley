package com.guxuede.gm.gdx.actor.parser;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import com.guxuede.gm.gdx.GdxSprite;
import com.guxuede.gm.gdx.MultiInOneSprite;
import com.guxuede.gm.gdx.ResourceManager;

public class DefaultSpriteParser extends AbstractSpriteParser {


    /**
     * 解析
     * {
     "name" : "Undead",
     "width":80,
     "height":96,
     "texture":"Undead",
     "animations":。。。
     }
     * @param jsonValue
     * @return
     */
    public ActorHolder parseAnimationHolder(JsonValue jsonValue){
        ActorHolder actorHolder = new ActorHolder();
        ParseContext parseContext = extendParentParseContext(null, jsonValue);
        String name = jsonValue.getString("name");
        ///////////////////////////actor attributes///////////////////////////////////
        parseActorAttributes(jsonValue, parseContext, actorHolder);
        /////////////////////////animations/////////////////////////////
        AnimationHolder animationHolder= new AnimationHolder();
        animationHolder.width = (int) parseContext.width;
        animationHolder.height = (int) parseContext.height;
        animationHolder.name = name;
        parseAnimations(jsonValue, parseContext, animationHolder);
        /////////////////////////sounds/////////////////////////////
        parseSounds(jsonValue, animationHolder);
        actorHolder.animationHolder = animationHolder;
        actorHolder.name = name;
        return actorHolder;
    }

    private static void parseSounds(JsonValue jsonValue, AnimationHolder animationHolder) {
        JsonValue soundsJ = jsonValue.get("sounds");
        if(soundsJ !=null){
            JsonValue.JsonIterator sit = soundsJ.iterator();
            for(;sit.hasNext();) {
                JsonValue soundJ = sit.next();
                String soundName= soundJ.getString("name");
                String soundFile = soundJ.getString("sound");
                boolean isLoop = soundJ.getBoolean("loop",false);
                Sound sound = ResourceManager.getSoundOrLoad(soundFile);
                if(sound!=null){
                    if(!soundName.contains(",")){
                        //animationHolder.getAnimation(S)
                        //animationHolder.addSound(soundName,new SoundHolder(sound,isLoop));
                    }else{
                        for(String sn : soundName.split(",")){
                            Animation<TextureRegion> animation = animationHolder.getAnimation(sn.hashCode());
                            if(animation!=null){
                                final TextureRegion sptite = animation.getKeyFrames()[0];
                                if(sptite instanceof GdxSprite){
                                    ResourceManager.putSpriteSound(((GdxSprite) sptite).id,sound);
                                }
                                final TextureRegion sptite2 = animation.getKeyFrames()[2];
                                if(sptite2 instanceof GdxSprite){
                                    ResourceManager.putSpriteSound(((GdxSprite) sptite2).id,sound);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected void parseAnimations(JsonValue jsonValue, ParseContext parseContext, AnimationHolder animationHolder) {
        JsonValue animationsJ = jsonValue.get("animations");
        JsonValue.JsonIterator it = animationsJ.iterator();
        for(;it.hasNext();){
            JsonValue animationJ = it.next();
            String animationName = animationJ.getString("name");
            Animation animation = parseAnimation(parseContext,animationJ);
            if(!animationName.contains(",")){
                animationHolder.addAnimation(animationName.hashCode(),animation);
            }else{
                for(String an : animationName.split(",")){
                    animationHolder.addAnimation(an.hashCode(),animation);
                }
            }
        }
    }

    protected void parseActorAttributes(JsonValue jsonValue, ParseContext parseContext, ActorHolder actorHolder){

    }

    /**
     * 解析
     *  "animations":[
     {
     "name":"walkDownAnimation",
     "frameDuration":0.1,
     "frames":...
     }
     *  ]
     * @param parentParseContext
     * @param animJ
     * @return
     */
    protected Animation parseAnimation(ParseContext parentParseContext, JsonValue animJ){
        ParseContext localParseContext = extendParentParseContext(parentParseContext, animJ);

        JsonValue framesJ = animJ.get("frames");
        final GdxSprite[] frames = new GdxSprite[framesJ.size];
        JsonValue.JsonIterator it = framesJ.iterator();
        for (int i = 0; it.hasNext() ; i++) {
            JsonValue frameJ = it.next();
            if(frameJ.isNumber()){
                frames[i]=parseNumbersSprite(localParseContext, frameJ.asInt());
            }else if(frameJ.isArray()){
                frames[i]=parseSingleArraySprite(localParseContext,frameJ);
            }else{
                if(frameJ.has("frame")){
                    frames[i]=parseSingleStyleSprite(localParseContext,frameJ);
                }else{
                    frames[i]=parseMultiSprite(localParseContext,frameJ);
                }
                if(frames[i]!=null && frameJ.has("sound")){
                    parseSound(i,frames[i],frameJ.get("sound"));
                }
            }
        }

        final Animation animation = new Animation(localParseContext.frameDuration,frames);
        return animation;
    }

    protected void parseSound(int frameIndex,GdxSprite gdxSprite,JsonValue soundJ){
        String soundName = soundJ.asString();
        Sound sound = ResourceManager.getSoundOrLoad(soundName);
        if(sound!=null){
            ResourceManager.putSpriteSound(gdxSprite.id,sound);
        }
    }

    /**
     * 解析 frames 里 直接标明图块位置的，如：
     * "frames"：[1,2,3,4,5]
     * @param parentParseContext
     * @param number
     * @return
     */
    protected GdxSprite parseNumbersSprite(ParseContext parentParseContext, int number){
        TextureRegion frame = getTextureRegionByNumber(parentParseContext, number);
        GdxSprite sprite = new GdxSprite(frame);
        sprite.setSize(parentParseContext.width, parentParseContext.height);
        sprite.setDrawOffSetX(parentParseContext.drawOffSetX, parentParseContext.drawOffSetY);
        sprite.setScale(parentParseContext.scaleX, parentParseContext.scaleY);
        sprite.setRotation(parentParseContext.rotation);
        sprite.setAlpha(parentParseContext.alpha);
        return sprite;
    }

    protected GdxSprite parseNumbersSprite(ParseContext parentParseContext,TextureRegion textureRegion, int number){
        TextureRegion frame = getTextureRegionByNumber(parentParseContext,textureRegion, number);
        GdxSprite sprite = new GdxSprite(frame);
        sprite.setSize(parentParseContext.width, parentParseContext.height);
        sprite.setDrawOffSetX(parentParseContext.drawOffSetX, parentParseContext.drawOffSetY);
        sprite.setScale(parentParseContext.scaleX, parentParseContext.scaleY);
        sprite.setRotation(parentParseContext.rotation);
        sprite.setAlpha(parentParseContext.alpha);
        return sprite;
    }


    /**
     * 解析frames里面只有一层动画的，比如：
     "frames":[
     [0,0,80,96],
     [80,0,80,96],
     [160,0,80,96],
     [240,0,80,96]
     ]
     * @param parentParseContext
     * @param posIntArrayJ
     * @return
     */
    protected GdxSprite parseSingleArraySprite(ParseContext parentParseContext, JsonValue posIntArrayJ){
        TextureRegion frame = getTextureRegion(parentParseContext, posIntArrayJ);
        GdxSprite sprite = new GdxSprite(frame);
        sprite.setSize(parentParseContext.width, parentParseContext.height);
        sprite.setDrawOffSetX(parentParseContext.drawOffSetX, parentParseContext.drawOffSetY);
        sprite.setScale(parentParseContext.scaleX, parentParseContext.scaleY);
        sprite.setRotation(parentParseContext.rotation);
        sprite.setAlpha(parentParseContext.alpha);
        return sprite;
    }

    /**
     * 解析：frames 里面有样式动画的，比如
     *   "frames":[
     {
     "scale":1.5,
     "drawOffSetY":1,
     "frame":1
     },
     {
     "scale":1.5,
     "drawOffSetY":1,
     "frame":2
     },
     ]
     * @param parentParseContext
     * @param frameJ
     * @return
     */
    protected GdxSprite parseSingleStyleSprite(ParseContext parentParseContext, JsonValue frameJ){
        ParseContext localParseContext = extendParentParseContext(parentParseContext, frameJ);
        return parseSingleArraySprite(localParseContext,frameJ.get("frame"));
    }


    /**
     * 解析：frames 里面有多层动画的，比如
     * "frames":[
     {
     "frameElements":[。。。]
     },
     {
     "frameElements":[。。。]
     },
     ]
     * @param parentParseContext
     * @param frameJ
     * @return
     */
    protected GdxSprite parseMultiSprite(ParseContext parentParseContext, JsonValue frameJ){
        JsonValue frameElementsJ = frameJ.get("frameElements");
        JsonValue.JsonIterator it = frameElementsJ.iterator();

        final GdxSprite[] frames = new GdxSprite[frameElementsJ.size];
        for (int i = 0; it.hasNext() ; i++) {
            /**
             * frameElements 支持一下格式：
             *             "frameElements":[
             {
             "drawOffSetY":20,                    //详细的位置描述
             "scale":1.5,
             "frame":[576,192,192,192]
             }
             */
            JsonValue frameElementJ = it.next();
            if(frameElementJ.isObject()){
                ParseContext localParseContext = extendParentParseContext(parentParseContext, frameElementJ);
                TextureRegion textureRegion = getTextureRegion(localParseContext, frameElementJ.get("frame"));
                GdxSprite spriteElement = new GdxSprite(textureRegion);
                applyDefaultValueToSprite(localParseContext,spriteElement);
                frames[i] = spriteElement;
            }else if(frameElementJ.isNumber()){
                TextureRegion textureRegion = getTextureRegionByNumber(parentParseContext, frameElementJ.asInt());
                GdxSprite spriteElement = new GdxSprite(textureRegion);
                applyDefaultValueToSprite(parentParseContext, spriteElement);
                frames[i] = spriteElement;
            }else{
                throw new RuntimeException("frameElements define not correct:"+frameElementJ.toString());
            }
        }

        MultiInOneSprite sprite = new MultiInOneSprite(frames);
        sprite.setRegionHeight((int) parentParseContext.width);
        sprite.setRegionWidth((int) parentParseContext.height);
        return sprite;
    }


    /**
     * 解析 frame 支持以下格式：
     * "frame":1 位置描述
     * "frame":[1] 位置描述
     * "frame":[576,192]            像素级位置描述 --》 [576,192,defaultW,defaultH]
     * "frame":[576,192,192]        像素级位置描述--》 [576,192,192,192]
     * "frame":[576,192,192,192]    像素级位置描述--》 [576,192,192,192]
     * @param parseContext
     * @param posIntArrayJ
     * @return
     */
    protected TextureRegion getTextureRegion(ParseContext parseContext, JsonValue posIntArrayJ){
        if(posIntArrayJ.isNumber()){
            return getTextureRegionByNumber(parseContext,posIntArrayJ.asInt());
        }else if(posIntArrayJ.isArray()){
            int[] xywh = posIntArrayJ.asIntArray();
            if(xywh.length == 1){
                return getTextureRegionByNumber(parseContext,xywh[0]);
            }else if(xywh.length == 2){
                return getTextureRegionByXYWH(parseContext, new int[]{xywh[0], xywh[1], (int) parseContext.width, (int) parseContext.height});
            }else if(xywh.length == 3){
                return getTextureRegionByXYWH(parseContext,new int[]{xywh[0],xywh[1], xywh[2] ,xywh[2]});
            }else if(xywh.length == 4){
                return getTextureRegionByXYWH(parseContext,new int[]{xywh[0],xywh[1], xywh[2] ,xywh[3]});
            }
        }
        throw new RuntimeException("frame define not correct:"+posIntArrayJ.toString());
    }

    protected TextureRegion getTextureRegionByNumber(ParseContext parseContext, int number){
        TextureRegion textureRegion = getTextureRegionByName(parseContext);
        int numOfLine = textureRegion.getRegionWidth() / (int) parseContext.width;//每行有几个图片
        int y = number / numOfLine;//第几行
        int x = number % numOfLine;//第几列
        return getTextureRegionByXYWH(parseContext,new int[]{(int) (x* parseContext.width + parseContext.textureOffSetX),(int) (y* parseContext.height + parseContext.textureOffSetY),(int) (parseContext.width),(int) (parseContext.height)});
    }

    protected TextureRegion getTextureRegionByNumber(ParseContext parseContext, TextureRegion textureRegion, int number){
        int numOfLine = textureRegion.getRegionWidth() / (int) parseContext.width;//每行有几个图片
        int y = number / numOfLine;//第几行
        int x = number % numOfLine;//第几列
        return getTextureRegionByXYWH(parseContext,textureRegion, new int[]{(int) (x* parseContext.width + parseContext.textureOffSetX),(int) (y* parseContext.height + parseContext.textureOffSetY),(int) (parseContext.width),(int) (parseContext.height)});
    }

    protected TextureRegion getTextureRegionByXYWH(ParseContext parseContext, TextureRegion textureRegion, int[] xywh){
        return new TextureRegion(textureRegion,xywh[0],xywh[1],xywh[2],xywh[3]);
    }

    protected TextureRegion getTextureRegionByXYWH(ParseContext parseContext, int[] xywh){
        return new TextureRegion(getTextureRegionByName(parseContext),xywh[0],xywh[1],xywh[2],xywh[3]);
    }

    protected TextureRegion getTextureRegionByName(ParseContext parseContext){
        if(parseContext.texture==null){
            parseContext.texture = ResourceManager.getTextureRegion(parseContext.textureName, (int) parseContext.textureOffSetX, (int) parseContext.textureOffSetY);
        }
        return parseContext.texture;
    }

    protected ParseContext extendParentParseContext(ParseContext parseContext, JsonValue animJ){
        if(parseContext == null){
            parseContext = buildDefaultParserContext();
        }
        float frameDuration = animJ.getFloat("frameDuration", parseContext.frameDuration);
        String textureName = animJ.getString("texture", parseContext.textureName);
        float drawOffSetX = animJ.getFloat("drawOffSetX", parseContext.drawOffSetX);
        float drawOffSetY = animJ.getFloat("drawOffSetY", parseContext.drawOffSetY);
        float width = animJ.getFloat("width", parseContext.width);
        float height = animJ.getFloat("height", parseContext.height);
        float alpha = animJ.getFloat("alpha", parseContext.alpha);
        float rotation = animJ.getFloat("rotation", parseContext.rotation);
        float scaleX = parseContext.scaleX, scaleY = parseContext.scaleY;
        if(animJ.has("scale")){
            scaleX = scaleY = animJ.getFloat("scale", 1);
        }
        if(animJ.has("scaleX")){
            scaleX = animJ.getFloat("scaleX", scaleX);
        }
        if(animJ.has("scaleY")){
            scaleY = animJ.getFloat("scaleY", scaleY);
        }

        float textureOffSetX = animJ.getFloat("textureOffSetX",parseContext.textureOffSetX);
        float textureOffSetY = animJ.getFloat("textureOffSetY",parseContext.textureOffSetY);

        String sound = animJ.getString("sound",parseContext.sound);

        ParseContext localParseContext = new ParseContext();
        localParseContext.textureName = textureName;
        localParseContext.width = width;
        localParseContext.height = height;
        localParseContext.alpha = alpha;
        localParseContext.rotation =rotation;
        localParseContext.scaleX = scaleX;
        localParseContext.scaleY = scaleY;
        localParseContext.drawOffSetX =drawOffSetX;
        localParseContext.drawOffSetY =drawOffSetY;
        localParseContext.frameDuration = frameDuration;
        localParseContext.textureOffSetX = textureOffSetX;
        localParseContext.textureOffSetY = textureOffSetY;
        localParseContext.sound = sound;
        return localParseContext;
    }

    protected ParseContext buildDefaultParserContext() {
        ParseContext parseContext;
        parseContext = new ParseContext();
        parseContext.alpha = 1;
        parseContext.scaleX = 1;
        parseContext.scaleY = 1;
        parseContext.frameDuration = 0.1f;
        return parseContext;
    }

    protected void applyDefaultValueToSprite(ParseContext parseContext, GdxSprite sprite){
        sprite.setSize(parseContext.width, parseContext.height);
        sprite.setDrawOffSetX(parseContext.drawOffSetX, parseContext.drawOffSetY);
        sprite.setScale(parseContext.scaleX,parseContext.scaleY);
        sprite.setRotation(parseContext.rotation);
        sprite.setAlpha(parseContext.alpha);
    }


}
