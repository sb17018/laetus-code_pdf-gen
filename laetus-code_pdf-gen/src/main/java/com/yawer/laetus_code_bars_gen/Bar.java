package com.yawer.laetus_code_bars_gen;

public class Bar {
	
	private float module;
	private float width;
	
	public Bar(CodeType codeType) {
		this.module = codeType.getModule();
	}
	
	public float getModule() {
		return module;
	}
	
	public float getWidth() {
		return width;
	}

	@Override
	public String toString() {
		return "m=" + module;
	}
	
}
