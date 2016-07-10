package me.hao0.basic.concurrent.objectcombine;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 将线程安全性委托给多个彼此独立的状态变量
 * VisualComponent使用CopyOnWriteArrayList(线程安全)来保存监听器列表
 * keyListeners, mouseListeners彼此独立
 * 因此VisualComponent线程安全
 */
public class VisualComponent {
	private final List<KeyListener> keyListeners = 
			new CopyOnWriteArrayList<>();
	private final List<MouseListener> mouseListeners = 
			new CopyOnWriteArrayList<>();
	
	public void addKeyListener(KeyListener keyListener){
		keyListeners.add(keyListener);
	}
	
	public void removeKeyListener(KeyListener keyListener){
		keyListeners.remove(keyListener);
	}
	
	public void addMouseListener(MouseListener mouseListener){
		mouseListeners.add(mouseListener);
	}
	
	public void removeMouseListener(MouseListener mouseListener){
		mouseListeners.remove(mouseListener);
	}
}	
