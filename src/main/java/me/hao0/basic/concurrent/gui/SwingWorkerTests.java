package me.hao0.basic.concurrent.gui;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SwingWorkerTests {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		SwingWorker<String, Integer> worker 
			= new SwingWorker<String, Integer>(){
				@Override
				protected String doInBackground() throws Exception {
					System.out.println("任务开始");
					// do some computation
					publish(20);
					publish(30);
					//...
					publish(100);
					return "返回计算结果";
				}

				@Override
				protected void process(List<Integer> chunks) {
					//接收publish发送的数据
					for (Integer chunk : chunks){
						//update ui, for example progress bar
						System.out.println(chunk);
					}
				}
				
				@Override
				protected void done() {
					System.out.println("任务完成");
					// do some state change
				}
		};
		GuiExecutor.instance().execute(worker);
		worker.get(); //block to wait results.
	}
}
