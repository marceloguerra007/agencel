package conexao;

import java.io.*;

import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

import principal.Consulta_Tab;
import principal.Generico;

import java.lang.*;
import java.util.Date;
import java.util.Vector;

public class Con_Servlet implements Runnable
{
	public final int EXE_ENVIO = 1;
	public final int EXE_RECEB = 2;
	public final int TIPOERRO_SEMDADOS = 0;
	public final int TIPOERRO_COMUM=1;
	
	private boolean bConexaoFinalizada = false; 
	private String sURL = "";
	private int sTipoExecucao = 0;
	private String sDadosRecebidos="";
	private Consulta_Tab objDadosConsulta=null;
	private String sMsgErro="";
	private int iTipoErro=-1;

	public Con_Servlet(String pURL)
	{
		sURL = pURL;
		sMsgErro="";
	}
	
	public String getMensagemErro()
	{
		return this.sMsgErro;
	}

	public int getTipoErro()
	{
		return this.iTipoErro;
	}
	
	public boolean existeErro()
	{
		return (!sMsgErro.equals(""));
	}
	
	private void threadconexao() throws Exception 
	{
		Thread thCon = new Thread(this);
		System.out.println("threadconexao...");
		thCon.start();
	}
	
	public boolean Conectado()
	{
		System.out.println("\nfuncao conectado...");
		
		return !(this.bConexaoFinalizada);
	}
	
	//  Esta rotina tem a função de retornar qualquer dado porém em formato
	//  apenas de codigo e nome.
	public void Conectar(int pTipoConexao) throws Exception
	{
		System.out.println("metodo conectar...");

		sDadosRecebidos = null;
		bConexaoFinalizada = false;
		sTipoExecucao = pTipoConexao;
		
		threadconexao();
	}

	public Vector getDadosGenericos() throws Exception
	{
		String sDados="";
		
		sDados = sDadosRecebidos;
		
		if (!sDados.equals(""))
		{
			return ajustaDadosGenericos(sDados);
		}
		else
			return null;
	}
	
	public void setDadosConsulta(Consulta_Tab pDadosConsulta) throws Exception
	{
		objDadosConsulta = pDadosConsulta;
	}
	
	private byte[] ajustaDadosEnvio() throws Exception
	{
		byte[] bDadosEnvio = new byte[5];
		
		if (objDadosConsulta != null)
		{
			bDadosEnvio = (objDadosConsulta.getCodigoAgenda()+";"+objDadosConsulta.getCodigoPaciente()).getBytes("8859_1");
		}
		
		return bDadosEnvio;
	}
	
	private Vector ajustaDadosGenericos(String pDados) throws Exception
	{
		Vector vecDados = new Vector();
		Generico objDadosGenericos = null;
		int i=0;
		String sValorCampo = "";
		
		// Instanciar o objeto generico para o primeiro registro
		objDadosGenericos = new Generico();
		
		// Percorrer toda a string de dados que contém todos os registros
		// juntos numa só e separar adequadamente num vetor.
		for (i=0;i<pDados.length();i++)
		{
			// Novo Campo
			if ( pDados.substring(i,i+1).equals(";") )
			{
				if (! sValorCampo.equals("") )
				{
					if (objDadosGenericos.getCodigo() == 0)
					{
						objDadosGenericos.setCodigo(Integer.parseInt(sValorCampo));
					}
					else if (objDadosGenericos.getNome().equals(""))
					{
						objDadosGenericos.setNome(sValorCampo);
					}
					
					sValorCampo = "";
				}
			}
			// Novo Registro
			else if ( pDados.substring(i,i+1).equals("|") )
			{
				vecDados.addElement(objDadosGenericos);
				
				objDadosGenericos = new Generico();
				sValorCampo = "";
			}
			// Fim dos Dados
			else if ( pDados.substring(i,i+2).equals("!!") )
			{
				vecDados.addElement(objDadosGenericos);
				
				break;
			}
			// Se não for nenhum simbolo de controle, então concatenar
			else
			{
				sValorCampo += pDados.substring(i,i+1);
			}
		}
		
		return vecDados;
	}
	
	private String executaRecebimento() throws IOException, Exception, ConnectionNotFoundException
	{
		HttpConnection conexao = null;
		InputStream IS = null;
		StringBuffer buff = new StringBuffer();
		String sDados = "";
		int CodRespostaConexao = -1;
		
		try
		{
			conexao = (HttpConnection)Connector.open(sURL);
			conexao.setRequestMethod(HttpConnection.GET);
			conexao.setRequestProperty("IF-Modified-Since", "22 Oct 2009 20:00:00 GMT");
			conexao.setRequestProperty("User-Agent", "Profile/MIDP-2.0 Configuration/CLDC-1.0");
			conexao.setRequestProperty("Content-Language", "en-CA");
			
			IS = conexao.openInputStream();

			CodRespostaConexao = conexao.getResponseCode();
			if (CodRespostaConexao != HttpConnection.HTTP_OK) 
			{
				throw new IOException("Erro de conexão HTTP. Codigo do erro: " + CodRespostaConexao);
			}			
			
			System.out.println("conexao HTTP. "+conexao.getResponseCode()+" - "+conexao.getResponseMessage());
			
			int dadosEntrada;
			
			while ((dadosEntrada = IS.read()) != -1)
			{
				buff.append((char) dadosEntrada);
			}
			
			sDados = buff.toString();
		}
/*
		catch (Exception ex)
		{
			System.out.println("Erro ao executar conexao HTTP. "+conexao.getResponseCode()+" - "+conexao.getResponseMessage());
			sDados = null;
		}
*/
		finally 
		{
			if (IS != null)
				IS.close();
			
			if (conexao != null)
				conexao.close();
		}

		System.out.println("executaRecebimento - antes do return sDados="+sDados);
		return sDados;
		
	}

	private void executaEnvio(byte pDados[]) throws IOException, Exception
	{
		HttpConnection conexao = null;
		OutputStream OS = null;
		InputStream IS = null;
		StringBuffer buffResposta = new StringBuffer();
		String sRespostaEnvio = "";
		
		try
		{
			conexao = (HttpConnection)Connector.open(sURL);
			conexao.setRequestMethod(HttpConnection.POST);
			conexao.setRequestProperty("IF-Modified-Since", "22 Oct 2009 20:00:00 GMT");
			conexao.setRequestProperty("User-Agent", "Profile/MIDP-2.0 Configuration/CLDC-1.0");
			conexao.setRequestProperty("Content-Language", "en-CA");
			
			// Enviar dados
			OS = conexao.openOutputStream();
			OS.write(pDados);
			OS.flush();
			
			//Receber resposta do servidor após envio de dados.
			IS = conexao.openInputStream();
			int dadosEntrada;
			
			while ((dadosEntrada = IS.read()) != -1)
			{
				buffResposta.append((char) dadosEntrada);
			}
			
			sRespostaEnvio = buffResposta.toString();
			
			System.out.println("sRespostaEnvio="+sRespostaEnvio);
			
			if ( !sRespostaEnvio.equals("OK!!") )
			{
				System.out.println("dentro do if sRespostaEnvio");
				
				throw new Exception(sRespostaEnvio);
			}
		} 
		finally 
		{
			if (OS != null)
				OS.close();
			
			if (IS != null)
				IS.close();
			
			if (conexao != null)
				conexao.close();
		}
	}
	
	public void run()   
	{
		System.out.println("metodo run()");
		
		if (sTipoExecucao == EXE_RECEB)
		{
			System.out.println("dentro if EXE_RECEB - metodo run()");

			iTipoErro = -1;
			try
			{
				sDadosRecebidos = executaRecebimento();

				// Tratamento quando não houver dados //
			    if ( sDadosRecebidos.indexOf("!vazio!!!") >= 0 )
			    {
			    	iTipoErro = TIPOERRO_SEMDADOS;
					System.out.println("dentro if - ERRO SEM DADOS");
			    	throw new Exception("");
			    }
				
				System.out.println("sDadosRecebidos ="+ sDadosRecebidos);
			}
			catch (ConnectionNotFoundException eCNF)
			{
				sMsgErro = "Servidor ou serviço indisponivel.";
				iTipoErro = TIPOERRO_COMUM;
			}
			catch (IOException eIO)
			{
				sMsgErro = eIO.getMessage();
				iTipoErro = TIPOERRO_COMUM;
			}
			catch (Exception e) 
			{
				sMsgErro = "Erro ao receber dados. Erro:"+e.getMessage();
				
				if ( iTipoErro == -1)
					iTipoErro = TIPOERRO_COMUM;
				
				System.out.println("EXCEPTION - AO RECEBER DADOS - TIPOERRO:"+iTipoErro);

			}
			finally {bConexaoFinalizada = true;}
		}
		else if (sTipoExecucao == EXE_ENVIO)
		{
			try
			{
				executaEnvio(ajustaDadosEnvio());
			}
			catch (Exception e) 
			{
				sMsgErro = "Erro ao enviar dados. Erro:"+e.getMessage();
				iTipoErro = TIPOERRO_COMUM;
			}
			finally {bConexaoFinalizada = true;}
		}
		
	}
}
