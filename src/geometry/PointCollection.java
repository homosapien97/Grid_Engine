package geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class PointCollection implements Collection<Point> {
	public final List<Point> points;
	
	public PointCollection() {
		points = Collections.synchronizedList(new ArrayList<Point>());
	}
	public PointCollection(int size) {
		points = Collections.synchronizedList(new ArrayList<Point>(size));
	}
	public PointCollection(Circle c) {
		points = Collections.synchronizedList(new ArrayList<Point>(c.area()));
		synchronized(points) {
			for(int i = c.x - c.r; i < c.x + c.r + 1; i++) {
				for(int j = c.y - c.r; j < c.y + c.r + 1; j++) {
					if(c.inside(i, j)) points.add(new Point(i, j));
				}
			}
		}
	}
	public PointCollection(Line l) {
		points = Collections.synchronizedList(new ArrayList<Point>(l.points.length));
		synchronized(points) {
			for(int i = 0; i < l.points.length; i++) {
				points.add(l.points[i]);
			}
		}
	}
	public PointCollection(Point p) {
		points = Collections.synchronizedList(new ArrayList<Point>(1));
		synchronized(points) {
			points.add(p);
		}
	}
	public PointCollection(Ray r) {
		points = Collections.synchronizedList(new ArrayList<Point>(r.points.length));
		synchronized(points) {
			for(int i = 0; i < r.points.length; i++) {
				points.add(r.points[i]);
			}
		}
	}
	public PointCollection(Rectangle r) {
		points = Collections.synchronizedList(new ArrayList<Point>(r.area()));
		synchronized(points) {
			for(int i = r.a.x; i <= r.b.x; i++) {
				for(int j = r.a.y; j <= r.b.y; j++) {
					points.add(new Point(i, j));
				}
			}
		}
	}
	@Override
	public boolean add(Point e) {
		return points.add(e);
	}
	@Override
	public boolean addAll(Collection<? extends Point> c) {
		return points.addAll(c);
	}
	@Override
	public void clear() {
		points.clear();
	}
	@Override
	public boolean contains(Object o) {
		return points.contains(o);
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		return points.containsAll(c);
	}
	@Override
	public boolean isEmpty() {
		return points.isEmpty();
	}
	@Override
	public Iterator<Point> iterator() {
		return points.iterator();
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		return points.removeAll(c);
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		return points.retainAll(c);
	}
	@Override
	public int size() {
		return points.size();
	}
	@Override
	public Object[] toArray() {
		return points.toArray();
	}
	@Override
	public <T> T[] toArray(T[] a) {
		return points.toArray(a);
	}
}
