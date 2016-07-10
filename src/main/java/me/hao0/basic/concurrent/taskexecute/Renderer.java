package me.hao0.basic.concurrent.taskexecute;

import java.util.List;
import java.util.concurrent.*;

/**
 * 使用CompletionService, 使页面元素在下载完成后立即显示出来
 * 类似Mobile中的新闻加载，图片时被异步加载的
 */
public class Renderer {
	private final ExecutorService executor;
	
	public Renderer(ExecutorService executor){
		this.executor = executor;
	}
	
	void rendererPage(CharSequence source){
		final List<ImageInfo> imageInfos = scanForImage(source); //抽出图片链接信息
		CompletionService<ImageData> completionService
					= new ExecutorCompletionService<>(this.executor);
		for (final ImageInfo imageInfo : imageInfos){
			//提交下载图片的任务, 每下载一个图片就是一个任务，达到下载图片并行性
			completionService.submit(new Callable<ImageData>() { 
				@Override
				public ImageData call() throws Exception {
					return imageInfo.downloadImageData();
				}
			});
		}
		renderText(source); //渲染文本
		try {
			for (int i=0, n=imageInfos.size(); i<n; i++){
				Future<ImageData> f = completionService.take();
				ImageData imageData = f.get();
				renderImage(imageData); //渲染图片
			}
		} catch (InterruptedException e) {
			//重新设置线程的中断状态
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			// handle exception e.getCause()
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
