package com.tipsandtricks.webelement.custom.elementV2.decorator;

import com.tipsandtricks.webelement.custom.elementV2.decorator.proxyhandlers.CustomElementListNamedProxyHandler;
import com.tipsandtricks.webelement.custom.elementV2.decorator.proxyhandlers.WebElementListNamedProxyHandler;
import com.tipsandtricks.webelement.custom.elementV2.decorator.proxyhandlers.WebElementNamedProxyHandler;
import com.tipsandtricks.webelement.custom.elementV2.impl.IElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.List;

public class CustomFieldDecoratorLists extends DefaultFieldDecorator {

    public CustomFieldDecoratorLists(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    /**
     * Метод вызывается фабрикой для каждого поля в классе
     */
    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<IElement> decoratableClass = decoratableClass(field);

        //get Name from @Name annotation for set it for element
        String elementName = Utils.getElementName(field);

        ElementLocator locator = factory.createLocator(field);
        if (locator == null) {
            return null;
        }

        // если класс поля декорируемый
        if (decoratableClass != null) {
            if (Utils.isList(field)) {
                return createCustomElementList(loader, locator, decoratableClass, elementName);
            } else {
                return createCustomElement(loader, locator, decoratableClass, elementName);
            }
        }


        if (WebElement.class.isAssignableFrom(field.getType())) {
            return createWebElement(loader, locator, elementName);
        } else if (Utils.isList(field)) {
            return createWebElementList(loader, locator, elementName);
        }

//        return super.decorate(loader, field); // if you no need to use Names in WebElements
        return null;
    }

    /**
     * Возвращает декорируемый класс поля,
     * либо null если класс не подходит для декоратора
     */
    @SuppressWarnings("unchecked")
    private Class<IElement> decoratableClass(Field field) {

        Class<?> clazz = field.getType();

        if (Utils.isList(field)) {
            // для списка обязательно должна быть задана аннотация
            if (field.getAnnotation(FindBy.class) == null &&
                    field.getAnnotation(FindBys.class) == null &&
                    field.getAnnotation(FindAll.class) == null) {
                return null;
            }

            // Список должен быть параметризирован
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            // получаем класс для элементов списка
            clazz = (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
        }

        if (IElement.class.isAssignableFrom(clazz)) {
            return (Class<IElement>) clazz;
        } else {
            return null;
        }
    }

    /**
     * Create custom element with name
     * find WebElement and pass it to the custom class IElement
     */
    protected IElement createCustomElement(ClassLoader loader, ElementLocator locator, Class<IElement> clazz, String name) {
        WebElement proxy = proxyForLocator(loader, locator);
        IElement instance = WrapperFactory.createInstance(clazz, proxy);
        instance.setName(name);
        return instance;
    }

    /**
     * Creating a List of custom elements with names
     */
    @SuppressWarnings("unchecked")
    protected List<IElement> createCustomElementList(ClassLoader loader, ElementLocator locator, Class<IElement> clazz, String name) {
        InvocationHandler handler = new CustomElementListNamedProxyHandler(locator, clazz, name);

        List<IElement> proxy;
        proxy = (List<IElement>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return proxy;
    }

    protected WebElement createWebElement(ClassLoader loader, ElementLocator locator, String name) {
        InvocationHandler handler = new WebElementNamedProxyHandler(locator, name);

        WebElement proxy;
        proxy = (WebElement) Proxy.newProxyInstance(
                loader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
        return proxy;
    }

    @SuppressWarnings("unchecked")
    protected List<WebElement> createWebElementList(ClassLoader loader, ElementLocator locator, String name) {
        InvocationHandler handler = new WebElementListNamedProxyHandler(locator, name);

        List<WebElement> proxy;
        proxy = (List<WebElement>) Proxy.newProxyInstance(
                loader, new Class[]{List.class}, handler);
        return proxy;
    }

}
