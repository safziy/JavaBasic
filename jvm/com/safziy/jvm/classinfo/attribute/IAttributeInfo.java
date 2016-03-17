package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;

public interface IAttributeInfo {

	public void analyze(InputStream is) throws IOException;

	public void showInfo();
}
