package br.com.strem.reader;

public class TestaStream {

	public static void main(String[] args) {
		StreamImpl stream = new StreamImpl("aAbBABacaf");
		System.out.println("Output: " + stream.findCharacter());
	}
}
