package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class AttributeInfoFactory {
	public static String CODE = "Code";
	public static String CONSTANTVALUE = "ConstantValue";
	public static String DEPRECATED = "Deprecated";
	public static String EXCEPTIONS = "Exceptions";
	public static String ENCLOSINGMETHOD = "EnclosingMethod";
	public static String INNERCLASSES = "InnerClasses";
	public static String LINENUMBERTABLE = "LineNumberTable";
	public static String LOCALVARIABLETABLE = "LocalVariableTable";
	public static String STACKMAPTABLE = "StackMapTable";
	public static String SIGNATURE = "Signature";
	public static String SOURCEFILE = "SourceFile";
	public static String SOURCEDEBUGEXTENSION = "SourceDebugExtension";
	public static String SYNTHETIC = "Synthetic";
	public static String LOCALVARIABLETYPETABLE = "LocalVariableTypeTable";
	public static String RUNTIMEVISIBLEANNOTATIONS = "RuntimeVisibleAnnotations";
	public static String RUNTIMEINVISIBLEANNOTATIONS = "RuntimeInvisibleAnnotations";
	public static String RUNTIMEVISIBLEPARAMETERANNOTATIONS = "RuntimeVisibleParameterAnnotations";
	public static String RUNTIMEINVISIBLEPARAMETERANNOTATIONS = "RuntimeInvisibleParameterAnnotations";
	public static String ANNOTATIONDEFAULT = "AnnotationDefault";
	public static String BOOSTSTRAPMETHODS = "BooststrapMethods";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static IAttributeInfo analyze(InputStream is, ConstantPoolInfo constantPoolInfo) throws IOException {
		short attributeType = ReadUtils.readShort(is, 2);
		String attributeName = constantPoolInfo.getConstantValue(attributeType);
		IAttributeInfo attribute;
		String className = AttributeInfoFactory.class.getPackage().getName() + "." + attributeName + "AttributeInfo";
		try {
			Class clazz = Class.forName(className);
			Constructor constructor = clazz.getConstructor(ConstantPoolInfo.class, String.class);
			attribute = (IAttributeInfo) constructor.newInstance(constantPoolInfo, attributeName);
		} catch (Exception e) {
			attribute = new UndefineAttributeInfo(constantPoolInfo, attributeName);
		}
		attribute.analyze(is);
		return attribute;
	}
}
