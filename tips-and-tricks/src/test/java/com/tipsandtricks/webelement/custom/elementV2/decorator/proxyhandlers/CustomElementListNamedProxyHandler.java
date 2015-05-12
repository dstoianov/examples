package com.tipsandtricks.webelement.custom.elementV2.decorator.proxyhandlers;

import com.tipsandtricks.webelement.custom.elementV2.decorator.WrapperFactory;
import com.tipsandtricks.webelement.custom.elementV2.impl.IElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CustomElementListNamedProxyHandler implements InvocationHandler {

    private final ElementLocator locator;
    private final Class<IElement> clazz;
    private final String name;

    public CustomElementListNamedProxyHandler(ElementLocator locator, Class<IElement> clazz, String name) {
        this.locator = locator;
        this.clazz = clazz;
        this.name = name;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        if ("toString".equals(method.getName())) {
            return name;
        }
        // Находит список WebElement и обрабатывает каждый его элемент,
        // возвращает новый список с элементами кастомного класса
        List<WebElement> elements = locator.findElements();
        List<IElement> customs = new ArrayList<IElement>();

        int elementNumber = 0;
        for (WebElement element : elements) {
            IElement instance = WrapperFactory.createInstance(clazz, element);
            String nameForElementInList = String.format("%s [%d]", name, elementNumber);
            instance.setName(nameForElementInList);
            customs.add(instance);
            elementNumber++;
        }

        try {
            return method.invoke(customs, objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
    }
}