package conexao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

import principal.Generico;

// Classe para armazenar dados no celular
public class Registros 
{
	public static final String REG_PACIENTES = "PACIENTES";
	public static final String REG_MEDICOS = "MEDICOS";
	private RecordStore rsGeral = null;
	private Vector vecReg = null;
	
	public void abrirArmazenador(String pNome) throws Exception, RecordStoreNotFoundException, RecordStoreException
	{
		rsGeral = RecordStore.openRecordStore(pNome, true);
	}
	
	public void fecharArmazenador() 
	{
		try 
		{
			rsGeral.closeRecordStore();
		} catch (RecordStoreNotOpenException e) 
		{
			e.printStackTrace();
		} catch (RecordStoreException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean adicionar(Generico pDados)
	{	
		boolean bOk = false;
		
		try
		{
			ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
			DataOutputStream DOS = new DataOutputStream(BAOS);
			
			DOS.writeInt(pDados.getCodigo());
			DOS.writeUTF(pDados.getNome());
			
			byte [] bRec = BAOS.toByteArray();
			rsGeral.addRecord(bRec, 0, bRec.length);
			
			DOS.close();
			BAOS.close();
			
			bOk = true;
		}
		catch (Exception ex)
		{
			bOk = false;
		}
		
		return bOk;
	}
	
	public Vector ListarTodos() throws Exception
	{
		RecordEnumeration rEnum = null;
		
		if (vecReg != null)
		{
			vecReg.removeAllElements();
		}
		else
		{
			vecReg = new Vector();
		}
		
		try
		{
			rEnum = rsGeral.enumerateRecords(null, null, false);
			
			while (rEnum.hasNextElement())
			{
				int iId = rEnum.nextRecordId();
				byte[] bDados = rsGeral.getRecord(iId);
				ByteArrayInputStream BAIS = new ByteArrayInputStream(bDados);
				DataInputStream DIS = new DataInputStream(BAIS);
				
				Generico objDados = new Generico();
				
				objDados.setCodigo(DIS.readInt());
				objDados.setNome(DIS.readUTF());
				
				System.out.println("carregarcelular - Codigo="+String.valueOf(objDados.getCodigo()));
				System.out.println("carregarcelular - Nome="+objDados.getNome());
				
				vecReg.addElement(objDados);
			}
			System.out.println("apos while");

			
		}
		catch (Exception ex)
		{
			throw new Exception(ex.getMessage());
		}
		finally
		{
			rEnum.destroy();
		}
		
		System.out.println("fim da função listartodos");
		
		return vecReg;
	}

}
