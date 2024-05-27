package at.ac.fhcampuswien.fhmdb.patterns.factoryPattern;

import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private static ControllerFactory instance;

    // TODO: create MyCtrl as singleton instance

    public static ControllerFactory getInstance() {
        if (instance == null) {
            return new ControllerFactory();
        } else return instance;
    }

    @Override
    public Object call(Class<?> aClass) {
        if (instance == null) {
            try {
                instance = (ControllerFactory) aClass.getDeclaredConstructor().newInstance();
                return instance;
            }
            catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        else return instance;
    }

}

