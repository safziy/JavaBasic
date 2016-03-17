package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.utils.ReadUtils;

public class BootstrapMethodsAttributeInfo extends AbstractAttributeInfo implements IAttributeInfo {
	short num_bootstrap_methods;
	List<BootstrapMethod> bootstrap_methods;

	public BootstrapMethodsAttributeInfo(ConstantPoolInfo constantPoolInfo, String name) {
		super(constantPoolInfo, name);
	}

	public void innerAnalyze(InputStream is) throws IOException {
		num_bootstrap_methods = ReadUtils.readShort(is, 2);
		bootstrap_methods = new LinkedList<BootstrapMethod>();
		for (int i = 0; i < num_bootstrap_methods; i++) {
			BootstrapMethod bootstrapMethod = new BootstrapMethod();
			bootstrapMethod.analyze(is);
			bootstrap_methods.add(bootstrapMethod);
		}
	}

	public void showInfo() {
		if (bootstrap_methods.size() > 0) {
			System.out.println("\tBootstrapMethods:");
//			System.out.println("\t\tstart\tline");
//			for (BootstrapMethod bootstrapMethod : bootstrap_methods) {
//				bootstrapMethod.showInfo();
//			}
		}
		System.out.println();
	}

	class BootstrapMethod {
		short bootstrap_method_ref;
		short num_bootstrap_arguments;
		List<Short> bootstrap_arguments;

		public void analyze(InputStream is) throws IOException {
			bootstrap_method_ref = ReadUtils.readShort(is, 2);
			num_bootstrap_arguments = ReadUtils.readShort(is, 2);
			for (int i = 0; i < num_bootstrap_arguments; i++) {
				bootstrap_arguments.add(ReadUtils.readShort(is, 2));
			}
		}

		public void showInfo() {
			// System.out.println("\t\t" + startPC + "\t" + lineNum);
		}
	}

}
