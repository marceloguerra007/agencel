package principal;

public class Generico 
{
	private int Codigo=0;
	private String Nome="";

	public Generico()
	{
		this.Codigo = 0;
		this.Nome = "";
	}

	public Generico(int pCodigo, String pNome)
	{
		this.Codigo = pCodigo;
		this.Nome = pNome;
	}
	
	public void setCodigo(int pCodigo)
	{
		this.Codigo = pCodigo;
	}
	
	public void setNome(String pNome)
	{
		this.Nome = pNome;
	}
	
	public int getCodigo()
	{
		return this.Codigo;
	}
	
	public String getNome()
	{
		return this.Nome;
	}
	
}
