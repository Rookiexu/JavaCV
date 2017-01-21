package com.rookiex.image.util;  
  
import java.awt.Image;
import java.awt.image.BufferedImage;  
import java.io.File;
import java.util.Random;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
import javax.imageio.ImageIO;  
import javax.swing.JFrame;  
  
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.javacv.CanvasFrame;  
import org.bytedeco.javacv.Frame;  
import org.bytedeco.javacv.Java2DFrameConverter;  
import org.bytedeco.javacv.OpenCVFrameConverter;  
import org.bytedeco.javacv.OpenCVFrameConverter.ToMat;


/**
 * @author RookieXu
 * @功能		JavaCV工具类
 * @日期和时间 2016年12月15日 下午10:34:58
 * @版本		 长期改动
 *
 */
public class JavaCVUtil implements Util {  
    
	//显示图片
    public  void imShow(Mat mat,String title) {  
        //opencv自带的显示模块，跨平台性欠佳，转为Java2D图像类型进行显示  
      ToMat converter = new OpenCVFrameConverter.ToMat();  
      CanvasFrame canvas = new CanvasFrame(title, 1);  
      canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
      canvas.showImage(converter.convert(mat));  
        
    }  
    //读取图片
    public  Mat imRead(String filePath){
        Mat mat = null;
        try {
  	  //使用java2D读取图像
  	  Image image = ImageIO.read(new File(filePath));
  	  /**
  	   * 转为mat
  	   * 1、由Java2D的image转为javacv自定义的Frame
  	   * 2、由Frame转为Mat
  	   */
  	  Java2DFrameConverter java2dFrameConverter = new Java2DFrameConverter();
  	  Frame frame = java2dFrameConverter.convert((BufferedImage) image);
  	  ToMat converter = new OpenCVFrameConverter.ToMat();
  	  mat = converter.convert(frame);
  	  
      } catch (Exception e) {
  	System.out.println("读取图像出现异常：filePath="+filePath);
  	e.printStackTrace();
      }
        return mat;
    }
    
    //写入图片，即保存
    public  boolean imWrite(Mat mat,String filePath){  
    //不包含中文，直接使用opencv原生方法进行保存  
        if(!containChinese(filePath)){  
      return opencv_imgcodecs.imwrite(filePath, mat);  
        }  
       try {  
     /** 
      * 将mat转为java的BufferedImage 
      */  
      ToMat convert= new ToMat();  
      Frame frame= convert.convert(mat);  
      Java2DFrameConverter java2dFrameConverter = new Java2DFrameConverter();  
      BufferedImage bufferedImage= java2dFrameConverter.convert(frame);  
      ImageIO.write(bufferedImage, "PNG", new File(filePath)); 
      System.out.println("保存文件到工作空间目录："+filePath+" 成功");  
      return true;  
      } catch (Exception e) {  
    System.out.println("保存文件出现异常:"+filePath);  
    e.printStackTrace();  
      }  
    return false;  
    } 
    
    //判断字符是否包含中文 ，并转码 
    public  boolean containChinese(String inputString){ 
    	
    	
          //四段范围，包含全面  
          String regex ="[\\u4E00-\\u9FA5\\u2E80-\\uA4CF\\uF900-\\uFAFF\\uFE30-\\uFE4F]";  
          Pattern pattern = Pattern.compile(regex);  
         Matcher matcher = pattern.matcher(inputString);  
         return matcher.find();  
      }
    
    //加入白噪声
    public  Mat salt(String filePath, int n) { 
    	
    	JavaCVUtil JavaCVUtil = new JavaCVUtil();
		Mat image = JavaCVUtil.imRead(filePath);
    	
    // 随机数生成对象  
    Random random = new Random();  
    /** 
     * 无符号字节索引，访问Mat结构的元素 
     * 访问Mat高效便捷 
     */  
    UByteIndexer indexer = image.createIndexer();  
    //图像通道  
    int nbChannels = image.channels();  
    //加盐数量  
    for (int i = 0; i < n; i++) {  
        /** 
         * 获取随机行、列 
         * 噪点随机分布 
         */  
        int row = random.nextInt(image.rows());  
        int col = random.nextInt(image.cols());  
        //处理全部通道数据，均进行加盐，设置为最大值255  
        for (int channel = 0; channel < nbChannels; channel++) {  
        	indexer.put(row, col, channel, 255);
        }  
    }  
    return image;  
    } 
    
    //加入高斯噪声
	public  Mat AddGaussianNoise(String filePath)  	{  

		JavaCVUtil JavaCVUtil = new JavaCVUtil();
		Mat I = JavaCVUtil.imRead(filePath);
Random random = new Random();
		
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
	        	System.out.print("x="+p.get(j)+"   ");
	            double val0 = p.get(j);
	            
	            if(val0 < 0)  {
	            	val0 = p.get(j) + 255 + random.nextGaussian() * 32;
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
	
	public  Mat nonlinearity(String filePath)  	{  
		JavaCVUtil JavaCVUtil = new JavaCVUtil();
		Mat I = JavaCVUtil.imRead(filePath);
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
	
	public  Mat linearity(String filePath,double coefficient)  	{  
		JavaCVUtil JavaCVUtil = new JavaCVUtil();
		Mat I = JavaCVUtil.imRead(filePath);
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
	            if(val0 < 0) { 
	                val0 = val0 * (2-coefficient);  
		            if(val0 < -128) {
		            	val0 = val0 + 255;
		            }else if(val0 > -1)
		            	val0 = -1;
	            }
		            
	            if(val0 >= 0)  {
	                val0 = val0 * coefficient;	 
	                if(val0 > 127) {
	            		val0 = val0 - 255;
	            	} else if(val0 < 1){
		            	val0 = 1;
	            	}
	            }
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

	//中值滤波
	public  void medianBlur(String filePath, Mat out,int m) {	
		JavaCVUtil JavaCVUtil = new JavaCVUtil();
		Mat src = JavaCVUtil.imRead(filePath);
	    opencv_imgproc.medianBlur(src,out,m);  
	}

	//均值滤波
	public void Blur(String filePath, Mat out, int m) {
		JavaCVUtil JavaCVUtil = new JavaCVUtil();
		Mat src = JavaCVUtil.imRead(filePath);
		 Size size = new Size(m, m);
		 opencv_imgproc.blur(src, out, size);
	}

	
	public Mat AddGaussianNoise(Mat I) {
		
		Random random = new Random();
		
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
	        	System.out.print("x="+p.get(j)+"   ");
	            double val0 = p.get(j);
	            
	            if(val0 < 0)  {
	            	val0 = p.get(j) + 255 + random.nextGaussian() * 20;
	            	if(val0 > 127) {
	            		val0= val0 - 255;
	            	} 
	            }  else {
	            	val0 = p.get(j) + random.nextGaussian() * 20;
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

}