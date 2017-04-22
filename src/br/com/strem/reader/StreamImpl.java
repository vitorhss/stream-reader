package br.com.strem.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StreamImpl implements Stream {
	private final static String vowels = "aeiou";
	private final static String consonants = "bcdfghjklmnpqrstvxyz";
	private Integer count = 0;
	private String input;
	
	private List<Character> letras = new ArrayList<>();
	
	public StreamImpl(String input) {
		this.input = input;
		populaLista();
	}
	@Override
	public boolean hasNext() {
		if(input.length() > count){
			return true;
		}
		return false;
	}

	@Override
	public char getNext() {
		char caracter = input.charAt(count);
		count++;
		return caracter;
	}
	
	/*
	 * Carrega cada caractére do input em uma posição da lista  
	 */
	
	private void populaLista() {
		StringBuilder str = new StringBuilder(input);

		for (Integer i = 0; i < input.length(); i++) {
			letras.add(str.charAt(i));
		}
	}
	
	
	/*
	 * Método responsável por verificar se o caracter informado é uma vogal  
	 */
	
	private boolean isVowel(Character caracter) {
		return vowels.indexOf(caracter) >= 0;
	}
	
	/*
	 * Método que verifica se no input há uma voga que não se repete
	 */
	
	private boolean isUniqueVowel(Character chr) {
		int count = 0;
		int positionUniqueVowel = 0;
		
		if (isVowel(chr)) {
			for (int i = 0; i < input.length(); i++) {
				
				char charTemp = input.charAt(i);
				
				String stringtemp = String.valueOf(charTemp);
				
				if (stringtemp.equalsIgnoreCase(chr.toString())) 
					count++;
			}
				
			if(count == 1){
				
				positionUniqueVowel = input.indexOf(chr);
				
				if (checkedCondition(positionUniqueVowel)){
					return true;
				}
			}
		}
		return false;
	}
	
   /*
	 * Recebe a posição da vogal única e verifica se na posição anterior existe uma consoante
   */
	
	private boolean checkedCondition(int positionUniqueVowel) {
		
		if(isConsonant(input.charAt(--positionUniqueVowel))){
			
			if(isVowel(input.charAt(--positionUniqueVowel))){
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * verifica se um caractere é consoante 
	 */
	
	private boolean isConsonant(Character chr){
		return consonants.indexOf(chr) >=0;
	}

	public String findCharacter(){
		String output = "";
		
		Optional<Character> optional = letras.stream()
		  .filter(s -> isUniqueVowel(s))
		  .findFirst();
		
		if(optional.isPresent()){
			output = optional.get().toString();
		}
		else{
			throw new RuntimeException("Não foi encontrada nenhuma vogal unica, precedida de uma consoante");
		}
		return output;
	}	
	
}
