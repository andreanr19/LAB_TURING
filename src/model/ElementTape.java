package model;

public class ElementTape {

	private String simbolo;

	private ElementTape nextElement;

	private ElementTape previousElement;

	public ElementTape getPreviousElement() {
		return previousElement;
	}

	public void setPreviousElement(ElementTape previousElement) {
		this.previousElement = previousElement;
	}

	public ElementTape(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public ElementTape getNextElement() {
		return nextElement;
	}

	public void setNextElement(ElementTape nextElement) {
		this.nextElement = nextElement;
	}

	public void insertAfter(ElementTape et) {

		et.nextElement = nextElement;
		if (nextElement != null)
			nextElement.previousElement = et;

		et.previousElement=this;
		nextElement=et;
	}
	
	public void insertBefore(ElementTape et) {
		
		if(previousElement!=null) 
			
			previousElement.nextElement=et;
		et.previousElement=previousElement;
		et.nextElement=this;
		previousElement=et;
	}

	
	
	
	
	
	
	
	
}