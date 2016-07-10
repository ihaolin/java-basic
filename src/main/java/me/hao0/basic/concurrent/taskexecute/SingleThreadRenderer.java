package me.hao0.basic.concurrent.taskexecute;

import java.util.ArrayList;
import java.util.List;

/**
 * 串行地渲染页面元素, 性能很低下
 * 下载图片过程中有可能IO时间长阻塞，
 * CPU没能有效利用
 */
public class SingleThreadRenderer {
	void rendererPage(CharSequence source){
		renderText(source);
		List<ImageData> imageDatas = new ArrayList<>();
		//解析文本中的图片连接
		for (ImageInfo imageInfo : scanForImage(source)){ 
			imageDatas.add(imageInfo.downloadImageData()); //下载图片
		}
		//渲染图片
		for (ImageData data : imageDatas){
			renderImage(data);
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
