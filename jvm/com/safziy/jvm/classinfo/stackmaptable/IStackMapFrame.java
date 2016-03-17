package com.safziy.jvm.classinfo.stackmaptable;

import java.io.IOException;
import java.io.InputStream;

public interface IStackMapFrame {
	public void analyze(InputStream is) throws IOException;

	public void showInfo();
}
