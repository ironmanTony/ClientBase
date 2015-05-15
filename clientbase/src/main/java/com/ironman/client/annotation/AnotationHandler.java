package com.ironman.client.annotation;

import com.ironman.client.params.Param;

/**
 * 获取方法上的注解
 * public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
 AnnotationTest2 annotationTest2 = new AnnotationTest2();
 //获取AnnotationTest2的Class实例
 Class<AnnotationTest2> c = AnnotationTest2.class;
 //获取需要处理的方法Method实例
 Method method = c.getMethod("execute", new Class[]{});
 //判断该方法是否包含MyAnnotation注解
 if(method.isAnnotationPresent(MyAnnotation.class)){
 //获取该方法的MyAnnotation注解实例
 MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
 //执行该方法
 method.invoke(annotationTest2, new Object[]{});
 //获取myAnnotation
 String[] value1 = myAnnotation.value1();
 System.out.println(value1[0]);
 }
 //获取方法上的所有注解
 Annotation[] annotations = method.getAnnotations();
 for(Annotation annotation : annotations){
 System.out.println(annotation);
 }
 }
 * Created by ironmanli on 15-5-14.
 */
public class AnotationHandler {


    public Param handleRequestAnnotation(Class[] classes){
        if(classes != null&&classes.length>0){
            Class clazz ;
            for(int index = 0;index<classes.length;index++){
                clazz = classes[index];
                if(clazz.isAnnotationPresent(Request.class)){
                    //TODO 生成对象，并且对他做处理，处理成request，使用volley。



                }
            }
        }
        return null;

    }

    //TODO 通过Dager生成对象
    public <T> T getObject(Class<T> c){
        return null;
    }
}
