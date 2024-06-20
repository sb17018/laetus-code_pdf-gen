package com.yawer.laetus_code_bars_gen;

import java.util.ArrayList;
import java.util.List;

public class BarCombinationGen {

	final int MAX_VALUE = 131070;

	private static int log2(int N) {
		// calculate log2 N indirectly
		// using log() method
		int result = (int) (Math.log(N) / Math.log(2));
		return result;
	}

	public List<Bar> generateBarsCombination(int codeValue, CodeType codeType) {
		List<Bar> barsCombination = new ArrayList<Bar>();

		if (codeValue <= MAX_VALUE) {
			int numberOfBars = log2(codeValue + 1);
			int summedBarsValue = (int) Math.pow(2, numberOfBars) - 1;
			if (codeValue == 0) {
				codeValue = 0;
				numberOfBars = 0;
				summedBarsValue = 0;
			}

			int valueAfterThickening = 0;
			for (int i = 0; i < numberOfBars; i++) {
				Bar bar = new BarThin(codeType);
				valueAfterThickening = summedBarsValue + (int) Math.pow(2, numberOfBars - 1 - i);

				if (valueAfterThickening <= codeValue) {
					bar = new BarThick(codeType);
					summedBarsValue = valueAfterThickening;
				}
				barsCombination.add(bar);
			}

		}
		return barsCombination;
	}

}
