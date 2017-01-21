package com.rookiex.image.util;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.opencv_core.Mat;

public class MyRoberts {
	public static Mat roberts(Mat src,Mat out) {		
		out = src.clone();
		int nbChannels = src.channels();  
		int nRows = src.rows();  
		int nCols = src.cols() * nbChannels ;  	
		//输出值
	    int temp;	    
		int i,j;  
	    for(i = 1; i < nRows; ++i){     	
	    	BytePointer o = out.ptr(i);		    	
	        for(j = 1; j < nCols; ++j){          	
	        	//将输出mat置0；         
		        o.put(j, (byte)0); 		        
	        	//取出abcd四个的值
	        	BytePointer s1 = src.ptr(i);
	        	BytePointer s2 = src.ptr(i+1);        	
	            int val_a = MyRoberts.transform(s1.get(j));
	            int val_b = MyRoberts.transform(s2.get(j));
	            int val_c = MyRoberts.transform(s1.get(j+1));
	            int val_d = MyRoberts.transform(s2.get(j+1));            
	            temp =(int) (Math.sqrt((float) ((val_a - val_d) * (val_a - val_d) + (val_b - val_c) * (val_b - val_c))));          
	            if(temp > 20) {
	            	temp = -1;
	            } else {
	            	temp = 0;
	            }
	            if(temp!=0 && temp != -1)
	            	System.out.println(temp);	            
	            o.put(j, (byte)temp);          
	        }  
	    }
		return out;
	}
	
	public static int transform(int num) {
		if(num<0) {
			num = num + 255;
		}
		return num;		
	}
}
