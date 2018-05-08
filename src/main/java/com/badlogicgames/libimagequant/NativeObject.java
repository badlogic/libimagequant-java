package com.badlogicgames.libimagequant;

public abstract class NativeObject {
	private long ptr;

	protected NativeObject(long ptr) {
		this.ptr = ptr;
		if (ptr == 0) throw new IllegalStateException("Couldn't create " + this.getClass().getSimpleName());
	}

	/** Releases the native memory. Also called in the finalizer. */
	public void destroy () {
		if (ptr != 0) {
			_nativeDestroy(ptr);
			ptr = 0;
		}
	}

	/** @return the pointer to the underlying native memory. */
	long getPointer () {
		return ptr;
	}

	@Override
	protected void finalize () throws Throwable {
		destroy();
	}

	protected abstract void _nativeDestroy(long object);
}
