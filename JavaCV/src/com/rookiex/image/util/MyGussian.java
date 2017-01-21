package com.rookiex.image.util;

import java.util.Random;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.opencv_core.Mat;

/**
 * @author RookieXu
 * @功能 		配置自己的高斯滤波功能
 * @日期和时间 2016年12月18日 下午10:35:46
 * @版本		功能转移到util类中、仅用于代码修改测试
 *
 */
public class MyGussian {
	
	public static Mat AddGaussianNoise(Mat I)  	{  
		Random random = new Random();
		
	    //图像通道  
	    int nbChannels = I.channels();  
	    System.out.println(nbChannels);
	    
	    //CV_Assert(I.depth() != sizeof(uchar));   
	    int nRows = I.rows();  
	    int nCols = I.cols() * nbChannels ;  
	 
	    
	    int i,j;  

	    for(i = 0; i < nRows; ++i){  

	    	BytePointer p = I.ptr(i); 
	        for(j = 0; j < nCols; ++j){  
	        	System.out.print("x="+p.get(j)+"   ");
	            double val0 = p.get(j);
	            
	            if(val0 < 0)  {
	            	val0 = p.get(j) + 255 + random.nextGaussian() * 40;
	            	if(val0 > 127) {
	            		val0= val0 - 255;
	            	} 
	            }  else {
	            	val0 = p.get(j) + random.nextGaussian() * 40;
	            	if(val0 < 0 ) {
	            		val0 = 0;
	            	} else if(val0 > 127) {
	            		val0 = val0 - 255;
	            	}
	            }
	            
	            System.out.print("  "+val0+"  ");
	            
	            if(val0 < -128)  
	                val0 = -128;  
	            if(val0 > 127)  
	                val0 = 127;
	            
	            int val ;
	            val = (int)val0;
	                
	            System.out.println(val); 
	            p.put(j, (byte)val);          
	        }  
	    }

	    
	    
		return I;  
	  
	}  
	public static Mat Ad(Mat I)  	{  
		
	    //图像通道  
	    int nbChannels = I.channels();  
	    System.out.println(nbChannels);
	    // accept only char type matrices 
	    
	    //CV_Assert(I.depth() != sizeof(uchar));   
	    int nRows = I.rows();  
	    int nCols = I.cols() * nbChannels ;  
	 
	    
	    int i,j;  

	    for(i = 0; i < nRows; ++i){  

	    	BytePointer p = I.ptr(i); 
	        for(j = 0; j < nCols; ++j){  
	        	
	        	//System.out.print("x="+p.get(j)+"   ");
	        	
	            double val0 = p.get(j); 
	            
	            if(val0 < 0)  
	                val0 = (val0+ 255)*(val0 + 255) / 255 - 255;  
	            if(val0 >= 0)  
	                val0 = val0*val0/255;
	            
	           //System.out.print("  "+val0+"  ");
	           
	            if(val0 < -128)  
	                val0 = -128;  
	            if(val0 > 127)  
	                val0 = 127;
	            
	            int val ;
	            val = (int)val0;
	                
	            //System.out.println(val); 
	            p.put(j, (byte)val);          
	        }  
	    }
		return I;

	}	
}
