package com.guxuede.gm.net.client.registry;

import java.util.HashMap;
import java.util.NoSuchElementException;

public abstract class ClassedGameRegistry<C, T extends ClassIDDataContainer<? extends C>> extends GameRegistry<T> {
   private final HashMap<Class<? extends C>, Integer> classToIDMap = new HashMap();

   public ClassedGameRegistry(String objectCallName, int maxSize) {
      super(objectCallName, maxSize);
   }

   public ClassedGameRegistry(String objectCallName, int maxSize, boolean stringIDUnique) {
      super(objectCallName, maxSize, stringIDUnique);
   }

   protected void onRegister(T object, int id, String stringID, boolean isReplace) {
      this.classToIDMap.put(object.data.aClass, id);
   }

   public int getElementID(Class<? extends C> clazz) {
      try {
         return this.getElementIDRaw(clazz);
      } catch (NoSuchElementException var3) {
         return -1;
      }
   }

   public int getElementIDRaw(Class<? extends C> clazz) throws NoSuchElementException {
      Integer id = this.classToIDMap.get(clazz);
      if (id == null) {
         throw new NoSuchElementException();
      } else {
         return id;
      }
   }

   public void applyIDData(Class<? extends C> clazz, IDData objectIDData) {
      try {
         int id = this.getElementIDRaw(clazz);
         ClassIDData<? extends C> registryIDData = this.getElement(id).data;
         objectIDData.setData(registryIDData.getID(), registryIDData.getStringID());
      } catch (NoSuchElementException var5) {
         throw new IllegalStateException("Cannot construct unregistered " + this.objectCallName + " class " + clazz.getSimpleName());
      }
   }
}
