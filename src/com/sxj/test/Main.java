package com.sxj.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.sxj.annotation.Field;
import com.sxj.annotation.Get;
import com.sxj.annotation.ITestAnnotation;
import com.sxj.annotation.Path;

public class Main {

	public static void main(String[] args) {

		ITestAnnotation test = (ITestAnnotation) Proxy.newProxyInstance(ITestAnnotation.class.getClassLoader(), new Class[] { ITestAnnotation.class },
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						StringBuilder sb = new StringBuilder();
						Get get = method.getAnnotation(Get.class);
						String value = get.value();
						sb.append(value).append("/");
						Annotation[][] anss = method.getParameterAnnotations();
						int i = 0 ;
						for (Annotation[] ans : anss) {
							for (Annotation an : ans) {
								if(an instanceof Path){
									Path path = (Path)an;
									sb.append(path.value()).append("=").append(args[i++]).append("/");
								}
								if(an instanceof Field){
									Field filed = (Field)an;
									sb.append(filed.value()).append("=").append(args[i++]).append("/");
								}
							}
						}
						return sb.toString();
					}

				});

		String result = test.testMethod("code_path","filed");

		System.out.println(result);
		
		
	}

}
