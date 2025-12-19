package com.guxuede.gm.gdx.system.render;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.net.client.registry.pack.PlayerChatPack;
import com.guxuede.gm.net.client.registry.pack.PlayerCommandPack;
import com.guxuede.gm.net.component.PlayerDataComponent;
import com.guxuede.gm.net.system.GlobalNetPackSystem;
import com.guxuede.gm.net.client.registry.pack.PlayerLoginPack;
import com.guxuede.samplegame.DesktopLauncher;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;

public class CommandSystem extends EntitySystem {

    private static Engine engine;

    public CommandSystem(Engine engine) {
        this.engine = engine;
    }

    public void executeCommand(Entity entity,String lineChat){
        if(lineChat.startsWith("/")){
            CommandLine commandLine = new CommandLine(new GameCommand());
            String command = StringUtils.substringAfter(lineChat, "/");
            int execute = commandLine.execute(command.split(" "));
            //if == 0, then local command
            //elase remove command
            if(execute != 0){
                if(entity!=null){
                    PlayerDataComponent playerDataComponent = Mappers.netPackCM.get(entity);
                    PlayerCommandPack playerCommandPack = new PlayerCommandPack(playerDataComponent.id, command);
                    playerDataComponent.outBoundPack(playerCommandPack);
                }else{
                    System.out.println("还没有选择角色,无法执行远程命令");
                }
            }
        }else{
            if(entity!=null){
                PlayerDataComponent playerDataComponent = Mappers.netPackCM.get(entity);
                PlayerChatPack playerChatPack = new PlayerChatPack(playerDataComponent.id, playerDataComponent.userName, lineChat);
                playerDataComponent.outBoundPack(playerChatPack);
            }else{
                System.out.println("还没有选择角色,无法聊天");
            }
        }

    }

    @CommandLine.Command(
            subcommands = {
                    LandingCommand.class,
                    HiCommand.class,
                    LoginCommand.class,
            }
    )
    public static class GameCommand implements Runnable{

        @Override
        public void run() {
            System.out.printf("123");
        }
    }


    @CommandLine.Command(
            name = "hi"
    )
    public static  class HiCommand implements Runnable{

        public HiCommand() {
        }

        @Override
        public void run() {
            System.out.println("hi");
        }
    }


    @CommandLine.Command(
            name = "landing" // landing character=RPGMarkGreg id=123 x=100 y=200
    )
    public static class LandingCommand implements Runnable{

        @CommandLine.Option(names = {"id"})
        private int id;

        @CommandLine.Option(names = {"x"})
        private int x;

        @CommandLine.Option(names = {"y"})
        private int y;

        @CommandLine.Option(names = {"character"})
        private String character;

        public LandingCommand() {
        }

        @Override
        public void run() {

        }
    }

    @CommandLine.Command(
            name = "login"  //login username=guxuede password=123
    )
    public static class LoginCommand implements Runnable{

        @CommandLine.Option(names = {"username"})
        private String username;

        @CommandLine.Option(names = {"password"})
        private String password;

        public LoginCommand() {
        }

        @Override
        public void run() {
            PlayerLoginPack playerLoginPack = new PlayerLoginPack(username, password, DesktopLauncher.currentUserName);
            engine.getSystem(GlobalNetPackSystem.class).outboundNetPack(playerLoginPack);
        }
    }

    @CommandLine.Command(
            name = "transport"  //transport x=0 y=0
    )
    public static class TransportCommand implements Runnable{

        @CommandLine.Option(names = {"x"})
        private String x;

        @CommandLine.Option(names = {"y"})
        private String y;

        public TransportCommand() {
        }

        @Override
        public void run() {

        }
    }

}
