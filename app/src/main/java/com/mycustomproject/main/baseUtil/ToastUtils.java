package com.mycustomproject.main.baseUtil;

import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mycustomproject.R;

public class ToastUtils {
	//整个页面只用一个提示Toast吐司
	private static Toast mToast = null;  
	//吐丝，true 为禁止使用吐丝，false为可以使用吐丝 
	private static boolean Silking=false;
	//吐丝测试用，true 为禁止使用吐丝，false为可以使用吐丝 
	private static boolean testSilking=false;
	/**
	 * 自己定义吐丝长短
	 * @param context
	 * @param text
	 * @param duration
	 */
	public static void showToast(Context context, String text, int duration) {
		if (Silking) {
			return;
		}
		try{
			LayoutInflater inflater = LayoutInflater.from(context);
			View view = inflater.inflate(R.layout.abroad_popup_toast_show, null);
			TextView MesView= view.findViewById(R.id.abroad_message);
			MesView.setText(text);
			if (mToast != null) {
				mToast.cancel();
			}
			mToast = new Toast(context);
			mToast.setDuration(duration);
			mToast.setView(view);
			mToast.setGravity(Gravity.CENTER, 0, 0);
			mToast.show();
		}catch (Exception e){
			//解决在子线程中调用Toast的异常情况处理
			Looper.prepare();
		}

	}


    /**
     * 自己定义吐丝长
     * @param context
     * @param text
     */
	public static void showToastLong(Context context, String text) {  
		if (Silking) {
			return;
		}
		try{
			LayoutInflater inflater = LayoutInflater.from(context);
			View view = inflater.inflate(R.layout.abroad_popup_toast_show, null);
			TextView MesView= view.findViewById(R.id.abroad_message);
			MesView.setText(text);
			if (mToast != null) {
				mToast.cancel();
			}
			mToast = new Toast(context);
			mToast.setDuration(Toast.LENGTH_LONG);
			mToast.setView(view);
			mToast.setGravity(Gravity.CENTER, 0, 0);

			mToast.show();
		}catch (Exception e){
			//解决在子线程中调用Toast的异常情况处理
			Looper.prepare();
		}

	}
	/**
	 * 自己定义吐丝短
	 * @param context
	 * @param text
	 */
	public static void showToastShort(Context context, String text) {  
		if (Silking) {
			return;
		}
		try{
			LayoutInflater inflater = LayoutInflater.from(context);
			View view = inflater.inflate(R.layout.abroad_popup_toast_show, null);
			TextView MesView= view.findViewById(R.id.abroad_message);
			MesView.setText(text);
			if (mToast != null) {
				mToast.cancel();
			}
			mToast = new Toast(context);
			mToast.setDuration(Toast.LENGTH_SHORT);
			mToast.setView(view);
			mToast.setGravity(Gravity.CENTER, 0, 0);
			//Toast  long默认显示的是1000毫秒，所以设置成1000
			mToast.show();
		}catch (Exception e){
			//解决在子线程中调用Toast的异常情况处理
			Looper.prepare();
		}

	}




	public static void showToastShortTest(Context context, String text) {  
		if (testSilking) {
			return;
		}
		try{
			LayoutInflater inflater = LayoutInflater.from(context);
			View view = inflater.inflate(R.layout.abroad_popup_toast_show, null);
			TextView MesView= view.findViewById(R.id.abroad_message);
			MesView.setText(text);
			if (mToast != null) {
				mToast.cancel();
			}
			mToast = new Toast(context);
			mToast.setDuration(Toast.LENGTH_SHORT);
			mToast.setView(view);
			mToast.setGravity(Gravity.CENTER, 0, 0);

			mToast.show();
		}catch (Exception e){
			//解决在子线程中调用Toast的异常情况处理
			Looper.prepare();
		}

	}



	public static void showToastLongTest(Context context, String text) {  
		if (testSilking) {
			return;
		}
		try{
			LayoutInflater inflater = LayoutInflater.from(context);
			View view = inflater.inflate(R.layout.abroad_popup_toast_show, null);
			TextView MesView= view.findViewById(R.id.abroad_message);
			MesView.setText(text);
			if (mToast != null) {
				mToast.cancel();
			}
			mToast = new Toast(context);
			mToast.setDuration(Toast.LENGTH_LONG);
			mToast.setView(view);
			mToast.setGravity(Gravity.CENTER, 0, 0);
			//Toast  long默认显示的是1000毫秒，所以设置成1000

			mToast.show();
		}catch (Exception e){
			//解决在子线程中调用Toast的异常情况处理
			Looper.prepare();
		}

	}
	
	
	/**
	 * 特殊界面必定需要可提示
	 * @param context
	 * @param text
	 */
	public static void showToastSpecial(Context context,String text,int time){
		try{
			LayoutInflater inflater = LayoutInflater.from(context);
			View view = inflater.inflate(R.layout.abroad_popup_toast_show, null);
			TextView MesView= view.findViewById(R.id.abroad_message);
			MesView.setText(text);
			if (mToast != null) {
				mToast.cancel();
			}
			mToast = new Toast(context);
			mToast.setDuration(time);
			mToast.setView(view);
			mToast.setGravity(Gravity.CENTER, 0, 0);
			mToast.show();
		}catch (Exception e){
			//解决在子线程中调用Toast的异常情况处理
			Looper.prepare();
		}

	}




}
