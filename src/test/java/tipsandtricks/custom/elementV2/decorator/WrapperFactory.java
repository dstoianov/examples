package tipsandtricks.custom.elementV2.decorator;

import org.openqa.selenium.WebElement;
import tipsandtricks.custom.elementV2.impl.IElement;

/**
 * Created by Funker on 08.01.2015.
 */
public class WrapperFactory {

    /**
     * Создает экземпляр класса,
     * реализующий IElement интерфейс,
     * вызывая конструктор с аргументом WebElement
     */
    public static IElement createInstance(Class<IElement> clazz, WebElement element) {
        try {
            return clazz.getConstructor(WebElement.class).newInstance(element);
        } catch (Exception e) {
            throw new AssertionError("WebElement can't be represented as " + clazz);
        }
    }

}
