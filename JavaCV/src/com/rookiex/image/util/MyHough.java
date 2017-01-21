package com.rookiex.image.util;

import java.util.ArrayList;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.opencv_core.Mat;

/**
 * @author RookieXu
 * @功能    自己实现的hough变换代码
 * @日期和时间 2016年12月22日 下午3:52:26
 * @版本 v0.1
 *
 */
class h{
    int ro;
    int angle;

    public h(int r,int a){
        this.ro = r;
        this.angle = a;
    }
}
    
public class MyHough {
	/**
	 * @param src	输入mat
	 * @param out	输出mat
	 * @param threshold 阈值
	 */
	
	
	public static Mat myHough(Mat src,Mat out, int threshold) {
		//生成参数坐标
		//参数坐标转为直角坐标系
		//处理坐标系的直线、判断交点量是否大于阈值
		
		 //Image im = this.clone();
        //im.sobel();
        //im.IterBinary();
		int nbChannels = src.channels();  		 
        int h = src.rows();  
        int w = src.cols() * nbChannels ; 
        

        int data[][] = new int[h][w];

        for(int i=0; i<h; i++) {

        	BytePointer s = src.ptr(i);	
        	for(int j=0;j<w;j++){
        		if(s.get(j)!=0)
        			//System.out.println(i+"+"+j+"+"+s.get(j));
        		data[i][j] = s.get(j);
        	}
        }
        
        int ro = (int)Math.sqrt(h*h+w*w);
        int theta = 180;
        int[][] hist = new int[ro][theta];

        for(int k=0;k<theta;k++){
            for(int i=0;i<h;i++){
                for(int j=0;j<w;j++){
                    if(data[i][j]!= 0){
                        int rho=(int)(j*Math.cos(k*Math.PI/(theta*2))+i*Math.sin(k*Math.PI/(theta*2)));
                        hist[rho][k]++;
                    }      
                }
            }
        }

        ArrayList<h> index = maxIndex(hist,50); //找到大于最大值*0.9的二维直方图的点

        for(int k = 0;k<index.size();k++){
        	  //System.out.println(index.size());
            double resTheta = index.get(k).angle*Math.PI/(theta*2);

            for(int i=0;i<h;i++){
            	BytePointer o = out.ptr(i);	
                for(int j=0;j<w;j++){
                	//System.out.println("----"+j+"+"+i);
      
                    int rho = (int)(j*Math.cos(resTheta)+i*Math.sin(resTheta));
                    if(rho == index.get(k).ro){
                        //data[j+i*w] = setRed();
                    	//data[i][j] != 0 && 
                        o.put(j, (byte)-1); 
                       // System.out.println("----"+j+"+"+i);
                        //在直线上的点设为红色
                    }
                }   
            }
        }

        return out;
	}
	
	  private static ArrayList<h> maxIndex(int[][] hist, int i) {
	        ArrayList<h> in = new ArrayList<h>();
	        int max = 0;
	        System.out.println("start");
	        int[][] hist2 = hist.clone();
	        
	        for(int i1=0;i1<hist.length;i1++){
	            for(int j1=0;j1<hist[i1].length;j1++){
	            	if(hist[i1][j1]!=0)
	                if(max < hist[i1][j1]){
	                	System.out.println(hist[i1][j1]);
	                    max = hist[i1][j1];        
	                    System.out.println(i1+"---"+j1);
	                }
	            }
	        }
	        System.out.println(max);

	        for(int i2=0;i2<hist2.length;i2++){
	            for(int j2=0;j2<hist[i2].length;j2++){
	            
	                    if(hist2[i2][j2] > max*(i*0.01)){
	                    	System.out.println("line + 1");
	                        in.add(new h(i2,j2));
	                    }
	            }
	        }
	        System.out.println(max);
	        return in;  
	    }
	  
}
