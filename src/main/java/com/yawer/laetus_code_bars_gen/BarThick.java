package com.yawer.laetus_code_bars_gen;

public class BarThick implements Bar {

	private float width;

	public BarThick(CodeType codeType) {
		this.width = codeType.getThickBar();
	}

	public float getWidth() {
		return width;
	}

	@Override
	public String toString() {
		return super.toString() + ", w=" + width;
	}

}
