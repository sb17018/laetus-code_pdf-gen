package com.yawer.laetus_code_bars_gen;

public class BarThin extends Bar {

	private float width;

	public BarThin(CodeType codeType) {
		super(codeType);
		this.width = codeType.getThinBar();
	}

	public float getWidth() {
		return width;
	}
	
	@Override
	public String toString() {
		return super.toString() +", w=" + width;
	}
}
