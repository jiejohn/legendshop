package com.done.util;

import java.io.File;
/**
 * 按时间排序
 * @author hewq
 *
 */
 public class FileTimeWrapper implements Comparable {
	     /** File */  
	     private File file;  
	       
	     public FileTimeWrapper(File file) {  
	         this.file = file;  
	     }  
	        
	     public int compareTo(Object obj) {  
	         assert obj instanceof FileTimeWrapper;  
	           
	         FileTimeWrapper castObj = (FileTimeWrapper)obj; 
	         if (this.file.lastModified()- castObj.getFile().lastModified() > 0) {  
	             return -1;  
	         } else if (this.file.lastModified()- castObj.getFile().lastModified() < 0) {  
	             return 1;  
	         } else {  
	             return 0;  
	         }  
	     }  
	       
	     public File getFile() {  
	         return this.file;  
	     }  
	 }  