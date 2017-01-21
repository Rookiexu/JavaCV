package com.rookiex.image.util;

import org.bytedeco.javacpp.opencv_core.Mat;


/**
 * @author RookieXu
 * @功能		util代理类，实现代理功能
 * @日期和时间 2016年12月18日 下午10:37:08
 * @版本		v0.1
 *
 */
public interface Util {
	//显示图片
    public  void imShow(Mat mat,String title);
    //读取图片
    public  Mat imRead(String filePath);
    //写入图片，即保存
    public  boolean imWrite(Mat mat,String filePath);
    //判断字符是否包含中文 ，并转码 
    public  boolean containChinese(String inputString);
    
    //加入白噪声
    public  Mat salt(String filePath, int n);
    //加入高斯噪声
	public  Mat AddGaussianNoise(String filePath);
	//非线性增强
	public  Mat nonlinearity(String filePath);
	//线性增强
	public  Mat linearity(String filePath,double coefficient);
	//
	public void medianBlur(String filePath,Mat out,int m);
	//
	public void Blur(String filePath,Mat out,int m);
	
	public Mat AddGaussianNoise(Mat filePath);
}
