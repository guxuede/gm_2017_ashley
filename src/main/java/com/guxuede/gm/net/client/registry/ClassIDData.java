package com.guxuede.gm.net.client.registry;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ClassIDData<C> extends IDData {
   public final Class<? extends C> aClass;
   public final Constructor<? extends C> constructor;

   public ClassIDData(Class<? extends C> aClass, Class<?>... constructorParameters) throws NoSuchMethodException {
      this.aClass = aClass;
      this.constructor = aClass.getConstructor(constructorParameters);
   }

   public C newInstance(Object... args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
      return this.constructor.newInstance(args);
   }
}
