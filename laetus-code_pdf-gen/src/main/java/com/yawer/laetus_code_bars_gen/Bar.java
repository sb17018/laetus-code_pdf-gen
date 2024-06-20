package com.yawer.laetus_code_bars_gen;

public class Bar {
	
	private float module;
	private float width;
	private float height = 8.0f;
	
	public Bar(CodeType codeType) {
		this.module = codeType.getModule();
	}
	
	public float getModule() {
		return module;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getWidth() {
		return width;
	}

	@Override
	public String toString() {
		return "m=" + module + ", h=" + height;
	}
	
}
