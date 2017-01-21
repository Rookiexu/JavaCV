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
 * @����		JavaCV������
 * @���ں�ʱ�� 2016��12��15�� ����10:34:58
 * @�汾		 ���ڸĶ�
 *
 */
public class JavaCVUtil implements Util {  
    
	//��ʾͼƬ
    public  void imShow(Mat mat,String title) {  
        //opencv�Դ�����ʾģ�飬��ƽ̨��Ƿ�ѣ�תΪJava2Dͼ�����ͽ�����ʾ  
      ToMat converter = new OpenCVFrameConverter.ToMat();  
      CanvasFrame canvas = new CanvasFrame(title, 1);  
      canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
      canvas.showImage(converter.convert(mat));  
        
    }  
    //��ȡͼƬ
    public  Mat imRead(String filePath){
        Mat mat = null;
        try {
  	  //ʹ��java2D��ȡͼ��
  	  Image image = ImageIO.read(new File(filePath));
  	  /**
  	   * תΪmat
  	   * 1����Java2D��imageתΪjavacv�Զ����Frame
  	   * 2����FrameתΪMat
  	   */
  	  Java2DFrameConverter java2dFrameConverter = new Java2DFrameConverter();
  	  Frame frame = java2dFrameConverter.convert((BufferedImage) image);
  	  ToMat converter = new OpenCVFrameConverter.ToMat();
  	  mat = converter.convert(frame);
  	  
      } catch (Exception e) {
  	System.out.println("��ȡͼ������쳣��filePath="+filePath);
  	e.printStackTrace();
      }
        return mat;
    }
    
    //д��ͼƬ��������
    public  boolean imWrite(Mat mat,String filePath){  
    //���������ģ�ֱ��ʹ��opencvԭ���������б���  
        if(!containChinese(filePath)){  
      return opencv_imgcodecs.imwrite(filePath, mat);  
        }  
       try {  
     /** 
      * ��matתΪjava��BufferedImage 
      */  
      ToMat convert= new ToMat();  
      Frame frame= convert.convert(mat);  
      Java2DFrameConverter java2dFrameConverter = new Java2DFrameConverter();  
      BufferedImage bufferedImage= java2dFrameConverter.convert(frame);  
      ImageIO.write(bufferedImage, "PNG", new File(filePath)); 
      System.out.println("�����ļ��������ռ�Ŀ¼��"+filePath+" �ɹ�");  
      return true;  
      } catch (Exception e) {  
    System.out.println("�����ļ������쳣:"+filePath);  
    e.printStackTrace();  
      }  
    return false;  
    } 
    
    //�ж��ַ��Ƿ�������� ����ת�� 
    public  boolean containChinese(String inputString){ 
    	
    	
          //�Ķη�Χ������ȫ��  
          String regex ="[\\u4E00-\\u9FA5\\u2E80-\\uA4CF\\uF900-\\uFAFF\\uFE30-\\uFE4F]";  
          Pattern pattern = Pattern.compile(regex);  
         Matcher matcher = pattern.matcher(inputString);  
         return matcher.find();  
      }
    
    //���������
    public  Mat salt(String filePath, int n) { 
    	
    	JavaCVUtil JavaCVUtil = new JavaCVUtil();
		Mat image = JavaCVUtil.imRead(filePath);
    	
    // ��������ɶ���  
    Random random = new Random();  
    /** 
     * �޷����ֽ�����������Mat�ṹ��Ԫ�� 
     * ����Mat��Ч��� 
     */  
    UByteIndexer indexer = image.createIndexer();  
    //ͼ��ͨ��  
    int nbChannels = image.channels();  
    //��������  
    for (int i = 0; i < n; i++) {  
        /** 
         * ��ȡ����С��� 
         * �������ֲ� 
         */  
        int row = random.nextInt(image.rows());  
        int col = random.nextInt(image.cols());  
        //����ȫ��ͨ�����ݣ������м��Σ�����Ϊ���ֵ255  
        for (int channel = 0; channel < nbChannels; channel++) {  
        	indexer.put(row, col, channel, 255);
        }  
    }  
    return image;  
    } 
    
    //�����˹����
	public  Mat AddGaussianNoise(String filePath)  	{  

		JavaCVUtil JavaCVUtil = new JavaCVUtil();
		Mat I = JavaCVUtil.imRead(filePath);
Random random = new Random();
		
	    //ͼ��ͨ��  
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
	    //ͼ��ͨ��  
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
	    //ͼ��ͨ��  
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

	//��ֵ�˲�
	public  void medianBlur(String filePath, Mat out,int m) {	
		JavaCVUtil JavaCVUtil = new JavaCVUtil();
		Mat src = JavaCVUtil.imRead(filePath);
	    opencv_imgproc.medianBlur(src,out,m);  
	}

	//��ֵ�˲�
	public void Blur(String filePath, Mat out, int m) {
		JavaCVUtil JavaCVUtil = new JavaCVUtil();
		Mat src = JavaCVUtil.imRead(filePath);
		 Size size = new Size(m, m);
		 opencv_imgproc.blur(src, out, size);
	}

	
	public Mat AddGaussianNoise(Mat I) {
		
		Random random = new Random();
		
	    //ͼ��ͨ��  
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