package com.safziy.jvm.classinfo.attribute;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.safziy.jvm.classinfo.ConstantPoolInfo;
import com.safziy.jvm.classinfo.accessflag.InnerClassAccessFlags;
import com.safziy.jvm.classinfo.utils.ReadUtils;

/**
 * InnerClasses
 * 
 * @author safziy
 *
 */
public class InnerClassesAttributeInfo extends AbstractAttributeInfo implements IAttributeInfo {
	short number_of_classes;
	List<InnerClasseInfo> inner_classes;

	public InnerClassesAttributeInfo(ConstantPoolInfo constantPoolInfo, String name) {
		super(constantPoolInfo, name);
	}

	public void innerAnalyze(InputStream is) throws IOException {
		number_of_classes = ReadUtils.readShort(is, 2);
		inner_classes = new LinkedList<InnerClasseInfo>();
		for (int i = 0; i < number_of_classes; i++) {
			InnerClasseInfo innerClasseInfo = new InnerClasseInfo();
			innerClasseInfo.analyze(is);
			inner_classes.add(innerClasseInfo);
		}
	}

	public void showInfo() {
		if (inner_classes.size() > 0) {
			System.out.println("\tInnerClasses:");
			System.out.println("\t\tinnerclass\touterclass\tinnerclassname\taccessflag");
			for (InnerClasseInfo innerClasseInfo : inner_classes) {
				innerClasseInfo.showInfo();
			}
		}
		System.out.println();
	}

	/**
	 * inner_classes_info
	 * 
	 * @author safziy
	 *
	 */
	class InnerClasseInfo {
		short inner_class_info_index;
		short outer_class_info_index;
		short inner_name_index;
		short inner_class_access_flags;

		public void analyze(InputStream is) throws IOException {
			inner_class_info_index = ReadUtils.readShort(is, 2);
			outer_class_info_index = ReadUtils.readShort(is, 2);
			inner_name_index = ReadUtils.readShort(is, 2);
			inner_class_access_flags = ReadUtils.readShort(is, 2);
		}

		public void showInfo() {
			System.out.print("\t\t" + constantPoolInfo.getConstantValue(inner_class_info_index) + "\t"
					+ constantPoolInfo.getConstantValue(outer_class_info_index) + "\t"
					+ constantPoolInfo.getConstantValue(inner_name_index) + "\t");
			InnerClassAccessFlags.showInfo(inner_class_access_flags);
		}
	}

}
