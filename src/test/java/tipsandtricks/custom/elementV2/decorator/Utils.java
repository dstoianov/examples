package tipsandtricks.custom.elementV2.decorator;

import org.apache.commons.lang.WordUtils;
import tipsandtricks.custom.elementV2.impl.Name;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Funker on 10.01.2015.
 */
public class Utils {

    public static String getElementName(Field field) {
        if (field.isAnnotationPresent(Name.class)) {
            return field.getAnnotation(Name.class).value();
        }
        if (field.getType().isAnnotationPresent(Name.class)) {
            return field.getType().getAnnotation(Name.class).value();
        } else {
            return splitCamelCase(field.getName());
        }
    }

    public static boolean isList(Field field) {
        return List.class.isAssignableFrom(field.getType());
    }

    private static String splitCamelCase(String camel) {
        return WordUtils.capitalizeFully(camel.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        ));
    }
}
