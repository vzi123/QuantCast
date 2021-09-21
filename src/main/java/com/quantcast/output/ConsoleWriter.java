package com.quantcast.output;

import java.util.List;

public class ConsoleWriter implements OutputWriter<List<String>>{

	@Override
	public void write(List<String> output) {
		if(output == null || output.size() == 0)
			throw new IllegalArgumentException();
		output.stream().forEach(System.out::println);
		
	}

}
