package com.rookiex.image.util;

import org.bytedeco.javacpp.opencv_core.Mat;


/**
 * @author RookieXu
 * @����		util�����࣬ʵ�ִ�����
 * @���ں�ʱ�� 2016��12��18�� ����10:37:08
 * @�汾		v0.1
 *
 */
public interface Util {
	//��ʾͼƬ
    public  void imShow(Mat mat,String title);
    //��ȡͼƬ
    public  Mat imRead(String filePath);
    //д��ͼƬ��������
    public  boolean imWrite(Mat mat,String filePath);
    //�ж��ַ��Ƿ�������� ����ת�� 
    public  boolean containChinese(String inputString);
    
    //���������
    public  Mat salt(String filePath, int n);
    //�����˹����
	public  Mat AddGaussianNoise(String filePath);
	//��������ǿ
	public  Mat nonlinearity(String filePath);
	//������ǿ
	public  Mat linearity(String filePath,double coefficient);
	//
	public void medianBlur(String filePath,Mat out,int m);
	//
	public void Blur(String filePath,Mat out,int m);
	
	public Mat AddGaussianNoise(Mat filePath);
}
