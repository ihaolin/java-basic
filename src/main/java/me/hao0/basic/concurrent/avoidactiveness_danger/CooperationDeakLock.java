package me.hao0.basic.concurrent.avoidactiveness_danger;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class CooperationDeakLock {
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
		
		public synchronized void setLocation(Point location){
			this.location = location;
			if (location.equals(destination)){
				dispatcher.notifyAvaliable(this);
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

		public void notifyAvaliable(Taxi taxi) {
			avaliableTaxis.add(taxi);
		}
		
		public synchronized Image getImage(){
			Image image = new Image();
			for (Taxi t :taxis){
				image.drawMarker(t.getLocation());
			}
			return image;
		}
	}
}
