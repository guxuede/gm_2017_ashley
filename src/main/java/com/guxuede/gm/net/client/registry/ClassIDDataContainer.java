package com.guxuede.gm.net.client.registry;

import java.lang.reflect.InvocationTargetException;

public class ClassIDDataContainer<C> implements IDDataContainer {
   protected final ClassIDData<? extends C> data;

   public ClassIDDataContainer(Class<? extends C> aClass, Class<?>... constructorParameters) throws NoSuchMethodException {
      this.data = new ClassIDData<C>(aClass, constructorParameters);
   }

   public ClassIDData<? extends C> getIDData() {
      return this.data;
   }

   public C newInstance(Object... args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
      return this.data.newInstance(args);
   }
}
