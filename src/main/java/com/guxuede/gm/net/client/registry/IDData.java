package com.guxuede.gm.net.client.registry;

public class IDData {
   private boolean isLocked = false;
   private int ID = -1;
   private String stringID = null;

   public void setData(int ID, String stringID) {
      if (this.isLocked) {
         throw new IllegalStateException("Cannot set ID data twice");
      } else {
         this.ID = ID;
         this.stringID = stringID;
         this.isLocked = true;
      }
   }

   public int getID() {
      if (!this.isLocked) {
         throw new IllegalStateException("Data ID has not been set");
      } else {
         return this.ID;
      }
   }

   public String getStringID() {
      if (!this.isLocked) {
         throw new IllegalStateException("Data stringID has not been set");
      } else {
         return this.stringID;
      }
   }

   public boolean isSet() {
      return this.isLocked;
   }
}
