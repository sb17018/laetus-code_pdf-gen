package com.yawer.laetus_code_bars_gen;

public enum CodeType {

	STANDARD(0.5f), MINI(0.375f), MICRO(0.25f);

	// constructor
	private CodeType(final float width) {
		this.thinBar = width;
		this.thickBar = width * 3;
		this.module = width * 2;
	}

	// internal state
	private float thinBar;
	private float thickBar;
	private float module;

	// access
	public float getThinBar() {
		return thinBar;
	}

	public float getThickBar() {
		return thickBar;
	}

	public float getModule() {
		return module;
	}

}
