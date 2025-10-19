package com.guxuede.gm.net.client.registry;


import com.guxuede.gm.net.client.registry.pack.*;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

public class PacketRegistry extends ClassedGameRegistry<NetPack, PacketRegistry.PacketRegistryElement> {
   public static final PacketRegistry instance = new PacketRegistry();


   private PacketRegistry() {
      super("Packet", 65535);
   }

   public static void registerCore() {
      registerPacket(PlayerLoginPack.class);
      registerPacket(ActorLandingPack.class);
      registerPacket(ActorPositionPack.class);
      registerPacket(PlayerDisconnectedPack.class);
      registerPacket(PlayerChatPack.class);
      registerPacket(PlayerCommandPack.class);
      registerPacket(ActorUnLandingPack.class);
      registerPacket(ActorPlayAnimationPack.class);
      registerPacket(ActorPlaySkillPack.class);
   }

   protected void onRegistryClose() {
   }

   public static String getPacketSimpleName(int type) {
      try {
         return instance.getElement(type).simpleName;
      } catch (NoSuchElementException var2) {
         return "UnknownPacket0x" + Integer.toHexString(type);
      }
   }

   public static int registerPacket(Class<? extends NetPack> packetClass) {
      return registerPacket(false, packetClass);
   }

   public static int registerPacket(boolean timestamp, Class<? extends NetPack> packetClass) {
      return registerPacket(false, true, timestamp, packetClass);
   }

   public static int registerPacket(boolean processInstantly, boolean onlyConnectedClients, boolean timestamp, Class<? extends NetPack> packetClass) {

         try {
            PacketRegistryElement e = new PacketRegistryElement(processInstantly, onlyConnectedClients, timestamp, packetClass);
            return instance.register(e.simpleName, e);
         } catch (NoSuchMethodException var5) {
            throw new IllegalArgumentException(packetClass.getSimpleName() + " does not have a constructor with byte[] parameter");
         }

   }

   public static int getPacketID(Class<? extends NetPack> clazz) {
      return instance.getElementID(clazz);
   }

   public static boolean hasTimestamp(int type) {
      return instance.getElement(type).hasTimestamp;
   }

   public static boolean onlyConnectedClients(int type) {
      return instance.getElement(type).onlyConnectedClients;
   }

   public static boolean processInstantly(int type) {
      return instance.getElement(type).processInstantly;
   }

   public static NetPack createPacket(int type, ByteBuf data){
       try {
           return instance.getElement(type).newInstance(data);
       } catch (IllegalAccessException e) {
           throw new RuntimeException(e);
       } catch (InvocationTargetException e) {
           throw new RuntimeException(e);
       } catch (InstantiationException e) {
           throw new RuntimeException(e);
       }
   }

   public static String getPacketClassName(int type) {
      PacketRegistryElement element = instance.getElement(type);
      return element == null ? "NULL" : element.simpleName;
   }

   public static int getTotalRegistered() {
      return instance.size();
   }

   protected static class PacketRegistryElement extends ClassIDDataContainer<NetPack> {
      public final boolean processInstantly;
      public final boolean onlyConnectedClients;
      public final boolean hasTimestamp;
      public final String simpleName;

      public PacketRegistryElement(boolean processInstantly, boolean onlyConnectedClients, boolean hasTimestamp, Class<? extends NetPack> packetClass) throws NoSuchMethodException {
         super(packetClass, ByteBuf.class);
         this.processInstantly = processInstantly;
         this.onlyConnectedClients = onlyConnectedClients;
         this.hasTimestamp = hasTimestamp;
         this.simpleName = packetClass.getSimpleName();
      }
   }
}
