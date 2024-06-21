package com.yawer.laetus_code_bars_gen;

import java.util.ArrayList;
import java.util.List;

public class BarCombinationGen {

	final int MAX_VALUE = 131070;

	private float module;
	private float codeWidth;
	private float codeHeight = 8.0f;
	private List<Bar> barsCombination;

	public BarCombinationGen(int codeValue, CodeType codeType) {

		this.module = codeType.getModule();

		this.barsCombination = new ArrayList<Bar>();

		if (codeValue <= MAX_VALUE) {
			int numberOfBars = log2(codeValue + 1);
			int summedBarsValue = (int) Math.pow(2, numberOfBars) - 1;

			int valueAfterThickening = 0;
			for (int i = 0; i < numberOfBars; i++) {
				Bar bar = new BarThin(codeType);
				valueAfterThickening = summedBarsValue + (int) Math.pow(2, numberOfBars - 1 - i);

				if (valueAfterThickening <= codeValue) {
					bar = new BarThick(codeType);
					summedBarsValue = valueAfterThickening;
				}
				this.codeWidth += bar.getWidth();
				if (i < numberOfBars - 1) {
					this.codeWidth += this.module;
				}
				this.barsCombination.add(bar);

			}
		}
	}

	public float getModule() {
		return this.module;
	}

	public float getCodeWidth() {
		return this.codeWidth;
	}

	public float getCodeHeight() {
		return this.codeWidth;
	}

	public List<Bar> getBarsCombination() {
		return new ArrayList<Bar>(this.barsCombination);
	}

	private static int log2(int N) {
		// calculate log2 N indirectly
		// using log() method
		int result = (int) (Math.log(N) / Math.log(2));
		return result;
	}

}
