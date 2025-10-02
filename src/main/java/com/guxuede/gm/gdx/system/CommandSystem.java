package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.guxuede.gm.net.client.registry.pack.PlayerLoginPack;
import com.guxuede.samplegame.DesktopLauncher;
import picocli.CommandLine;

public class CommandSystem extends EntitySystem {

    private static Engine engine;

    public CommandSystem(Engine engine) {
        this.engine = engine;
    }

    public void executeCommand(String line){
        CommandLine commandLine = new CommandLine(new GameCommand());
        commandLine.execute(line.split(" "));
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
            DesktopLauncher.currentUserName = username;
            PlayerLoginPack playerLandingPack = new PlayerLoginPack(username, password);
            engine.getSystem(GlobalNetPackSystem.class).outboundNetPack(playerLandingPack);
        }
    }
}
