package it.polito.tdp.borders.model;

public class Country implements Comparable<Country>{

	private int cod;
	private String sigla;
	private String nome;
	
	public Country(int cod, String sigla, String nome) {
		super();
		this.cod = cod;
		this.sigla = sigla;
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int compareTo(Country c) {
		// TODO Auto-generated method stub
		return this.nome.compareTo(c.nome);
	}

	@Override
	public String toString() {
		return nome;
	}
	
	
	
}
