package me.hao0.basic.concurrent.avoidactiveness_danger;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 通过公开调用来避免在相互协作的对象之间产生死锁
 */
public class OpenCall {
	class Taxi {
		private Point location; 
		private Point destination;
		private final Dispatcher dispatcher;

		public Taxi(Dispatcher dispatcher) {
			this.dispatcher = dispatcher;
		}
		
		public synchronized Point getLocation(){
			return location;
		}
		
		public void setLocation(Point location){
			boolean reachedDestination;
			synchronized(this){
				this.location = location;
				reachedDestination = location.equals(destination);
			}
			if (reachedDestination){
				dispatcher.notifyAvaliable(this); //这里持有dispatcher锁，但已释放taxi锁
			}
		}
	}
	
	class Dispatcher {
		private final Set<Taxi> taxis;
		private final Set<Taxi> avaliableTaxis;
		
		public Dispatcher(){
			taxis = new HashSet<>();
			avaliableTaxis = new HashSet<>();
		}

		public synchronized void notifyAvaliable(Taxi taxi) {
			avaliableTaxis.add(taxi);
		}
		
		public Image getImage(){
			Set<Taxi> copy;
			synchronized (this) {
				copy = new HashSet<Taxi>(taxis);
			}
			Image image = new Image();
			for (Taxi t :copy){
				image.drawMarker(t.getLocation());
			}
			return image;
		}
	}
}
