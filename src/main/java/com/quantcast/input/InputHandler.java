package com.quantcast.input;

import com.beust.jcommander.JCommander;


public class InputHandler {
	
	public InputArgs getInputArgs(String[] runTimeArgs) {
		InputArgs args = new InputArgs();
		JCommander commander = JCommander.newBuilder().addObject(args).build();
		commander.parse(runTimeArgs);
		return args;
	}
	
}
