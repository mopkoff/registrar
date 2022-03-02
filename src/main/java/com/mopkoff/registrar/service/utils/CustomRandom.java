package com.mopkoff.registrar.service.utils;

import java.util.Random;

public class CustomRandom extends Random {

    public CustomRandom() {
        super();
    }

    public int nextNonNegative() {
        return next(Integer.SIZE - 1);
    }

    public int nextNonNegative(int lowerBound, int upperBound) {
        return next(Integer.SIZE - 1) % (upperBound - lowerBound) + lowerBound;
    }
}
