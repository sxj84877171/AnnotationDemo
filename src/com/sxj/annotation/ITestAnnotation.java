package com.sxj.annotation;

public interface ITestAnnotation {
	
	@Get(value="testPath")
	String testMethod(@Path("path")String path,@Field("filed")String filed);

}
