package br.com.strem.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class StreamTest {

	@Test
	public void deveRetornarVogalUnica() {
		String input = "aAbBABacafe";

		StreamImpl stream = new StreamImpl(input);
		assertEquals("e", stream.findCharacter());

	}

	@Test
	public void naoDeveRetornarVogal() {
		String input = "aAbBABacaf";

		StreamImpl stream = new StreamImpl(input);
		try{
			stream.findCharacter();
			fail();
		} catch(RuntimeException ex){
			assertEquals("Não foi encontrada nenhuma vogal unica, precedida de uma consoante", ex.getMessage());
		}
		
	}

}
