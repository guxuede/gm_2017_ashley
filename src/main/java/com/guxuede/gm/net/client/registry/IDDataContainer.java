package com.guxuede.gm.net.client.registry;

public interface IDDataContainer {
   IDData getIDData();

   default String getStringID() {
      return this.getIDData().getStringID();
   }

   default int getID() {
      return this.getIDData().getID();
   }
}
