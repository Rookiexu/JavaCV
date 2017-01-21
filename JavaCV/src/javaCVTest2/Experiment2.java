package javaCVTest2;



import org.bytedeco.javacpp.opencv_core.Mat;

import com.rookiex.image.util.JavaCVUtil;
import com.rookiex.image.util.MyUtil;


/**
 * @author RookieXu
 * @���� 		ʵ���Ӿ�����ʵ��2��ͼ���˲�����ǿ 
 * @���ں�ʱ�� 2016��12��18�� ����10:32:32
 * @�汾 v0.1
 *
 */
public class Experiment2 {
	
	public static void main(String[] args) {
		
		String filePath1 = "image/hui.jpg";
		String filePath2 = "image/��ԫ����.jpg";
		String filePath3 = "image/gaussian.jpg";
		
		Mat out = new Mat();
		
		JavaCVUtil util = new JavaCVUtil();
		MyUtil myUtil = new MyUtil(util);
		
	     //��ʾ�Ҷ�ͼԭͼ
		Mat image1 = myUtil.imRead(filePath1);  
		myUtil.imShow(image1, "�Ҷ�ͼ");
		
		//������ǿ
	     double coefficient = 1.5;
	     myUtil.linearity(filePath1, coefficient);
	      
	     //��������ǿ
	     myUtil.nonlinearity(filePath1);

	     //������ԫ����ԭͼ����ʾ
	     Mat image2 = myUtil.imRead(filePath2);  
	     myUtil.imShow(image2, "��ԫ����");  
		
	   //�����˹��������ʾ����Ϊ������̺�ʱ�ϳ�����Ϊֱ�Ӵ�������ͼ
	     Mat image3 = myUtil.imRead(filePath3);
	     myUtil.imShow(image3,"������ͼ��");
	     
	   //��ֵ�˲�1 ����ʾ 
	     myUtil.medianBlur(filePath3,out,3);  
	     
	 	//��ֵ�˲�2 ����ʾ    
	     myUtil.medianBlur(filePath3,out,7);  

	     
	 	//��ֵ�˲�1 ����ʾ       	   
	     myUtil.Blur(filePath3, out, 3);

	     
	 	//��ֵ�˲�2 ����ʾ 
	     myUtil.Blur(filePath3, out, 7);

		/*
     
     //��ʾ�Ҷ�ͼԭͼ
     Mat image2 = JavaCVUtil.imRead("image/hui.jpg");           
     JavaCVUtil.imShow( image2,"���Ҷ�ͼ��"); 
     
     //������ǿ
     double coefficient = 1.5;
     Mat out5 = JavaCVUtil.linearity(image2, coefficient);
     JavaCVUtil.imShow(out5,"������ǿ��Ч��ͼ��");  
    
     //��������ǿ
     Mat out6 = JavaCVUtil.nonlinearity(image2);
     JavaCVUtil.imShow(out6,"��������ǿ��Ч��ͼ��"); 

     
     //����ԭͼ����ʾ
     Mat image1 = JavaCVUtil.imRead("image/��ԫ����.jpg");           
     JavaCVUtil.imShow( image1,"��ԭͼ��");  

     //�����˹��������ʾ����Ϊ������̺�ʱ�ϳ�����Ϊֱ�Ӵ�������ͼ��
	//Mat out0 = JavaCVUtil.AddGaussianNoise(image1);
	//JavaCVUtil.imShow( out0,"������ͼ��"); 
     
     Mat out0 = JavaCVUtil.imRead("image/gaussian.jpg");
     JavaCVUtil.imShow( out0,"������ͼ��"); 
	
	//��ֵ�˲�1 ����ʾ
	Mat out1 = new Mat();      
    opencv_imgproc.medianBlur(out0,out1,3);  
    JavaCVUtil.imShow(out1,"��ֵ�˲�5��Ч��ͼ��");  
    
	//��ֵ�˲�2 ����ʾ
    Mat out2 = new Mat();      
    opencv_imgproc.medianBlur(out0,out2,7);  
    JavaCVUtil.imShow(out2,"��ֵ�˲�11��Ч��ͼ��"); 
    
	//��ֵ�˲�1 ����ʾ
    Mat out3 = new Mat();          
    Size size5 = new Size(3, 3);
    opencv_imgproc.blur(out0, out3, size5);
    JavaCVUtil.imShow(out3,"��ֵ�˲�5*5��Ч��ͼ��");  
    
	//��ֵ�˲�2 ����ʾ 
    Mat out4 = new Mat();          
    Size size11 = new Size(7, 7);
    opencv_imgproc.blur(out0, out4, size11);
    JavaCVUtil.imShow(out4,"��ֵ�˲�11*11��Ч��ͼ��");  
    
	*/
	}
}
