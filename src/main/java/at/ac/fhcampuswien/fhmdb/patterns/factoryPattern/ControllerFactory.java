package at.ac.fhcampuswien.fhmdb.patterns.factoryPattern;

import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ControllerFactory implements Callback<Class<?>, Object> {

    // TODO: create MyCtrl as singleton instance
    private static ControllerFactory instance;

    private ControllerFactory(){

    }

    public static ControllerFactory getInstance(){
        if(instance == null){
            return new ControllerFactory();
        }
        else return instance;
    }
    private static Map<Class<?>, Object> instances = new HashMap<>();

    @Override
    public Object call(Class<?> aClass) {
        if ((!instances.containsKey(aClass))) {
            try {
                Object instance = aClass.getDeclaredConstructor().newInstance();
                instances.put(aClass, instance);
                return instances.get(aClass);
            }
            catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        else return instances.get(aClass);
    }

}

