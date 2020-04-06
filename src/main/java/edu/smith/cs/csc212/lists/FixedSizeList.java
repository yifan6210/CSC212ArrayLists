package edu.smith.cs.csc212.lists;

import me.jjfoley.adt.ArrayWrapper;
import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.RanOutOfSpaceError;
import me.jjfoley.adt.errors.TODOErr;

/**
 * FixedSizeList is a List with a maximum size.
 * @author jfoley
 *
 * @param <T>
 */
public class FixedSizeList<T> extends ListADT<T> {
	/**
	 * This is the array of fixed size.
	 */
	private ArrayWrapper<T> array;
	/**
	 * This keeps track of what we have used and what is left.
	 */
	private int fill;

	/**
	 * Construct a new FixedSizeList with a given maximum size.
	 * @param maximumSize - the size of the array to use.
	 */
	public FixedSizeList(int maximumSize) {
		this.array = new ArrayWrapper<>(maximumSize);
		this.fill = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.fill == 0;
	}

	@Override
	public int size() {
		return this.fill;
	}

	@Override
	public void setIndex(int index, T value) {
		checkNotEmpty();
		this.checkExclusiveIndex(index);
		this.array.setIndex(index, value);
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		this.checkExclusiveIndex(index);
		return this.array.getIndex(index);
	}

	@Override
	public T getFront() {
		checkNotEmpty();
		return this.array.getIndex(0);
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		return this.array.getIndex(fill-1);

	}

	@Override
	public void addIndex(int index, T value) {

		this.checkInclusiveIndex(index);
		
		if (isFull() != true) {		
			for (int i = fill; i > index; i--) {
				this.array.setIndex(i, this.array.getIndex(i - 1));
			}
			fill++;	
			this.array.setIndex(index, value);
		}else {
			throw new RanOutOfSpaceError();
		}
	}

	@Override
	public void addFront(T value) {

		if( isFull() != true) {

			for (int i = fill; i > 0; i--) {
				this.array.setIndex(i, this.array.getIndex(i-1));
				
			}
			fill++;
			this.array.setIndex(0, value);

		}else {
			throw new RanOutOfSpaceError();
		}
	}

	@Override
	public void addBack(T value) {
		if (fill < array.size()) {
			array.setIndex(fill++, value);
		} else {
			throw new RanOutOfSpaceError();
		}
	}

	@Override
	public T removeIndex(int index) {
		
		checkNotEmpty();
		this.checkExclusiveIndex(index);

		T removed = this.array.getIndex(index);
	

		for (int i = index; i <fill-1; i++) {
			this.array.setIndex(i, this.array.getIndex(i +1));
		}
		
		fill--;

		this.array.setIndex(fill, null);
		return removed;

	}

	@Override
	public T removeBack() {

		checkNotEmpty();

		T removed = this.getIndex(fill-1);

		fill--;
		this.array.setIndex(fill, null);

		return removed;


	}

	@Override
	public T removeFront() {
		checkNotEmpty();
		
		T removed = this.array.getIndex(0);
		
		for (int i = 0; i < fill-1; i++) {
			this.array.setIndex(i, this.array.getIndex(i+1));
		}
		
		fill--;
		
		this.array.setIndex(fill, null);
		
		return removed;
	}

	/**
	 * Is this data structure full? Used in challenge: {@linkplain ChunkyArrayList}.
	 * 
	 * @return if true this FixedSizeList is full.
	 */
	public boolean isFull() {
		return this.fill == this.array.size();
	}

}
