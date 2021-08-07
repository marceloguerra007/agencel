package principal;

public class Consulta_Tab 
{
	private int AGE_IN_CODIGO;
	private int PAC_IN_CODIGO;
	
	public Consulta_Tab()
	{
		this.AGE_IN_CODIGO = 0;
		this.PAC_IN_CODIGO = 0;
	}

	public Consulta_Tab(int pAgendaCod, int pPacienteCod)
	{
		this.AGE_IN_CODIGO = pAgendaCod;
		this.PAC_IN_CODIGO = pPacienteCod;
	}
	
	public void setAgendaCodigo(int pCodigo)
	{
		this.AGE_IN_CODIGO = pCodigo;
	}
	
	public void setPacienteCodigo(int pCodigo)
	{
		this.PAC_IN_CODIGO = pCodigo;
	}
	
	public int getCodigoAgenda()
	{
		return this.AGE_IN_CODIGO;
	}
	
	public int getCodigoPaciente()
	{
		return this.PAC_IN_CODIGO;
	}
}
