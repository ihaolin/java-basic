package me.hao0.basic.concurrent.taskexecute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用Future等待图像下载
 * 将渲染过程分为:
 *     IO密集型(下载图像)
 *     CPU密集型(渲染页面)
 * 但这里仍然必须图片下载完成了才能看到页面，只是缩短了总时间
 */
public class FutureRenderer {
	private final ExecutorService exec = Executors.newFixedThreadPool(10);
	
	void rendererPage(CharSequence source){
		final List<ImageInfo> imageInfos = scanForImage(source); //抽出图片链接信息
		Callable<List<ImageData>> task = 
				new Callable<List<ImageData>>() {
					@Override
					public List<ImageData> call() throws Exception {
						List<ImageData> result = 
								new ArrayList<>();
						for (ImageInfo imageInfo : imageInfos){
							result.add(imageInfo.downloadImageData()); //下载图片
						}
						return result;
					}
				};
		Future<List<ImageData>> future = exec.submit(task); //提交下载图片的任务
		renderText(source); //渲染文本
		try {
			List<ImageData> imageDatas = future.get();//阻塞获取下载的图片
			for (ImageData data : imageDatas){ //渲染图片
				renderImage(data); 
			}
		} catch (InterruptedException e) {
			//重新设置线程的中断状态
			Thread.currentThread().interrupt();
			future.cancel(true);
		} catch (ExecutionException e) {
			// handle exception
		} 
	}

	private void renderImage(ImageData data) {
		
	}

	private List<ImageInfo> scanForImage(CharSequence source) {
		return null;
	}

	private void renderText(CharSequence source) {
		
	}
}
