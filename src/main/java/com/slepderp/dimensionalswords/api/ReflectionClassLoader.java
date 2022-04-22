package com.slepderp.dimensionalswords.api;

import com.slepderp.dimensionalswords.DimensionalSwords;

public class ReflectionClassLoader {

	 public static void classLoad(String classNamePath) {
	        try {
	            Class.forName(classNamePath);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            DimensionalSwords.error("CLASSLOAD", "Failed to load a class. This error probably happened due to file corruption, so please try downloading the mod again. If you're running dataGen, ignore this message");
	        }
	    }
	 
}
