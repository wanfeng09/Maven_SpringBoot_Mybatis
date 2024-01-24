package com.hui.framework;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具类
 */
public class WebResolveXML {
    public static <T> List<T> parse(String file,Class<T> targetClass){
        ArrayList<T> list = new ArrayList<>();
        try {
            // 获取解析器对象
            SAXReader saxReader = new SAXReader();
            // 利用解析器吧XML文件加载到内存中，并返回一个文档对象
            Document document = saxReader.read(new File(file));
            // 获取根标签
            Element rootElement = document.getRootElement();
            // 通过根标签获取info标签
            List<Element> elements = rootElement.elements("info");
            for (Element e: elements){
                String name = e.element("name").getText();
                String age = e.element("age").getText();
                // 组装数据
                // 获取有参数构造器
                Constructor<T> constructor = targetClass.getDeclaredConstructor(String.class,Integer.class);
                constructor.setAccessible(true);
                T object = constructor.newInstance(name,Integer.parseInt(age));
                list.add(object);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
