package com.surpass.utils;

import java.util.UUID;

public final class UUIDGenerator {

	// non-instantiable
	private UUIDGenerator() {
	}

	/**
	 * Generate a new UUID.
	 */
	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}
}
