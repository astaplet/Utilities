package self.aleksandra.utilities.collections.sets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A set that contains every value. Intended to be used similar to EnumSet.AllOf for non-enum types
 * Some functions, such as size() or iterator(), that are nonsensical throw an UnsupportedOperationException
 * on this class unless the set has first been specifically degraded to a HashSet using the {@link degrade} method
 * or implicitly degraded with either {@link retainAll} or {@link clear}, which both have specified behaviors
 * which can logically reduce the infinite set to a sensible finite set.
 * @author aleksandra
 *
 */
public class InfiniteSet<E> implements Set<E>{
	
	private boolean degraded = false;
	private Set<E> backingSet;
	private Class<E> klazz;
	
	/**
	 * This constructor must be used if you will use the retainAll method before degrading, as
	 * the class must be available to typecheck the wildcard-typed collection
	 * members
	 * @param klazz Necessary to allow properfunction of retainAll method
	 */
	public InfiniteSet(Class<E> klazz) {
		this.klazz = klazz;
	}
	
	/**
	 * Use this constructor if you will necer use retrainAll before degrading the set by another method
	 */
	public InfiniteSet() {
		
	}
	
	/**
	 * Degrades the set to a {@link HashSet}. Returns true if the set was not already degraded.
	 * If false if returned, nothing happened
	 * @return True if the degrading operation was performed
	 */
	public boolean degrade() {
		if (!degraded) {
			this.backingSet = new HashSet<E>();
			this.degraded = true;
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		if (!degraded) {
			throw new UnsupportedOperationException("This operation can't be supported for the infinite set");
		}
		return backingSet.size();
	}

	@Override
	public boolean isEmpty() {
		if (!degraded) {
			return false;
		}
		return backingSet.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		if (!degraded) {
			return true;
		}
		return backingSet.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		if (!degraded) {
			throw new UnsupportedOperationException("This operation can't be supported for the infinite set");
		}
		return backingSet.iterator();
	}

	@Override
	public Object[] toArray() {
		if (!degraded) {
			throw new UnsupportedOperationException("This operation can't be supported for the infinite set");
		}
		return backingSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		if (!degraded) {
			throw new UnsupportedOperationException("This operation can't be supported for the infinite set");
		}
		return backingSet.toArray(a);
	}

	@Override
	public boolean add(E e) {
		if (!degraded) {
			throw new UnsupportedOperationException("This operation can't be supported for the infinite set");
		}
		return backingSet.add(e);
	}

	@Override
	public boolean remove(Object o) {
		if (!degraded) {
			throw new UnsupportedOperationException("This operation can't be supported for the infinite set");
		}
		return backingSet.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if (!degraded) {
			return true;
		}
		return backingSet.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		if (!degraded) {
			throw new UnsupportedOperationException("This operation can't be supported for the infinite set");
		}
		return backingSet.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (!degraded) {
			this.backingSet = new HashSet<E>();
			c.forEach(val -> {
				if (this.klazz.isInstance(val)) {
					this.backingSet.add(this.klazz.cast(val));
				}
			});
			this.degraded = true;
			return true;
		}
		return backingSet.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if (!degraded) {
			throw new UnsupportedOperationException("This operation can't be supported for the infinite set");
		}
		return backingSet.removeAll(c);
	}

	@Override
	public void clear() {
		if (!degraded) {
			this.backingSet = new HashSet<E>();
			degraded = true;
		}
		this.backingSet.clear();
	}

}
