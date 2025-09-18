package com.automendes.backend.mapper.imp;

import java.util.UUID;

import com.fasterxml.uuid.Generators;

public class UuidV7GeneratorImp {
	
	public static UUID generateUuidV7() {
        return Generators.timeBasedEpochRandomGenerator().generate();
    }
	
}