package com.safziy.jvm.classinfo.stackmaptable.verificationtypeinfo;

import java.io.IOException;
import java.io.InputStream;

public interface IVerificationTypeInfo {
	public void analyze(InputStream is) throws IOException;

	public void showInfo();
}
